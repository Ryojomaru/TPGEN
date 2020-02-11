package app

import library._

object FiltrageURLs extends library.FiltrageURLs {
  
  def filtreAnnonce (p:Html) : List[String] = p match {
    case Tag(x,y,l) => {
      
      var childExplore:List[String] = Nil
      
      for (i <- l) 
        childExplore = childExplore:::filtreAnnonce(i)
      
      if(x == "a") {
        val u = url(y)
        
        if (u != "" && isAnnonce(y)) u::childExplore
        else childExplore
        
      } else childExplore
    }
    
    case _ => Nil
  }
  
  def url (l:List[(String, String)]) : String = l match {
    case (a,b)::xs => if(a == "href" && b.contains("vivastreet")) b else url(xs)
    case _ => ""
  }
  
  def isAnnonce(l:List[(String, String)]) : Boolean = l match {
    case (a,b)::xs => if(a == "class" && b == "clad__wrapper") true else isAnnonce(xs)
    case Nil => false
  }
}