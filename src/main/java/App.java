import java.io.*;
import java.util.ArrayList;

public class App {
    private ArrayList<Integer> playersScore = new ArrayList(); // min max = 5;
    private ArrayList words = new ArrayList(); // infinity


   private int playerI = 0;

    public static void main(String[] args) throws IOException {
 App app = new App();
 app.mustHaveToStart();
 app.typeWords("ара");
 app.typeWords("ара");
 app.typeWords("арра");
 app.typeWords("ааа");
 app.typeWords("123");
 app.typeWords("вав");
 app.typeWords("сас");
 app.typeWords("саас");
    }



    //-----------------------------------------------------------------\\

    public void typeWords(String word) throws IOException {
        String wordWithoutSpace = word.replaceAll(" ", "").trim();
        if (word.matches("[а-я\\s2]+") || word.matches("[a-z\\sA-Z]+") || wordWithoutSpace.matches("[а-яА-Я]+") || wordWithoutSpace.matches("[a-z\\sA-Z]+")) {
            StringBuilder reverseWord = new StringBuilder();
            reverseWord.append(wordWithoutSpace);
            reverseWord = reverseWord.reverse();
            wordCheaker(word, wordWithoutSpace, reverseWord);
        } else {
            System.out.println("【 Нельзя использовать цифры - попробуйте повторно 】");
        }
    }

    private int wordCheaker(String word, String wordA, StringBuilder wordB) throws IOException {
        if (wordA.equalsIgnoreCase(String.valueOf(wordB))) {
            if (usedWords(wordA.replaceAll(" ", ""))) {
                words.add(word.trim());
                scoreAdder(wordA.replaceAll(" ", "").length());
            } else {
                System.out.println("【 Слово/фраза уже было использованно -  попробуйте повторно 】");
            }
        } else {
            System.out.println("【 Слово/фраза не подходит -  попробуйте повторно 】");
        }
        return 0;
    }

    private void scoreAdder(int score) throws IOException {
        int saveFromOut = score;
        if (playerI <= 5) {
            try {
                playersScore.set(playerI, playersScore.get(playerI) + score);
                playerI++;
                fileOutput();
            } catch (IndexOutOfBoundsException e) {
                playerI = 0;
                scoreAdder(saveFromOut);
            }
        }
    }

    private boolean usedWords(String word) {
        for (int i = 0; i < words.size(); i++) {
            if (word.equals(words.get(i))) {
                return false;
            } else {
                return true;
            }

        }
        return true;
    }


    //-----------------------------------------------------------------\\


    public void mustHaveToStart() {
        playersScore.add(0);
        playersScore.add(0);
        playersScore.add(0);
        playersScore.add(0);
        playersScore.add(0);

    }

    private void fileOutput() throws IOException {
        File file = new File("score.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0; i < playersScore.size(); i++) {
            int scores = playersScore.get(i);
            String string = "【 Игрок " + (i + 1) + " 】 - " + scores + "\n";
            fileOutputStream.write(string.getBytes());
        }
        fileOutputStream.close();
    }

}
