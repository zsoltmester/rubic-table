package rubictable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RubicTableLogic {

    private final List<List<Color>> fields = new ArrayList<>();
    private int size;
    private List<Color> colors;

    public void reset(int size) {
        this.size = size;
        colors = AppConfig.AVAILABLE_COLORS.subList(0, size);
        fields.clear();
        for (int row = 0; row < size; ++row) {
            List<Color> currentRow = new ArrayList<>(size);
            for (int column = 0; column < size; ++column) {
                addField(currentRow, column);
            }
            fields.add(currentRow);
        }
    }

    private void addField(List<Color> currentRow, int column) {
        List<Color> possibleColors = colors.stream()
                .filter((color) -> !currentRow.contains(color))
                .filter((color) -> (fields.stream().noneMatch((row)
                        -> (row.get(column).equals(color)))))
                .collect(Collectors.toList());
        currentRow.add(possibleColors.get(new Random().nextInt(possibleColors.size())));
    }

    public Color getField(int row, int column) {
        return fields.get(row).get(column);
    }
}
