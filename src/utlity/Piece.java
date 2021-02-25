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
     * @param table the table
     *
     * @return the array list
     */
    ArrayList<Place> moveSet(Piece[][] table);

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
     * Render piece.
     *
     * @param graphics the graphics
     */
    void renderPiece(Graphics graphics);
}
