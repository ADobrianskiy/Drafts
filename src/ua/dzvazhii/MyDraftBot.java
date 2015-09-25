package ua.dzvazhii;

import ua.drafts.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dima on 23.09.2015.
 */
public class MyDraftBot implements Client {

    private GameElement myGameElement;

    private GameElement myExtendedGameElement;

    private PlayerType myColor;

    public MyDraftBot(PlayerType playerType){
        myColor = playerType;

        if(myColor == PlayerType.BLACK_PLAYER){
            myGameElement = GameElement.BLACK;
            myExtendedGameElement = GameElement.BLACK_EXTENDED;
        }else {
            myGameElement = GameElement.WHITE;
            myExtendedGameElement = GameElement.WHITE_EXTENDED;
        }
    }

    ArrayList<Move> resultMoves = new ArrayList<Move>();

    private Move[] getAllMoves(PlayerType type, Board board){
        return null;
    }

    @Override
    public List<Move> getMove(Board board) {
        return resultMoves;
    }


}
