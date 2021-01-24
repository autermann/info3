package auktionsplattform;

import java.util.*;
import java.util.stream.Collectors;

public class Kategorie {
    private String name;
    private Kategorie oberKategorie;
    private Set<Kategorie> unterKategorien;
    private Set<Artikel> artikelListe;

    public Kategorie(String name) {
        this.name = name;
        unterKategorien = new HashSet<>();
        artikelListe = new HashSet<>();
    }

    public Kategorie(String name, Kategorie oberKategorie) {
        this.name = name;
        this.oberKategorie = oberKategorie;
        unterKategorien = new HashSet<>();
        artikelListe = new HashSet<>();
        oberKategorie.addUnterKategorie(this);
    }

    public String getName() { return name; }

    public void addUnterKategorie(Kategorie unterKategorie) {
        unterKategorien.add(unterKategorie);
    }

    public void addArtikel(Artikel artikel) { artikelListe.add(artikel); }

    public Set<Artikel> getArtikel() {
        if(this.artikelListe == null && this.unterKategorien == null) {
            return Collections.emptySet();
        }
        Set<Artikel> set = new HashSet<>();
        if(this.artikelListe != null) {
            set.addAll(this.artikelListe);
        }
        if(this.unterKategorien != null) {
            for (Kategorie kategorie : this.unterKategorien) {
                set.addAll(kategorie.getArtikel());
            }
        }
        return set;
    }

    public List<Artikel> getAllArtikel() {
        return this.getArtikel().stream().sorted().collect(Collectors.toList());
    }

    public String getNiedrigsterPreis() {
        Artikel artikel = this.getAllArtikel().get(0);
        return String.format("Kategorie: %s%n%s : Artikel: %s, Preis: %f", this.getName(), artikel.getKategorie(), artikel.getName(), artikel.getPreis());
    }

    public List<String> getEverything() {
        List<String> list = new ArrayList<>();
        list.add(String.format("Kategorie %s:", getName()));
        list.addAll(this.unterKategorien.stream().map(Kategorie::toString).sorted().collect(Collectors.toList()));
        list.addAll(this.artikelListe.stream().map(Artikel::toString).sorted().collect(Collectors.toList()));
        return list;
    }

    @Override
    public String toString() {
        return String.format("|-- %s", getName());
    }
}
