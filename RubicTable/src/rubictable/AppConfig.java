package rubictable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class AppConfig {
    
    public static final int DEFAULT_SIZE = 4;
    
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
