package com.eightballgirl.dialogueapp;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    public static /*final*/ String CHAR_NAME = "Haurchefant";
    public static boolean useScanner = false;
    public static int total;
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
        String path = new File(basePath + "/src/main/resources/csvxivapi").getAbsolutePath();
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
                is = Main.class.getResourceAsStream("/csvxivapi/" + path);
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

    private static String formatAllNameInstances(ArrayList<String> fullListOfNameInstances) {

        ArrayList<String> chararacterIsSpeaking = new ArrayList<>();
        ArrayList<String> characterIsMentioned = new ArrayList<>();

        StringBuilder formatter = new StringBuilder();

        for (int i = 0; i < fullListOfNameInstances.size(); i++) {
            if (containsSpeakerText(CHAR_NAME, fullListOfNameInstances.get(i))) {
                chararacterIsSpeaking.add(fullListOfNameInstances.get(i));
            } else {
                characterIsMentioned.add(fullListOfNameInstances.get(i));
            }

        }

        formatter.append("Character is speaking:\n");
        for (int i = 0; i < chararacterIsSpeaking.size(); i++) {
            formatter.append(chararacterIsSpeaking.get(i)).append("\n");
        }
        formatter.append("\n");
        formatter.append("Character is mentioned:\n");
        for (int i = 0; i < characterIsMentioned.size(); i++) {
            formatter.append(characterIsMentioned.get(i)).append("\n");
        }
        return formatter.toString();
    }

    public static void collectAllNameInstances(List<String[]> csvContents, ArrayList<String> target) {
        boolean alreadyDetected = false;
        for (int j = 0; j < csvContents.size(); j++) {
            for (int k = 0; k < csvContents.get(j).length; k++) {
                if (containsCaseInsensitive(CHAR_NAME, csvContents.get(j)[k])) {
                    if (!alreadyDetected) {
                        isInFileCount++;
                    }
                    alreadyDetected = true;
                    ArrayList<String> currentCsvNameInstances = new ArrayList<>();
                    StringBuilder textFormatter = new StringBuilder();
                    textFormatter.
                            append(csvContents.get(j)[1] + " -- " + csvContents.get(j)[2])
                    ;
                    target.add(textFormatter.toString());
                    resetStringBuilder(textFormatter);
                }
            }
        }
    }


    public static void main(String[] args) {

        System.out.println("Loading CSV files. . . ");
        String[] csvFileList = makeCsvFileList();
        for (String s : csvFileList) {
            System.out.println(s);
        }
        System.out.println("Loading all dialogue. . . ");

        if (useScanner){
            characterLookup();
        }


        String finalizedResult = hard_readCSVFiles(csvFileList);
        System.out.println(finalizedResult);

        //write finalized result to file location
//        writeToFile(finalizedResult);
        writeToFile(finalizedResult, "D:/java-projects/personal/rest/dialoguer_output/" + CHAR_NAME.toUpperCase() + "_dialogue.txt");


        System.out.println("---------------------------------------------------------------------------------------------------\n" + "Done! " +
                /*allNameInstances.size() + */ total + " Instances of the name '" + CHAR_NAME + "' found in " + isInFileCount + " of " + csvFileList.length + " total files.");

        System.out.println("Powered by xivapi. " + "https://xivapi.com/" +"Created by Sage Belknap.  All Final Fantasy XIV content is property of Square Enix Co., LTD.");
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
        System.out.println("location of '" + CHAR_NAME.toUpperCase() + "_dialogue.txt'" + " file :  ");
        System.out.println(soutpath);
    }

    public static void writeToFile(String finalizedText) {
        String pathname = basePath + "/src/main/output/" + CHAR_NAME.toUpperCase() + "_dialogue.txt";
        Path path = Paths.get(pathname);
        try {
            Files.writeString(path, finalizedText, StandardCharsets.UTF_8);
            System.out.println("Wrote to file:");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not write to file!");
        }
        String soutpath = new File(pathname).getAbsolutePath();
        System.out.println("location of '" + CHAR_NAME.toUpperCase() + "_dialogue.txt'" + " file :  ");
        System.out.println(soutpath);
    }

}
