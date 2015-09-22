package ua.drafts;

/**
 * Created by adobrianskiy on 22.09.15.
 */
public class Board {
    public GameElement[][] elements;
    public Board(){
        elements = new GameElement[7][7];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                elements[i][j] = GameElement.NONE;
            }
        }
        initBlack();
        initWhite();
    }

    private void initBlack(){
        for(int i = 1; i < 7; i++){
            if(i%2 == 0){
                elements[i][1] = GameElement.BLACK;
            } else {
                elements[i][0] = GameElement.BLACK;
                elements[i][3] = GameElement.BLACK;
            }
        }
    }

    private void initWhite(){
        for(int i = 1; i < 7; i++){
            if(i%2 != 0){
                elements[i][6] = GameElement.WHITE;
            } else {
                elements[i][7] = GameElement.WHITE;
                elements[i][5] = GameElement.WHITE;
            }
        }
    }

    public void makeTurn(Turn black_turn) {
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
}
