package Statics;

import java.awt.*;

/**
 * Created by ABOLFZL on 7/9/2017.
 */
public class Dim {
    private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WS= (int) dimension.getWidth();
    public static int HS= (int) dimension.getHeight();
    public static Dimension ScreenDim=new Dimension(((int) WS), ((int) HS));
}
