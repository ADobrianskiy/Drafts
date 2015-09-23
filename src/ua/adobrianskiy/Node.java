package ua.adobrianskiy;

import ua.drafts.Board;
import ua.drafts.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adobrianskiy on 23.09.15.
 */
class Node
{
    private Node parent;
    private Board board;
    private List<Move> moves = new ArrayList<Move>();
    private List<Node> children = new ArrayList<Node>();

    public Node(Board board, Node parent) {
        this.board = board;
        this.parent = parent;
    }

    public Board getBoard() {
        return board;
    }

//    public void setBoard(Board board) {
//        this.board = board;
//    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}