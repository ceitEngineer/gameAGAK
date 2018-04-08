import sun.misc.Cache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by AK on 06/05/2017.
 * this class just gives an address and then read the map from that address and run the map
 */

public class MapReader {

    Map map;
    FileInputStream fin = null;
    ObjectInputStream ois = null;

    public MapReader(String str) {

        try {
            fin = new FileInputStream(str);
            ois = new ObjectInputStream(fin);
            map = (Map) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //run the map
        new MapInterpreter(map);
    }
}
