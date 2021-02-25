package pieces;

import utlity.Piece;
import utlity.Place;
import utlity.Setting;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * The type Rook.
 */
public class Rook implements Piece {
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
     * Instantiates a new Rook.
     *
     * @param place         the place
     * @param isPlayerBlack the is player black
     * @param image         the image
     */
    public Rook(Place place, Boolean isPlayerBlack, Image image) {
        this.place = place;
        this.isPlayerBlack = isPlayerBlack;
        this.image = image;
        this.type = 'R';
        isMoved = false;
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
        for (int i = row + 1; i < 8; i++) {
            if (table[i][col] == null) {
                moves.add(new Place(i, col));
            } else {
                if (table[i][col].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(i, col));
                }
                break;
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            if (table[i][col] == null) {
                moves.add(new Place(i, col));
            } else {
                if (table[i][col].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(i, col));
                }
                break;
            }
        }
        for (int i = col + 1; i < 8; i++) {
            if (table[row][i] == null) {
                moves.add(new Place(row, i));
            } else {
                if (table[row][i].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row, i));
                }
                break;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            if (table[row][i] == null) {
                moves.add(new Place(row, i));
            } else {
                if (table[row][i].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row, i));
                }
                break;
            }
        }
        return moves;
    }

    /**
     * Gets is moved.
     *
     * @return the is moved
     */
    public boolean getIsMoved() {
        return this.isMoved;
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
        isMoved = true;
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
        graphics.drawImage(this.image, col * Setting.CELL_WIDTH + 15, row * Setting.CELL_HEIGHT + 1, null);
    }
}
