package auktionen;

import java.util.Comparator;

public class Article implements AuctionElement, Comparable<Article> {
    private String name;
    private double price;

    public Article(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() { return name; }
    @Override
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "Article{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Article o) {
        return Comparator.comparing(Article::getPrice).compare(this, o);
    }
}
