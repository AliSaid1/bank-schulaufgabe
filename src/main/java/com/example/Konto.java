package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Konto {
    protected String ktoNummer;
    protected double kontostand;
    protected double habenzins;
    protected Kunde myKunde;
    protected List<Kontobewegung> myBew = new ArrayList<>();
    protected LocalDate eroeffnungsdatum;

    public Konto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum) {
        this.ktoNummer = ktoNummer;
        this.kontostand = kontostand;
        this.habenzins = habenzins;
        this.eroeffnungsdatum = eroeffnungsdatum;
    }

    //wenn die erste > als dispo 3000
    // Kontoeröffnung kontostand ist immer null aber man kann von dispo abheben. Man muss erstmal einzahlen
    public void eroeffnen(double ersteEinzahlung, LocalDate eroeffnungsdatum, String grund) {
        if (ersteEinzahlung == 0) {
            System.out.println("Error: neues Konto kann nicht eröffnet werden! Einzahlung ist erforderlich");
        } else if (ersteEinzahlung == 1) {
            einzahlen(ersteEinzahlung, eroeffnungsdatum, grund);
        } else {
            abheben(ersteEinzahlung, eroeffnungsdatum, grund);
        }

    }

    public void abheben(double betrag, LocalDate transaktionDatum, String grund) {
        kontostand -= betrag;
        this.myBew.add(new Kontobewegung(-betrag, transaktionDatum, this, grund));
    }

    public void einzahlen(double betrag, LocalDate transaktionDatum, String grund) {
        this.kontostand += betrag;
        this.myBew.add(new Kontobewegung(betrag, transaktionDatum, this, grund));
    }

}



//    public void einzahlen(double betrag, LocalDate datum) {
//        if (betrag <= 0) {
//            System.out.println("Error: Der Einzahlungsbetrag muss positiv sein.");
//        }
//        kontostand += betrag;
//        myBew.add(new Kontobewegung(betrag, datum, this));//Verfolgung der Transaktionshistorie eines Kontos
//    }

//eröffnen( ) einzahlen( )