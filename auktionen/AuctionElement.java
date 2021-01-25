package auktionen;

public interface AuctionElement {
    void accept(Visitor v);
    String getName();
    void setName(String name);
}
