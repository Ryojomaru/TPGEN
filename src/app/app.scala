package app

object main extends App {
   println(ProductionResultatIMP.resultat2html(("Bon","jour")::("au","revoir")::Nil))
   println("")
   println(ProductionResultatIMP.resultat2html(Nil))

}