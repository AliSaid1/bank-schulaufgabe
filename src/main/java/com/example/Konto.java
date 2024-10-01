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
    protected LocalDate alteDatum;

    public Konto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum) {
        this.ktoNummer = ktoNummer;
        this.kontostand = kontostand;
        this.habenzins = habenzins;
        this.eroeffnungsdatum = eroeffnungsdatum;
    }

    public LocalDate getLastTransactionDate() {
        for (int i = 0; i < myBew.size(); i++) {
            alteDatum = myBew.get(i).datum;//Transaktionsdatum von der Liste der Kontobewegung holen.
        }
        return alteDatum;
    }

    public abstract void zinsBuchung(LocalDate neueDatum, String grund);

    public abstract void schliesseZinsenAb(LocalDate letzteTransaktion);

    public void betragBuchen(LocalDate transaktionDatum, double betrag, String grund) {
        switch (grund) {
            case "Einzahlung" -> einzahlen(betrag, transaktionDatum, grund);
            case "Auszahlung" -> abheben(betrag, transaktionDatum, grund);
            case "Quartal" -> schliesseZinsenAb(transaktionDatum);
            default -> eroeffnen(betrag, transaktionDatum, grund);
        }
    }
    public void eroeffnen(double ersteEinzahlung, LocalDate eroeffnungsdatum, String grund) {
        if (ersteEinzahlung <= 0) {
            System.out.println("Error: neues Konto kann nicht eröffnet werden! Einzahlung ist erforderlich");
        } else {
            einzahlen(ersteEinzahlung, eroeffnungsdatum, grund);
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

