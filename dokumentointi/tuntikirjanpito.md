
	NOTE:
	Pid� kirjaa harjoitusty�h�n k�ytt�m�st�si ty�m��r�st�
	K�ytt�m�si ty�m��r� ei vaikuta arvosanaan, joten ole rehellinen
	Merkitse aina v�hint��n p�iv�m��r�, k�ytt�m�si aika ja ajank�yt�n kohde

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
		lis�sin enumit Value ja Suit. Tein abstraktit luokat CardOwner ja Player. Lis�sin luokan HumanPlayer
	21

	
	23
		Loin Pile luokan, joka on "pelikent�n" korttipino. Lis�ki muutin CardOwnerin metodeja, muun muuassa addCard:ia.

19.12.2015

	00
		Tein Pile luokalla testit, joiden l�p�istess� addCard metodi palauttaa true.
	01
		Loin pari JUnit testi� Card:eille, Packi:lle ja Hand:ille sek� my�s testitiedoston Pile:ille
	02
		Pile:in addCard-testej� korjailen... aina kauheen n�k�ist� koodia syntyy. Pit�nee kai siirt�� (addCard-)testit toiselle luokalle, niin koodi pysyy siistimp�n�.
	03
		Pari ongelmaa sain korjattua, mutta on korjattava testi�, joka tarkistaa onko pino suora vai ei. Ongelmana on se, ett� suora saa olla syklinen (esim. 2 - A - K) ja n�m� tilanteet sek� jokereiden lis�ys aiheuttaa ongelmia.
	03:30