package com.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.example.RoundingAndFormatting.*;

public class Girokonto extends Konto {
    protected double sollzins;
    protected double dispo;
    protected double zinsen;
    protected double zinssumme;
    protected LocalDate alteDatum;

    public Girokonto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum, double sollzins, double dispo) {
        super(ktoNummer, kontostand, habenzins, eroeffnungsdatum);
        this.sollzins = sollzins;
        this.dispo = dispo;
    }

    @Override
    public void abheben(double betrag, LocalDate transaktionDatum, String grund) {
        if (betrag > dispo + kontostand) {
            System.out.println("Kreditlimit von Konto " + ktoNummer + " überzogen. Buchung über -" +
                    getFormat(betrag) + " Euro vom " + formatDatum(transaktionDatum) + " wurde nicht ausgeführt.\n");
        } else {
            super.abheben(betrag, transaktionDatum, grund);
        }
    }

    @Override
    public LocalDate getLastTransactionDate() {
        return super.getLastTransactionDate();
    }

    @Override
    public void zinsBuchung(LocalDate neueDatum, String grund) {
        alteDatum = getLastTransactionDate();
        //alte Datum ist das Transaktionsdatum, neueDatum ist für das nächste Transaktionsdatum
        long tageZwischen = ChronoUnit.DAYS.between(alteDatum, neueDatum);
        if (grund.equals("Quartal")) {//Quartalsabschluss durchführen, Falls grund ist Quartal
            zinsen = zinssumme;//hier werden die letzten berechneten Zinsen als Zinssumme genommen.
            schliesseZinsenAb(neueDatum);//hier wird den Quartalsabschluss berechnet.
        } else {
            double zinsen = berechneZinsen(tageZwischen);//hier werden die Zinsen berechnet.
            zinssumme = zinssumme + zinsen;
        }
    }

    public double berechneZinsen(long tageZwischen) {
        if (kontostand >= 0) {//1.5
            //habenzins
            zinsen = ((habenzins / 100) * kontostand) / 365 * tageZwischen;
        } else { //7.5
            //sollZins
            zinsen = ((sollzins / 100 ) * kontostand) / 365  * tageZwischen;
        }
        return runden(zinsen);
    }

@Override
    public void schliesseZinsenAb(LocalDate letzteTransaktion) {
        zinssumme = zinsen;

        kontostand += zinssumme;

        myBew.add(new Kontobewegung(zinssumme, letzteTransaktion, this, "Zinsen"));

//        System.out.println("Zinssumme für das Quartal: " + runden(zinssumme) + " Euro");
//        System.out.println("Neuer Kontostand nach Quartal: " + runden(kontostand) + " Euro");

        // Reset zinssumme für den nächsten Quartalsabschluss
        zinssumme = 0;

    }

    @Override
    public void betragBuchen(LocalDate transaktionDatum, double betrag, String grund) {
        super.betragBuchen(transaktionDatum, betrag, grund);
    }
}
