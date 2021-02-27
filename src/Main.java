import pieces.GeneralPiece;
import utlity.GameGraphics;
import utlity.Move;
import utlity.Piece;
import utlity.Place;
import utlity.Setting;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Main.
 */
public class Main {
    /**
     * The Pieces.
     */
    private static ArrayList<Piece> pieces;
    /**
     * The constant board.
     */
    //private static Piece board[][];
    /**
     * The constant selectedPiece.
     */
    private static Piece selectedPiece;
    /**
     * The constant isItCheckOnWhite.
     */
    private static boolean isItCheckOnWhite;
    /**
     * The constant isItCheckOnBlack.
     */
    private static boolean isItCheckOnBlack;
    /**
     * The constant isGameOver.
     */
    private static boolean isGameOver;
    /**
     * The constant isWhiteTurn.
     */
    private static boolean isWhiteTurn;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main( String[] args ) {
        isWhiteTurn = true;
        isGameOver = false;
        isItCheckOnBlack = false;
        isItCheckOnWhite = false;
        Display display = new Display(Setting.WIDTH, Setting.HEIGHT, "Chess");
        pieces = GeneralPiece.initPieces();
        selectedPiece = null;
        display.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped( KeyEvent keyEvent ) {
                if (keyEvent.getKeyChar() == 'p') {
                    int h = 0;
                }
            }

            @Override
            public void keyPressed( KeyEvent keyEvent ) {

            }

            @Override
            public void keyReleased( KeyEvent keyEvent ) {

            }
        });
        display.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked( MouseEvent mouseEvent ) {
                if (isGameOver) {
                    return;
                }
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();
                int row = y / Setting.CELL_HEIGHT;
                int col = x / Setting.CELL_WIDTH;
                if (selectedPiece != null) {
                    if (tryToMove(row, col, selectedPiece, pieces)) {
                        selectedPiece = null;
                        isWhiteTurn = !isWhiteTurn;
                        return;
                    }
                }
                Piece piece = GeneralPiece.findPieceByPlace(pieces, new Place(row, col));
                if (piece != null && piece.getPlayerBlack() != isWhiteTurn) {
                    selectedPiece = piece;
                }
            }

            @Override
            public void mousePressed( MouseEvent mouseEvent ) {

            }

            @Override
            public void mouseReleased( MouseEvent mouseEvent ) {

            }

            @Override
            public void mouseEntered( MouseEvent mouseEvent ) {

            }

            @Override
            public void mouseExited( MouseEvent mouseEvent ) {

            }
        });
        display.run();
    }

    /**
     * Mini max double.
     *
     * @param depth  the depth
     * @param isMax  the is max
     * @param pieces the pieces
     *
     * @return the double
     */
    public static double miniMax( int depth, boolean isMax, ArrayList<Piece> pieces ) {
        if (depth == 0) {
            return GeneralPiece.evaluate(pieces, isMax);
        }
        ArrayList<Piece> copyList = GeneralPiece.copyList(pieces);
        if (isMax) {
            ArrayList<Piece> blackList = GeneralPiece.getColorPieces(copyList, true);
            double maxScore = Double.NEGATIVE_INFINITY;
            for (Piece piece : blackList) {
                Place oldPlace = piece.getPlace();
                ArrayList<Place> placesToMove = piece.moveSet(copyList);
                for (Place place : placesToMove) {
                    Piece pieceEaten = GeneralPiece.findPieceByPlace(copyList, place);
                    if (pieceEaten != null) {
                        copyList.remove(pieceEaten);
                    }
                    double score = miniMax(depth - 1, false, copyList);
                    if (score > maxScore) {
                        maxScore = score;
                    }
                    piece.move(oldPlace);
                    if (pieceEaten != null) {
                        copyList.add(pieceEaten);
                    }
                }
            }
            return maxScore;
        }
        double miniScore = Double.POSITIVE_INFINITY;
        ArrayList<Piece> whiteList = GeneralPiece.getColorPieces(copyList, false);
        for (Piece piece : whiteList) {
            Place oldPlace = piece.getPlace();
            ArrayList<Place> placesToMove = piece.moveSet(copyList);
            for (Place place : placesToMove) {
                Piece pieceEaten = GeneralPiece.findPieceByPlace(copyList, place);
                if (pieceEaten != null) {
                    copyList.remove(pieceEaten);
                }
                piece.move(place);
                double score = miniMax(depth - 1, true, copyList);
                if (score < miniScore) {
                    miniScore = score;
                }
                piece.move(oldPlace);

                if (pieceEaten != null) {
                    copyList.add(pieceEaten);
                }
            }
        }
        return miniScore;
    }

    /**
     * Try to move boolean.
     *
     * @param row           the row
     * @param col           the col
     * @param selectedPiece the selected piece
     * @param pieces        the pieces
     *
     * @return the boolean
     */
    public static boolean tryToMove( int row, int col, Piece selectedPiece, ArrayList<Piece> pieces ) {
        ArrayList<Place> places = selectedPiece.moveSet(pieces);
        for (Place p : places) {
            if (p.getRow() == row && p.getCol() == col) {
                if (GeneralPiece.isItStillCheck(pieces, selectedPiece, p)) {
                    return false;
                }
                Piece piece = GeneralPiece.findPieceByPlace(pieces, p);
                if (piece != null) {
                    pieces.remove(piece);
                }
                selectedPiece.move(p);
            }
        }
        return true;
    }

    /**
     * Special moves.
     */
    private static void specialMoves() {
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece.getType() == 'p') {
                Place place = piece.getPlace();
                if (piece.getPlayerBlack()) {
                    if (place.getRow() == 0) {
                        pieces.remove(piece);
                        pieces.add(GeneralPiece.createPiece(place, true, 'Q'));
                    }
                } else {
                    if (place.getRow() == 7) {
                        pieces.remove(piece);
                        pieces.add(GeneralPiece.createPiece(place, false, 'Q'));
                    }
                }
            }
        }
    }


    /**
     * Update.
     */
    public static void update() {
        Piece[][] board = GeneralPiece.getBoardPieces(pieces);
        if (!(isWhiteTurn || isGameOver)) {
            double maxScore = Double.NEGATIVE_INFINITY;
            Move move = null;
            ArrayList<Piece> copyList = GeneralPiece.copyList(pieces);
            ArrayList<Piece> blackPieces = GeneralPiece.getColorPieces(copyList, true);
            for (Piece piece : blackPieces) {
                if (piece.getType() == 'p' && (piece.getPlace().getCol() == 4 || piece.getPlace().getCol() == 6)) {
                    int h = 0;
                }
                ArrayList<Place> places = piece.moveSet(copyList);
                Place oldPlace = piece.getPlace();
                for (Place place : places) {
                    Piece pieceEaten = GeneralPiece.findPieceByPlace(copyList, place);
                    if (pieceEaten != null) {
                        copyList.remove(pieceEaten);
                    }
                    piece.move(place);
                    double score = miniMax(3, false, GeneralPiece.copyList(pieces));
                    if (score > maxScore) {
                        maxScore = score;
                        move = new Move(piece, place);
                    }
                    piece.move(oldPlace);
                    if (pieceEaten != null) {
                        copyList.add(pieceEaten);
                    }
                }

            }
            if (move == null) {
                Random random = new Random();
                Piece randomPiece = pieces.get(random.nextInt(pieces.size()));
                ArrayList<Place> places = randomPiece.moveSet(copyList);
                Place randomPlace = places.get(random.nextInt(places.size()));
                move = new Move(randomPiece, randomPlace);
            }
            Piece piece = move.getPieceMoving();
            Place place = move.getPlaceToMove();
            System.out.println("Piece: " + piece.getType());
            System.out.println("Place: " + place.getRow() + " , " + place.getCol());
            if (board[place.getRow()][place.getCol()] != null) {
                pieces.remove(board[place.getRow()][place.getCol()]);
            }
            for (Piece p : pieces) {
                if (GeneralPiece.isTwoPiecesEqual(p, piece)) {
                    piece = p;
                    break;
                }
            }
            tryToMove(place.getRow(), place.getCol(), piece, pieces);
            isWhiteTurn = true;
            return;
        }
        for (Piece value : pieces) {
            ArrayList<Piece> piecesThreaded = GeneralPiece.getPiecesThreaded(pieces, value);
            for (Piece p : piecesThreaded) {
                if (p.getType() == 'K') {
                    if (p.getPlayerBlack()) {
                        isItCheckOnBlack = true;
                    } else {
                        isItCheckOnWhite = true;
                    }
                    break;
                }
            }
        }

        if ((isWhiteTurn && isItCheckOnWhite) || (!isWhiteTurn && isItCheckOnBlack)) {
            boolean isThereAWay = false;
            for (Piece piece : pieces) {
                if ((isItCheckOnBlack && piece.getPlayerBlack()) || (isItCheckOnWhite && !piece.getPlayerBlack())) {
                    ArrayList<Place> movesSet = piece.moveSet(pieces);
                    for (Place place : movesSet) {
                        if (!GeneralPiece.isItStillCheck(pieces, piece, place)) {
                            isThereAWay = true;
                            break;
                        }
                    }
                }
            }
            if (!isThereAWay) {
                isGameOver = true;
                return;
            }
        }
        int numberOfKings = 2;
        for (Piece piece : pieces) {
            if (piece.getType() == 'K') {
                numberOfKings--;
            }
        }
        if (numberOfKings == 1) {
            isGameOver = true;
        }
        specialMoves();
    }

    /**
     * Render.
     *
     * @param g the g
     */
    public static void render( Graphics g ) {
        GameGraphics.render(g, pieces, selectedPiece);
    }
}
