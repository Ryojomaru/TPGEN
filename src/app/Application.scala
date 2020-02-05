package app

import library._
import java.io.FileWriter

object Application {
  def reqString(e:Expression):String = e match {
    case Word(x) => x
    case And(x, y) => reqString(x) + "+" + reqString(y)
    case Or(x, y) => reqString(x) + "+" + reqString(y)
  }
  
  def generatePage(keyWords:String, e:Expression):String = {
     val listTuple:List[(String,String)] = AnalysePage.resultats("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords="+keyWords,e)
     
     Html2String.process(ProductionResultat.resultat2html(listTuple))
  }
  
  def main(args:Array[String]) {
    
    val exp : Expression = ExpressionParser.readExp
    
    val file= new FileWriter("resultats.html")
    
    try{
      file.write(generatePage(reqString(exp), exp))
    } finally file.close()
    
    print("finished !")
  }
}