package app
import library._

object Html2String {
  /** Produit la chaıne de caracteres correspondant a un document Html
   *  @param h le document Html
   *  @return la chaıne de caracteres representant h
*/
def process(h:Html):String={
 /* var res = "" 
    h match{
      case Text(content)=> content 
      case Tag(name ,attributes,children) => 
       res ='<' + name  + " " +  attributs (attributes) 
           for(i <- children){
                 res = res   + process(i)} 
                    res + '>' +  "</" + name +'>' +'>' +"\n"               
} 
    }*/
var res = ""
    h match{
      case Text(content)=>""+ content
      case Tag(name ,attributes,children) =>
           for(i <- children){
                 res = res   + process(i)}
                    "<" + name +  attributs (attributes) + ">"  + "\n"+ res + "</" + name +'>' +"\n"            
}
    }
def attributs  (l:List[(String,String)]):String={
  /*l match {
    case Nil=> ""
    case (a,b)::Nil=> a + '=' + b
    case (a,b)::x => " " +  a + '=' +"\"" +  b + "\"" +  ';' + attributs(x)} }*/
var resu = " "
for (a <- l){
  resu += " " +a._1 + "=" + "\"" + a._2 + "\""
}
resu
}
}

object Test extends App {
//  val exemple= Tag("cici",List (("ci","rt")),List(Text("MyPage")))
 // println(Html2String.process(exemple))
  val exemple1= Tag("html",List(),
                   List(Tag("head",List(),
                            List(Tag("meta",List(("content","text/html"),("charset","iso-8859-1")),List()),
                            		Tag("title",List(),List(Text("MyPage"))))),
                        Tag("body",List(),List(
                            Text("&nbsp"),
                            Tag("center",List(),List(
                            		Tag("a", List(("href","http://www.irisa.fr")),	
                            				List(Text("Lien"),Tag("img",List(),List())))))))))
                            				println(Html2String.process(exemple1))
}
