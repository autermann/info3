package auktionen;

import java.util.ArrayList;
import java.util.List;

public class PrintHierarchyVisitor implements Visitor {
    
    @Override
    public void visit(Category category) {
         category.getSubElements()
            .stream()
            .sorted(AuctionElementComparator.getInstance())
            .forEach(System.out::println);
    }
}