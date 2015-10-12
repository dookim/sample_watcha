package data;

/**
 * 하나의 카드객체들 gson으로 읽어와 세팅된다.
 * Created by dookim on 10/8/15.
 */
public class Card {

    public enum Type {
        RATING(0), CONTENT(1);

        private final int typeVal;

        Type(int typeVal) {
            this.typeVal = typeVal;
        }

        public int getTypeVal() {
            return typeVal;
        }
    }

    public static final String RATING = Type.RATING.name();
    public static final String CONTENT = Type.CONTENT.name();

    public String type;
    public Movie movie;
    public int typeVal;


    Card(Card card) {
        this.type = card.type;
        this.movie = card.movie.clone();
        this.typeVal = type.equals(Type.CONTENT.name()) ? Type.CONTENT.getTypeVal() : Type.RATING.getTypeVal();

    }

    public int getViewType() {
        return typeVal;
    }

}
