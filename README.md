# Multipage site with Hoplon

Run with

```
boot dev
open http://localhost:3000/
```

We pinned the project to Clojure 1.7.0 in the `boot.properties` files
as recommended by https://github.com/adzerk-oss/boot-cljs-repl/issues/17
and used the latest hoplon and cljs library versions we found at the time.


## Current issue

If page names are the same but under different subdirectories,
then they mask each other out.

We have these files in the example:
```
src/
  index.cljs.hl -- (page "index.html")
  page.cljs.hl -- (page "page.html")

src/other/
  index.cljs.hl -- (page "other/index.html")
  page.cljs.hl -- (page "other/page.html")

```

### (cljs :optimizations :none)

If we compile it with `(cljs :optimizations :none)` we are facing the following issues:

#### [/index.html](http://localhost:3000/index.html)

No errors but it shows the /other/index.html page

#### [/other/index.html](http://localhost:3000/other/index.html)

There is no content and we get these errors:

```
GET http://localhost:3000/other/other/out/goog/base.js
  (anonymous function) @ index.html.js:1           index.html.js:2

GET http://localhost:3000/other/other/out/cljs_deps.js 404 (Not Found)
  (anonymous function) @ index.html.js:2           index.html:2

ClojureScript could not load :main, did you forget to specify :asset-path?
```

#### [/page.html](http://localhost:3000/page.html)

We see the content of the page but we get these errors:

```
goog.require could not find: tailrecursion.hoplon.app_pages._other_SLASH_page_DOT_html
  goog.logToConsole_ @ base.js:643
  goog.require @ base.js:684
  (anonymous function) @ page.html.cljs:1

Uncaught Error: goog.require could not find: tailrecursion.hoplon.app_pages._other_SLASH_page_DOT_html    base.js:686
  goog.require @ base.js:686
  (anonymous function) @ page.html.cljs:1
```

#### [/other/page.html](http://localhost:3000/other/page.html)

There is no content and but we get these errors:

```
GET http://localhost:3000/other/out/tailrecursion/hoplon/app_pages/_page_DOT_html.js    base.js:1139
  goog.writeScriptSrcNode_ @ base.js:1139
  goog.writeScriptTag_ @ base.js:1211
  goog.importScript_ @ base.js:901
  goog.writeScripts_ @ base.js:1326
  goog.require @ base.js:678
  (anonymous function) @ page.html:2

goog.require could not find: tailrecursion.hoplon.app_pages._other_SLASH_page_DOT_html     base.js:643
  goog.logToConsole_ @ base.js:643
  goog.require @ base.js:684
  (anonymous function) @ page.html.cljs:1

Uncaught Error: goog.require could not find: tailrecursion.hoplon.app_pages._other_SLASH_page_DOT_html    base.js:686
  goog.require @ base.js:686
  (anonymous function) @ page.html.cljs:1
```

### (cljs :optimizations :simple) or (cljs :optimizations :advanced)

#### [/index.html](http://localhost:3000/index.html)

No errors but it shows the /other/index.html page


#### [/other/index.html](http://localhost:3000/other/index.html)

Correct.


#### [/page.html](http://localhost:3000/page.html)

Correct.


#### [/other/page.html](http://localhost:3000/other/page.html)

No errors but it shows the /page.html page
