import pieces.GeneralPiece;
import utlity.Piece;
import utlity.Place;
import utlity.Setting;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
                    if (tryToMove(row, col)) {
                        selectedPiece = null;
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
     * Try to move boolean.
     *
     * @param row the row
     * @param col the col
     *
     * @return the boolean
     */
    public static boolean tryToMove( int row, int col ) {
        Piece[][] board = GeneralPiece.getBoardPieces(pieces);
        ArrayList<Place> places = selectedPiece.moveSet(board);
        for (Place p : places) {
            if (p.getRow() == row && p.getCol() == col) {
                if (isWhiteTurn) {
                    if (isItCheckOnWhite) {
                        if (isItStillCheck(selectedPiece, p)) {
                            return false;
                        }
                    }
                } else {
                    if (isItCheckOnBlack) {
                        if (isItStillCheck(selectedPiece, p)) {
                            return false;
                        }
                    }
                }
                Piece piece = GeneralPiece.findPieceByPlace(pieces, p);
                if (piece != null) {
                    pieces.remove(piece);
                }
                selectedPiece.move(p);
                isWhiteTurn = !isWhiteTurn;
            }
        }
        return true;
    }

    /**
     * Special moves.
     */
    private static void specialMoves() {
       /* for (int i = 0; i < 8; i++) {
            if (board[0][i] != null && board[0][i].getType() == 'p' && !board[0][i].getPlayerBlack()) {
                board[0][i] = GeneralPiece.createPiece(new Place(0, i), false, 'Q');
            }
            if (board[7][i] != null && board[7][i].getType() == 'p' && !board[7][i].getPlayerBlack()) {
                board[7][i] = GeneralPiece.createPiece(new Place(7, i), false, 'Q');
            }

        }*/
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
     * Is it still check boolean.
     *
     * @param selectedPiece the selected piece
     * @param selectedPlace the selected place
     *
     * @return the boolean
     */
    public static boolean isItStillCheck( Piece selectedPiece, Place selectedPlace ) {
        Piece[][] board = GeneralPiece.getBoardPieces(pieces);
        ArrayList<Piece> threadedPieces = new ArrayList<>();
        for (Piece p : pieces) {
            ArrayList<Piece> piecesThreaded = GeneralPiece.getPLacesThreaded(board, p);
            if (piecesThreaded.isEmpty()) {
                 continue;
            }
             for (Piece piece : piecesThreaded) {
                if (piece.getType() == 'K' && selectedPiece.getPlayerBlack() == piece.getPlayerBlack()) {
                    piecesThreaded.add(p);
                }
            }
        }
        Place oldPlace = selectedPiece.getPlace();
        Piece pieceInOldPlace = board[selectedPlace.getRow()][selectedPlace.getCol()];
        for (Piece piece : threadedPieces) {
            if (piece.getPlace().equals(selectedPlace)) {
                return false;
            }

            board[selectedPlace.getRow()][selectedPlace.getCol()] = selectedPiece;
            ArrayList<Piece> piecesThreaded = GeneralPiece.getPLacesThreaded(board, piece);
            for (Piece p : piecesThreaded) {
                if (p.getType() == 'K' && selectedPiece.getPlayerBlack() == p.getPlayerBlack()) {
                    return true;
                }
            }
            board[oldPlace.getRow()][oldPlace.getCol()] = pieceInOldPlace;
        }
        return false;
    }

    /**
     * Update.
     */
    public static void update() {
        for (Piece value : pieces) {
            if (value.getType() == 'N' && value.getPlayerBlack()) {
                if (value.getPlace().getRow() == 2) {
                    int h = 0;
                }
            }
            Piece[][] board = GeneralPiece.getBoardPieces(pieces);
            ArrayList<Piece> piecesThreaded = GeneralPiece.getPLacesThreaded(board, value);
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(new Color(102, 51, 0));
                }
                g.fillRect(
                        (i * Setting.CELL_WIDTH), (j * Setting.CELL_HEIGHT), (Setting.CELL_WIDTH - 1),
                        (Setting.CELL_HEIGHT - 1)
                          );
            }
        }
        g.setColor(new Color(255, 0, 0, 166));
        for (Piece piece : pieces) {
     /*       ArrayList<Piece> piecesThreaded = GeneralPiece.getPLacesThreaded(board, pieces.get(i));
            for (Piece p : piecesThreaded) {
                Place place = p.getPlace();
                int x = place.getCol() * Setting.CellWidth;
                int y = place.getRow() * Setting.CellHeight;
                x += Setting.CellWidth / 2;
                y += Setting.CellHeight / 2;
                g.fillOval(x - 35, y - 35, 70, 70);
            }*/
            piece.renderPiece(g);
        }

        if (selectedPiece != null && !isGameOver) {
            int x = selectedPiece.getPlace().getCol() * Setting.CELL_WIDTH;
            int y = selectedPiece.getPlace().getRow() * Setting.CELL_HEIGHT;
            x += Setting.CELL_WIDTH / 2;
            y += Setting.CELL_HEIGHT / 2;
            g.setColor(new Color(38, 220, 238, 100));
            g.fillOval(x - 40, y - 40, 80, 80);
            if (selectedPiece == null) {
                return;
            }
            Piece[][] board = GeneralPiece.getBoardPieces(pieces);
            ArrayList<Place> placesToGo = selectedPiece.moveSet(board);
            for (int i = 0; i < placesToGo.size(); i++) {
                Place place = placesToGo.get(i);
                int row = place.getRow();
                int col = place.getCol();
                x = col * Setting.CELL_WIDTH + Setting.CELL_WIDTH / 2;
                y = row * Setting.CELL_HEIGHT + Setting.CELL_HEIGHT / 2;
                if (GeneralPiece.findPieceByPlace(pieces, place) == null) {
                    g.setColor(new Color(20, 20, 245, 240));
                } else {

                    g.setColor(new Color(92, 245, 9, 100));
                }
                g.fillOval(x - 25, y - 25, 50, 50);

            }
        }


    }


}
