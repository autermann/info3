package auktionen;

import java.util.ArrayList;
import java.util.List;

public class PrintLowestPriceVisitor implements Visitor {
    private List<Article> list;

    public PrintLowestPriceVisitor() {
        list = new ArrayList<>();
    }

    @Override
    public void visit(Article auction) {

    }

    @Override
    public void visit(Category category) {
        for(AuctionElement e : category.getSubElements()) {
            if(e instanceof Category) {
                e.accept(this);
            } else {
                list.add((Article) e);
            }
        }
    }

    @Override
    public void apply() {
        list.sort(Article::compareTo);
        System.out.println(list.get(0));
    }
}
