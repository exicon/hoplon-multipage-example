(set-env!
  :project 'hoplon-multipage-example
  :version "0.1.0-SNAPSHOT"
  :dependencies
  '[[tailrecursion/boot-hoplon "0.1.0"]
    [tailrecursion/hoplon "6.0.0-alpha2"]
    [pandeiro/boot-http "0.6.3-SNAPSHOT"]
    [adzerk/boot-cljs "1.7.48-SNAPSHOT"]]
  :source-paths #{"src"}
  :asset-paths #{"assets"})

(require
  '[tailrecursion.boot-hoplon :refer [hoplon prerender]]
  '[pandeiro.boot-http :refer [serve]]
  '[adzerk.boot-cljs :refer [cljs]])

(deftask dev "" []
  (comp (serve) (watch) (hoplon)
    (cljs :optimizations :none
          :source-map true)
    (prerender)
    (speak :theme "woodblock")))
