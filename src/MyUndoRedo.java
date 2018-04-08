import javax.swing.*;
import java.util.Stack;
/**
 * Created by ABOLFZL on 6/22/2017.
 */
public class MyUndoRedo {
    private static boolean redoable;
    Panel panel;
    Stack<int[]> vecTypes = new Stack<>();
    Stack<int[][]> vecMap = new Stack<>();
    int index = 0;
    int counterUndoCanRedoable = 0;
    int currentIndexSave;

    MyUndoRedo(JPanel panel) {
        this.panel = ((Panel) panel);
    }

    public static boolean isRedoable() {
        return redoable;
    }

    public static void setRedoable(boolean redoable) {

        MyUndoRedo.redoable = redoable;

    }

    public void save() {
        //System.out.println("save vecMap.size():" + vecMap.size());
        //System.out.println("SubMenu.counterUndo " + SubMenu.counterUndo);
        if (SubMenu.counterUndo >= 0) {
            int k = SubMenu.counterUndo;
            int last = vecMap.size();
            for (int i = k + 1; i < last; i++) {
                vecMap.pop();
                vecTypes.pop();
            //    System.out.println("pop size ee " + vecMap.size() + "lsat old  " + last);
            }
        }
        SubMenu.counterUndo = vecMap.size();

        int[] types = new int[panel.m.textures.size()];
        for (int i = 0; i < panel.m.textures.size(); i++)
            types[i] = panel.m.textures.get(i).getType();
        vecTypes.push(types);
        vecMap.push(panel.m.map.mapType);
        //System.out.println("2  SubMenu.counterUndodd " + SubMenu.counterUndo);
        //System.out.println("2  save vecMap.size():" + vecMap.size());
    }

    public void undo(int step) {
       // System.out.println("undo undo undo");
       // System.out.println("undo undo vecMap.size():" + vecMap.size());
        int i = 0;
//        System.out.println("p.redo.vecTypes.size() " + p.redo.vecTypes.size() );
        while (i < panel.m.textures.size() && vecTypes.size() > 0 && step >= 0 && step < vecMap.size()) {
            panel.m.textures.get(i).setType(vecTypes.get(step)[i]);
            i++;
        }
        if (vecTypes.size() > 0 && step >= 0 && step < vecMap.size())
            copyRedoInMap(panel.m.map.mapType, vecMap.elementAt(step));

    }

    private void copyRedoInMap(int[][] mapType, int[][] vector) {
        for (int i = 0; i < mapType.length; i++)
            for (int j = 0; j < mapType[0].length; j++) {
                mapType[i][j] = vector[i][j];
//                System.out.println("mapType     " + mapType[i][j]);
//                System.out.println("[0].length"+mapType[0].length);
            }
    }
}
