import ua.adobrianskiy.DraftBot;
import ua.drafts.Board;
import ua.drafts.GameProcess;
import ua.drafts.PlayerType;

/**
 * Created by adobrianskiy on 22.09.15.
 */
public class Main {
    public static void main (String[] args){
        Board board = new Board();
        DraftBot df = new DraftBot(PlayerType.BLACK_PLAYER);
        df.getMove(board);

        //System.out.println(board);
        //GameProcess gm = new GameProcess(white, black);
    }
}
