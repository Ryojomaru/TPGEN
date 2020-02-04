package app
import library._

object ProductionResultatIMP extends ProductionResultat {
  /**
   * A partir d’une liste de couples (titre,URL), produit un document Html, qui
   * liste les solutions sous la forme de liens cliquables
   * @param l la liste des couples solution (titre,URL)
   * @return le document Html listant les solutions
   */

  def resultat2html(l: List[(String, String)]): Html = {
    var corpspage: Tag = Tag("body", List(), List(Text("Voici les resultats:")))
    if (l == Nil) {
      corpspage = Tag("p", List(), List(Text("Aie! Aucun resultat trouvé :(")))
    }
    for (i <- l) { 
      var x = i._1
      var y = i._2
      corpspage = Tag("p", List(), List(
        Tag("center", List(), List(
          Tag("a", List(("href", x)),
            List(Text(y), Tag("img", List(), List())))))))

    }
    val prod: Html = Tag("html", List(),
      List(
        Tag("head", List(),
          List(
            Tag("meta", List(("content", "text/html"), ("charset", "UTF-8")), List()),
            Tag("title", List(), List(Text("Page de rendu"))))),
        corpspage))

    //					println("voici la prod:   "+prod)
    //					println("et la le corpspage   "+corpspage)
    //
    prod

  }
  resultat2html(("Bon", "jour") :: ("au", "revoir") :: Nil)

  //    l match {
  //
  //      case Nil =>corpspage; Tag("body", List(), List(Text("fin de la recherche")))
  //
  //      case (x, y) :: m =>corpspage = Tag("body", List(), List(Text(x), Tag("center", List(), List(Tag("a", List(("href", y)),
  //          List(Text("Lien"), Tag("img", List(), List()))))))); resultat2html(m)
  //
  //    }
}

