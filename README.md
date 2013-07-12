# nile

Stream utilities for everyday Clojure use, published because I find
myself using them over and over again in different projects.

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
derefing the future can be blocked on if desired.

### counted-stream

```clojure
([stream handler])
```

Decorate a stream to turn it into a counted stream, which will keep
track of the bytes that have passed through the stream. Once the
stream has been closed, the handler will be called with the count of
bytes that have been passed through the stream.

## License

Copyright © 2013 Lee Hinman
