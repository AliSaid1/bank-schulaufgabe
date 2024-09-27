package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundingAndFormatting {

    public static double runden(double zahl){
        BigDecimal bigDecimal = new BigDecimal(Double.toString(zahl));//Die Methode Double.toString() konvertiert die Zahl in eine String-Darstellung, um den genauen Wert in BigDecimal zu erhalten. Grund: Der double-Typ hat bei sehr kleinen oder sehr großen Zahlen manchmal Probleme mit der genauen Darstellung
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);//Rundet den Wert auf 2 Dezimalstellen.Z.B: von 0.125 auf 0.13).
        return bigDecimal.doubleValue();//Konvertiert das gerundete bigDecimal zurück in einen double
    }
    public static String getFormat(double betrag) {
        return String.format("%.2f", betrag);
    }
}
