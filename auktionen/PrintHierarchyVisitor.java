package auktionen;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrintHierarchyVisitor implements Visitor {
    @Override
    public void visit(Article auction) {

    }

    @Override
    public void visit(Category category) {
        List<AuctionElement> list = new ArrayList<>(category.getSubElements());
        list.sort(new AuctionElementComparator());
        List<String> list2 = list.stream().map(AuctionElement::toString).sorted().collect(Collectors.toList());
        list2.forEach(System.out::println);
    }
}