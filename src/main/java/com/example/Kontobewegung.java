package com.example;


import java.time.LocalDate;

public class Kontobewegung {
    protected double betrag;
    protected LocalDate datum;
    protected Konto myKonto;

    public Kontobewegung(double betrag, LocalDate datum, Konto myKonto) {
        this.betrag = betrag;
        this.datum = datum;
        this.myKonto = myKonto;
    }

    @Override
    public String toString() {
        return "Kontobewegung{" +
                "betrag=" + betrag +
                ", datum=" + datum +
                ", myKonto=" + myKonto +
                '}';
    }
}
