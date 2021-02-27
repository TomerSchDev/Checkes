package utlity;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * The interface Piece.
 */
public interface Piece {
    /**
     * Move set array list.
     *
     * @param pieces the pieces
     *
     * @return the array list
     */
    ArrayList<Place> moveSet(ArrayList<Piece>pieces);

    /**
     * Gets player black.
     *
     * @return the player black
     */
    Boolean getPlayerBlack();

    /**
     * Gets place.
     *
     * @return the place
     */
    Place getPlace();

    /**
     * Gets type.
     *
     * @return the type
     */
    char getType();

    /**
     * Move.
     *
     * @param place the place
     */
    void move(Place place);

    /**
     * Gets points.
     *
     * @return the points
     */
    double getPoints();

    /**
     * Render piece.
     *
     * @param graphics the graphics
     */
    void renderPiece(Graphics graphics);
}
