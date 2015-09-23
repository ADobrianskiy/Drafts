package ua.adobrianskiy;

import ua.drafts.*;

import java.util.*;

/**
 * Created by adobrianskiy on 23.09.15.
 */
public class DraftBot implements Client {
    private PlayerType player;
    private static int deep = 3;
    @Override
    public List<Move> getMove(Board board) {
        Node tree = getTree(board);

        for (Node node : tree.getChildren())
        {
            System.out.println(node.getBoard());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
            for (Node n : node.getChildren())
            {
                System.out.println(n.getBoard());
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^");
            }
        }
        System.out.print(tree.getChildren().size());
        return null;
    }

    public DraftBot(PlayerType player) {
        this.player = player;
    }

    private Node getTree(Board board) {
        Node root = new Node(board, null);
        //ArrayList<Node> rootChildren = getChildren(root, player);
        //root.setChildren(rootChildren);

        ArrayList<Node> nodesToDeep = new ArrayList<Node>();
        nodesToDeep.add(root);

        for(int i = 0; i <= deep; i++){
            ArrayList<Node> nextNodesToDeep = new ArrayList<Node>();
            for(Node node: nodesToDeep){
                PlayerType player = this.player;
                if(i % 2 == 0) {
                    if(player == PlayerType.BLACK_PLAYER){
                        player = PlayerType.WHITE_PLAYER;
                    } else  if(player == PlayerType.WHITE_PLAYER){
                        player = PlayerType.BLACK_PLAYER;
                    }
                }
                ArrayList<Node> children = getChildren(node, player);
                node.setChildren(children);
                nextNodesToDeep.addAll(children);
            }
            nodesToDeep = nextNodesToDeep;
        }
        return root;
    }
    private ArrayList<Node> getChildren(Node node, PlayerType player) {
        ArrayList<Node> children = getAllHitsForNode(node);
        if(children.isEmpty()){
            children = getAllMovesForNode(node, player);
        }

        return children;
    }

    private ArrayList<Node> getAllHitsForNode(Node node) {
        ArrayList<Node> childen = new ArrayList<Node>();
        /*
        * Body of the method
        * */
        return childen;
    }

    private Node getNodeForVectorDirection(PlayerType player, Board board, Node node,
                                           Pair position, Pair direction){

        if(position.first + direction.first < 0 || position.first + direction.first >= board.getSize()){
            return null;
        }

        if(position.second + direction.second < 0 || position.second + direction.second >= board.getSize()){
            return null;
        }


        Pair to = new Pair(position.first + direction.first , position.second + direction.second);

        if(board.getElement(to.first, to.second) != GameElement.NONE){
            return null;
        }

        Board childBoard = new Board(board);
        Pair from = position;
        List<Move> moves = new ArrayList<Move>();
        moves.add(new Move(player, from, to));
        childBoard.move(moves);
        Node child = new Node(childBoard, node);


        return child;
    }


    private ArrayList<Node> getAllMovesForNode(Node node, PlayerType player) {
        ArrayList<Node> children = new ArrayList<Node>();
        Board board = node.getBoard();
        int size = board.getSize();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                GameElement currentElement = board.getElement(i,j);
                if(currentElement != GameElement.NONE){
                    if(player == PlayerType.WHITE_PLAYER) {
                        children.addAll(getMovesForWhite(player, board, node, new Pair(i, j)));
                    } else if(player == PlayerType.BLACK_PLAYER){
                        children.addAll(getMovesForBlack(player, board, node, new Pair(i, j)));
                    }
                }
            }
        }


        return children;
    }

    private ArrayList<Node> getMovesForBlack(PlayerType player, Board board, Node node, Pair position) {
        ArrayList<Node> children = new ArrayList<Node>();

        int i = position.first;
        int j = position.second;
        GameElement currentElement = board.getElement(i,j);
        if(currentElement == GameElement.BLACK){

            Node child_1 = getNodeForVectorDirection(player, board, node,new Pair(i,j), new Pair(1, 1));
            if(child_1 != null) {
                children.add(child_1);
            }

            Node child_2 = getNodeForVectorDirection(player, board, node,new Pair(i,j), new Pair(-1, 1));
            if(child_2 != null) {
                children.add(child_2);
            }
        } else if(currentElement == GameElement.BLACK_EXTENDED){
            ArrayList<Node> extended_moves = getExtendedMoves(player, board, node,new Pair(i,j));
            if(!extended_moves.isEmpty()){
                children.addAll(extended_moves);
            }
        }

        return children;
    }

    private ArrayList<Node> getMovesForWhite(PlayerType player, Board board, Node node, Pair position) {
        ArrayList<Node> children = new ArrayList<Node>();

        int i = position.first;
        int j = position.second;
        GameElement currentElement = board.getElement(i,j);
        if(currentElement == GameElement.WHITE){

            Node child_1 = getNodeForVectorDirection(player, board, node,new Pair(i,j), new Pair(-1, -1));
            if(child_1 != null) {
                children.add(child_1);
            }

            Node child_2 = getNodeForVectorDirection(player, board, node,new Pair(i,j), new Pair(1, -1));
            if(child_2 != null) {
                children.add(child_2);
            }
        } else if(currentElement == GameElement.WHITE_EXTENDED){
            ArrayList<Node> extended_moves = getExtendedMoves(player, board, node,new Pair(i,j));
            if(!extended_moves.isEmpty()){
                children.addAll(extended_moves);
            }
        }

        return children;
    }

    private ArrayList<Node> getExtendedMoves(PlayerType player, Board board, Node node, Pair position) {
        ArrayList<Node> res = new ArrayList<Node>();

        ArrayList<Node> direction_1 = getExtendedMovesForDirection(player, board, node,position,new Pair(1,1));
        if(!direction_1.isEmpty()){
            res.addAll(direction_1);
        }

        ArrayList<Node> direction_2 = getExtendedMovesForDirection(player, board, node,position,new Pair(1,-1));
        if(!direction_2.isEmpty()){
            res.addAll(direction_2);
        }

        ArrayList<Node> direction_3 = getExtendedMovesForDirection(player, board, node,position,new Pair(-1,1));
        if(!direction_3.isEmpty()){
            res.addAll(direction_3);
        }

        ArrayList<Node> direction_4 = getExtendedMovesForDirection(player, board, node,
                position,new Pair(-1,-1));
        if(!direction_4.isEmpty()){
            res.addAll(direction_4);
        }

        return res;
    }

    private ArrayList<Node> getExtendedMovesForDirection(PlayerType player, Board board,
                                                         Node node, Pair position, Pair pair) {
        ArrayList<Node> res = new ArrayList<Node>();
        int multiplier = 1;
        Node child;
        do{
            child = getNodeForVectorDirection(player, board, node,new Pair(position.first, position.second),
                    new Pair(pair.first * multiplier, pair.second * multiplier));
            multiplier++;
            if (child != null) {
                res.add(child);
            }
        } while(child != null);
        return res;
    }
}
