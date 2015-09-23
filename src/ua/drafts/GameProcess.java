package ua.drafts;

import static ua.drafts.GameElement.BLACK;
import static ua.drafts.GameElement.WHITE;

/**
 * Created by adobrianskiy on 22.09.15.
 */
public class GameProcess {

    private Board board;
    private Client white;
    private Client black;

    private GameState gameState;

    public GameProcess(Client white, Client black){
        board = new Board();
        this.white = white;
        this.black = black;
        startGame();
    }

    private void startGame() {
//        while(getState(board) != GameState.PLAYING){
        while (!isFinished()){
            Move white_turn = white.getTurn(board);
            board.makeTurn(white_turn);
            if(getState(board) != GameState.PLAYING){
                return;
            }
            Move black_turn = black.getTurn(board);
            board.makeTurn(black_turn);
        }
    }

    private GameState getState(Board board) {
        boolean possible_white = false;
        boolean possible_black = false;

        GameState state;
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                GameElement e = board.getElement(i, j);
                switch (e){
                    case WHITE:
                        if(checkWhite(i, j)){
                            possible_white = true;
                        }
                        break;
                    case BLACK:
                        if(checkBlack(i, j)){
                            possible_black = true;
                        }
                        break;
                    case WHITE_EXTENDED:
                        if(checkWhiteExtended(i, j)){
                            possible_white = true;
                        }
                        break;
                    case BLACK_EXTENDED:
                        if(checkBlackExtended(i, j)){
                            possible_black = true;
                        }
                        break;
                }
            }
        }
         /*
        * STATE ????
        * */
        return null;
    }


    private boolean isFinished(){

        int black = 0;

        int white = 0;

        for (int i=0;i<board.getBOARD_SIZE()-1;i++){

            for(int j=0;j<board.getBOARD_SIZE()-1;j++){
                if(board.getElement(i,j) == GameElement.BLACK || board.getElement(i,j) == GameElement.BLACK_EXTENDED){
                    black+=1;
                }else if(board.getElement(i,j) == GameElement.WHITE || board.getElement(i,j) == GameElement.WHITE_EXTENDED){
                    white+=1;
                }
            }
        }

        if(black == 0){
            gameState = GameState.WHITE_WON;
            return true;
        }else if(white ==0){
            gameState = GameState.BLACK_WON;
            return true;
        }

        return false;
    }

    private boolean checkBlackExtended(int i, int j) {
        if(board.getElement(i,j) != GameElement.BLACK_EXTENDED){
            return false;
        }
        /*
        * Method body here
        * */
        return false;
    }

    private boolean checkWhiteExtended(int i, int j) {
        if(board.getElement(i,j) != GameElement.WHITE_EXTENDED){
            return false;
        }
         /*
        * Method body here
        * */
        return false;
    }

    private boolean checkBlack(int i, int j) {
        if(board.getElement(i,j) != GameElement.BLACK){
            return false;
        }
         /*
        * Method body here
        * */
        return false;
    }

    private boolean checkWhite(int i, int j) {
        if(board.getElement(i,j) != GameElement.WHITE){
            return false;
        }
         /*
        * Method body here
        * */
        return false;
    }
}
