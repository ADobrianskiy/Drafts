package ua.drafts;

import java.util.List;

/**
 * Created by adobrianskiy on 22.09.15.
 */
public interface Client {
    public List<Move> getMove(Board board);
}
