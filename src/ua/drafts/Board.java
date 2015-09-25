package ua.drafts;

/**
 * Created by adobrianskiy on 22.09.15.
 */
public class Board {

    private final int BOARD_SIZE = 8;

    public GameElement[][] elements;
    public Board(){
        elements = new GameElement[8][8];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                elements[i][j] = GameElement.NONE;
            }
        }
        initBlack();
        initWhite();
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
    }

    public GameElement getElement(int i, int j) {
        if(i < 0 || i > 7 || j < 0 || j > 7){
            return null;
        }
        return elements[i][j];
    }

    public String toString() {
        String res = "";
        for(int i = 0; i < elements.length; i++){
            for(int j = elements.length - 1; j >= 0; j--){
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
}
