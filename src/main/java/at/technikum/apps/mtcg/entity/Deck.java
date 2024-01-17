package at.technikum.apps.mtcg.entity;

public class Deck {
    private String card_id;
    private String user_id;
    private String deck_id;

    public Deck(String card_id, String user_id, String deck_id) {
        this.card_id = card_id;
        this.user_id = user_id;
        this.deck_id = deck_id;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
