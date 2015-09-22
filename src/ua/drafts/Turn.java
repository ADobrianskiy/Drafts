package ua.drafts;

import jdk.internal.util.xml.impl.Pair;

/**
 * Created by adobrianskiy on 22.09.15.
 */
public class Turn {
    private PlayerType player;
    private Pair from;
    private Pair to;

    public Turn(PlayerType player, Pair from, Pair to) {
        this.player = player;
        this.from = from;
        this.to = to;
    }
}
