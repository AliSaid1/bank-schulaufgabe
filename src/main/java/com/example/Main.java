package com.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate paulKundeSeit = LocalDate.of(2023, 2, 10);
        LocalDate paulKontoErf = LocalDate.of(2023, 2, 10);
        LocalDate paulsTransaktion = LocalDate.of(2023, 2, 11);

        LocalDate lieseKundeSeit = LocalDate.of(2020, 5, 2);
        LocalDate liesesTransaktion = LocalDate.of(2023, 2, 11);
        LocalDate lieseG2Datum = LocalDate.of(2023, 1, 2);
        LocalDate lieseS1Datum = LocalDate.of(2023, 1, 2);


        Kunde paul = new Kunde("Paul Müller", "Münster", paulKundeSeit);
        Kunde liese = new Kunde("Liese Fleißig", "Osnabrück", lieseKundeSeit);

        Girokonto g1 = new Girokonto("50060080", 0.0, 1.5, paulKontoErf, 7.5, 3000);
        Girokonto g2 = new Girokonto("50060090", 0.0, 1.5, lieseG2Datum,7.5, 2000);
        Sparkonto s1 = new Sparkonto("22222222",0.0,5.0, lieseS1Datum, 'p');


        Bank bank = new Bank(new Kunde[]{paul,liese}, new Konto[]{g1,g2,s1});

        bank.addKontoToKunde(paul,g1);
        bank.addKontoToKunde(liese,s1);
        bank.addKontoToKunde(liese,g2);

        g1.eroeffnen(1, paulKontoErf);
        g2.eroeffnen(1, lieseG2Datum);
        s1.eroeffnen(1, lieseS1Datum);

        s1.einzahlen(2850, liesesTransaktion);
        s1.berechneZinsen(LocalDate.of(2024, 1, 1));

        g2.abheben(100.50, liesesTransaktion);
        g2.abheben(50.00, liesesTransaktion);
        g2.berechneZinsen1(LocalDate.of(2023,4,1),LocalDate.of(2023, 1, 2));


        bank.printKontoInfo1(paul);
        bank.printKontoInfo1(liese);

    }
}
//        Kontobewegung kb1 = new Kontobewegung(-8500.0, paulsTransaktion, g1);