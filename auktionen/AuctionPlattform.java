package auktionen;

public class AuctionPlattform {

    public static void main(String[] args) {
        Article a1 = new Article("a1", 6.00);
        Article a2 = new Article("a2", 3.00);
        Article a3 = new Article("a3", 5.00);
        Article a4 = new Article("a4", 2.00);
        Article a5 = new Article("a5", 7.00);
        Article a6 = new Article("a6", 1.00);

        Category c1 = new Category("c1");
        Category c2 = new Category("c2");
        Category c3 = new Category("c3");

        c1.addElement(c2);
        c1.addElement(a1);
        c1.addElement(a2);
        c1.addElement(a3);

        c2.addElement(a4);
        c2.addElement(a5);

        c3.addElement(a6);

        Visitor v = new PrintHierarchyVisitor();
        c1.accept(v);
    }
}
