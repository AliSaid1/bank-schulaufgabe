package com.example;

public class Bank {
    protected final String blz = "500 401 50";
    protected final String institutsname = "Sparkasse Münster";
    protected Kunde[] myKunden;
    protected Konto[] myKonto;

    public Bank(Kunde[] myKunden, Konto[] myKonto) {
        this.myKonto = myKonto;
        this.myKunden = myKunden;
    }

    public void addKontoToKunde(Kunde kunde, Konto konto) {//why we added this in Bank class
        kunde.konten.add(konto);//In der Liste konten wir speichern einKonto zu einem Kunden
    }

    public void printKontoInfo(Kunde myKunde) {
        System.out.println("Kontoinhaber: " + myKunde.name);
        for (Konto konto : myKunde.konten) {
            System.out.println(
                    "Kto-Nr.: " + konto.ktoNummer + ",\n" +
                    "BLZ: " + blz + ", " + institutsname + ",\n" +
                    "kontostand: " + konto.kontostand + " Euro\n"
            );
        }
    }


    public void printKontoauszug(Kunde kunde) {
        for (Konto konto : kunde.konten) {
            System.out.println(
                    "Kontoauszug\n" +
                    "Kto-Nr.: " + konto.ktoNummer + ",\n" +
                    "BLZ: " + blz + ", " + institutsname + ",\n" +
                    "Kontostand: " + konto.kontostand + " Euro\n" +
                    "Kontoinhaber: " + kunde.name
            );
            System.out.println();
            printTransaktionen(konto);
        }
    }

    public void printTransaktionen(Konto konto) {
        int i = 1;
        for (Kontobewegung kontobewegung: konto.myBew) {
            System.out.println(i + "  " + kontobewegung.datum + "   " +
                    kontobewegung.betrag + " Euro   " + kontobewegung.grund);
            i++;
        }
        System.out.println();
    }
}