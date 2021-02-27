package pieces;

import utlity.Piece;
import utlity.Place;
import utlity.Setting;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The type Pawn.
 */
public class Pawn implements Piece {
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
     * The First move.
     */
    private boolean firstMove;
    /**
     * The Image.
     */
    private final Image image;

    /**
     * Instantiates a new Pawn.
     *
     * @param place         the place
     * @param isPlayerBlack the is player black
     * @param image         the image
     */
    public Pawn( Place place, Boolean isPlayerBlack, Image image ) {
        this.place = place;
        this.isPlayerBlack = isPlayerBlack;
        this.type = 'p';
        this.firstMove = true;
        this.image = image;
        this.points = 1;
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
        ArrayList<Place> moveSet = new ArrayList<>();
        int row = this.place.getRow();
        int col = this.place.getCol();
        int advance = -1;
        if (this.isPlayerBlack) {
            advance = 1;
        }
        if (row != 0 && row != 7) {
            Place newPlace = new Place(row + advance, col);
            if (GeneralPiece.findPieceByPlace(pieces, newPlace) == null) {
                moveSet.add(newPlace);
                if (firstMove) {
                    if (GeneralPiece.findPieceByPlace(pieces, new Place(row + (2 * advance), col)) == null) {
                        moveSet.add(new Place(row + (2 * advance), col));
                    }
                }
            }
            if (col != 0) {
                Place place = new Place(row + advance, col - 1);
                Piece piece = GeneralPiece.findPieceByPlace(pieces, place);
                if (piece != null) {
                    if (piece.getPlayerBlack() != this.getPlayerBlack()) {
                        moveSet.add(place);
                    }
                }
            }
            if (col != 7) {
                Place place = new Place(row + advance, col + 1);
                Piece piece = GeneralPiece.findPieceByPlace(pieces, place);
                if (piece != null) {
                    if (piece.getPlayerBlack() != this.getPlayerBlack()) {
                        moveSet.add(place);
                    }
                }
            }
        }
        return moveSet;
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
     * @param place the place
     */
    @Override
    public void move( Place place ) {
        this.firstMove = false;
        this.place = place;
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
