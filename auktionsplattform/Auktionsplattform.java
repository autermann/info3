package auktionsplattform;

public class Auktionsplattform {
    public static void main(String[] args) {
        Kategorie cat1 = new Kategorie("cat1");
        Kategorie cat2 = new Kategorie("cat2", cat1);
        Kategorie cat3 = new Kategorie("cat3", cat2);
        Kategorie cat4 = new Kategorie("cat4", cat1);
        Kategorie cat5 = new Kategorie("cat5", cat4);
        Artikel art1 = new Artikel("art1",3,cat1);
        Artikel art2 = new Artikel("art2",5.5,cat1);
        Artikel art3 = new Artikel("art3",1.2,cat2);
        Artikel art4 = new Artikel("art4",1.1,cat3);
        Artikel art5 = new Artikel("art5",1.3,cat1);
        System.out.println("Alle Artikel in einem gegebenen Element der Hierarchie inklusive aller ggf. vorhandenen Unterkategorien auflisten:");
        cat1.getEverything().forEach(System.out::println);
        System.out.println();
        System.out.println("Den (bzw. einen) Artikel mit minimalem Preis in einem gegebenen Element der Hierarchie inklusive aller ggf. vorhandenen Unterkategorien finden:");
        System.out.println(cat1.getNiedrigsterPreis());
    }
}
