package auktionen;

import java.util.ArrayList;
import java.util.List;

public class PrintHierarchyVisitor implements Visitor {
    @Override
    public void visit(Article auction) {

    }

    @Override
    public void visit(Category category) {
        List<AuctionElement> list = new ArrayList<>(category.getSubElements());
        list.sort(new AuctionElementComparator());
        list.forEach(System.out::println);
    }

    @Override
    public void apply() {

    }
}