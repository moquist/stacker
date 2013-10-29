stacker
=======

I'm learning to use datomic+clojure+clojurescript together. In a stack. The history of this repo shows where I started, and the steps I've taken along the way.

## Usage

1. git clone https://github.com/moquist/stacker
2. cd stacker
3. lein deps
4. lein run

And then, in a separate terminal (henceforth "term2"):
6. cd stacker
7. lein trampoline cljsbuild repl-listen

And then, in your browser:
8. http://localhost:4001/app
9. Open up the javascript console.

And lastly, in term2:
10. (.log js/console "hi there")

Your console log entry should appear in ... your console log. You're brepling!

## License

Copyright © 2013 Matt Oquist <moquist@majen.net>

Distributed under the Eclipse Public License, the same as Clojure.
