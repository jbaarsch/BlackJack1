package GameAbstractions;

public interface Card {

    Suit getSuit();
    void setSuit(Suit suit);

    Integer getValue();
    void setValue(Integer value);

    Orientation getOrientation();
    void setOrientation(Orientation orientation);

}
