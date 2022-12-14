package dialogur;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

//DONE:
/*
1. first, put all the csvs you wish to read into a folder in resources called csv
2. method that makes and returns an array of every csv file name
3. method that iterates through the array of csv file names and reads it into a csv reader, returning an Array`List<String[]>`


 */

//TODO:
/*
4. method that takes the CSVReader `List<String[]>` result and goes through to search for every
instance of the NAME variable. Because this program is searching for the NAME in each of these CSV files.
    1. this method creates a list of all of the occurrences of the NAME, giving the list the entire row’s contents in which it was found.
    2. This list of rows that contain the character’s name is what is returned from this method.
5. method that combines the results of each CSVReader character search row result into one big list.
6. method that organizes the list into to lists, reformatting it, and then building it back up into a string builder and returning this as a string.
7. print out the result of the method that uses a string builder to create a string.
 */
public class Diae {

    public static final String CHAR_NAME = "Estinien";
    public static int total;
    public static int isInFileCount;

    //2. method that makes and returns an array of every csv file name
    public static String[] makeCsvFileList() {
        String csvLocation = returnsFilepath();
        File folder = new File(csvLocation);
        File[] listOfFiles = folder.listFiles();
        String[] listOfFilesAsString = new String[Objects.requireNonNull(folder.listFiles()).length];


        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile()) {
//                System.out.println("File " + listOfFiles[i].getName());
                listOfFilesAsString[i] = listOfFiles[i].getName();
            } else if (listOfFiles[i].isDirectory()) {
//                System.out.println("Directory " + listOfFiles[i].getName());
                listOfFilesAsString[i] = listOfFiles[i].getName();
            }
        }

        return listOfFilesAsString;
    }

    public static String returnsFilepath() {
        String basePath = new File("").getAbsolutePath();
        //example:
        //D:\java-projects\personal\rest\dialoguer\src\main\resources\pathcsv
        //user of code may differ.
        String path = new File(basePath + "/src/main/resources/csvxivapi").getAbsolutePath();
        System.out.println("location of csv files:  " + path);
        System.out.println("---------------------------------------------------------------------------------------------------");
        return path;
    }

    //3. method that iterates through the array of csv file names and reads it into a csv reader, returning an Array`List<String[]>`

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
                is = Diae.class.getResourceAsStream("/csvxivapi/" + path);
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
                if (containsCaseInsensitive(CHAR_NAME,csvContents.get(j)[k])) {
                    if (!alreadyDetected){
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

    //Make an alternative to the method above where it returns the result of all of the row instances of the name instead of the contents of every single csv.

    //4. method that takes the CSVReader `List<String[]>` result and goes through to search for every
    //instance of the NAME variable. Because this program is searching for the NAME in each of these CSV files.


    //


    public static void main(String[] args) {

        String[] csvFileList = makeCsvFileList();
        for (String s : csvFileList) {
            System.out.println(s);
        }
        System.out.println("Loading. . . ");

//        ArrayList<String[]> allCSVText = hard_readCSVFiles(csvFileList);

//        ArrayList<String> allNameInstances = hard_readCSVFiles(csvFileList);
//
//        for (int i = 0; i < allNameInstances.size(); i++) {
//            System.out.println(allNameInstances.get(i));
//        }

        String finalizedResult = hard_readCSVFiles(csvFileList);
        System.out.println(finalizedResult);


        System.out.println("---------------------------------------------------------------------------------------------------\n" + "Done! " +
                /*allNameInstances.size() + */ total + " Instances of the name '" + CHAR_NAME + "' found in " + isInFileCount + " of " + csvFileList.length + " total files.");

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

}
