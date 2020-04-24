### Generating, Building and Running:

##### Server

- Generate

In order to generate files (they are already generated)

```
cd server
.\java_gen_air_quality.bat
```

- Build & Run

or 

```
.\build_and_run.bat
```

or 

```
.\build_and_run2.bat
```

or

Just run AirQualityClientRunner.java in Intellij IDEA

or

```
.\build.bat
.\run.bat
```

##### Client

- Generate

In order to generate files (they are already generated)

```
cd client
python_gen_air_quality.bat
```

There might be a problem sometimes with content of ```air_quality_pb2_grpc.py```

In order to fix it, modify 4th line of this file, it should be like:

```
from . import air_quality_pb2 as air__quality__pb2
```

- Run

```
.\build_and_run.bat
```

or 

```
.\build.bat
.\run.bat
```

or 

```
poetry install
poetry run python air_quality_client.py
```

### Opis zadania domowego - technologie middleware

#### Zadanie składa się z dwóch części.

##### Zadanie 4.1

Wynikiem prac ma być aplikacja klient-serwer, 
w której komunikacja rozproszona jest zrealizowana 
z wykorzystaniem technologii gRPC. 

Klient powinien dokonywać subskrypcji na zdarzenia. 

To, o czym mają one informować, jest w gestii Wykonawcy, 
np. o nadchodzącym wydarzeniu lub spotkaniu, którym jesteśmy zainteresowani, 
o osiągnięciu określonych w żądaniu warunków pogodowych w danym miejscu, itp. 

Na dane zdarzenie może się naraz zasubskrybować wielu odbiorców 
i może istnieć wiele niezależnych subskrypcji 
(tj. np. na wiele różnych instancji spotkań).  

Należy odpowiednio wykorzystać mechanizm strumieniowania (stream). 
Wiadomości mogą przychodzić z dowolnymi odstępami czasowymi (nawet bardzo długimi), 
jednak na potrzeby demonstracji rozwiązania należy 
przyjąć interwał rzędu pojedynczych sekund).

W definicji wiadomości przesyłanych do klienta należy wykorzystać pola enum, 
string, ew. message - wraz z co najmniej jednym modyfikatorem repeated. 

Etap subskrypcji powinien w jakiś sposób parametryzować otrzymywane wiadomości 
(np. obejmować wskazanie miasta, którego warunki pogodowe nas interesują.

Dla uproszczenia realizacji zadania można (nie trzeba) 
pominąć funkcjonalność samego tworzenia instancji wydarzeń lub miejsc, 
których dotyczy subskrypcja i notyfikacja 
- może to być zawarte w pliku konfiguracyjnym, 
a nawet kodzie źródłowym strony serwerowej. 
Treść wysyłanych zdarzeń może być wynikiem działania bardzo prostego generatora.

W realizacji należy zadbać o odporność komunikacji 
na błędy sieciowe (które można symulować czasowym wyłączeniem klienta 
lub serwera lub włączeniem firewalla). 

Ustanie przerwy w łączności sieciowej musi pozwolić 
na ponowne ustanowienie komunikacji bez konieczności restartu procesu. 

Rozwiązanie musi być także "PAT-friendly" 
(tj. uwzględniać rozważane na laboratorium sytuacje). 

##### Zadanie 4.2

Wynikiem prac ma być aplikacja klient-serwer, 
w której komunikacja rozproszona jest realizowana w technologii ICE albo Thfitt 
(do wyboru).
 
Aplikacja ma pozwalać na sterowanie urządzeniami tzw. inteligentnego domu, 
na którego wyposażeniu znajdują się różne urządzenia, 
np. czujniki temperatury czy zdalnie sterowane lodówki, piece, 
kamery monitoringu z opcją PTZ (pan/tilt/zoom), bulbulatory, itp. 

Każde z urządzeń może występować w kilku nieznacznie się różniących odmianach, 
a każda z nich w pewnej (niewielkiej) liczbie instancji. 
Dom ten nie oferuje obecnie możliwości budowania złożonych układów, 
pozwala użytkownikom jedynie na sterowanie pojedynczymi 
urządzeniami oraz odczytywanie ich stanu.

##### Dodatkowe informacje i wymagania:

Projektując interfejs urządzeń postaraj się użyć także typów bardziej złożonych 
niż string czy int/long. Pamiętaj o deklaracji wyjątków tam, 
gdzie to może mieć zastosowanie.

Wystarczająca jest obsługa dwóch-trzech typów urządzeń, 
jeden-dwa z nich mogą mieć dwa-trzy podtypy. 

Odwzoruj podane wymagania do cech wybranej technologii (ICE albo Thrift) w taki sposób,
by jak najlepiej wykorzystać oferowane przez nią 
możliwości budowy takiej aplikacji i by osiągnąć 
jak najbardziej eleganckie rozwiązanie 
(gdyby żądanej funkcjonalności nie dało się wprost osiągnąć).

Zestaw urządzeń może być niezmienny w czasie życia serwera 
(tj. dodanie nowego urządzenia może wymagać modyfikacji kodu serwera 
i restartu procesu). 

Początkowy stan instancji obsługiwanego urządzenia może 
być zawarty w kodzie źródłowym strony serwerowej lub pliku konfiguracyjnym.

##### Dla chętnych: wielowątkowość strony serwerowej.

Aplikacja kliencka powinna pozwalać zademonstrować sterowanie 
różnymi urządzeniami bez konieczności restartu w celu przełączenia na inne urządzenie.

Serwer może zapewnić funkcjonalność wylistowania nazw 
(identyfikatorów) aktualnie dostępnych instancji urządzeń.

##### Realizując komunikację w ICE:

należy zaimplementować poszczególne urządzenia inteligentnego domu jako osobne obiekty
middleware, do których dostęp jest możliwy po podaniu pewnego identyfikatora 
(znanego klientowi -> Joe) - nie należy projektować żadnego obiektu, 
który po nazwie urządzenia zwracałby jego referencję. 
 
Choć może nie ma to w tym zadaniu uzasadnienia, instancjonowanie serwantów skojarzonych z obiektem middleware powinno nastąpić dopiero w czasie pierwszyego odwołaniu do danego urządzenia (-> ServantLocator).

Realizując komunikację w Thrift należy dążyć do minimalizacji liczby instancji eksponowanych usług (ale bez ekstremizmu - lodówka i bulbulator nie mogą być opisane wspólnym interfejsem!). 

##### Uwagi wspólne (4.1+4.2):

Interfejs IDL powinien być prosty, ale zaprojektowany w sposób dojrzały 
(odpowiednie typy proste, właściwe wykorzystanie typów złożonych), 
uwzględniając możliwość wystąpienia różnego rodzaju błędów. 

Tam gdzie to możliwe i uzasadnione należy wykorzystać dziedziczenie interfejsów IDL.

- Stan serwerów nie musi być persystowany 
(z zastrzeżeniem symulacji awarii w zadaniu 4.1).

- Do realizacji zadania należy wykorzystać przynajmniej dwa różne języki programowania 
i w każdym z zadań klient i serwer muszą być w różnych językach programowania.

- Działanie aplikacji może (nie musi) być demonstrowane na jednej maszynie.

- Kod źródłowy zadania powinien być demonstrowany w IDE. 

- Aktywność poszczególnych elementów aplikacji należy odpowiednio logować 
(wystarczy na konsolę) by móc sprawnie ocenić poprawność jej działania.

- Aplikacja kliencka powinna mieć postać tekstową i może być minimalistyczna, 
lecz musi pozwalać na przetestowanie funkcjonalności aplikacji szybko 
i na różny sposób (musi więc być przynajmniej w części interaktywna).

- Pliki generowane (stub, skeleton) powinny się znajdować w osobnym katalogu 
niż kod źródłowy klienta i serwera. 

- Pliki stanowiące wynik kompilacji (.class, .o itp) 
powinny być w osobnych katalogach niż pliki źródłowe.

##### Sposób oceniania

Wykonanie tylko jednego z zadań nie pozwoli na uzyskanie zaliczenia zadania 4.

Sposób wykonania zadania będzie miał zasadniczy wpływ na ocenę. 

W szczególności:

- niestarannie przygotowany interfejs IDL: -3 pkt.

- niestarannie napisany kod (m.in. zła obsługa wyjątków, błędy działania w czasie demonstracji): -3 pkt.

- brak aplikacji w więcej niż jednym języku programowania: -3 pkt.

- brak wymaganej funkcjonalności lub realizacja funkcjonalności w sposób niezgodny z wytycznymi: -10 pkt.

- brak wymaganej niezawodności w zadaniu 4.1: -3 pkt.

- nieznajomość zasad działania aplikacji w zakresie zastosowanych mechanizmów: -10 pkt,

- dodatkowa funkcjonalność (oznaczona w wymaganiach jako opcjonalna): +3 pkt.

- Punktacja dotyczy sytuacji ekstremalnych - 
całkowitego braku pewnego mechanizmu albo pełnej i poprawnej implementacji - 
możliwe jest przyznanie części punktów (lub punktów karnych).

#### Pozostałe uwagi

Zadanie trzeba zaprezentować sprawnie, na jednego studenta przypada 6,5 minuty…

Termin nadesłania zadania dla wszystkich grup: 27 kwietnia, godz. 8:00. 

Prezentowane musi być zadania zamieszczone na moodle, tj. nie są dopuszczalne żadne późniejsze poprawki.

Przypominam o konieczności dołączenia do zadania oświadczenia o samodzielnym jego wykonaniu (co oczywiście nie wyklucza konsultacji pomiędzy studentami jak zrealizować poszczególne funkcjonalności).