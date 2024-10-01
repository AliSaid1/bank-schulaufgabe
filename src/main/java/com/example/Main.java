package com.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //Pauls Daten
        LocalDate paulKundeSeit = LocalDate.of(2023, 2, 10);
        LocalDate paulGiroErf = LocalDate.of(2023, 2, 10);

        //Lieses Daten
        LocalDate lieseKundeSeit = LocalDate.of(2020, 5, 2);
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
        g1.betragBuchen(LocalDate.of(2023, 2, 10), 1, "Ersteinzahlung");

        g1.betragBuchen(LocalDate.of(2023, 2, 11), 8500, "Auszahlung");
        g1.zinsBuchung(LocalDate.of(2023, 4, 1), "Quartal");

        //Liese
        s1.betragBuchen(LocalDate.of(2023, 1, 2), 1, "Ersteinzahlung");
        s1.zinsBuchung(LocalDate.of(2023, 2, 11), "Ersteinzahlung");
        s1.betragBuchen(LocalDate.of(2023, 2, 11), 2850, "Einzahlung");

        s1.zinsBuchung(LocalDate.of(2024, 1, 1), "jährlich");//Jahresabschluss

        g2.betragBuchen(LocalDate.of(2023, 1, 2), 1, "Ersteinzahlung");
        g2.zinsBuchung(LocalDate.of(2023, 2, 11), "Ersteinzahlung");

        g2.betragBuchen(LocalDate.of(2023, 2, 11), 100.50, "Auszahlung");
        g2.zinsBuchung(LocalDate.of(2023, 2, 11), "Auszahlung");

        g2.betragBuchen(LocalDate.of(2023, 2, 11), 50.00, "Auszahlung");
        g2.zinsBuchung(LocalDate.of(2023, 4, 1), "Auszahlung");

        g2.zinsBuchung(LocalDate.of(2023, 4, 1), "Quartal");//Quartalsabschluss

        bank.printKontoInfo(paul);
        bank.printKontoInfo(liese);

        bank.printKontoauszug(paul);
        bank.printKontoauszug(liese);


    }
}
