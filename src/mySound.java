import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ABOLFZL on 7/8/2017.
 */
public class mySound{
    AudioStream audioStream;
    FileInputStream inn ;
    mySound(String name){
        try {
            inn = new FileInputStream("sounds\\"+name);
            audioStream = new AudioStream(inn);
        }  catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public void start(){
        AudioPlayer.player.start(audioStream);
    }
    public void stop(){
        AudioPlayer.player.stop(audioStream);

    }
}
