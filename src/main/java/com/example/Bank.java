package com.example;

import java.text.NumberFormat;
import java.util.Locale;

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


    public void printKontoInfo1(Kunde kunde) {
        NumberFormat nf = NumberFormat.getInstance(Locale.GERMANY);
        for (Konto konto : kunde.konten) {
            System.out.println("Kontoauszug");
            System.out.println("Kto-Nr.: " + konto.ktoNummer + ",");
            System.out.println("BLZ: " + blz + ", " + institutsname + ",");
            System.out.println("Kontostand: " + nf.format(konto.kontostand) + " Euro");
            System.out.println("Kontoinhaber: " + kunde.name);
            System.out.println();
            printTransaktionen(konto, nf);
        }
    }

    public void printTransaktionen(Konto konto, NumberFormat nf) {
        int index = 1;
        for (Kontobewegung bewegung : konto.myBew) {
            System.out.println(index + "   " + bewegung.datum + "   " + nf.format(bewegung.betrag) + " Euro");
            index++;
        }
    }
}