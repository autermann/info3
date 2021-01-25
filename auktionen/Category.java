package auktionen;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Category implements AuctionElement, Comparable<Category> {
    private String name;
    private Set<AuctionElement> subElements;

    public Category(String name) {
        this.name = name;
        this.subElements = new HashSet<>();
    }

    @Override
    public String getName() { return name; }
    @Override
    public void setName(String name) { this.name = name; }
    public Set<AuctionElement> getSubElements() { return subElements; }
    public void setSubElements(Set<AuctionElement> subElements) { this.subElements = subElements; }
    public void addElement(AuctionElement element) { subElements.add(element); }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Category o) {
        return Comparator.comparing(Category::getName).compare(this, o);
    }
}
