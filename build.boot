(set-env!
  :project 'hoplon-multipage-example
  :version "0.1.0-SNAPSHOT"
  :dependencies
  '[[tailrecursion/boot-hoplon "0.1.0-SNAPSHOT"]
    [tailrecursion/hoplon "6.0.0-alpha4"]
    [pandeiro/boot-http "0.6.3-SNAPSHOT"]
    [adzerk/boot-cljs "0.0-3308-0" :scope "test"]]
  :source-paths #{"src"}
  :asset-paths #{"assets"})

(require
  '[tailrecursion.boot-hoplon :refer [hoplon]]
  '[pandeiro.boot-http :refer [serve]]
  '[adzerk.boot-cljs :refer [cljs]])

(deftask dev "" []
  (comp (serve) (watch) (hoplon)
    (cljs :optimizations :advanced
          :source-map true)
    (speak :theme "woodblock")))
