package auktionen;

public interface Visitor {
    void visit(Category category);
    void visit(Article auction);
}
