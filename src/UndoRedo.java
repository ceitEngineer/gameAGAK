import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Created by 9531070 on 5/26/2017.
 */
public class UndoRedo {

    Panel panel;
    Stack<int[][]> Back = new Stack<int[][]>();
    Stack<int[][]> Front = new Stack<int[][]>();

    public UndoRedo(Panel panel) {
        this.panel = panel;
    }

    public void save(int[][] map) {
        Back.push(map);
        for (int i = 0; i < panel.m.height; i++) {
            for (int j = 0; j < panel.m.width; j++) {
               // System.out.print(map[i][j] + " ");
            }
        }
    }

    public void undo() {
        if (!(Back.isEmpty())) {

            int map[][] = Back.pop();

            for (int i = 0; i < panel.m.height; i++) {
                for (int j = 0; j < panel.m.width; j++) {
                    //System.out.print(map[i][j] + " ");
                }
            }
        }

/*            int m [][] = Back.pop();
            Front.push(m);

            for (int i = 0; i < panel.m.height; i++) {
                for (int j = 0; j < panel.m.width; j++) {

                    //System.out.print(m[i][j] + " ");
                    Texture t = panel.m.textures.get(i * panel.m.width + j);

                    // 1 is Texture
                    if (m[i][j] == 1) {
                        t.setType(1);
                    }
                    // 2 is Water
                    else if (m[i][j] == 2) {
                        t.setType(2);
                    }
                    // 3 is Deep Water
                    else if (m[i][j] == 3) {
                        t.setType(3);
                    }
                    // 4 is Tree
                    else if (m[i][j] == 4) {
                        t.setType(4);
                    }
                    // 5 is Gold Mine
                    else if (m[i][j] == 5) {
                        t.setType(5);
                    }
                }
            }*/
    }

    public void redo() {
        if (!(Front.isEmpty())) {
            int[][] m = Front.pop();
            Back.push(m);
        }
    }
}
