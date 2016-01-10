
# Waffe Game 2 aihemäärittely - 17.12.2015

Waffe Game 2 on korttipeli, jossa on tarkoituksena pelata pois omat korttinsa ennen vastustajaa. Kuten monetkin muutkin korttipelit, peliä pelataan vuorotellen. Pelissä on lyötävä samanlaisia kortteja pelikenttään. Vuorollaan saa myös löydä useampi kortti. Jos ei pysty lyödä (tai jos ei halua lyödä) korttia pelikentälle, niin on laitettava kaikki kortit näkyville pöydälle ja otettava pakasta kortteja, kunnes on yhteensä kymmenen ("pöytäkäsi" lasketaan siis mukaan). Pelaaja pystyy käyttämään vastustajan pöytäkättä omilla vuoroillaan (sääntöä voi muuttaa). Pelikentällä on tyyppi joka voi vaihdella lyötäessään erilaisia kortteja. Tyyppi vaihtuu kuitenkin aina seuraavanlaisesti:

	Maa voi muuttua suoraksi
	
	Suora voi muuttua ryhmiksi (HUOM. Suora on "syklinen", eli 3|2|A|K|Q on validi suora)
	
	Ryhmien koko voi vaihdella kahden (parien) ja neljän välissä.
	
	Suurin ryhmä on kuitenkin neljän kortin ryhmä

Kaikkien pelikentässä olevien korttejen on seurattava sen tyyppiä. Lisäksi suorassa kaikki kortit kuuluu samaan, yhtenäiseen suoraan.

Pelissä on perinteisesti mukana kolme jokeri korttia, joiden "arvoa" voi aina pelikentässä "muuttaa". Toisin sanoin jokeri on aina "tyhjä kortti", joka voi tilanteen mukaan siirtää esim. suorassa toisen arvon paikalle.

Esimerkki jokerin käytöstä:

	Pelikenttä: K, Joker, J

	jos tähän lyödään Q, niin saa myös lyödä 9, sillä jokeri siirty 10 paikalle

	Pelikenttä: K, Q, J, Joker, 9 

	Tämän saa vielä muutettua pareiksi, jos lisää esim. K, Q, Joker

	Pelikenttä: K, K, Q, Q, J, Joker, 9, Joker

	
Jokeria ei aina pysty lyödä:

	7, 7, 7, 10, 10, 10 <-- tähän ei saa lyödä yhtä jokeria, sillä silloin syntyisi yksi kolmen, ja yksi neljän kortin ryhmä

	4, 4, 4, 4 <-- tähän ei myöskään saa lyödä jokeria, sillä suurin sallittu ryhmä on neljä korttia.

### Käyttäjän toiminnot:
* Sääntöjen valitseminen
* Uuden kaksinpelin aloittamisen
* [BONUS] Uuden yksinpelin aloittamisen tietokonetta vastaan
* Omien korttien valitseminen kädestä ja pöytäkäsistä
* Korttien laittaminen pelikentälle. Onnistuu vain jos
  * on valinnut ainakin yhden kortin, ja
  * kaikki kortit seuraa samaa "tyyppiä" sekä
  * suorassa/ryhmässä ei ole liikaa kortteja
* Oman vuoron ohitus
	
