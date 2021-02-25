package pieces;

import utlity.Piece;
import utlity.Place;
import utlity.Setting;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The type Knight.
 */
public class Knight implements Piece {
    /**
     * The Place.
     */
    private Place place;
    /**
     * The Type.
     */
    private char type;
    /**
     * The Is player black.
     */
    private final Boolean isPlayerBlack;
    /**
     * The Image.
     */
    private final Image image;

    /**
     * Instantiates a new Knight.
     *
     * @param place         the place
     * @param isPlayerBlack the is player black
     * @param image         the image
     */
    public Knight(Place place, Boolean isPlayerBlack, Image image) {
        this.place = place;
        this.isPlayerBlack = isPlayerBlack;
        this.image = image;
        this.type = 'N';
    }

    /**
     * Change type.
     *
     * @param t the t
     */
    public void changeType(char t) {
        this.type = t;
    }

    /**
     * Move set array list.
     *
     * @param table the table
     *
     * @return the array list
     */
    @Override
    public ArrayList<Place> moveSet(Piece[][] table) {
        ArrayList<Place> moves = new ArrayList<>();
        int row = this.place.getRow();
        int col = this.place.getCol();
        if (row + 2 < 8) {
            if (col + 1 < 8) {
                if (table[row + 2][col + 1] == null || table[row + 2][col + 1].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row + 2, col + 1));
                }
            }
            if (col - 1 >= 0) {
                if (table[row + 2][col - 1] == null || table[row + 2][col - 1].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row + 2, col - 1));
                }
            }
        }
        if (row - 2 >= 0) {
            if (col + 1 < 8) {
                if (table[row - 2][col + 1] == null || table[row - 2][col + 1].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row - 2, col + 1));
                }
            }
            if (col - 1 >= 0) {
                if (table[row - 2][col - 1] == null || table[row - 2][col - 1].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row - 2, col - 1));
                }
            }
        }
        if (col + 2 < 8) {
            if (row + 1 < 8) {
                if (table[row + 1][col + 2] == null || table[row + 1][col + 2].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row + 1, col + 2));
                }
            }
            if (row - 1 >= 0) {
                if (table[row - 1][col + 2] == null || table[row - 1][col + 2].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row - 1, col + 2));
                }
            }
        }
        if (col - 2 >= 0) {
            if (row + 1 < 8) {
                if (table[row + 1][col - 2] == null || table[row + 1][col - 2].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row + 1, col - 2));
                }
            }
            if (row - 1 >= 0) {
                if (table[row - 1][col - 2] == null || table[row - 1][col - 2].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row - 1, col - 2));
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
     * @param place the place
     */
    @Override
    public void move(Place place) {
        this.place = place;
    }

    /**
     * Render piece.
     *
     * @param graphics the graphics
     */
    @Override
    public void renderPiece(Graphics graphics) {
        int row = this.place.getRow();
        int col = this.place.getCol();
        graphics.drawImage(this.image, col * Setting.CELL_WIDTH + 15, row * Setting.CELL_HEIGHT, null);
    }
}
