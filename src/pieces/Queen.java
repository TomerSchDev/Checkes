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
    public Queen(Place place, Boolean playerBlack, Image image) {
        this.type = 'Q';
        this.place = place;
        this.isPlayerBlack = playerBlack;
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
    public ArrayList<Place> moveSet(Piece[][] table) {
        ArrayList<Place> places = new ArrayList<>();
        int row = this.place.getRow();
        int col = this.place.getCol();
        for (int i = col + 1; i < 8; i++) {
            if (table[row][i] == null) {
                places.add(new Place(row, i));
            } else {
                if (table[row][i].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(row, i));
                }
                break;
            }
        }
        for (int i = col - 1; i > -1; i--) {
            if (table[row][i] == null) {
                places.add(new Place(row, i));
            } else {
                if (table[row][i].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(row, i));
                }
                break;
            }
        }
        for (int i = row + 1; i < 8; i++) {
            if (table[i][col] == null) {
                places.add(new Place(i, col));
            } else {
                if (table[i][col].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(i, col));
                }
                break;
            }
        }
        for (int i = row - 1; i > -1; i--) {
            if (table[i][col] == null) {
                places.add(new Place(i, col));
            } else {
                if (table[i][col].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(i, col));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (row + i >= 8 || col + i >= 8) {
                break;
            }
            if (table[row + i][col + i] == null) {
                places.add(new Place(row + i, col + i));
            } else {
                if (table[row + i][col + i].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(row + i, col + i));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (row - i < 0 || col - i < 0) {
                break;
            }
            if (table[row - i][col - i] == null) {
                places.add(new Place(row - i, col - i));
            } else {
                if (table[row - i][col - i].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(row - i, col - i));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (row - i < 0 || col + i >= 8) {
                break;
            }
            if (table[row - i][col + i] == null) {
                places.add(new Place(row - i, col + i));
            } else {
                if (table[row - i][col + i].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(row - i, col + i));
                }
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (row + i >= 8 || col - i < 0) {
                break;
            }
            if (table[row + i][col - i] == null) {
                places.add(new Place(row + i, col - i));
            } else {
                if (table[row + i][col - i].getPlayerBlack() != this.isPlayerBlack) {
                    places.add(new Place(row + i, col - i));
                }
                break;
            }
        }
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
    public void move(Place place) {
        this.place = place;
        this.timesMoves++;
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
