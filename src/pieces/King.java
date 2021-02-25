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
    public King(Place place, Boolean isPlayerBlack, Image image) {
        this.place = place;
        this.isPlayerBlack = isPlayerBlack;
        this.image = image;
        this.type = 'K';
        isMoved = false;
        this.rooks = new Rook[2];
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
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i + row < 0 || j + col < 0 || i + row >= 8 || j + col >= 8) {
                    continue;
                }
                if (table[i + row][col + j] == null || table[i + row][j + col].getPlayerBlack() != this.isPlayerBlack) {
                    moves.add(new Place(row + i, col + j));
                }
            }
        }
        if (!this.isMoved) {
            if (table[row][0] != null && table[row][0].getType() == 'R') {
                Rook rook = (Rook) table[row][0];
                if (!rook.getIsMoved() && rook.getPlayerBlack() == this.isPlayerBlack) {
                    boolean isPossible = true;
                    for (int i = 1; i < col; i++) {
                        if (table[row][i] != null) {
                            isPossible = false;
                            break;
                        }
                    }
                    if (isPossible) {
                        this.rooks[0] = rook;
                        moves.add(new Place(row, 2));
                    }
                }
            }
            if (table[row][7] != null && table[row][7].getType() == 'R') {
                Rook rook = (Rook) table[row][7];
                if (!rook.getIsMoved()) {
                    boolean isPossible = true;
                    for (int i = 7; i > col; i--) {
                        if (table[row][i] != null) {
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
    public void move(Place placeToGo) {
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
