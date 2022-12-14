import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class PCcode {

    public final static String GET_NAME = "Estinien";

    public static void main(String[] args) {

        //make a multidimenstional array
        //assign things to this multidimensional array.

        String[][] testArray = new String[12][2];

        //////initialize the array/////
        for (int i = 0; i < testArray.length; i++) {
            testArray[i][0] = "Speaker:";
        }
        //assign the col 1 contents.
        for (int i = 0; i < testArray.length; i++) {
            testArray[i][1] = "  This is the string for row " + i;
        }

        testArray[2][0] = "ESTINIEN:";
        testArray[5][1] = "Estinien is a good friend of mine.";

        /////////////////////////////////////////////


        //--Printing out the Array--//
//        System.out.println(Arrays.deepToString(testArray));

        //for i j loop / nested for loop
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
                System.out.print(testArray[i][j]);
            }
            System.out.println();
        }

        //Let's say i only wanted row 6's contents.

        System.out.println();
        String resultString = testArray[6][0] + testArray[6][1];
        System.out.println(resultString);


        //now, find the row that "ESTINIEN" is placed inside.

        System.out.println();

        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
//                System.out.println(testArray[i][j]);
                if (testArray[i][j].contains("ESTINIEN")) {
                    System.out.println("Found.");
                }
            }
        }

        //now that it is found, how do i record the row of that location?

        //idea one: make an array of ints

        ArrayList<Integer> rowsFound = new ArrayList<>();
        int tempIntRow = 0;


        //How to ignore case?
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
                if (containsCaseInsensitive("EstiNien", testArray[i][j])) {
                    rowsFound.add(tempIntRow, i);
                    tempIntRow++;
                }
            }
        }
        System.out.println(rowsFound);

        //Okay, but now  I want the contents of the row to be printed out.

        StringBuilder stringOfCurrentCSV = new StringBuilder();

        //add the contents of the test array at the int of the row that was recorded in the rowsFound array.

        for (int i = 0; i < rowsFound.size(); i++) {
//            System.out.println(testArray[rowsFound.get(i)][0]);
            stringOfCurrentCSV.append(testArray[rowsFound.get(i)][0])
                    .append(" -- ")
                    .append(testArray[rowsFound.get(i)][1])
                    .append("\n")
            ;
        }
        System.out.println(stringOfCurrentCSV);


        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("------------------------------");

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        //Now for the extra challenge. How would I do this if there were tow arrays that i want to look at
        //for this information? At what point do i reset the temp variables, and how to I add the final result
        //into one string with a string builder?

        //////initialize the first array/////

        String[][] firstArray = new String[22][2];

        for (int i = 0; i < firstArray.length; i++) {
            firstArray[i][0] = "Speaker:";
        }
        //assign the col 1 contents.
        for (int i = 0; i < firstArray.length; i++) {
            firstArray[i][1] = "  This is the string for row " + i;
        }

        firstArray[6][0] = "ESTINIEN:";
        firstArray[16][1] = "Estinien is not coming with us tonight. He's gone off on his own.";

        /////////////////////////////////////////////

        //////initialize the second array/////

        String[][] secondArray = new String[25][2];

        for (int i = 0; i < secondArray.length; i++) {
            secondArray[i][0] = "Speaker:";
        }
        //assign the col 1 contents.
        for (int i = 0; i < secondArray.length; i++) {
            secondArray[i][1] = "  This is the string for row " + i;
        }

        secondArray[10][0] = "ESTINIEN SAYS:";
        secondArray[7][1] = "Stop being like that, Estinien. Talk to me.";

        /////////////////////////////////////////////

        //what am i trying to accomplish here?
        //I want to combine every row instance if either column contains "estinien" inside of it.
        //I want it to be one big long string.

        //This is my target string:

        //ESTINIEN: --   This is the string for row 6
        //Speaker: -- Estinien is not coming with us tonight. He's gone off on his own.
        //ESTINIEN SAYS: --   This is the string for row 10
        //Speaker: -- Stop being like that, Estinien. Talk to me.

        //String builder builds all of the strings together
        //Arraylist makes a list of every  row that contains "estinien" inside of it.
        //Array list should only belong to a designated array[][] that we are looking at.

        String path = "/pathcsv/VoiceMan_03003.csv";
        String xivpath = "/VoiceMan_03008.csv";


//        System.out.println(Arrays.toString(convertToArray(path)));

        String[] testString = convertToArray(path);


        String[] yass = csvReaderMethod(xivpath); //todo
        for (int i = 0; i < yass.length; i++) {
            System.out.println(yass[i]);
        }

//        csvReaderListConverted("/csv/VoiceMan_03002.csv");
    }

    //put inside:
    //if (values.length == 1) {
    ////                    thest.set((thest.size()-1), thest.get(thest.size()-1) + values[0]);
    //                    System.out.println("Jumped. Line:" + values[0]);
    //                } else {
    public static String[] csvReaderMethod(String path) {
        String[] returnvalue;
        ArrayList<String> thest = new ArrayList<>();

        String line = "";
        String previousLine = "";
//        previousLine = thest.get((thest.size()-1));


        try {
            InputStream is = PCcode.class.getResourceAsStream(path);
            assert is != null;
            InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            //


            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("#")) {
                    line = "#,#,#";
                }
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // > char is to show each line in [0].
                thest.add(">" + values[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        returnvalue = new String[thest.size()];
        for (int i = 0; i < thest.size(); i++) {
            returnvalue[i] = thest.get(i);
        }

        return returnvalue;

    }

    public static String[] csvReaderListConverted(String path) {
        String[] returnvalue;
        ArrayList<String> thest = new ArrayList<>();

        String line = "";

        try {
            InputStream is = PCcode.class.getResourceAsStream(path);
            assert is != null;
            InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            //


            while ((line = bufferedReader.readLine()) != null) {

                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        returnvalue = new String[thest.size()];
        for (int i = 0; i < thest.size(); i++) {
            returnvalue[i] = thest.get(i);
        }

        return returnvalue;

    }

    public static String[] convertToArray(String path) {
        ArrayList<String[]> stringArrayList2d = new ArrayList<>();
        ArrayList<String> thisIsATest = new ArrayList<>();
        String[] returnValue;

        String line = "";


        try {
            InputStream is = PCcode.class.getResourceAsStream(path);
            assert is != null;
            InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);


            while ((line = bufferedReader.readLine()) != null) {
                String[] thing = line.split(",");
                thisIsATest.add(0, thing[1]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < stringArrayList2d.size(); i++) {
            System.out.println(Arrays.toString(stringArrayList2d.get(i)));
        }

        returnValue = new String[thisIsATest.size()];
        for (int i = 0; i < thisIsATest.size(); i++) {
            returnValue[i] = thisIsATest.get(i);
        }


        return returnValue;
    }

    //resources/csv/VoiceMan_03003.csv

    public static StringBuilder searchCSVArray(String[][] csvArray) {
        StringBuilder dialogueWithContents = new StringBuilder();
        ArrayList<Integer> rowsFound = new ArrayList<>();
        int temp = 0;

        for (int i = 0; i < csvArray.length; i++) {
            for (int j = 0; j < csvArray[i].length; j++) {
                if (containsCaseInsensitive("EstiNien", csvArray[i][j])) {
                    rowsFound.add(temp, i);
                    temp++;
                }
            }
        }

        for (int i = 0; i < rowsFound.size(); i++) {
            dialogueWithContents.append(csvArray[rowsFound.get(i)][0])
                    .append(" -- ")
                    .append(csvArray[rowsFound.get(i)][1])
                    .append("\n")
            ;
        }

        return dialogueWithContents;
    }

    public static boolean containsCaseInsensitive(String s, String s2) {
        s2 = s2.toUpperCase(Locale.ROOT);
        s = s.toUpperCase(Locale.ROOT);
        return s2.contains(s);
    }

    public static boolean containsCaseInsensitive(String s, List<String> l) {
        for (String string : l) {
            if (string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
}