package com.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static com.example.RoundingAndFormatting.getFormat;
import static com.example.RoundingAndFormatting.runden;

public class Girokonto extends Konto {
    protected double sollzins;
    protected double dispo;
    protected double zinsen;
    protected double zinssumme;
    protected double kontostandNew;

    public Girokonto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum, double sollzins, double dispo) {
        super(ktoNummer, kontostand, habenzins, eroeffnungsdatum);
        this.sollzins = sollzins;
        this.dispo = dispo;
    }
    @Override
    public void abheben(double betrag, LocalDate transaktionDatum, String grund) {
        if (betrag > dispo + kontostand) {
            System.out.println("Kreditlimit von Konto " + ktoNummer + " überzogen. Buchung über -" +
                    getFormat(betrag) + " Euro vom " + transaktionDatum + " wurde nicht ausgeführt.\n");
        } else {
          super.abheben(betrag, transaktionDatum, grund);
        }
    }

    public void buchen(LocalDate alteDatum, LocalDate neueDatum, double betrag, String grund) {

        long tageZwischen = ChronoUnit.DAYS.between(alteDatum, neueDatum);

        if (grund.equals("Einzahlung") || grund.equals("Ersteinzahlung")) {
            kontostandNew = kontostand + betrag;
        } else if (grund.equals("Auszahlung")){
            kontostandNew = kontostand - betrag;
        } else {
            kontostandNew =  kontostand;//kein Betrag d.h: kontostandNew ist direkt der letzte kontostandNew bzw. kontostand unverändert
        }

        if (grund.equals("Quartal")) {
            zinsen = zinssumme;
        } else {
            double zinsen = berechneZinsen(tageZwischen);
            zinssumme = zinssumme + zinsen;
        }

        betragBuchen(alteDatum,neueDatum ,betrag, grund);
    }

    private double berechneZinsen(long tageZwischen) {
        if (kontostandNew >= 0) {//1.5 NOTE: er nutzt den alten Kontostand 0.0
            //habenzins
            zinsen = ((habenzins / 100) * kontostandNew) / 365 * tageZwischen;
        } else { //7.5
            //sollZins
            zinsen = ((sollzins / 100 ) * kontostandNew) / 365  * tageZwischen;
        }
        return zinsen;
    }

    public void zinsenBerechnungenQuartal(LocalDate letzteTransaktion) {
        zinssumme = zinsen;

        kontostand += zinssumme;

        myBew.add(new Kontobewegung(zinssumme, letzteTransaktion, this, "Zinsen"));


        System.out.println("Zinssumme für das Quartal: " + runden(zinssumme) + " Euro");
        System.out.println("Neuer Kontostand nach Quartal: " + runden(kontostand) + " Euro");

        // Reset zinssumme für den nächsten Quartalsabschluss
        zinssumme = 0;

    }

    private void betragBuchen(LocalDate alteDatum, LocalDate neueDatum, double betrag, String grund) {
        switch (grund) {
            case "Einzahlung" -> einzahlen(betrag, alteDatum, grund);
            case "Auszahlung" -> abheben(betrag, alteDatum, grund);
            case "Quartal" -> zinsenBerechnungenQuartal(neueDatum);
            default -> eroeffnen(betrag, alteDatum, grund);
        }
    }

}

/*
   public void zinsenBerechnungenQuartal(long tageZwischen, double Zinssumme, double kontostandNew) {
        long summeAllerTagen = 0;
        double result;

        summeAllerTagen += tageZwischen;
        long quartal = 121;//365/3
        //Quartalsabschluss alle 3 Monate = Zinssumme
        //KontostandNew = Zinssumme +/- (kontostand)
        //Zinssumme muss nach der Buchung auf 0 gesetzt.
        if (summeAllerTagen > quartal) {
            kontostandNew = Zinssumme + kontostand;
        }
         result = kontostandNew;
        System.out.println("nach Buchung: " + runden(kontostand) + " new Kontostand: " + runden(result) + " tage: " + tageZwischen + " zinsen: " + runden( zinsen) + " zinssumme: " + runden(zinssumme));
    }
 */