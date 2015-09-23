package ua.drafts;


/**
 * Created by adobrianskiy on 22.09.15.
 */
public class Move {
    private PlayerType player;
    private Pair from;
    private Pair to;

    public Move(PlayerType player, Pair from, Pair to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }
}
