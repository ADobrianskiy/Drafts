package ua.drafts;

/**
 * Created by adobrianskiy on 22.09.15.
 */
public class Board {
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
        for(int i = 0; i < 8; i++){
            if(i%2 == 0){
                elements[i][1] = GameElement.BLACK;
            } else {
                elements[i][0] = GameElement.BLACK;
                elements[i][2] = GameElement.BLACK;
            }
        }
    }

    private void initWhite(){
        for(int i = 0; i < 8; i++){
            if(i%2 != 0){
                elements[i][6] = GameElement.WHITE;
            } else {
                elements[i][7] = GameElement.WHITE;
                elements[i][5] = GameElement.WHITE;
            }
        }
    }

    public void makeTurn(Move[] moves) throws Exception {
        for(Move move : moves){
            Pair from = move.getFrom();
            Pair to = move.getTo();

            if (elements[from.first][from.second] == GameElement.NONE ||
                    elements[to.first][to.second] != GameElement.NONE) {
                throw new Exception("BAD MOVE:" + move + "\nfor board\n" + toString());
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
}
