package rubictable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class RubicTableGui implements RubicTableLogic.FinishListener {

    private final JFrame frame;
    private RubicTablePanel tablePanel;

    private final RubicTableLogic logic;

    public RubicTableGui() {
        logic = new RubicTableLogic(this);
        frame = new JFrame("Rubic Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initTable();
        initMenu();
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    private void initTable() {
        tablePanel = new RubicTablePanel(AppConfig.DEFAULT_SIZE, logic);
        frame.getContentPane().add(BorderLayout.CENTER, tablePanel);
    }

    private void initMenu() {
        // menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // menu - new game
        JMenu newGameMenu = new JMenu("New Game");
        menuBar.add(newGameMenu);

        // menuitem - easy
        JMenuItem easyMenuItem = new JMenuItem("Easy (2x2)");
        newGameMenu.add(easyMenuItem);
        easyMenuItem.addActionListener((ActionEvent e) -> {
            reset(2);
        });

        // menuitem - medium
        JMenuItem normalMenuItem = new JMenuItem("Normal (4x4)");
        newGameMenu.add(normalMenuItem);
        normalMenuItem.addActionListener((ActionEvent e) -> {
            reset(4);
        });

        // menuitem - hard
        JMenuItem hardMenuItem = new JMenuItem("Hard (6x6)");
        newGameMenu.add(hardMenuItem);
        hardMenuItem.addActionListener((ActionEvent e) -> {
            reset(6);
        });
    }

    private void reset(int size) {
        frame.getContentPane().remove(tablePanel);
        tablePanel = new RubicTablePanel(size, logic);
        frame.getContentPane().add(BorderLayout.CENTER, tablePanel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    @Override
    public void gameFinished() {
        JOptionPane.showMessageDialog(frame, "Moves: " + logic.getMoves(),
                "Win!", JOptionPane.INFORMATION_MESSAGE);
        reset(logic.getSize());
    }
}
