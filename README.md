# Specifikacija i modelovanje softvera

Instrukcije za skidanje MySql baze:

https://www.youtube.com/watch?v=WuBcTJnIuzo

Preuzmite ako hocete Intellij IDE(Community edition) sa linka:

https://www.jetbrains.com/idea/download/

Koraci za podesavanje u Intellij-u:

  VCS -> Enable Verion Control Integration.. -> Izaberete Git(OK) -> ponudice da vam skine git ako ga nemate 

  Nakon toga:

  VCS -> Get From Version Control.. -> Uzmite url sa repozitorijuma sa gitHuba, ulogujte se

  Drajver za povezivanje sa bazom smo vam ostavili u okviru repozitorijuma.
  Povezujete se tako sto odete na:
  File -> Project Structure -> Modules -> Depencies -> '+' sa desne strane i izaberete jars -> izaberete driver koji smo vam okacili -> stiklirate kvadratic sa leve   strane za export -> Apply -> Ok

------------------------------------------------
Modul util/FConnection vam sluzi za konekciju sa bazom i da bi se povezali morate staviti svoju sifru od baze. 
Kada spojite sve, imate fajl scipt.txt koji ce vam kreirati bazu i tabele.
