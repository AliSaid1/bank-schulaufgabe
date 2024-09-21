package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Kunde {
    protected String name;
    protected String adresse;
    protected LocalDate kundeSeit;
    protected List<Konto> konten;//ein Kunde besitzt eine Liste von Konten

    public Kunde(String name, String adresse, LocalDate kundeSeit) {
        this.name = name;
        this.adresse = adresse;
        this.kundeSeit = kundeSeit;
        this.konten = new ArrayList<>();
    }

}
