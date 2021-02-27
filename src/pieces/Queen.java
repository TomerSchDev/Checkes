package pieces;

import utlity.Piece;
import utlity.Place;
import utlity.Setting;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The type Queen.
 */
public class Queen implements Piece {
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
     * The Times moves.
     */
    private int timesMoves = 0;

    /**
     * Instantiates a new Queen.
     *
     * @param place       the place
     * @param playerBlack the player black
     * @param image       the image
     */
    public Queen( Place place, Boolean playerBlack, Image image ) {
        this.type = 'Q';
        this.place = place;
        this.isPlayerBlack = playerBlack;
        this.image = image;
        this.points = 9;
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
        ArrayList<Place> places = new ArrayList<>();
        places.addAll(GeneralPiece.goStraightLine(pieces, this));
        places.addAll(GeneralPiece.goDiagnoseLine(pieces, this));
        return places;
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
        this.place = place;
        this.timesMoves++;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    @Override
    public double getPoints() {
        return 0;
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
