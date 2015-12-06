package rubictable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Contains the logic of the Rubic Table game.
 *
 * @author zsmester
 */
public class RubicTableLogic {

    /**
     * The direction of a column or a row.
     */
    public enum Direction {
        POSITIVE, NEGATIVE
    }

    private final List<List<Color>> fields = new ArrayList<>();
    private int size;
    private Map<Color, Integer> colors;
    private int moves;
    private final FinishListener finishListener;

    /**
     * Crates a new Robic Table logic.
     *
     * @param finishListener Called when the game is finished.
     */
    public RubicTableLogic(FinishListener finishListener) {
        this.finishListener = finishListener;
    }

    /**
     * Resets the game based on the given size.
     *
     * @param size The size of the table.
     */
    public void reset(int size) {
        this.size = size;
        colors = new HashMap<>();
        for (int i = 0; i < size; ++i) {
            colors.put(AppConfig.AVAILABLE_COLORS.get(i), 0);
        }
        moves = 0;
        fields.clear();
        for (int row = 0; row < size; ++row) {
            List<Color> currentRow = new ArrayList<>(size);
            for (int column = 0; column < size; ++column) {
                addField(currentRow);
            }
            fields.add(currentRow);
        }
        if (checkHorizontal() || checkVertical()) {
            reset(size);
        }
    }

    private void addField(List<Color> currentRow) {
        Color color = null;
        while (color == null) {
            color = AppConfig.AVAILABLE_COLORS.get(new Random().nextInt(size));
            if (colors.get(color) == size) {
                color = null;
            }
        }
        colors.put(color, colors.get(color) + 1);
        currentRow.add(color);
    }

    /**
     * Gets the color of a field.
     *
     * @param row The field row.
     * @param column The field column.
     * @return The color of the field.
     */
    public Color getField(int row, int column) {
        return fields.get(row).get(column);
    }

    /**
     * Moves the given row to the given direction.
     *
     * @param rowIndex The index of the row.
     * @param direction The direction to move,
     */
    public void moveHorizontal(int rowIndex, Direction direction) {
        move(fields.get(rowIndex), direction);
    }

    /**
     * Moves the given column to the given direction.
     *
     * @param columnIndex The index of the column.
     * @param direction The direction to move,
     */
    public void moveVertical(int columnIndex, Direction direction) {
        List<Color> column = new ArrayList();
        fields.stream().forEach((row) -> {
            column.add(row.get(columnIndex));
        });

        move(column, direction);

        int i = 0;
        for (List<Color> row : fields) {
            row.remove(columnIndex);
            row.add(columnIndex, column.get(i));
            ++i;
        }
    }

    private void move(List<Color> list, Direction direction) {
        ++moves;
        for (int i = 0; i < size - 1; ++i) {
            if (direction == Direction.POSITIVE) {
                Collections.swap(list, i, size - 1);
            } else {
                Collections.swap(list, size - 1 - i, 0);
            }
        }
    }

    /**
     * Gets the number of moves.
     *
     * @return The number of moves.
     */
    public int getMoves() {
        return moves;
    }

    /**
     * Gets the size of the table.
     *
     * @return The size of the table.
     */
    public int getSize() {
        return size;
    }

    /**
     * Use this if you want to get noticed if the game finished.
     */
    public interface FinishListener {

        /**
         * Called when the game is finished.
         */
        void gameFinished();
    }

    /**
     * Check if the game is finished. If finished, class the attached
     * {@link FinishListener}.
     */
    public void checkFinish() {
        if (checkHorizontal() || checkVertical()) {
            finishListener.gameFinished();
        }
    }

    private boolean checkHorizontal() {
        for (List<Color> row : fields) {
            Color first = row.get(0);
            if (!row.stream().allMatch((color) -> color.equals(first))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkVertical() {
        int column = 0;
        while (column < size) {
            for (List<Color> rowI : fields) {
                Color first = rowI.get(column);
                for (List<Color> rowJ : fields) {
                    if (!rowJ.get(column).equals(first)) {
                        return false;
                    }
                }
            }
            ++column;
        }
        return true;
    }
}
