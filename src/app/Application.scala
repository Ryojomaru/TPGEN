package app

import library._
import java.io.FileWriter
import java.net.URI

object Application {
  def keyWords(e:Expression):List[String] = e match {
    case Word(x) => x::Nil
    case And(x, y) => keyWords(x):::keyWords(y)
    case Or(x, y) => keyWords(x):::keyWords(y)
  }
  
  def request(e:Expression, l:List[String]):List[String] = {
    
    e match {
      case And(Word(x), y) => {
        var newList:List[String] = Nil
        
        for (i<-l)
          newList = (i + "+" + x)::newList
        
        newList
      }
      
      case And(x,y) => request(x,l):::request(y,l)
      case Or(x, y) => request(x,l):::request(y,l)
      case _ => Nil
    }
   
  }

  def generatePage(keyWords:List[String], e:Expression):String = {
     var listTuple:List[(String,String)] = Nil
     
     println(keyWords)
     
     for(i <- keyWords)
       listTuple = listTuple:::AnalysePage.resultats("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords="+i,e)
       
     Html2String.process(ProductionResultat.resultat2html(listTuple))
  }
  
  def main(args:Array[String]) {
    
    val exp : Expression = ExpressionParser.readExp
    val file= new FileWriter("resultats.html")
    val keyWord:List[String] = keyWords(exp)
    
    try{
      file.write(generatePage(keyWord:::request(exp, keyWord), exp))
    } finally file.close()
    
    java.awt.Desktop.getDesktop().browse(new URI("resultats.html"))
    
    print("finished !")
  }
}