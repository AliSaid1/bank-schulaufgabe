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

    public double zinsenBerechnen(LocalDate alteDatum, LocalDate neueDatum) {
        double neueKontostand;
        long tageZwischen = ChronoUnit.DAYS.between(alteDatum, neueDatum);

        double zinsen = berechneZinsen(tageZwischen);
        zinssumme += zinsen;

        kontostand = kontostand + zinssumme;

        myBew.add(new Kontobewegung(zinssumme, neueDatum, this, "Zinsen"));

        return runden(kontostand);
    }

    private double berechneZinsen(long tageZwischen) {
        if (kontostand >= 0) {
            //habenzins
            zinsen = ((habenzins / 100) * kontostand) / 365 * tageZwischen;
        }
        return zinsen;
    }
//zinsen 15.6219
    @Override
    public void eroeffnen(double ersteEinzahlung, LocalDate eroeffnungsdatum, String grund) {
        super.eroeffnen(ersteEinzahlung, eroeffnungsdatum, grund);
    }

    @Override
    public void abheben(double betrag, LocalDate transaktionDatum, String grund) {
        super.abheben(betrag, transaktionDatum, grund);
    }

    @Override
    public void einzahlen(double betrag, LocalDate transaktionDatum, String grund) {
        super.einzahlen(betrag, transaktionDatum, grund);
    }

    @Override
    public String toString() {
        return "Sparkonto{" +
                "kontostand=" + kontostand +
                '}';
    }
}
