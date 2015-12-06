package rubictable;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import rubictable.RubicTableLogic.Direction;

public class RubicTablePanel extends JPanel {

    private final List<List<JPanel>> fields = new ArrayList<>();
    private int size;

    private RubicTableLogic logic;

    public RubicTablePanel(RubicTableLogic logic) {
        this(AppConfig.DEFAULT_SIZE, logic);
    }

    public RubicTablePanel(int size, RubicTableLogic logic) {
        this.size = size;
        this.logic = logic;
        reset(size);
    }

    public void reset(int size) {
        setLayout(new GridLayout(size, size));
        logic.reset(size);
        initFields();
    }

    private void initFields() {
        fields.clear();
        for (int row = 0; row < size; ++row) {
            List<JPanel> currentContent = new ArrayList<>(size);
            for (int column = 0; column < size; ++column) {
                addField(currentContent, row, column);
            }
            fields.add(currentContent);
        }
    }

    private void addField(List<JPanel> currentRow, int row, int column) {
        JPanel field = new JPanel();
        field.setBackground(logic.getField(row, column));
        field.addMouseListener(new PressAndReleaseListener(row, column));
        currentRow.add(field);
        add(field);
    }

    private void repaintFields() {
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                fields.get(row).get(column)
                        .setBackground(logic.getField(row, column));
            }
        }
    }

    private class PressAndReleaseListener implements MouseListener {

        private final int row;
        private final int column;
        private Point press;

        private PressAndReleaseListener(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Nothing to do
        }

        @Override
        public void mousePressed(MouseEvent e) {
            press = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (press == null) {
                return;
            }

            Point release = e.getPoint();
            double diffX = press.getX() - release.getX();
            double diffY = press.getY() - release.getY();
            if (Math.abs(diffX) >= Math.abs(diffY)) {
                logic.moveHorizontal(row, diffX > 0
                        ? Direction.NEGATIVE : Direction.POSITIVE);
            } else {
                logic.moveVertical(column, diffY > 0
                        ? Direction.NEGATIVE : Direction.POSITIVE);
            }
            repaintFields();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Nothing to do
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Nothing to do
        }
    }
}
