package app

import library.{ExempleHtml, Expression, Html, Tag, Text, UrlProcessor}

trait AnalyseDePage extends library.AnalysePage{
  def resultats(url:String,exp:Expression):List[(String,String)] = {
    val page : Html = ExempleHtml.exemple
    val urls : List[String] = FiltrageUrl.filtreAnnonce(page)
    val toReturn : List[(String, String)] = Nil

    for (i <- urls){
      val page : Html = UrlProcessor.fetch(i)
      if (FiltrageHtml.filtreHtml(page, exp))
        (i, titre(page))::toReturn
    }
    toReturn
  }

  def titre(p : Html): String = p match {
    case Tag(x, _, z) => if (x == "title") z match {
      case Text(x)::_ => x
    } else {
      case Tag(x,y,z)::_ => titre(Tag(x,y,z))
    }
    case Text(_) => ""
  }

}