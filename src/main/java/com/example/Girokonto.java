package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Girokonto extends Konto {
    protected double sollzins;
    protected double dispo; // Überziehungslimit
    protected double zinssumme;
    protected double zinsen;

    public Girokonto(String ktoNummer, double kontostand, double habenzins, LocalDate eroeffnungsdatum, double sollzins, double dispo) {
        super(ktoNummer, kontostand, habenzins, eroeffnungsdatum);
        this.sollzins = sollzins;
        this.dispo = dispo;
    }
    public void abheben(double betrag, LocalDate transaktionDatum, String grund) {
        if (betrag > dispo + kontostand) {
            System.out.println("Kreditlimit von Konto " + ktoNummer + " überzogen. Buchung über " +
                    String.format("%.2f", betrag) + " Euro vom " + transaktionDatum + " wurde nicht ausgeführt.\n");
        } else {
            kontostand -= betrag;
            this.myBew.add(new Kontobewegung(-betrag, transaktionDatum, this, grund));
        }
    }


    public void buchen(LocalDate alteDatum, LocalDate neueDatum, double betrag, String grund) {
        double kontostandNew;
        //er muss erstmal zinsen rechnen und dann betrag addieren mit kst., weil betrag wird als g1.zinssumme benutzt
        long tageZwischen = ChronoUnit.DAYS.between(alteDatum, neueDatum);
        double zinsen = berechneZinsen(tageZwischen);

        if (grund.equals("Einzahlung") || grund.equals("Ersteinzahlung")) {
            kontostandNew = kontostand + betrag;
        } else if (grund.equals("Auszahlung")){
            kontostandNew = kontostand - betrag;
        } else {
            kontostandNew =  kontostand;//kein Betrag d.h: kontostandNew ist direkt der letzte kontostandNew bzw. kontostand unverändert
        }
        System.out.println("vor Buchung: " + kontostand);

        betragBuchen(alteDatum, betrag, grund);

        zinssumme = zinssumme + zinsen;

        System.out.println("nach Buchung: " + runden(kontostand) + " new Kontostand / bei Quartal OLD = new: " + runden(kontostandNew) + " tage: " + tageZwischen + " zinsen: " + runden(zinsen) + " zinssumme: " + runden(zinssumme));

    }

    private double berechneZinsen(long tageZwischen) {
        if (kontostand >= 0) {
            //habenzins
            zinsen = ((habenzins / 100) * kontostand) / 365 * tageZwischen;
        } else {
            //sollZins
            zinsen = ((sollzins / 100 ) * kontostand) / 365  * tageZwischen;
        }
        return zinsen;
    }

    public void zinsenBerechnungenQuartal() {
        //berechneZinsen wieder hier um auf -4,44 zu kommen, zinssumme ist hier -4,15
        //letzte zinsen -0.29 + zinssumme -4,15
        //letzte zinsen + die letzte berechnete zinsumme
        zinssumme += zinsen;

        kontostand += zinssumme;

        System.out.println("Zinssumme für das Quartal: " + runden(zinssumme) + " Euro");
        System.out.println("Neuer Kontostand nach Quartal: " + runden(kontostand) + " Euro");

        // Reset zinssumme für den nächsten Quartalsabschluss
        zinssumme = 0;

    }

    private void betragBuchen(LocalDate alteDatum, double betrag, String grund) {
        switch (grund) {
            case "Einzahlung" -> einzahlen(betrag, alteDatum, grund);
            case "Auszahlung" -> abheben(betrag, alteDatum, grund);
            case "Quartal" -> zinsenBerechnungenQuartal();
            default -> eroeffnen(betrag, alteDatum, grund);
        }
    }

    public double runden(double zahl){
        BigDecimal bigDecimal = new BigDecimal(Double.toString(zahl));//Die Methode Double.toString() konvertiert die Zahl in eine String-Darstellung, um den genauen Wert in BigDecimal zu erhalten. Grund: Der double-Typ hat bei sehr kleinen oder sehr großen Zahlen manchmal Probleme mit der genauen Darstellung
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);//Rundet den Wert auf 2 Dezimalstellen.Z.B: von 0.125 auf 0.13).
        return bigDecimal.doubleValue();//Konvertiert das gerundete bigDecimal zurück in einen double
    }


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