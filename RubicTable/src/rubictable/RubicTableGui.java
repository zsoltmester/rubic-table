package rubictable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class RubicTableGui implements Runnable {
    
    private final JFrame frame;
    private RubicTablePanel tablePanel;
    
    public RubicTableGui() {
        frame = new JFrame("Rubic Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initTable();
        initMenu();
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
    
    private void initTable() {
        RubicTableLogic logic = new RubicTableLogic();
        tablePanel = new RubicTablePanel(logic);
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
             // TODO
        });
        
        // menuitem - medium
        JMenuItem normalMenuItem = new JMenuItem("Normal (4x4)");
        newGameMenu.add(normalMenuItem);
        normalMenuItem.addActionListener((ActionEvent e) -> {
             // TODO
        });
        
        // menuitem - hard
        JMenuItem hardMenuItem = new JMenuItem("Hard (6x6)");
        newGameMenu.add(hardMenuItem);
        hardMenuItem.addActionListener((ActionEvent e) -> {
             // TODO
        });
    }
    
    @Override
    public void run() {
        // TODO
    }
}
