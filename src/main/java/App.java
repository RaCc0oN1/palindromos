import java.io.*;
import java.util.ArrayList;

public class App {
    ArrayList<Integer> playersScore = new ArrayList(); // min max = 5;
    ArrayList words = new ArrayList(); // infinity
    ArrayList<String> buttons = new ArrayList<String>();

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    int playerI = 0;

    void main() throws IOException {
        mustHaveToStart();
        boolean loop = true;
        while (loop) {
            switch (chooseButton()) {
                case 1:
                    rules();
                    break;
                case 2:
                    typeWords();
                    break;
                case 3:
                    score();
                    break;
                case 4:
                    printUsedWords();
                    break;
                case 5:
                    loop = false;
                    break;
            }
        }
    }

    void fileOutput() throws IOException {
        File file = new File("C:\\Users\\Динара\\IdeaProjects\\palindromos\\src\\main\\java\\score.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int i = 0; i < playersScore.size(); i++) {
            int scores = playersScore.get(i);
            String string = "【 Игрок " + (i + 1) + " 】 - " + scores + "\n";
            fileOutputStream.write(string.getBytes());
        }
        fileOutputStream.close();
    }

    //-----------------------------------------------------------------\\

    void typeWords() throws IOException {
        System.out.print("【 Введите слово 】 - ");
        String word = input.readLine().toLowerCase();
        String wordWithoutSpace = word.replaceAll(" ", "").trim();
        if (word.matches("[а-я\\s2]+") || word.matches("[a-z\\sA-Z]+") || wordWithoutSpace.matches("[а-яА-Я]+") || wordWithoutSpace.matches("[a-z\\sA-Z]+")) {
            StringBuilder reverseWord = new StringBuilder();
            reverseWord.append(wordWithoutSpace);
            reverseWord = reverseWord.reverse();
            wordCheaker(word, wordWithoutSpace, reverseWord);
        } else {
            System.out.println("【 Нельзя использовать цифры 】");
            typeWords();
        }
    }

    int wordCheaker(String word, String wordA, StringBuilder wordB) throws IOException {
        if (wordA.equalsIgnoreCase(String.valueOf(wordB))) {
            if (usedWords(wordA.replaceAll(" ", ""))) {
                words.add(word.trim());
                scoreAdder(wordA.replaceAll(" ", "").length());
            } else {
                System.out.println("【 Слово/фраза уже было использованно 】");
                typeWords();
            }
        } else {
            System.out.println("【 Слово/фраза не подходит 】");
            typeWords();
        }
        return 0;
    }

    void scoreAdder(int score) throws IOException {
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

    //-----------------------------------------------------------------\\
    void score() {
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
        System.out.println("【 Таблица Игроков: 】");

        for (int i = 0; i < playersScore.size(); i++) {
            System.out.println("【 Игрок " + (i + 1) + " 】 - " + playersScore.get(i));
        }
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
    }

    boolean usedWords(String word) {
        for (int i = 0; i < words.size(); i++) {
            if (word.equals(words.get(i))) {
                return false;
            } else {
                return true;
            }

        }
        return true;
    }

    void printUsedWords() {
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
        System.out.println("【 Использованные слова: 】");
        for (int i = 0; i < words.size(); i++) {
            System.out.println("【─═ " + words.get(i) + " 】");
        }
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
    }

    //-----------------------------------------------------------------\\

    void rules() {
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
        System.out.println("【 Игра Палиндром 】 \n【 Фраза/слово , будет являться палиндромом, если читаться будет одинаково в обоих направлениях 】 \n【 Пример: 】\n【─═ Топот】\n【─═ А роза упала на лапу Азора】");
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
    }

    int chooseButton() throws IOException {
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
        System.out.println("【 Правила 】\t【 Ввести Слово 】\t【 Счет 】\t【 Использованные Слова 】\t【 Выход 】\t\n●【 Или введите число от 1 до 5 】●");
        System.out.println("• ──────────────────────────────────────────────────────────────────────────────────────────────────────── •");
        System.out.print("【 Вводите 】 - ");
        String pressedButton = input.readLine().trim();
        if (isInteger(pressedButton)) {
            int buttonNumber = Integer.valueOf(pressedButton);
            if (buttonNumber >= 1 && buttonNumber <= 5) {
                return buttonNumber;
            } else {
                System.out.println("【 Вы ввели недопустимое значение! Попробуйте ввести значение повторно 】");
                chooseButton();
            }
        } else {
            pressedButton = pressedButton.toLowerCase().trim();
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).equals(pressedButton)) {
                    int buttonNumber = buttons.indexOf(pressedButton) + 1;
                    return buttonNumber;
                }

            }
        }
        System.out.println("【 Вы ввели недопустимое значение! Попробуйте ввести значение повторно 】");
        chooseButton();
        return 0;
    }

    void mustHaveToStart() {
        playersScore.add(0);
        playersScore.add(0);
        playersScore.add(0);
        playersScore.add(0);
        playersScore.add(0);

        buttons.add("правила");
        buttons.add("ввести слово");
        buttons.add("счет");
        buttons.add("использованные слова");
    }

    boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
