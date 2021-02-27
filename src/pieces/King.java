package pieces;

import utlity.Piece;
import utlity.Place;
import utlity.Setting;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The type King.
 */
public class King implements Piece {
    /**
     * The Points.
     */
    private double points;
    /**
     * The Place.
     */
    private Place place;
    /**
     * The Type.
     */
    private final char type;
    /**
     * The Is player black.
     */
    private final Boolean isPlayerBlack;
    /**
     * The Image.
     */
    private final Image image;
    /**
     * The Is moved.
     */
    private boolean isMoved;
    /**
     * The Rooks.
     */
    private Rook[] rooks;

    /**
     * Instantiates a new King.
     *
     * @param place         the place
     * @param isPlayerBlack the is player black
     * @param image         the image
     */
    public King( Place place, Boolean isPlayerBlack, Image image ) {
        this.place = place;
        this.isPlayerBlack = isPlayerBlack;
        this.image = image;
        this.type = 'K';
        isMoved = false;
        this.rooks = new Rook[2];
        this.points = 10;
    }

    /**
     * Move set array list.
     *
     * @param pieces the pieces
     *
     * @return the array list
     */
    @Override
    public ArrayList<Place> moveSet( ArrayList<Piece> pieces ) {
        ArrayList<Place> moves = new ArrayList<>();
        int row = this.place.getRow();
        int col = this.place.getCol();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i + row < 0 || j + col < 0 || i + row >= 8 || j + col >= 8) {
                    continue;
                }
                Place place = new Place(i + row, col + j);
                Piece piece = GeneralPiece.findPieceByPlace(pieces, place);
                if (piece == null || piece.getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(place);
                }
            }
        }
        if (!this.isMoved) {
            Place rook1Place = new Place(this.place.getRow(), 0);
            Place rook2Place = new Place(this.place.getRow(), 7);
            Piece piece1 = GeneralPiece.findPieceByPlace(pieces, rook1Place);
            Piece piece2 = GeneralPiece.findPieceByPlace(pieces, rook2Place);
            if (piece1 != null && piece1.getType() == 'R') {
                Rook rook = (Rook) piece1;
                if (!rook.getIsMoved() && rook.getPlayerBlack() == this.isPlayerBlack) {
                    boolean isPossible = true;
                    for (int i = 1; i < col; i++) {
                        if (GeneralPiece.findPieceByPlace(pieces, new Place(row, i)) != null) {
                            isPossible = false;
                            break;
                        }
                    }
                    if (isPossible) {
                        moves.add(new Place(row, 2));
                    }
                }
            }
            if (piece2 != null && piece2.getType() == 'R') {
                Rook rook = (Rook) piece2;
                if (!rook.getIsMoved()) {
                    boolean isPossible = true;
                    for (int i = 7; i > col; i--) {
                        if (GeneralPiece.findPieceByPlace(pieces, new Place(row, i)) != null) {
                            isPossible = false;
                            break;
                        }
                    }
                    if (isPossible) {
                        this.rooks[1] = rook;
                        moves.add(new Place(row, 6));
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Gets player black.
     *
     * @return the player black
     */
    @Override
    public Boolean getPlayerBlack() {
        return this.isPlayerBlack;
    }

    /**
     * Gets place.
     *
     * @return the place
     */
    @Override
    public Place getPlace() {
        return this.place;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    @Override
    public char getType() {
        return this.type;
    }

    /**
     * Move.
     *
     * @param placeToGo the place to go
     */
    @Override
    public void move( Place placeToGo ) {
        if (placeToGo.getCol() == 2) {
            if (this.place.getCol() == 4) {
                this.rooks[0].move(new Place(this.place.getRow(), 3));
            }
        }
        if (placeToGo.getCol() == 6) {
            if (this.place.getCol() == 4) {
                this.rooks[1].move(new Place(this.place.getRow(), 5));
            }
        }
        this.place = placeToGo;
        isMoved = true;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    @Override
    public double getPoints() {
        return this.points;
    }

    /**
     * Render piece.
     *
     * @param graphics the graphics
     */
    @Override
    public void renderPiece( Graphics graphics ) {
        int row = this.place.getRow();
        int col = this.place.getCol();
        graphics.drawImage(this.image, col * Setting.CELL_WIDTH + 15, row * Setting.CELL_HEIGHT, null);
    }
}
