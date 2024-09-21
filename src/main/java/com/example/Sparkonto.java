package com.example;

import java.time.LocalDate;

public class Sparkonto extends Konto {
    protected char art; // Art des Sparens (z.B. 'F' für Festgeld)

    public Sparkonto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum, char art) {
        super(ktoNummer, kontostand, habenzins, eroeffnungsdatum);
        this.art = art;
    }

    public double abheben(double betrag) {
        kontostand -= betrag;
        return kontostand;
    }

    public void berechneZinsen(LocalDate datum) {
        double zinsen = kontostand * (habenzins / 100);//Jährlich
        einzahlen(zinsen, datum);
    }
}
