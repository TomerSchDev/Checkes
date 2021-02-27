package utlity;

/**
 * The type Move.
 */
public class Move {
    /**
     * The Piece moving.
     */
    private Piece pieceMoving;
    /**
     * The Place to move.
     */
    private Place placeToMove;

    /**
     * Instantiates a new Move.
     *
     * @param pieceMoving the piece moving
     * @param placeToMove the place to move
     */
    public Move( Piece pieceMoving, Place placeToMove ) {
        this.pieceMoving = pieceMoving;
        this.placeToMove = placeToMove;
    }

    /**
     * Gets place to move.
     *
     * @return the place to move
     */
    public Place getPlaceToMove() {
        return this.placeToMove;
    }

    /**
     * Gets piece moving.
     *
     * @return the piece moving
     */
    public Piece getPieceMoving() {
        return this.pieceMoving;
    }
}

