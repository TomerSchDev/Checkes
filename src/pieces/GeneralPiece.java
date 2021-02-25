package pieces;

import utlity.Piece;
import utlity.Place;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * The type General piece.
 */
public abstract class GeneralPiece {
    /**
     * The constant piecesImages.
     */
    private static Image[] piecesImages;

    /**
     * Get board pieces piece [ ] [ ].
     *
     * @param pieces the pieces
     *
     * @return the piece [ ] [ ]
     */
    public static Piece[][] getBoardPieces(ArrayList<Piece> pieces) {
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
        for (Piece piece : pieces) {
            int row = piece.getPlace().getRow();
            int col = piece.getPlace().getCol();
            board[row][col] = piece;
        }
        return board;
    }

    /**
     * Gets pieces from board.
     *
     * @param board the board
     *
     * @return the pieces from board
     */
    public static ArrayList<Piece> getPiecesFromBoard(Piece[][] board) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null) {
                    pieces.add(board[i][j]);
                    board[i][j] = null;
                }
            }
        }
        return pieces;
    }

    /**
     * Gets p laces threaded.
     *
     * @param table the table
     * @param piece the piece
     *
     * @return the p laces threaded
     */
    public static ArrayList<Piece> getPLacesThreaded(Piece[][] table, Piece piece) {
        ArrayList<Place> movesSet = piece.moveSet(table);
        ArrayList<Piece> threadedPieces = new ArrayList<>();
        for (Place p : movesSet) {
            if (table[p.getRow()][p.getCol()] != null) {
                threadedPieces.add(table[p.getRow()][p.getCol()]);
            }
        }
        return threadedPieces;
    }

    /**
     * Init pieces array list.
     *
     * @return the array list
     */
    public static ArrayList<Piece> initPieces() {
        initPiecesImages();
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Boolean isPlayerBlack;
            Image image;
            int row;
            if (i < 8) {
                isPlayerBlack = true;
                image = piecesImages[2];
                row = 1;
            } else {
                isPlayerBlack = false;
                row = 6;
            }
            pieces.add(createPiece(new Place(row, i % 8), isPlayerBlack, 'p'));
        }
        for (int i = 0; i < 8; i++) {
            char type = ' ';
            switch (i) {
                case (0):
                case (7):
                    type = 'R';
                    break;
                case (1):
                case (6):
                    type = 'N';
                    break;
                case (2):
                case (5):
                    type = 'B';
                    break;
                case (3):
                    type = 'Q';
                    break;
                case (4):
                    type = 'K';
                    break;
                default:
                    return null;
            }
           /* if (type == 'N' && i == 2) {
                Knight knight = new Knight(new Place(0, i), true, piecesImages[1]);
                knight.changeType('J');
                pieces.add(knight);
            }*/
            pieces.add(createPiece(new Place(0, i), true, type));
            pieces.add(createPiece(new Place(7, i), false, type));
        }
        return pieces;
    }

    /**
     * Find piece by place piece.
     *
     * @param pieces the pieces
     * @param p      the p
     *
     * @return the piece
     */
    public static Piece findPieceByPlace(ArrayList<Piece> pieces, Place p) {
        for (Piece piece : pieces) {
            if (piece.getPlace().equals(p)) {
                return piece;
            }
        }
        return null;
    }

    /**
     * Create piece piece.
     *
     * @param place         the place
     * @param isPlayerBlack the is player black
     * @param type          the type
     *
     * @return the piece
     */
    public static Piece createPiece(Place place, boolean isPlayerBlack, char type) {
        int imageIndex = 0;
        if (!isPlayerBlack) {
            imageIndex = 6;
        }
        Piece piece = null;
        switch (type) {
            case ('K'):
                piece = new King(place, isPlayerBlack, piecesImages[imageIndex]);
                break;
            case ('N'):
                piece = new Knight(place, isPlayerBlack, piecesImages[imageIndex + 1]);
                break;
            case ('p'):
                piece = new Pawn(place, isPlayerBlack, piecesImages[imageIndex + 2]);
                break;
            case ('Q'):
                piece = new Queen(place, isPlayerBlack, piecesImages[imageIndex + 3]);
                break;
            case ('R'):
                piece = new Rook(place, isPlayerBlack, piecesImages[imageIndex + 4]);
                break;
            case ('B'):
                piece = new Bishop(place, isPlayerBlack, piecesImages[imageIndex + 5]);
                break;
            default:
                throw null;
        }
        return piece;
    }

    /**
     * Init pieces images.
     */
    private static void initPiecesImages() {
        String startPath = "./images/";
        piecesImages = new Image[12];
        String end = ".png";
        //--BLACK--
        piecesImages[0] = readImage(new File(startPath + "BLACK_KING" + end));
        piecesImages[1] = readImage(new File(startPath + "BLACK_KNIGHT" + end));
        piecesImages[2] = readImage(new File(startPath + "BLACK_PAWN" + end));
        piecesImages[3] = readImage(new File(startPath + "BLACK_QUEEN" + end));
        piecesImages[4] = readImage(new File(startPath + "BLACK_ROOK" + end));
        piecesImages[5] = readImage(new File(startPath + "BLACK_BISHOP" + end));
        //---WHITE---
        piecesImages[6] = readImage(new File(startPath + "WHITE_KING" + end));
        piecesImages[7] = readImage(new File(startPath + "WHITE_KNIGHT" + end));
        piecesImages[8] = readImage(new File(startPath + "WHITE_PAWN" + end));
        piecesImages[9] = readImage(new File(startPath + "WHITE_QUEEN" + end));
        piecesImages[10] = readImage(new File(startPath + "WHITE_ROOK" + end));
        piecesImages[11] = readImage(new File(startPath + "WHITE_BISHOP" + end));
    }

    /**
     * Read image image.
     *
     * @param file the file
     *
     * @return the image
     */
    public static Image readImage(File file) {
        Image image = null;
        try {
            InputStream inputStream = new FileInputStream(file);
            image = ImageIO.read(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
