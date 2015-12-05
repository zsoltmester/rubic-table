package rubictable;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

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
        currentRow.add(field);
        add(field);
    }
}
