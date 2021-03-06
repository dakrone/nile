# Nile

Stream utilities for everyday Clojure use, published because I find
myself using them over and over again in different projects.

Only works with Clojure 1.5.x, sorry.

## Usage

In your project.clj:

```clojure
[nile "1.0"]
```

Then, to use:

```clojure
(ns myns.foo
  (:require [nile.streams :refer [split-input-stream counted-stream]]))
```

Splitting streams into multiple streams without reading all of the
data up front:

```clojure
(def orig (java.io.ByteArrayInputStream. (.getBytes "test data")))

;; Split the stream into three streams
(let [[f streams] (split-input-stream orig 3)
      readers (doall (for [stream streams]
                       (future (slurp stream))))]
  (= ["test data" "test data" "test data"]
     (map deref readers)))
;; => true
```

Define a handler for getting the counts of an InputStream or OutputStream:

```clojure
(let [in (counted-stream (java.io.ByteArrayInputStream.
                          (.getBytes "test-data"))
                         (fn [cnt] (println cnt "bytes were read")))]
  (slurp in))
;; => "9 bytes were read"
```

### split-input-stream

```clojure
([stream n] [stream n buffer-length])
```

Splits the given stream into n PipedInputStreams, spawns a future that
reads from the original stream and writes to the streams. Once the
InputStream has been completely read and written, it is closed. Uses a
buffer size of 8192 if no size is specified.

NOTE: The InputStreams must be read from IN PARALLEL (different
threads), or else reading will block while waiting for the other
streams to be read.  Streams can be read as fast as the slowest
reader.

Returns a tuple of the future and sequence of input-streams, so
derefing the future can be blocked on if desired. The deref'd future
will be `true` if successful, or an Exception object if unsuccessful.

### counted-stream

```clojure
([stream handler])
```

Decorate a stream to turn it into a counted stream, which will keep
track of the bytes that have passed through the stream. Once the
stream has been closed, the handler will be called with the count of
bytes that have been passed through the stream.

Note: works on either an InputStream or an OutputStream

## License

Copyright © 2013 Lee Hinman
