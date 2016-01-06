
	NOTE TO SELF:
	* Pidä kirjaa harjoitustyöhön käyttämästäsi työmäärästä
	* Käyttämäsi työmäärä ei vaikuta arvosanaan, joten ole rehellinen
	* Merkitse aina vähintään päivämäärä, käyttämäsi aika ja ajankäytön kohde

17.12

	18
		Tein Git ja GitHub jutut
	19
		Tein aihemäärittelyn
	20
	
18.12

	19:30
		Tein perus luokat: Main, Card, Hand, Pack
	20
		lisäsin enumit Value ja Suit. Tein abstraktit luokat CardOwner ja Player. Lisäsin luokan HumanPlayer
	21

	
	23
		Loin Pile luokan, joka on "pelikentän" korttipino. Lisäki muutin CardOwnerin metodeja, muun muuassa addCard:ia.

19.12

	00
		Tein Pile luokalle testit, joiden läpäistessä addCard metodi palauttaa true.
	01
		Loin pari JUnit testiä Card:eille, Packi:lle ja Hand:ille sekä myös testitiedoston Pile:ille
	02
		Pile:in addCard-testejä korjailen... aika kauheen näköistä koodia syntyy. Pitänee kai siirtää (addCard-)testit toiselle luokalle, niin koodi pysyy siistimpänä.
	03
		Pari ongelmaa sain korjattua, mutta on korjattava testiä, joka tarkistaa onko pino suora vai ei. Ongelmana on se, että suora saa olla syklinen (esim. 2 - A - K) ja nämä tilanteet sekä jokereiden lisäys aiheuttaa ongelmia.
	03:30

	
	14
		Debuggaus jatkuu. Kirjotain pinon suoratesti-metodin uudelleen.
	14:30

	16
		Sain väännettyä suoratesti-metodin kuntoon. Loin vielä viimeiset testit pinolle.
	17
	
21.12

	21
		Tuntikirjanpito unohtui, mutta muistaakseni tein metodeista (mm. AbstractCardOwnerissa) siistimpiä yms.
	22
		Viimeistelin testit, pit-testi generoitu. Luokkakaavion viimeistely
	23
		-"-. Git commit ja push
	23:30
		
22.12

	15
		Siistin pakkauksia, ja mietin käyttöliittymää.
	16
	
26.12
	
	19
		Otin "Abstract" - sanan pois abstrakteista luokista. Teen "ruman" version peli logiikasta, jonka jälkeen refactoroin sen.
	20
		Tein GameRule rajapinnan ja GameRuleWaffeGame2 luokan. Laadin pelilogiikkaa.
	21	
		Jatkan pelilogiikan tekemistä. Tuumin miten käyttöliittymä tulee logiikan kanssa toimeen... Muutin pelaajia niin, että on vain yksi Player-luokka, jolla on CardSelector-rajapinnan toteuttava objekti, minkä avulla valitaan kortteja vuorolla. Tietokonepelaajalla on tähän tekoäly, ja ihmispelaajalla on käyttöliittymä
	22
		Jatkan pelilogiikan tekemistä. Eka testi rundi, ohjelma käynnistyi, mutta löytyi logiikan kannalta puutteita.
	23
		Teen uuden luokan Play. Tämä johtuu siitä, ettei Player ole CardOwner, joten kun yritetään lyödä kortteja pöytään on vaikea seurata niitä jos ne on otettu monesta Handistä.

27.12

	00
		Korjasin käyttöliittymää. Debuggaan yhtä ihmeellistä bugia liittyen katoavia kortteja.
	01
		Bugi korjattu, kesti noin ½ tuntia. Peli toimii!! Muutin kaiken ohjelmalogiikan pois käyttöliittymästä. GameRule nimetty GameLogic:iksi
	02
		Koodin siistintää ja testausta. On mietittävä teko-älyn implementaatiota (etukäteen)
	03
	
28.12
	
	15
		Pientä yleistä viilausta/refaktorointia. Teen testit valmiiksi
	16
		Tein Play-luokan testit.
	17
	
	
	19
		Lisää testejä
	20
		PIT-raportti ja luokkakaavio tehty
	21
		Koodikatselmointi
	22
		Koodikatselmointi
	23
	
29.12

	14
		Teen uuden luokan GameRules, jonka avulla sääntöjä voi muuttaa. Mietin graaffisen käyttöliittymän implementaatiota
	15
	
1.1.2016

	14
		Alotin GUIn rakentamista
	15
		-"-
	16
		Siistin pelilogiikkaa GUI:ta varten
	17
		-"-
	18
	
	
	19
		Vieläkin sitä samaa. Muutin GameLogic:in niin ettei se enään implementoi CardSelectoria, vaan siirsin sen UI:lle. Siirsin samalla metodeja sinne minne ne kuuluu.
	20
		Koodin siistintää. Lisäsin vaihtoehdon, jonka avulla voi muuttaa jos pelaaja saa käyttää toisten kortteja. Tein HandAccessibility enumin.
	21
		Sitä samaa. Ohjelman testausta. Keskityn tämän jälkeen GUIn tekemiseen.
	22
	
2.1.2016
	
	14
		Korjaan luokkakaaviot yms.
	15
		Tein testejä mm. TextBasedUI:lle, PIT-raportti
	16
		Commit ja push
	17


	23
		JavaDoc. Commit ja push
	00
	
4.1

	18:30
		Mietin miten pelin GUIn tulee toimia. Taidan tehdä niin, että hiirellä saa valittua/siirrettyä kortteja. Lisäksi voi olla nappi joka automaattisesti lajittelee kortit jotenkin loogisesti
	19

5.1

	18
		Tein GUI:ta
	19
		Ei edistystä... Otan matopeli-tehtävästä (ohjelmoinnin jatkokurssista) esimerkkiä
	20
		Sain jonkinlaisen systeemin tästä pystyyn.
	21
		Pientä testausta ja kokeilua. Voi olla että käytän wait() ja notifyAll() metodeja pysäytääkseen main-threadin
	22
	
6.12

	14
		Lisään JavaDocceja ja jatkan GUI:ta
	15
		Tein GUI:ta
	16
		Loin enumin EventFlag, jonka avulla saadaan tiedot GameWindow:ista GraphicalUI-oliolle.
	17
	
	
	22
		Tein pelilogiikan testejä, sekä luokkakaavion päivitys. Sekvenssikaavioita en ehdi tehdä
	23
		Lisää testejä. Commit ja push
	00