package app

import library._

object ProductionResultat extends ProductionResultat {
    def resultat2html(l: List[(String, String)]): Html = {
      
      var listResults:List[Html] = List(Text("<h1>Voici les resultats :</h1>"))
      
      if (l == Nil)
        listResults = List(Text("<p>Aie! Aucun resultat trouvé :(</p>"))
        
      else {
        for (i <- l) { 
          val x = i._1
          val y = i._2
          
          listResults = listResults:::List(Tag("p", List(), List(Tag("a", List(("href", x)),List(Text(y))))))
        }
      }
      
      var corpspage: Tag = Tag("body", List(), listResults)
      
      val prod: Html = Tag("html", List(),
        List(
          Tag("head", List(),
            List(
              Tag("meta", List(("content", "text/html"), ("charset", "UTF-8")), List()),
              Tag("title", List(), List(Text("Page de rendu"))))),
            //ajout le plus important du tp
            Tag("style", List(), List(Text("body{background-image:url(\"https://images.techhive.com/images/article/2014/04/windows-xp-bliss-start-screen-100259803-orig.jpg\"); background-size:cover;}"))),
            Tag("script", List(), List(Text("let parag=document.getElementsByTagName('P'),j=100,reverse=!0;setInterval(function(){for(i=0;i<parag.length;i++){parag[i].style.fontSize=j+'%';parag[i].style.backgroundColor='#'+(j-100)+'A'+String.fromCharCode(j-100+65)+'FE'+(j-100)}console.log(String.fromCharCode(j-100+65));!reverse&&j>100?j--:reverse&&j<105?j++:reverse=!reverse},150);"))),
            //-----------------------------
            corpspage))

      prod
  }
}