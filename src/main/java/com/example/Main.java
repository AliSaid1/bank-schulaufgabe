package com.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        Girokonto g2 = new Girokonto("50060090", 0.0, 1.5, lieseGiroErf, 7.5, 2000);
        Sparkonto s1 = new Sparkonto("22222222", 0.0, 5.0, lieseSparErf, 'p');

        //Bank verwaltet alle Konten und Kunden
        Bank bank = new Bank(new Kunde[]{paul, liese}, new Konto[]{g1, g2, s1});
        //weise ein Konto zu einem Kunden zu.
        bank.addKontoToKunde(paul, g1);
        bank.addKontoToKunde(liese, s1);
        bank.addKontoToKunde(liese, g2);

        //Paul
        g1.buchen(paulGiroErf, paulGiroErf, 8500, "Ersteinzahlung");

        //Liese


        s1.eroeffnen(1, lieseSparErf, "Ersteinzahlung");//0
        s1.einzahlen(2850, liesesTransaktion, "Einzahlung");//1
        System.out.println("Sparkontostand: " + s1.zinsenBerechnen(liesesTransaktion, LocalDate.of(2024,1,1)));

        g2.buchen(LocalDate.of(2023, 1, 2), LocalDate.of(2023, 2, 11), 1, "Ersteinzahlung");

        g2.buchen(LocalDate.of(2023, 2, 11), LocalDate.of(2023, 2, 11), 100.50, "Auszahlung");//2
        g2.buchen(LocalDate.of(2023, 2, 11), LocalDate.of(2023,4,1), 50, "Auszahlung");//3
        //4   01.Apr.2023        -1,51 Euro     Zinsen
        g2.buchen(LocalDate.of(2023, 2, 11), LocalDate.of(2023,4,1), 0, "Quartal");

        System.out.println();
        bank.printKontoauszug(liese);

    }
}

/*
g1.buchen(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 1), 200, "Einzahlung");

        g1.buchen(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 28), 500, "Einzahlung");

        g1.buchen(LocalDate.of(2023, 1, 28), LocalDate.of(2023, 2, 2), 300,"Einzahlung");

        g1.buchen(LocalDate.of(2023, 2, 2), LocalDate.of(2023, 2, 12), 1600, "Auszahlung");

        g1.buchen(LocalDate.of(2023, 2, 12), LocalDate.of(2023, 3, 24), 400, "Einzahlung");

        g1.buchen(LocalDate.of(2023, 3, 24), LocalDate.of(2023, 3, 31), 0, "Quartal");
 */