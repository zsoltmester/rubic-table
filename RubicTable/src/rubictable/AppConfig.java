package rubictable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains configurations for the Rubic Table game.
 * 
 * @author zsmester
 */
public class AppConfig {
    
    /**
     * The default size of the table.
     */
    public static final int DEFAULT_SIZE = 4;
    
    /**
     * The available colors for the fields.
     */
    public static final List<Color> AVAILABLE_COLORS = new ArrayList<>();
    
    static {
        AVAILABLE_COLORS.add(Color.BLUE);
        AVAILABLE_COLORS.add(Color.GREEN);
        AVAILABLE_COLORS.add(Color.ORANGE);
        AVAILABLE_COLORS.add(Color.PINK);
        AVAILABLE_COLORS.add(Color.RED);
        AVAILABLE_COLORS.add(Color.YELLOW);
    }
}
