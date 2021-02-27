package utlity;

import pieces.GeneralPiece;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class GameGraphics {
    private static final Color BROWN = new Color(102, 51, 0);
    private static final Color SELECTED_EMPTY_PLACE = new Color(20, 20, 245, 240);
    private static final Color SELECTED_ENEMY_PLACE = new Color(92, 245, 9, 100);
    private static final Color SELECTED_PIECE = new Color(38, 220, 238, 100);
    private static final Color THREATENED_PIECE = new Color(248, 83, 41, 166);

    public static void render( Graphics g, ArrayList<Piece> pieces, Piece selectedPiece ) {
        renderBoard(g);

        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            if (piece != null) {
                piece.renderPiece(g);
            }
        }
        if (selectedPiece != null) {
            Piece[][] board = GeneralPiece.getBoardPieces(pieces);
            renderSelectedPiece(g, selectedPiece);
            renderMoves(g, selectedPiece, pieces);
        }

    }

    public static void renderSelectedPiece( Graphics g, Piece selectedPiece ) {
        int x = selectedPiece.getPlace().getCol() * Setting.CELL_WIDTH;
        int y = selectedPiece.getPlace().getRow() * Setting.CELL_HEIGHT;
        x += Setting.CELL_WIDTH / 2;
        y += Setting.CELL_HEIGHT / 2;
        g.setColor(SELECTED_PIECE);
        g.fillOval(x - 40, y - 40, 80, 80);
    }

    public static void renderMoves( Graphics g, Piece selectedPiece, ArrayList<Piece> pieces ) {
        ArrayList<Place> placesToGo = selectedPiece.moveSet(pieces);
        for (int i = 0; i < placesToGo.size(); i++) {
            Place place = placesToGo.get(i);
            if (GeneralPiece.isItStillCheck(pieces, selectedPiece, place)) {
                continue;
            }
            int row = place.getRow();
            int col = place.getCol();
            int x = col * Setting.CELL_WIDTH + Setting.CELL_WIDTH / 2;
            int y = row * Setting.CELL_HEIGHT + Setting.CELL_HEIGHT / 2;
            if (GeneralPiece.findPieceByPlace(pieces, place) == null) {
                g.setColor(SELECTED_EMPTY_PLACE);
            } else {
                g.setColor(SELECTED_ENEMY_PLACE);
            }
            g.fillOval(x - 25, y - 25, 50, 50);
        }


    }

    public static void renderBoard( Graphics g ) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                g.setColor(Color.WHITE);
                if ((i + j) % 2 == 1) {
                    g.setColor(BROWN);
                }
                g.fillRect(
                        (i * Setting.CELL_WIDTH),
                        (j * Setting.CELL_HEIGHT),
                        (Setting.CELL_WIDTH - 1),
                        (Setting.CELL_HEIGHT - 1)
                          );
            }
        }
    }
}
