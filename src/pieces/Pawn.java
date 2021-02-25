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
    }

    /**
     * Move set array list.
     *
     * @param table the table
     *
     * @return the array list
     */
    @Override
    public ArrayList<Place> moveSet( Piece[][] table ) {
        int row = this.place.getRow();
        int col = this.place.getCol();
        ArrayList<Place> moveSet = new ArrayList<>();
        if (!this.isPlayerBlack) {
            if (row != 0) {
                if (table[row - 1][col] == null) {
                    moveSet.add(new Place(row - 1, col));
                    if (firstMove) {
                        if (table[row - 2][col] == null) {
                            moveSet.add(new Place(row - 2, col));
                        }
                    }
                }
                if (col != 0 && table[row - 1][col - 1] != null && table[row - 1][col - 1].getPlayerBlack()) {
                    moveSet.add(new Place(row - 1, col - 1));
                }
                if (col != 7 && table[row - 1][col + 1] != null && table[row - 1][col + 1].getPlayerBlack()) {
                    moveSet.add(new Place(row - 1, col + 1));
                }
            }
        } else {
            if (row != 7) {
                if (table[row + 1][col] == null) {
                    moveSet.add(new Place(row + 1, col));
                    if (firstMove) {
                        if (table[row + 2][col] == null) {
                            moveSet.add(new Place(row + 2, col));
                            if (col != 0) {
                                if (table[row + 1][col - 1] != null) {
                                    if (!table[row + 1][col - 1].getPlayerBlack()) {
                                        moveSet.add(new Place(row + 1, col - 1));
                                    }
                                }
                            }
                        } else {
                            if (col != 0) {
                                if (table[row + 1][col + 1] != null) {
                                    if (!table[row + 1][col + 1].getPlayerBlack()) {
                                        moveSet.add(new Place(row + 1, col - 1));
                                    }
                                }
                            }
                        }
                        if (col != 7) {
                            if (table[row + 1][col + 1] != null) {
                                if (!table[row + 1][col + 1].getPlayerBlack()) {
                                    moveSet.add(new Place(row + 1, col + 1));
                                }
                            }
                        }
                    } else {
                        if (col != 0) {
                            if (table[row + 1][col + 1] != null) {
                                if (!table[row + 1][col + 1].getPlayerBlack()) {
                                    moveSet.add(new Place(row + 1, col - 1));
                                    if (col != 7 && null != table[row + 1][col + 1] &&
                                            !table[row + 1][col + 1].getPlayerBlack()) {
                                        moveSet.add(new Place(row + 1, col + 1));
                                    }
                                } else {
                                    if (!((table[row + 1][col + 1] == null) || (col == 7)
                                            || table[row + 1][col + 1].getPlayerBlack())) {
                                        moveSet.add(new Place(row + 1, col + 1));
                                    }
                                }
                            } else {
                                if (!((table[row + 1][col + 1] == null) || (col == 7)
                                        || table[row + 1][col + 1].getPlayerBlack())) {
                                    moveSet.add(new Place(row + 1, col + 1));
                                }
                            }
                        } else {
                            if (!((table[row + 1][col + 1] == null) || (col == 7)
                                    || table[row + 1][col + 1].getPlayerBlack())) {
                                moveSet.add(new Place(row + 1, col + 1));
                            }
                        }
                    }
                } else {
                    if (!(table[row + 1][col - 1] == null)) {
                        if ((col != 0) && !table[row + 1][col + 1].getPlayerBlack()) {
                            moveSet.add(new Place(row + 1, col - 1));
                        }
                    }
                    if (col != 7 && table[row + 1][col + 1] != null && !table[row + 1][col + 1].getPlayerBlack()) {
                        moveSet.add(new Place(row + 1, col + 1));
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
