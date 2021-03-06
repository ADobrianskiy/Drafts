package ua.drafts;

import java.util.List;

/**
 * Created by adobrianskiy on 22.09.15.
 */

public class Board {
    private GameElement[][] elements;
    private int size = 8;
    private final int BOARD_SIZE = 8;
    public Board(){
        elements = new GameElement[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                elements[i][j] = GameElement.NONE;
            }
        }
        initBlack();
        initWhite();
    }

    public Board(Board board) {
        elements = new GameElement[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                elements[i][j] = board.getElement(i, j);
            }
        }
    }

    private void initBlack(){
        int i = 1;

        for(int j = 0; i < 8; j+=2){
            elements[i][j] = GameElement.BLACK;
            elements[i-1][j+1] = GameElement.BLACK;
            elements[i+1][j+1] = GameElement.BLACK;

        }
    }

    private void initWhite(){
        int i = 6;

        for(int j = 7; j > 0; j-=2){
            elements[i][j] = GameElement.WHITE;
            elements[i-1][j-1] = GameElement.WHITE;
            elements[i+1][j-1] = GameElement.WHITE;
        }
    }

    public void makeTurn(Move[] black_turn) {
        /*
        Code for make Turn method
         */
        for(int i = 0; i < size; i++){
            if(i%2 != 0){
                elements[i][size - 2] = GameElement.WHITE;
            } else {
                elements[i][size - 1] = GameElement.WHITE;
                elements[i][size - 3] = GameElement.WHITE;
            }
        }
    }

    public void move(List<Move> moves){
        for(Move move : moves){
            Pair from = move.getFrom();
            Pair to = move.getTo();

            if (elements[from.first][from.second] == GameElement.NONE ||
                    elements[to.first][to.second] != GameElement.NONE) {
                throw new RuntimeException ("BAD MOVE:" + move + "\nfor board\n" + toString());
            }

            GameElement e = elements[from.first][from.second];
            elements[from.first][from.second] = GameElement.NONE;
            elements[to.first][to.second] = e;
        }
    }

    public GameElement getElement(int i, int j) {
        if(i < 0 || i > 7 || j < 0 || j > 7){
            return null;
        }
        return elements[i][j];
    }

    public String toString() {
        String res = "";
        for(int i = 0; i < size; i++){
            for(int j = size - 1; j >= 0; j--){
                GameElement e = getElement(i, j);
                switch (e){
                    case WHITE:
                        res += "w";
                        break;
                    case BLACK:
                        res += "b";
                        break;
                    case WHITE_EXTENDED:
                        res += "W";
                        break;
                    case BLACK_EXTENDED:
                        res += "B";
                        break;
                    case NONE:
                        res += "_";
                        break;
                }
                res += " ";
            }
            res += "\n";
        }
        return res;
    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    public int getSize(){
        return size;
    }
}
