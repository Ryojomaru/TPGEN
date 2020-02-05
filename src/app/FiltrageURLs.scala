package app

import library._

object FiltrageURLs extends library.FiltrageURLs {
  
    def filtreAnnonce (p:Html) : List[String] = p match {
      case Tag(x,y,z::l) => {
        
        var childExplore:List[String] = Nil
        
        for (i <- l) childExplore = childExplore:::filtreAnnonce(i)
        
        (if(x=="a") url(y)::filtreAnnonce(z) else filtreAnnonce(z)):::childExplore
      }
      case Tag(x,y,Nil) => if(x=="a") url(y)::Nil else Nil
      case _ => Nil
    }
  
  def url (l:List[(String, String)]) : String = l match {
    case (a,b)::xs => if(a == "href" && b.contains("vivastreet")) b else url(xs)
    case _ => ""
  }
}