WaffeGame2:n rakennekuvaus:

Main luo GraphicalUI:n (joka luo itselleen GameWindow:in) ja pelin (Game). Peli käynnistetään.

Peli luo pelilogiikan (GameLogicWaffeGame2), joka luo itselleen pelisäännöt (GameRulesWaffeGame2). Pelilogiikalle annetaan UI käytettäviksi.

Peli luo UIn avulla pelaajat (Player) ja antaa ne pelilogiikalle.

Pelilogiikka alustetaan. Luodaan pakka (Pack) ja pelikenttä (Pile). Pelikentälle annetaan pelikenttäsääntö (PileRuleWaffeGame2). Pelikenttäsäännöstä tarkistetaan, onko pelikentässä kelvollinen kokoelma kortteja. Pelaajille luodaan kädet (Hand), joista yksi on julkinen ja toinen yksityinen.

Ohjelmalooppi.

UI:n ja pelilogiikan arvot nollataan/asetetaan uudelleen. Luodaan uudet kortit (Card) pakkaan, josta jaetaan kortteja pelaajille.

Pelilooppi.

Tarkistetaan jos jokin pelaaja voitti. Jos ei, pyydetään pelaajan CardSelectoria (tässä tapauksessa GUI) valitsemaan pelattavat kortit. GUI luo jokaiselle pelattavalle kortille oman CardButton:in, joiden avulla valitaan kortteja. Luodaan Play-olio jonka avulla siirretään kortit käsistä pelikenttään. Jos pelaaja ei pysty lyödä kortteja, hän ottaa kortteja pakasta.

Pelaajan voittaessa "peli" loppuu ja palataan ohjelmalooppiin.