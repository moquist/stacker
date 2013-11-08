stacker
=======

I'm learning to use clojure+clojurescript together. The history of this repo shows where I started, and the steps I've taken along the way.

## Usage

1. git clone https://github.com/moquist/stacker
2. cd stacker
3. lein deps
4. lein run
5. Switch to a separate terminal (henceforth "term2").
6. cd stacker
7. lein trampoline cljsbuild repl-listen
 * Highly recommended -- use rlwrap for readline features in your bREPL: rlwrap lein trampoline cljsbuild repl-listen
8. Switch to your browser.
9. http://localhost:4001/app
10. Open up the javascript console.
11. Switch back to your term2 (from above).
12. (.log js/console "hi there")

Your console log entry should appear in ... your console log. You're bREPLing!
(For more on using the bREPL, see https://github.com/magomimmo/modern-cljs/blob/master/doc/tutorial-02.md)

## License

Copyright © 2013 Matt Oquist <moquist@majen.net>

Distributed under the Eclipse Public License, the same as Clojure.
