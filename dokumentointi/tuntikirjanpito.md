
	NOTE:
	Pidä kirjaa harjoitustyöhön käyttämästäsi työmäärästä
	Käyttämäsi työmäärä ei vaikuta arvosanaan, joten ole rehellinen
	Merkitse aina vähintään päivämäärä, käyttämäsi aika ja ajankäytön kohde

17.12.2015

	18
		Tein Git ja GitHub jutut
	19
		Tein aihemaarittelyn
	20
	
18.12.2015

	19:30
		Tein perus luokat: Main, Card, Hand, Pack
	20
		lisäsin enumit Value ja Suit. Tein abstraktit luokat CardOwner ja Player. Lisäsin luokan HumanPlayer
	21

	
	23
		Loin Pile luokan, joka on "pelikentän" korttipino. Lisäki muutin CardOwnerin metodeja, muun muuassa addCard:ia.

19.12.2015

	00
		Tein Pile luokalle testit, joiden läpäistessä addCard metodi palauttaa true.
	01
		Loin pari JUnit testiä Card:eille, Packi:lle ja Hand:ille sekä myös testitiedoston Pile:ille
	02
		Pile:in addCard-testejä korjailen... aina kauheen näköistä koodia syntyy. Pitänee kai siirtää (addCard-)testit toiselle luokalle, niin koodi pysyy siistimpänä.
	03
		Pari ongelmaa sain korjattua, mutta on korjattava testiä, joka tarkistaa onko pino suora vai ei. Ongelmana on se, että suora saa olla syklinen (esim. 2 - A - K) ja nämä tilanteet sekä jokereiden lisäys aiheuttaa ongelmia.
	03:30

	14
		Debuggaus jatkuu. Kirjotain pinon suoratesti-metodin uudelleen.
	14:30

	16
		Sain väännettyä suoratesti-metodin kuntoon. Loin vielä viimeiset testit pinolle.
	17