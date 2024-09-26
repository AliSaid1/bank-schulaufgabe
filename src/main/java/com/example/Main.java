package com.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Pauls Daten
        LocalDate paulKundeSeit = LocalDate.of(2023, 2, 10);
        LocalDate paulGiroErf = LocalDate.of(2023, 2, 10);
        LocalDate paulsTransaktion = LocalDate.of(2023, 2, 11);
        //Lieses Daten
        LocalDate lieseKundeSeit = LocalDate.of(2020, 5, 2);
        LocalDate liesesTransaktion = LocalDate.of(2023, 2, 11);
        LocalDate lieseGiroErf = LocalDate.of(2023, 1, 2);
        LocalDate lieseSparErf = LocalDate.of(2023, 1, 2);

        Kunde paul = new Kunde("Paul Müller", "Münster", paulKundeSeit);
        Kunde liese = new Kunde("Liese Fleißig", "Osnabrück", lieseKundeSeit);

        Girokonto g1 = new Girokonto("50060080", 0.0, 1.5, paulGiroErf, 7.5, 3000);
        Girokonto g2 = new Girokonto("50060090", 0.0, 1.5, lieseGiroErf,7.5, 2000);
        Sparkonto s1 = new Sparkonto("22222222",0.0,5.0, lieseSparErf, 'p');

        //Bank verwaltet alle Konten und Kunden
        Bank bank = new Bank(new Kunde[]{paul,liese}, new Konto[]{g1,g2,s1});
        //weise ein Konto zu einem Kunden zu.
        bank.addKontoToKunde(paul,g1);
        bank.addKontoToKunde(liese,s1);
        bank.addKontoToKunde(liese,g2);

        g1.eroeffnen(0, paulGiroErf, "Ersteinzahlung");
//        g2.eroeffnen(1, lieseGiroErf, "Ersteinzahlung");
//        s1.eroeffnen(1, lieseSparErf, "Ersteinzahlung");

        g1.buchen(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), 200, "Einzahlung");

        g1.buchen(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 28), 500, "Einzahlung");

        g1.buchen(LocalDate.of(2023, 1, 28), LocalDate.of(2023, 2, 2), 300,"Einzahlung");

        g1.buchen(LocalDate.of(2023, 2, 2), LocalDate.of(2023, 2, 12), 1600, "Auszahlung");

        g1.buchen(LocalDate.of(2023, 2, 12), LocalDate.of(2023, 3, 24), 400, "Einzahlung");

        g1.buchen(LocalDate.of(2023, 3, 24), LocalDate.of(2023, 3, 31), 0, "Quartal");

        System.out.println();

        //bank.printKontoauszug(paul);
        //bank.printKontoauszug(liese);

    }
}

/*
g1.abheben(8500, paulsTransaktion, "Auszahlung");

        g2.abheben(100.50, liesesTransaktion, "Auszahlung");
        g2.abheben(50.00, liesesTransaktion, "Auszahlung");
        s1.einzahlen(2850, liesesTransaktion , "Einzahlung");

        g2.buchen(liesesTransaktion, liesesTransaktion, -100.50);
        g2.buchen(liesesTransaktion, liesesTransaktion, -50.00);

 */