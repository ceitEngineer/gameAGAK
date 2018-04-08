
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ABOLFZL on 5/28/2017.
 * Created by ABOLFZL on 5/28/2017.
 */
public class SuitablerTiles {
    static private int JMax;
    static private int IMax;

    static Image showImage(Vector<Texture> textures, Texture texture) {
        int mainType = texture.getType();
        int inow=texture.getI();
        int jnow=texture.getJ();
        if (inow==0 || jnow==0 || inow==(Map.width-1) || jnow==(Map.width-1))
            return (new ImageIcon("images\\81\\1111.png").getImage());

        int topRightType = textures.get(indexIJ(inow - 1, jnow)).getType();
        int topLeftType = textures.get(indexIJ(inow, jnow - 1)).getType();
        int downLeftType = textures.get(indexIJ(inow + 1, jnow)).getType();
        int downRightType = textures.get(indexIJ(inow, jnow + 1)).getType();
        if (topRightType==3  || topLeftType==3 || downLeftType==3 || downRightType==3)
            return (new ImageIcon("images\\81\\1111.png").getImage());

        if (mainType == 1) {
            if (topLeftType == 1 && topRightType == 1 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\1111.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\1112.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\1113.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\1121.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\1122.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\1123.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\1131.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\1132.png").getImage());
            else if (topLeftType == 1 && topRightType == 1 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\1133.png").getImage());


            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\1211.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\1212.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\1213.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\1221.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\1222.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\1223.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\1231.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\1232.png").getImage());
            else if (topLeftType == 1 && topRightType == 2 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\1233.png").getImage());


            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\1311.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\1312.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\1313.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\1321.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\1322.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\1323.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\1331.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\1332.png").getImage());
            else if (topLeftType == 1 && topRightType == 3 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\1333.png").getImage());

            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\2111.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\2112.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\2113.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\2121.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\2122.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\2123.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\2131.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\2132.png").getImage());
            else if (topLeftType == 2 && topRightType == 1 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\2133.png").getImage());


            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\2211.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\2212.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\2213.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\2221.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\2222.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\2223.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\2231.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\2232.png").getImage());
            else if (topLeftType == 2 && topRightType == 2 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\2233.png").getImage());


            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\2311.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\2312.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\2313.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\2321.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\2322.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\1123.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\2331.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\2332.png").getImage());
            else if (topLeftType == 2 && topRightType == 3 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\2333.png").getImage());


            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\3111.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\3112.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\3113.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\3121.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\3122.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\3123.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\3131.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\1132.png").getImage());
            else if (topLeftType == 3 && topRightType == 1 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\3133.png").getImage());


            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\3211.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\3212.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\3213.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\3221.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\3222.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\3223.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\3231.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\3232.png").getImage());
            else if (topLeftType == 3 && topRightType == 2 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\3233.png").getImage());


            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 1 && downRightType == 1)
                return (new ImageIcon("images\\81\\3311.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 1 && downRightType == 2)
                return (new ImageIcon("images\\81\\3312.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 1 && downRightType == 3)
                return (new ImageIcon("images\\81\\3313.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 2 && downRightType == 1)
                return (new ImageIcon("images\\81\\3321.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 2 && downRightType == 2)
                return (new ImageIcon("images\\81\\3322.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 2 && downRightType == 3)
                return (new ImageIcon("images\\81\\3323.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 3 && downRightType == 1)
                return (new ImageIcon("images\\81\\3331.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 3 && downRightType == 2)
                return (new ImageIcon("images\\81\\3332.png").getImage());
            else if (topLeftType == 3 && topRightType == 3 && downLeftType == 3 && downRightType == 3)
                return (new ImageIcon("images\\81\\3333.png").getImage());
        }
        return null;

    }

    static private int indexIJ(int i, int j) {
        return (i * Map.width + j);
    }


}
