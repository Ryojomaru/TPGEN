package app

import library._

object FiltrageHtmlPage extends library.FiltrageHtml {
    
    def filtreHtml(h:Html,e:Expression):Boolean = filtreString(webPageToString(h),e)
    
    def filtreString(s:String,e:Expression): Boolean = e match {
      case Word(x) => s.contains(x)
      case And(x,y) => filtreString(s,x) && filtreString(s,y)
      case Or(x,y) => filtreString(s,x) || filtreString(s,y)
    }
    
    def webPageToString(h:Html):String = h match {
      case Tag("script",_,_) => ""
      case Tag(x,y,z::l) => webPageToString(z) + webPageToString(Tag(x,y,l))
      case Tag(x,y,Nil) => ""
      case Text(x) => x
    }
}

