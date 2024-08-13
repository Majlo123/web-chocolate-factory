Specifikacija projektnog zadatka

Za moj deo projekta je uradjen deo: Registracija i prijava na sistem,fabrika čokolade,korisnici i prvi i treći dodatni zadatak.

Računarstvo i automatika 2024/2025

Projektni zadatak

Potrebno je realizovati veb aplikaciju za sistem koji podržava rukovanje fabrikama čokolade.

Model podataka

Aplikaciju koriste 4 grupe (uloge) korisnika: Kupac, Radnik, Menadžer, Administrator. Aplikacija
rukuje sa sledećim entitetima:

KORISNIK

● Korisničko ime (jedinstveno)

● Lozinka

● Ime

● Prezime

● Pol

● Datum rođenja

● Uloga (Kupac, Radnik, Menadžer, Administrator)

● Sve kupovine (ako je korisnik Kupac)

● Korpa (ako je korisnik Kupac)

● Fabrika čokolade objekat (ako je korisnik Menadžer)

● Broj sakupljenih bodova (ako je korisnik Kupac)

● Tip kupca

Tip kupca

● Ime tipa (npr. Zlatni, Srebrni, Bronzani)

● Popust (procenat koji se koristi za obračunavanje cene prilikom kupovine)

● Traženi broj bodova (potreban broj bodova kako bi korisnik postao npr. Zlatni kupac)

Lokacija

● Geografska dužina

● Geografska širina

● Adresa u formatu: ulica i broj, mesto/grad, poštanski broj

FABRIKA ČOKOLADE OBJEKAT

● Naziv

● Čokolade koja su u ponudi

● Radno vreme objekta

● Status (Radi ili Ne radi)

● Lokacija

● Logo (slika)

● Ocena

ČOKOLADA

● Naziv

● Cena

● Vrsta (obična, za kuvanje, za piće,…)

● Fabrika kojoj pripada

● Tip (crna, mlečna, bela,...)

● Gramaža

● Opis

● Slika

● Status (Ima na stanju, Nema na stanju)

● Količina (broj koji označava koliko ovakvih čokolada ima na stanju)

KORPA

● Čokolade koje se nalaze u korpi

● Korisnik čija je korpa

● Cena

KUPOVINA

● Jedinstveni identifikator (10 karaktera)

● Čokolade koje su kupljene

● Fabrika iz koje je kupljeno

● Datum i vreme kupovine

● Cena

● Kupac (ime i prezime)

● Status (Obrada, Odobreno, Odbijeno, Otkazano)

KOMENTAR

● Kupac koji je ostavio komentar

● Fabrika na koju se komentar odnosi

● Tekst komentara

● Ocena (na skali od 1 do 5)

FUNKCIONALNOSTI

● Registracija - neregistrovan korisnik se registruje na aplikaciju popunjavajući polja:
korisničko ime, lozinka (potrebno je uneti dva puta), ime, prezime, pol i datum rođenja i
nakon toga postaje Kupac. Administratori se programski učitavaju iz tekstualnog fajla i
ne mogu se naknadno dodati. Menadžere mogu kreirati samo administratori.
Radnike mogu kreirati samo menadžeri. Kupac ne može da postane Menadžer.

● Prijavljivanje na sistem - neprijavljeni korisnik loguje se na sistem tako što unosi
korisničko ime i lozinku korisnika za koji je registrovan. Nakon toga, korisnik je prijavljen i
može da izvršava aktivnosti predviđene njegovom ulogom.

FABRIKA ČOKOLADE

● Administrator može da kreira novu fabriku. Prilikom kreiranja mora da unese
informacije o nazivu, lokaciji, radnom vremenu i da izabere logo. Pored toga, mora da
izabere menadžera koji će biti zadužen za fabriku. Prilikom odabira menadžera
neophodno je omogućiti odabir samo menadžera koji nije zadužen ni za jednu fabriku.
Ukoliko takvi ne postoje, omogućiti administratoru da registruje novog menadžera koji će
automatski biti povezan sa novododatom fabrikom.

● Na početnoj strani je potrebno prikazati sve fabrike čokolade. Ova stranica je vidljiva
svim tipovima korisnika (uključujući i neregistrovane). Ova stranica treba da prikaže sve
fabrike i to tako da su pri vrhu (na početku) one koje su otvorene. Na ovoj stranici
prikazati podatke o fabrici: naziv, lokacija, logo i prosečna ocena.

● Omogućiti pretragu svih fabrika po sledećim kriterijumima:

○ Nazivu fabrike

○ Nazivu čokolade

○ Lokaciji (korisnik zadaje naziv grada ili države)

○ Prosečnoj oceni

Prilikom prikaza rezultata pretrage voditi računa o tome da se prikažu sledeći podaci:

○ Naziv fabrike

○ Lokacija

○ Logo fabrike

○ Prosečna ocena fabrike

U okviru pretrage pružiti mogućnost sortiranja i filtriranja rezultata. Sortiranje je potrebno
implementirati po rastućem ili opadajućem kriterijumu prema sledećim parametrima:

○ Naziv fabrike

○ Lokacija

○ Prosećna ocena

dok je filtriranje potrebno omogućiti po sledećim kriterijumima:

○ Vrsta čokolade

○ Tip čokolade

○ Prikaz samo otvorenih fabrika.

Izborom neke od prikazanih fabrika, korisnik se prebacuje na prikaz te konkretne fabrike.

● Prikaz fabrike treba da prikaže osnovne informacije o svakoj fabrici:

○ Naziv

○ Radno vreme

○ Status (Radi ili Ne radi)

○ Lokacija

○ Logo (slika)

○ Ocena (ukoliko ona postoji)

○ Komentari (ukoliko oni postoje)

○ Spisak čokolada koje fabrika nudi zajedno sa informacijama o čokoladama

Prikaz adrese pored prikaza teksta lokacije u formatu definisanom unutar entiteta
Lokacija, prikazati mapu ukoliko bude implementiran dodatni zadatak 1.

● Menadžerima je dostupna funkcionalnost dodavanje novih radnika u fabriku za koju je
on zadužen. Prilikom kreiranja radnika neophodno je uneti sve informacije kao i prilikom
registracije novog korisnika..

● Pored dodavanja novih radnika, omogućiti menadžerima funkcionalnost dodavanja nove
čokolade, izmena postojeće čokolade i brisanje čokolade. Za kreiranje čokolade
potrebno je uneti: naziv, cenu, vrstu, tip, gramažu, opis i sliku. Kada se čokolada kreira
količina je 0 i nema je odmah na stanju.

● Radnicima je dostupna funkcionalnost izmene broja količine određene čokolade u
fabrici u kojoj je zaposlen. Radniku omogućiti da se klikom na neku čokoladu iz njegove
fabrike, pojavi opcija “Izmeni količinu”. Nakon izmene količine, ta izmena je odmah
vidljiva i čokolade su dostupne korisnicima za kupovinu.
Kupovina

● Kupci imaju opciju pregleda svih svojih kupovina u okviru svog korisničkog profila.

● Menadžer ima opciju pregleda svih kupovina koje su vezane za fabriku za koji je on
zadužen.

● Kupovine je moguće pretražiti i sortirati. Pretraga se vrši po sledećim kriterijumima:

○ Nazivu fabrike iz koje su kupljene čokolade (samo za Kupca)

○ Cena (cena u opsegu od-do)

○ Datumu kupovine (od-do),

dok se sortiranje vrši (rastuće i opadajuće) po:

○ Nazivu fabrike iz koje su kupljene čokolade (samo za Kupca)

○ Ceni

○ Datumu

● Kupac može da kupi čokolade iz nekog objekta.

Koraci prilikom kupovine:

1. Kupac izborom na fabriku vidi sve čokolade koje su dostupne (samo one
kojih trenutno ima na stanju) u toj fabrici. Korisnik može da doda u korpu
jednu ili više od čokolada koje su prikazane. Za svaku čokoladu može da
izabere količinu koju želi da kupi. Zabraniti korisniku da unese količinu
veću nego što ima na stanju za svaku čokoladu.

2. Nakon što je završio sa odabirom čokolada, kupac ide na pregled korpe u
kom mu se izlistaju informacije o čokoladi (naziv, gramaža, tip, vrsta,
cena, slika), kao i ukupna cena. Kupac može da izbaci čokoladu iz korpe
ili da izmeni količinu čokolade u korpi (količina ne može biti manja od 1 i
veća od količine čokolade na stanju). Ukupna cena korpe se ažurira sa
svakom izmenom.

3. Kupac klikne na dugme kupi nakon čega se kreira porudžbina. Kreirana
porudžbina je u statusu Obrada i dodaje se u listu porudžbina vezane za
fabriku iz koje je kupljeno..
Kada korisnik obavi kupovinu dobija određeni broj bodova. Ovaj broj bodova se
računa po sledećoj formuli:
broj_bodova = ukupna_cena_porudžbine/1000 * 133

● Kupac ima opciju da otkaže kupovinu dok je ona u statusu Obrada. Ako se odustane od
kupovine gubi se broj bodova po sledećoj formuli:
broj_izgubljenih_bodova = ukupna_cena_porudžbine/1000 * 133 * 4

● Menadžer koji je zadužen za fabriku može da promeni status u Odobreno ili Odbijeno.
Ukoliko odbije porudžbinu mora da napiše razlog zbog kog je odbio.
Komentari

● Nakon što kupovina pređe u status Odobreno, korisnik koji je kreirao kupovinu može da
ostavi komentar za fabriku iz koje je kupio i istu ujedno i oceni.

● Nakon što je komentar kreiran, on se ne prikazuje na stranici fabrike dok god ga
Menadžer ne odobri (može i da ga odbije).

● Kupci mogu da vide samo komentare koje je menadžer odobrio.

● Menadžeri i Administratori mogu da vide sve komentare (prihvaćene i odbijene).
Korisnici

● Svi ulogovani korisnici mogu da vide svoje profile i da menjaju svoje lične podatke.

● Administratori imaju pregled svih korisnika registrovanih na sistemu.

● Menadžer ima pregled svoje fabrike, kupovine koje su vezane za tu fabriku i kupaca koji
su kupili iz te fabrike.

● Administrator ima mogućnost prikaza svih registrovanih korisnika sistema. Može da ih
pretražuje, filtrira i sortira. Pretraga se vrši po:

○ Imenu

○ Prezimenu

○ Korisničkom imenu

Sortiranje je potrebno implementirati u rastućem i opadajućem režimu, a moguće ga je
izvršiti prema sledećim parametrima:

○ Imenu

○ Prezimenu

○ Korisničkom imenu

○ Broju sakupljenih bodova.

Filtriranje se vrši prema sledećim parametrima:

○ Ulozi

○ Tipu korisnika.

Realizovati kombinovanu pretragu fabrika po prethodno navedenim kriterijumima u pretrazi
fabrika. Kombinovana ili višestruka pretraga znači da korisnik može da odabere više opcija po
kojim će da vrši pretragu.

Dodatne napomene

1. Brisanje svih entiteta u sistemu je logičko. Svi entiteti koji se mogu dodati, mogu se i
obrisati.

2. U zavisnosti od konkretne implementacije, studenti mogu proizvoljno da prošire date
entitete ili dodavati druge.

3. Potrebno je da student samostalno definiše skalu bodova za Tipove korisnika (npr. da bi
korisnik postao Srebrni potrebno je da sakupi 3000 bodova (i tom prilikom ima npr.
popust od 3% prilikom svake kupovine), a da bi postao Zlatan potrebno je da sakupi
4000 bodova (i tom prilikom ima npr. popust od 5% prilikom svake kupovine)).
   
Dodatni zadaci za više ocene

1. Prilikom odabira lokacije koristiti OpenLayers mape (https://openlayers.org/) ili neku
alternativu mape za pretragu lokacije fabrike na osnovu lokacije ili zadavanja lokacije
prilikom kreiranja fabrike.

2. Omogućiti administratorima prikaz svih “sumnjivih” korisnika koji često otkazuju svoje
kupovine. Ukoliko korisnik (Kupac) izvrši više od 5 otkazivanja kupovina u periodu od
mesec dana on se smatra sumnjivim i izdvaja se u okviru spiska takvih korisnika.
Omogućiti Administratoru da može da blokira ovakve korisnike.

3. Administratori imaju mogućnost blokiranja registrovanih korisnika (ako nisu
administrator). Blokiran korisnik ne može da se uloguje na svoj nalog i nema mogućnost
da izvršava bilo koju akciju u zavisnosti od svoje uloge.

Ocenjivanje projektnog zadatka

● Za verzionisanje projekta je potrebno koristiti git. Repozitorijum treba biti dostupan na
https://gitlab.com. Potrebno je dodati nalog web-e2-ftn kao Maintainer.

● Nakon formiranja tima i projekta, dostavite Vašem asistentu sledeće informacije:

● Spisak članova tima

● Link ka javnom repozitorijumu

● U git istoriji se mora jasno videti tok razvoja u koji moraju biti uključeni svi članovi tima.
Ne prihvata se projekat sa malim brojem ogromnih commitova.

● Dizajn korisničkog interfejsa kao i stilovi (CSS) su prepušteni studentu i obavezni su.
Dozvoljeno je korišćenje CSS biblioteka kao što su Bootstrap, Foundation i Materialize

● Aplikacija treba da trajno čuva podatke u tekstualnim datotekama, bilo u CSV ili JSON
formatu

● Nije dozvoljena upotreba bilo koje baze podataka ili Java serijalizacije objekata

● Od tehnologija je dozvoljeno koristiti:

o Vue za frontend

o JAX-RS REST (Jersey) ili Node.js za backend

● Za dozvolu upotrebe dodatnih biblioteka za navedene tehnologije se treba obratiti
asistentu

● Nije dozvoljeno korišćenje tehnologija koje nisu navedene (npr. Spring, programski jezik
osim Jave i JavaScript itd)

Za ocenu 6 aplikacija ne treba da podrži funkcionalnost kupovine čokolada. Treba zadati
“zabetonirane” vrednosti za kupovine i prilikom pokretanja aplikacije ih učitati.
Za ocenu 8 aplikacija ne treba implementirati dodatne zadatke.
Za ocenu 9 i 10 aplikacija mora da podržava sve navedene funkcionalnosti kao i dodatne
zadatke.
