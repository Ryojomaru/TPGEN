package app

import library._

object AnalysePage extends library.AnalysePage {

  def resultats(url:String,exp:Expression):List[(String,String)] = {
    
    val page : Html = UrlProcessor.fetch(url)
    val urls : List[String] = FiltrageURLs.filtreAnnonce(page)
    var toReturn : List[(String, String)] = Nil

    for (i <- urls){
      if(i.contains("vivastreet")) {
        val page : Html = UrlProcessor.fetch(i)
        if (FiltrageHtmlPage.filtreHtml(page, exp))
          toReturn = toReturn:::List((i, titre(page)))
      }
    }
    
    toReturn
  }

  def titre(p : Html): String = p match {
    
    case Tag("title", y, z) => z match {
      case Nil => ""
      case Text(x)::l => x
      case _::l => titre(Tag("title", y, l))
    }
    case Tag(x,y,z::l) => titre(z) + titre(Tag(x,y,l))
    case _ => ""
  }
}