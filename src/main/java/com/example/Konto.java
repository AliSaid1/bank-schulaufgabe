package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Konto {
    protected String ktoNummer;
    protected double kontostand;
    protected double habenzins;
    protected Kunde myKunde;
    protected List<Kontobewegung> myBew = new ArrayList<>();
    protected LocalDate eroeffnungsdatum;

    public Konto(String ktoNummer, double kontostand, double habenzins,LocalDate eroeffnungsdatum) {
        this.ktoNummer = ktoNummer;
        this.kontostand = kontostand;
        this.habenzins = habenzins;
        this.eroeffnungsdatum = eroeffnungsdatum;
    }

    // Methode zur Kontoeröffnung
    public void eroeffnen(double ersteEinzahlung, LocalDate eroeffnungsdatum) {
        if (ersteEinzahlung <= 0) {
            System.out.println("Error: neues Konto kann nicht eröffnet werden! Einzahlung ist erforderlich");
        }
        // erste Einzahlung bei Kontoeröffnung
        einzahlen(ersteEinzahlung, eroeffnungsdatum);
    }
    public void einzahlen(double betrag, LocalDate transaktionDatum) {
        this.kontostand += betrag;
        this.myBew.add(new Kontobewegung(betrag, transaktionDatum, this));
    }

    public void abheben(double betrag, LocalDate transaktionDatum) {
        this.kontostand -= betrag;
        this.myBew.add(new Kontobewegung(-betrag, transaktionDatum, this));
    }



//    public void einzahlen(double betrag, LocalDate datum) {
//        if (betrag <= 0) {
//            System.out.println("Error: Der Einzahlungsbetrag muss positiv sein.");
//        }
//        kontostand += betrag;
//        myBew.add(new Kontobewegung(betrag, datum, this));//Verfolgung der Transaktionshistorie eines Kontos
//    }
}
//eröffnen( ) einzahlen( )