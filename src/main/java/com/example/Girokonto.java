package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Girokonto extends Konto {
    protected double sollzins;
    protected double dispo; // Überziehungslimit

    public Girokonto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum, double sollzins, double dispo) {
        super(ktoNummer, kontostand, habenzins, eroeffnungsdatum);
        this.sollzins = sollzins;
        this.dispo = dispo;
    }
       // vierteljährlich (alle drei Monate)
    public void berechneZinsen(LocalDate datum) {
        if (kontostand > 0){
            double zinsen = kontostand * ((habenzins / 100) / 4);//12 / 3 = 4 Buchungen im Jahr
            einzahlen(zinsen, datum);
        }
        else {
            double ueberziehungszinsen =  Math.abs(kontostand * (sollzins / 100) / 4);
            abheben(ueberziehungszinsen, datum);
        }
    }
    // Vierteljährliche Berechnung von Habenzinsen oder Sollzinsen
    public void berechneZinsen1(LocalDate letzterZinstag, LocalDate datum) {
        // Calculate the number of days between the last interest calculation and the new date
        long tageZwischen = ChronoUnit.DAYS.between(letzterZinstag, datum);
        double zinsSumme = 0.0;

        if (kontostand > 0){
            double zinsen = kontostand * (habenzins / 100 * tageZwischen / 365);//12 / 3 = 4 Buchungen im Jahr
            einzahlen(zinsen, datum);
        } else {
            double ueberziehungszinsen = Math.abs(kontostand * (sollzins / 100 * tageZwischen / 365));
            abheben(ueberziehungszinsen, datum);
        }

    }
//149.5*(0.075/365*49)= 1.505
    @Override
    public String toString() {
        return "Girokonto{" +
                "sollzins=" + sollzins +
                ", dispo=" + dispo +
                ", ktoNummer='" + ktoNummer + '\'' +
                ", kontostand=" + kontostand +
                '}';
    }
}




/*
@Override
    public void abheben(double betrag, LocalDate transaktionDatum) {
        if (kontostand - betrag < -dispo) {
            System.out.println("Kreditlimit von Konto " + ktoNummer + " überzogen. Buchung über " + String.format("%.2f", betrag) + " Euro vom " + transaktionDatum + " wurde nicht ausgeführt.");
        } else {
            super.abheben(betrag, transaktionDatum);
        }
    }
 */