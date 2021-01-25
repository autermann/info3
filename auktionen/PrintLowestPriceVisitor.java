package auktionen;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrintLowestPriceVisitor implements Visitor {
    private Article lowest;

    @Override
    public void visit(Article article) {
        if (this.lowest == null || this.lowest.compareTo(article) > 0) {
            this.lowest = article;
        }
    }

    @Override
    public void visit(Category category) {
        category.getSubElements().forEach(e -> e.accept(this));
    }

    public Optional<Article> getLowest() {
        return Optional.ofNullable(this.lowest);
    }
}
