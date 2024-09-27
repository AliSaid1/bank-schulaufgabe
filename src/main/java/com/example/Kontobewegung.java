package com.example;


import java.time.LocalDate;

public class Kontobewegung {
    protected double betrag;
    protected LocalDate datum;
    protected Konto myKonto;
    protected String grund;

    public Kontobewegung(double betrag, LocalDate datum, Konto myKonto, String grund) {
        this.betrag = betrag;
        this.datum = datum;
        this.myKonto = myKonto;
        this.grund = grund;
    }

    @Override
    public String toString() {
        return "Kontobewegung{" +
                "betrag=" + betrag +
                ", datum=" + datum +
                ", myKonto=" + myKonto +
                ", grund='" + grund + '\'' +
                '}';
    }
}
