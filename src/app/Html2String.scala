package app

import library._

object Html2String extends Html2String {
  
    def process(h:Html):String = {
        
        h match {
          case Text(content) => content
          case Tag(name,attributes,children) => {
            
               var res : String = ""
                
               for(i <- children)
                     res = res + process(i)
           
               "<" + name +  attributs (attributes) + ">"  + "\n"+ res + "</" + name + ">" +"\n"            
          }
        }
    }
    
    def attributs  (l:List[(String,String)]):String={
      var resu : String = ""
      
      for (a <- l)
        resu += " " + a._1 + "=" + "\"" + a._2 + "\""
        
      resu
    }
}