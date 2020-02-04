package app

import library.{ExempleHtml, Word, And, Or, Expression, Html, Tag, Text, UrlProcessor, ExpressionParser}
import java.io.FileWriter

object Application {
  def reqString(e:Expression):List[String] = e match {
    case Word(x) => x::Nil
    case And(x, y) => (reqString(x) + "+" +  reqString(y))::Nil
    case Or(x, y) => reqString(x):::reqString(y)
  }
  
  def generatePage(l:List[String], e:Expression):String = {
     var listTuple:List[(String,String)] = Nil
       
     for (i<-l)
        listTuple = listTuple:::AnalysePage.resultats("https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords="+i,e)
     
     Html2String.process(ProductionResultatIMP.resultat2html(listTuple))
  }
  
  def main(args:Array[String]) { //tests
    
    val exp : Expression = ExpressionParser.readExp
    
    val file= new FileWriter("results.html")
    
    try{
      file.write(generatePage(reqString(exp), exp))
    } finally file.close()
        
    println(reqString(exp))
  }
}