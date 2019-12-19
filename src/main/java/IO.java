import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class IO {

    static void fileOutput(ArrayList<Integer> playerScore) throws IOException {
        File file = new File("score.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0; i < playerScore.size(); i++) {
            int scores = playerScore.get(i);
            String string = "【 Игрок " + (i + 1) + " 】 - " + scores + "\n";
            fileOutputStream.write(string.getBytes());
        }
        fileOutputStream.close();
    }
}
