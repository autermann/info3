package auktionen;

import java.util.Comparator;

public class AuctionElementComparator implements Comparator<AuctionElement> {
    private static final AuctionElementComparator INSTANCE = new AuctionElementComparator();

    public static final AuctionElementComparator getInstance() {
        return INSTANCE;
    }

    protected AuctionElementComparator() {}

    @Override
    public int compare(AuctionElement a1, AuctionElement a2) {
        if(a1 instanceof Article) {
            if(a2 instanceof Article) {
                return ((Article) a1).compareTo((Article) a2);
            } else return 1;
        } else if (a2 instanceof Category) {
            return ((Category) a1).compareTo((Category)a2);
        } else return -1;
    }
}
