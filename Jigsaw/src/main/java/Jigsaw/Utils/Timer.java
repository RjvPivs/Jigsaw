package Jigsaw.Utils;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/***
 * Timer class.
 */
public class Timer extends Thread {
    /**
     * Time that paced since the game started.
     */
    int time;
    /**
     * The text field we should change.
     */
    TextField timer;
    /**
     * Variable-checker.
     */
    static boolean stopped;

    public Timer(TextField timer) {
        this.timer = timer;
        time = 0;
        stopped = true;
    }

    @Override
    public void run() {
        while (stopped) {
            try {
                sleep(1000);
                time++;
                int min = time / 60, sec = time % 60;
                Platform.runLater(() -> timer.setText("Minutes: " + min + ", seconds: " + sec));
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * A method to stop the thread in case of an exit earlier then the game ended.
     */
    public static void Stopping() {
        stopped = false;
    }
}
