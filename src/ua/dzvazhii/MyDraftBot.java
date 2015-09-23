package ua.dzvazhii;

import ua.drafts.Board;
import ua.drafts.Client;
import ua.drafts.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 23.09.2015.
 */
public class MyDraftBot implements Client {

    ArrayList<Move> resultMoves = new ArrayList<Move>();

    @Override
    public List<Move> getMove(Board board) {
        return resultMoves;
    }


}
