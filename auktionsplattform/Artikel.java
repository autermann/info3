package auktionsplattform;

import java.util.Comparator;

public class Artikel implements Comparable<Artikel> {
    private String name;
    private double preis;
    private Kategorie kategorie;

    public Artikel(String name, double preis, Kategorie kategorie) {
        this.name = name;
        this.preis = preis;
        this.kategorie = kategorie;
        kategorie.addArtikel(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public double getPreis() {
        return this.preis;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public Kategorie getKategorie() {
        return this.kategorie;
    }

    @Override
    public String toString() {
        return String.format("| Artikel: %s, Preis: %f", getName(), getPreis());
    }

    @Override
    public int compareTo(Artikel o) {
        return Comparator.comparing(Artikel::getPreis).compare(this, o);
    }
}
