package utlity;

import java.util.Objects;

/**
 * The type Place.
 */
public class Place {
    /**
     * The Row.
     */
    private int row;
    /**
     * The Col.
     */
    private int col;

    /**
     * Instantiates a new Place.
     *
     * @param row the row
     * @param col the col
     */
    public Place(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets col.
     *
     * @return the col
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     *
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Place place = (Place) o;
        return row == place.row && col == place.col;
    }

    /**
     * Hash code int.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }


}
