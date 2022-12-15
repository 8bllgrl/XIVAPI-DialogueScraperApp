package com.eightballgirl.dialogueapp.version_2;

import com.eightballgirl.dialogueapp.version_1.DialogueFinderV1;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


//one of the versions should extend the other and override the methods that need changed.
public class DialoguerFinderV2 {

    public static /*final*/ String CHAR_NAME = "Estinien";
    public static final int DIALOGUE_COLUMN = 2;
    public static final int SPEAKER_COLUMN = 1;
    public static boolean useScanner = false;
    public static int total;
    public static ArrayList<String> fullAbsoluteListOfFiles = new ArrayList<>();
    public static int isInFileCount;
    public static String basePath = new File("").getAbsolutePath();

    // method that makes and returns an array of every csv file name
    public static String[] makeCsvFileList() {
        String csvLocation = returnsFilepath();
        File folder = new File(csvLocation);
        File[] listOfFiles = folder.listFiles();
        String[] listOfFilesAsString = new String[Objects.requireNonNull(folder.listFiles()).length];

        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile()) {
                listOfFilesAsString[i] = listOfFiles[i].getName();
            } else if (listOfFiles[i].isDirectory()) {
                listOfFilesAsString[i] = listOfFiles[i].getName();
            }
        }

        return listOfFilesAsString;
    }

    public static String returnsFilepath() {
        //example:
        //D:\java-projects\personal\rest\dialoguer\src\main\resources\pathcsv
        //user of code may differ.
        String path = new File(basePath + "\\src\\main\\resources\\csvxivapi").getAbsolutePath();
        System.out.println("location of csv files:  " + path);
        System.out.println("---------------------------------------------------------------------------------------------------");
        return path;
    }

    public static String returnsFilepathExtended() {
        //example:
        //D:\java-projects\personal\rest\dialoguer\src\main\resources\pathcsv
        //user of code may differ.
        String path = new File(basePath + "\\src\\main\\resources\\csvdirectory").getAbsolutePath();
        System.out.println("location of csv files:  " + path);
        System.out.println("---------------------------------------------------------------------------------------------------");
        return path;
    }

    // method that iterates through the array of csv file names and reads it into a csv reader, returning an Array`List<String[]>`
    public static String hard_readCSVFiles(String[] csvFileList) {
        String result = "";
        ArrayList<String> fullListOfNameInstances = new ArrayList<>();
        ArrayList<String[]> returnValue = new ArrayList<>();
        String path;
        InputStream is;

        try {
            for (int i = 0; i < csvFileList.length; i++) {
                //get the filepath of the file name.
                path = csvFileList[i];
                is = DialogueFinderV1.class.getResourceAsStream("/csvxivapi/" + path);
                assert is != null;
                Reader targetReaderConvert = new InputStreamReader(is);

                CSVReader reader = new CSVReaderBuilder(targetReaderConvert).build();
                List<String[]> currentCSVContents = reader.readAll();

                collectAllNameInstances(currentCSVContents, fullListOfNameInstances);
                total = fullListOfNameInstances.size();
                result = formatAllNameInstances(fullListOfNameInstances);
            }

        } catch (Exception e) {
            System.out.println("Failure inside of the method for iterating through the string array of csv names.");
            e.printStackTrace();
        }

        return result;
    }

    public static String extended_readCSVFiles(String[] csvFileList) {
        String result = "";
        //to format correctly, turn this into a string[]
        ArrayList<String> fullListOfNameInstances = new ArrayList<>();
        ArrayList<String[]> returnValue = new ArrayList<>();
        String path;
        InputStream is;

        try {
            for (int i = 0; i < csvFileList.length; i++) {
                //get the filepath of the file name.
                path = csvFileList[i];

                CSVReader reader = new CSVReaderBuilder(new FileReader(path)).build();
                List<String[]> currentCSVContents = reader.readAll();

                collectAllNameInstances(currentCSVContents, fullListOfNameInstances);
                total = fullListOfNameInstances.size();
                result = formatAllNameInstances(fullListOfNameInstances);
            }

        } catch (Exception e) {
            System.out.println("Failure inside of the method for iterating through the string array of csv names.");
            e.printStackTrace();
        }

        return result;
    }

    private static String formatAllNameInstances(ArrayList<String> fullListOfNameInstances) {
        //for v2
        ArrayList<String> cutsceneOccurrence = new ArrayList<>();
        ArrayList<String> characterIsMentioned = new ArrayList<>();

        StringBuilder formatter = new StringBuilder();

        for (int i = 0; i < fullListOfNameInstances.size(); i++) {
            if (fullListOfNameInstances.get(i).contains("VOICEMAN")) {
                cutsceneOccurrence.add(fullListOfNameInstances.get(i));
            } else {
                characterIsMentioned.add(fullListOfNameInstances.get(i));
            }

        }

        for (int i = 0; i < cutsceneOccurrence.size(); i++) {
            formatter.
                    append(" ").
                    append(cutsceneOccurrence.get(i)).append("\n")
            ;
        }
        formatter.append("\n");
        for (int i = 0; i < characterIsMentioned.size(); i++) {
            formatter.
                    append(" ").
                    append(characterIsMentioned.get(i)).append("\n")
            ;
        }
        return formatter.toString();
    }

    public static void collectAllNameInstances(List<String[]> csvContents, ArrayList<String> target) {
        boolean alreadyDetected = false;
        for (int j = 0; j < csvContents.size(); j++) {

            if (containsCaseInsensitive(CHAR_NAME, csvContents.get(j)[1])) {
                if (!alreadyDetected) {
                    isInFileCount++;
                }
                alreadyDetected = true;
                ArrayList<String> currentCsvNameInstances = new ArrayList<>();
                StringBuilder textFormatter = new StringBuilder();

                //making sure that errors are accounted for, like WOL or special characters.
                while (csvContents.get(j)[DIALOGUE_COLUMN].contains("<If(PlayerParameter(4))>her<Else/>his</If>")) {
                    csvContents.get(j)[DIALOGUE_COLUMN] = csvContents.get(j)[DIALOGUE_COLUMN].replace("<If(PlayerParameter(4))>her<Else/>his</If>", "[his/her]");
                }
                while (csvContents.get(j)[DIALOGUE_COLUMN].contains("─")) {
                    csvContents.get(j)[DIALOGUE_COLUMN] = csvContents.get(j)[DIALOGUE_COLUMN].replace("─", "--");
                }
                while (csvContents.get(j)[DIALOGUE_COLUMN].contains("â\u20AC€")) {
                    csvContents.get(j)[DIALOGUE_COLUMN] = csvContents.get(j)[DIALOGUE_COLUMN].replace("â\u20ac€", "-");
                }
                while (csvContents.get(j)[DIALOGUE_COLUMN].contains("<Highlight>ObjectParameter(1)</Highlight>") || csvContents.get(j)[DIALOGUE_COLUMN].contains("<Split(<Highlight>ObjectParameter(1)</Highlight>, ,1)/>")){
                    csvContents.get(j)[DIALOGUE_COLUMN] = csvContents.get(j)[2].replace("<Split(<Highlight>ObjectParameter(1)</Highlight>, ,1)/>", "[WARRIOR OF LIGHT]");

                    csvContents.get(j)[DIALOGUE_COLUMN] = csvContents.get(j)[2].replace("<Highlight>ObjectParameter(1)</Highlight>", "[WARRIOR OF LIGHT]");

                }

                if (csvContents.get(j)[SPEAKER_COLUMN].contains("VOICEMAN")) {
                    textFormatter.append("C>> ");
                } else {
                    textFormatter.append("Q>> ");
                }
                textFormatter.

                        append(csvContents.get(j)[DIALOGUE_COLUMN])
                        .append("\n")
                ;
                target.add(textFormatter.toString());
                resetStringBuilder(textFormatter);

            }
        }
    }

    private static void characterLookup() {
        System.out.println();
        System.out.println("Welcome to my ffx|v dialogue scraper");
        System.out.println("What is the name of the character you wish to look up?");
        Scanner scanner = new Scanner(System.in);
        CHAR_NAME = scanner.nextLine();
    }

    public static void resetStringBuilder(StringBuilder stringBuilder) {
        stringBuilder.replace(0, stringBuilder.length(), "");
    }

    public static boolean containsCaseInsensitive(String s, String s2) {
        s2 = s2.toUpperCase(Locale.ROOT);
        s = s.toUpperCase(Locale.ROOT);
        return s2.contains(s);
    }

    public static boolean containsSpeakerText(String character, String stringinput) {
        character = character.toUpperCase(Locale.ROOT);
        return stringinput.contains(character);
    }

    public static void writeToFile(String finalizedText, String desiredPath) {
        //specify path:
        Path path = Paths.get(desiredPath);
        try {
            Files.writeString(path, finalizedText, StandardCharsets.UTF_8);
            System.out.println("Wrote to file:");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not write to file!");
        }
        String soutpath = new File(desiredPath).getAbsolutePath();
        System.out.println("location of '" + CHAR_NAME.toUpperCase() + "_dialogue_abridged.txt'" + " file :  ");
        System.out.println(soutpath);
    }

    public static void writeToFile(String finalizedText) {
        String pathname = basePath + "\\src\\main\\java\\com\\eightballgirl\\dialogueapp\\version_2\\output\\" + CHAR_NAME.toUpperCase() + "_dialogueAbridged.txt";
        Path path = Paths.get(pathname);
        try {
            Files.writeString(path, finalizedText, StandardCharsets.UTF_8);
            System.out.println("Wrote to file:");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not write to file!");
        }
        String soutpath = new File(pathname).getAbsolutePath();
        System.out.println("location of '" + CHAR_NAME.toUpperCase() + "_dialogueAbridged.txt'" + " file :  ");
        System.out.println(soutpath);
    }

    //TODO: This needs to be finished.
    public static void chooseFileLocation() {
        //
        //unfinished method.
        //
        //Goal:
        //make it so that the user picks a directory in which the finalized string is written to.
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(basePath));
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(frame);
        File fileToSave = null; // for being able to reference the file name.
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            //put something here.
        }
        frame.dispose();
    }

    static void recursiveWrite(File[] arr, int index, int level) {
// terminate condition
        if (index == arr.length) {
            return;
        }
        // for files
        if (arr[index].isFile()) {
//            System.out.println(arr[index].getName());
            fullAbsoluteListOfFiles.add(arr[index].getAbsolutePath());
        }


        // for sub-directories
        else if (arr[index].isDirectory()) {
            // recursion for sub-directories
            recursiveWrite(arr[index].listFiles(), 0,
                    level + 1);
        }
        // recursion for main directory
        recursiveWrite(arr, ++index, level);
    }

    static String[] extendedMakeCsvFileList() {
        String[] returnStringArray = null;
        String csvLocation = returnsFilepathExtended();
        File folderDirectory = new File(csvLocation);
        if (folderDirectory.exists() && folderDirectory.isDirectory()) {
            // array for files and sub-directories
            // of directory pointed by maindir
            File arr[] = folderDirectory.listFiles();

            // Calling recursive method
            recursiveWrite(arr, 0, 0);

            returnStringArray = new String[fullAbsoluteListOfFiles.size()];
            for (int i = 0; i < fullAbsoluteListOfFiles.size(); i++) {
                returnStringArray[i] = fullAbsoluteListOfFiles.get(i);
            }
        }
        return returnStringArray;
    }

    public static void main(String[] args) {

        System.out.println(basePath);

        //make a recursive method that makes the csv list into a more precise version.


        //

        System.out.println("Loading CSV files. . . ");
//        String[] csvFileList = makeCsvFileList();
        String[] csvFileList = extendedMakeCsvFileList();

        for (String s : csvFileList) {
            System.out.println(s);
        }
        System.out.println("Loading all dialogue. . . ");

        if (useScanner) {
            characterLookup();
        }


//        String finalizedResult = hard_readCSVFiles(csvFileList);
        String finalizedResult = extended_readCSVFiles(csvFileList);


        //separate by cutscene or by quest.
        System.out.println(finalizedResult);

        writeToFile(finalizedResult);

        //write finalized result to file location

//        writeToFile(finalizedResult, basePath + "\\src\\main\\java\\com\\eightballgirl\\dialogueapp\\version_2\\output\\" + CHAR_NAME.toUpperCase() + "_dialogue_abridged.txt");

        System.out.println("---------------------------------------------------------------------------------------------------\n" + "Done! " +
                /*allNameInstances.size() + */ total + " Instances of the name '" + CHAR_NAME + "' found in " + isInFileCount + " of " + csvFileList.length + " total files.");
//        System.out.println("Location of file:");
//        System.out.println();
        System.out.println("Powered by xivapi. " + "https://xivapi.com/" + "Created by Sage Belknap.  All Final Fantasy XIV content is property of Square Enix Co., LTD.");
        System.out.println("\u20AC");
    }

}

//to return diff between cutscene and quest just look for VOICEMAN (cutscene)
