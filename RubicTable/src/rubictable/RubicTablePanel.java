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
    
    public RubicTablePanel() {
        this(AppConfig.DEFAULT_SIZE);
    }

    public RubicTablePanel(int size) {
        this.size = size;
        reset(size);
    }
    
    public void reset(int size) {
        setLayout(new GridLayout(size, size));
        initFields();
    }
    
    private void initFields() {
        fields.clear();
        for (int row = 0; row < size; ++row) {
            List<JPanel> rowContent = new ArrayList<>(size);
            for (int column = 0; column < size; ++column) {
                addField(rowContent, row, column);
            }
            fields.add(rowContent);
        }
    }

    private void addField(List<JPanel> rowContent, int row, int column) {
        JPanel field = new JPanel();
        field.setBackground(new Color(
                new Random().nextFloat(),
                new Random().nextFloat(),
                new Random().nextFloat())); // TODO temporary
        rowContent.add(field);
        add(field);
    }
}
