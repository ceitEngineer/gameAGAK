import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ABOLFZL on 7/8/2017.
 */
public class TRT {
    public static void main(String[] args) {
        try {
           FileInputStream inn = new FileInputStream("sounds\\Online.wav");
            AudioStream audioStream1 = new AudioStream(inn);
            AudioPlayer.player.start(audioStream1);
            System.out.println("sstart");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
