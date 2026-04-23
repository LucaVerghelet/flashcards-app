import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class Main {

    public static <K, V> K getKey(HashMap<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void saveLog (ArrayList<String> log) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = sc.nextLine();
        log.add("File name:");
        log.add(fileName);

        FileWriter writer = new FileWriter(fileName);

        log.add("The log has been saved.");
        log.add("exit");
        log.add("Bye bye!");

        for (String a : log) {
            writer.write(a + "\n");
        }

        System.out.println("The log has been saved.");;
        writer.close();
    }


    public static void resetStats (LinkedHashMap<String, Integer> errorMap, ArrayList<String> log) {
        System.out.println("Card statistics have been reset.");
        log.add("Card statistics have been reset.");
        errorMap.replaceAll((a, v) -> 0);
    }

    public static void hardestCard (LinkedHashMap<String, Integer> errorMap, ArrayList<String> log){
//        System.out.println(errorMap);
        int maxError = 0;
        for (String b : errorMap.keySet()) {
            if (errorMap.get(b) > maxError) {
                maxError = errorMap.get(b);
            }
        }
//        System.out.println(maxError);

        int countErrorTitle = 0;

        for (String a : errorMap.keySet()) {
            if (errorMap.get(a).equals(maxError)) {
                countErrorTitle++;
            }
        }

        if (maxError == 0) {
            System.out.println("There are no cards with errors.");
            log.add("There are no cards with errors.");
        }
        else if (countErrorTitle == 1) {
            System.out.println("The hardest card is \"" + getKey(errorMap, maxError) + "\". You have " + maxError + " errors answering it.");
            log.add("The hardest card is \"" + getKey(errorMap, maxError) + "\". You have " + maxError + " errors answering it.");
        }
        else if (countErrorTitle > 1){
            System.out.print("The hardest cards are ");
            log.add("The hardest cards are ");
            for (String a : errorMap.keySet()) {
                if (errorMap.get(a) == maxError) {
                    System.out.print("\"" + a + "\" ");
                    log.add("\"" + a + "\" ");
                }
            }
            System.out.print(". You have " + maxError + " errors answering them.");
            log.add(". You have " + maxError + " errors answering them.");
            System.out.println();
        }
    }

    public static void importCards (LinkedHashMap<String, String> cardList, LinkedHashMap<String, Integer> errorMap, ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);
        System.out.println("File name: ");
        log.add("File name: ");
        String fileName = sc.nextLine();
        log.add(fileName);
        int count = 0;


        try {
            Scanner fileSc = new Scanner(new File(fileName));
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                String[] splitLine = line.split(" ");
                cardList.put(splitLine[0], splitLine[1]);
                try {
                    errorMap.put(splitLine[0], Integer.valueOf(splitLine[2]));
                } catch (NumberFormatException a) {
                }
                count++;
            }
            System.out.println(count + " cards have been loaded.");
            log.add(count + " cards have been loaded.");
        }
        catch (FileNotFoundException _) {
            System.out.println("File not found!");
            log.add("File not found!");
        }

    }

    public static void exportCards (LinkedHashMap<String, String> cardList, LinkedHashMap<String, Integer> errorMap, ArrayList<String> log) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("File name:");
        String fileName = sc.nextLine();
        log.add("File name:");
        log.add(fileName);
        int count = 0;

        FileWriter writer = new FileWriter(fileName);

        for (String a : cardList.keySet()) {
            writer.write(a + " ");
            writer.write(cardList.get(a) + " " + errorMap.get(a) + "\n");

            count++;
        }

        System.out.println(count + " cards have been saved.");
        log.add(count + " cards have been saved.");
        writer.close();
    }

    public static void exportArgumentCards (LinkedHashMap<String, String> cardList, LinkedHashMap<String, Integer> errorMap, ArrayList<String> log, String fileName) throws IOException {
        log.add("File name:");
        log.add(fileName);
        int count = 0;

        FileWriter writer = new FileWriter(fileName);

        for (String a : cardList.keySet()) {
            writer.write(a + " ");
            writer.write(cardList.get(a) + " " + errorMap.get(a) + "\n");

            count++;
        }

        System.out.println(count + " cards have been saved.");
        log.add(count + " cards have been saved.");
        writer.close();
    }

    public static void importArgumentCards (LinkedHashMap<String, String> cardList, LinkedHashMap<String, Integer> errorMap, ArrayList<String> log, String fileName) {
        log.add("File name: ");
        log.add(fileName);
        int count = 0;


        try {
            Scanner fileSc = new Scanner(new File(fileName));
            while (fileSc.hasNextLine()) {
                String line = fileSc.nextLine();
                String[] splitLine = line.split(" ");
                cardList.put(splitLine[0], splitLine[1]);
                try {
                    errorMap.put(splitLine[0], Integer.valueOf(splitLine[2]));
                } catch (NumberFormatException a) {
                }
                count++;
            }
            System.out.println(count + " cards have been loaded.");
            log.add(count + " cards have been loaded.");
        }
        catch (FileNotFoundException _) {
            System.out.println("File not found!");
            log.add("File not found!");
        }

    }

    public static void addCard (LinkedHashMap<String, String> cardList, ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);
        System.out.println("The card: ");
        String cardTerm = sc.nextLine();
        log.add("The card: ");
        log.add(cardTerm);
        if (cardList.containsKey(cardTerm)) {
            System.out.println("The card \"" + cardTerm + "\" already exists.");
            log.add("The card \"" + cardTerm + "\" already exists.");
            return;
        }
        System.out.println("The definition of the card: ");
        log.add("The definition of the card: ");
        String cardDef = sc.nextLine();
        log.add(cardDef);
        if (cardList.containsValue(cardDef)) {
            System.out.println("The definition \"" + cardDef + "\" already exists.");
            log.add("The definition \"" + cardDef + "\" already exists.");
            return;
        }
        cardList.put(cardTerm, cardDef);
        System.out.println("The pair (\"" + cardTerm + "\":\"" + cardDef + "\") has been added.");
        log.add("The pair (\"" + cardTerm + "\":\"" + cardDef + "\") has been added.");
    }

    public static void removeCard (LinkedHashMap<String, String> cardList, LinkedHashMap<String, Integer> errorMap, ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which card? ");
        String cardTerm = sc.nextLine();
        log.add("Which card? ");
        log.add(cardTerm);
        if (cardList.containsKey(cardTerm)) {
            cardList.remove(cardTerm);
            errorMap.remove(cardTerm);
            System.out.println("The card has been removed.");
            log.add("The card has been removed. ");
            return;
        }
        else {
            System.out.println("Can't remove \"" + cardTerm + "\": there is no such card.");
            log.add("Can't remove \"" + cardTerm + "\": there is no such card.");
            return;
        }
    }

    public static LinkedHashMap<String, Integer> guessCard (LinkedHashMap<String, String> cardList, LinkedHashMap<String, Integer> errorMap, ArrayList<String> log) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many times to ask?");
        int times = sc.nextInt();

        log.add("How many times to ask?");
        log.add(String.valueOf(times));
        sc.nextLine();


        List<String> keyList = new ArrayList<>(cardList.keySet());

        Random random = new Random();
        int countError = 1;

        for (int i = 0; i < times; i++) {
            int number = random.nextInt(cardList.size());
            String key = keyList.get(number);
            String value = cardList.get(key);
            String correctKey = getKey(cardList, value);


            System.out.println("Print the definition of " + "\"" +  correctKey + "\"");
            log.add("Print the definition of " + "\"" +  correctKey + "\"");
            String defGuess = sc.nextLine();
            log.add(defGuess);
            if (defGuess.equals(cardList.get(correctKey))) {
                System.out.println("Correct!");
                log.add("Correct!");
            }
            else if (cardList.containsValue(defGuess)){
                System.out.println("Wrong. The right answer is " + "\"" + cardList.get(correctKey) + "\"" + ", but your definition is correct for \"" + getKey(cardList, defGuess) + "\"");
                log.add("Wrong. The right answer is " + "\"" + cardList.get(correctKey) + "\"" + ", but your definition is correct for \"" + getKey(cardList, defGuess) + "\"");
                if (errorMap.containsKey(correctKey)) {
                    countError = errorMap.get(correctKey);
                    countError++;
                    errorMap.put(correctKey, countError);
                }
                else {
                    errorMap.put(correctKey, countError);
                }
            }
            else {
                System.out.println("Wrong. The right answer is \"" + cardList.get(correctKey) + "\".");
                log.add("Wrong. The right answer is \"" + cardList.get(correctKey) + "\".");
                if (errorMap.containsKey(correctKey)) {
                    countError = errorMap.get(correctKey);
                    countError++;
                    errorMap.put(correctKey, countError);
                }
                else {
                    errorMap.put(correctKey, countError);
                }
            }
        }

        return errorMap;
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        LinkedHashMap<String, String> cardList = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> errorMap = new LinkedHashMap<>();
        ArrayList<String> log = new ArrayList<>();

        String exportFileName = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-import")) {
                importArgumentCards(cardList, errorMap, log, args[i + 1]);
            } else if (args[i].equals("-export")) {
                exportFileName = args[i + 1];
            }
        }

        while (true) {

            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            log.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String option = sc.nextLine();
            log.add(option);

            switch (option) {
                case "add":
                    addCard(cardList, log);
                    break;
                case "remove":
                    removeCard(cardList, errorMap, log);
                    break;
                case "ask":
                    errorMap = guessCard(cardList, errorMap, log);
                    break;
                case "import":
                    importCards(cardList, errorMap, log);
                    break;
                case "export":
                    exportCards(cardList, errorMap, log);
                    break;
                case "hardest card":
                    hardestCard(errorMap, log);
                    break;
                case "reset stats":
                    resetStats(errorMap, log);
                    break;
                case "log":
                    saveLog(log);
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    if (exportFileName != null) {
                        exportArgumentCards(cardList, errorMap, log, exportFileName);
                    }
                    return;
            }
        }
    }
}
