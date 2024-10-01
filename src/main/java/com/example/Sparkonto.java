package com.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.example.RoundingAndFormatting.runden;

public class Sparkonto extends Konto {
    protected char art;// Art des Sparens (z.B. 'F' für Festgeld)
    protected double zinsen;
    protected double zinssumme;

    public Sparkonto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum, char art) {
        super(ktoNummer, kontostand, habenzins, eroeffnungsdatum);
        this.art = art;
    }

    @Override
    public LocalDate getLastTransactionDate() {
        return super.getLastTransactionDate();
    }

    @Override
    public void zinsBuchung(LocalDate neueDatum, String grund) {
        alteDatum = getLastTransactionDate();

        long tageZwischen = ChronoUnit.DAYS.between(alteDatum, neueDatum);

        double zinsen = zinsenBerechnung(tageZwischen);

        zinssumme += zinsen;

        if (grund.equals("jährlich")) {
            schliesseZinsenAb(neueDatum);
        }
    }

    private double zinsenBerechnung(long tageZwischen) {
        if (kontostand >= 0) {
            //habenzins
            zinsen = ((habenzins / 100) * kontostand) / 365 * tageZwischen;
        }
        return zinsen;
    }
    @Override
    public void schliesseZinsenAb(LocalDate letzteTransaktion) {
        //hier Jährlich
        kontostand = kontostand + zinssumme;
//        System.out.println("kontostand: " + runden(kontostand) + " zinssumme: " + runden(zinssumme) + " Datum: " + letzteTransaktion);
        myBew.add(new Kontobewegung(zinssumme, letzteTransaktion, this, "Zinsen"));
        zinssumme = 0;

    }
    @Override
    public void betragBuchen(LocalDate transaktionDatum, double betrag, String grund) {
        super.betragBuchen(transaktionDatum, betrag, grund);
    }

    @Override
    public String toString() {
        return "Sparkonto{" +
                "kontostand=" + kontostand +
                '}';
    }


}
