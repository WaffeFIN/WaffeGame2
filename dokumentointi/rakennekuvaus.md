WaffeGame2:n rakennekuvaus:

Main luo GraphicalUI:n (joka luo itselleen GameWindow:in) ja pelin (Game). Peli k�ynnistet��n.

Peli luo pelilogiikan (GameLogicWaffeGame2), joka luo itselleen pelis��nn�t (GameRulesWaffeGame2). Pelilogiikalle annetaan UI k�ytett�viksi.

Peli luo UIn avulla pelaajat (Player) ja antaa ne pelilogiikalle.

Pelilogiikka alustetaan. Luodaan pakka (Pack) ja pelikentt� (Pile). Pelikent�lle annetaan pelikentt�s��nt� (PileRuleWaffeGame2). Pelikentt�s��nn�st� tarkistetaan, onko pelikent�ss� kelvollinen kokoelma kortteja. Pelaajille luodaan k�det (Hand), joista yksi on julkinen ja toinen yksityinen.

Ohjelmalooppi.

UI:n ja pelilogiikan arvot nollataan/asetetaan uudelleen. Luodaan uudet kortit (Card) pakkaan, josta jaetaan kortteja pelaajille.

Pelilooppi.

Tarkistetaan jos jokin pelaaja voitti. Jos ei, pyydet��n pelaajan CardSelectoria (t�ss� tapauksessa GUI) valitsemaan pelattavat kortit. GUI luo jokaiselle pelattavalle kortille oman CardButton:in, joiden avulla valitaan kortteja. Luodaan Play-olio jonka avulla siirret��n kortit k�sist� pelikentt��n. Jos pelaaja ei pysty ly�d� kortteja, h�n ottaa kortteja pakasta.

Pelaajan voittaessa "peli" loppuu ja palataan ohjelmalooppiin.