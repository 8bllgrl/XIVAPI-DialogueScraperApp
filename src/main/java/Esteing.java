import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Esteing {

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
        testArray[7][0] = "ESTINIEN";
        testArray[9][1] = "I love Estinien.";

        /////////////////////////////////////////////


        //--Printing out the Array--//
//        System.out.println(Arrays.deepToString(testArray));

/*
        //for i j loop / nested for loop
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
                System.out.print(testArray[i][j]);
            }
            System.out.println();
        }

 */

/*
        //Let's say i only wanted row 6's contents.
        System.out.println();
        String resultString = testArray[6][0] + "--" + testArray[6][1];
        System.out.println(resultString);


*/
        //now, find the row that "ESTINIEN" is placed inside.

        System.out.println();

        /*
        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j < testArray[i].length; j++) {
//                System.out.println(testArray[i][j]);
                if (testArray[i][j].contains("ESTINIEN")) {
                    System.out.println("Found.");
                }
            }
        }

         */


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
            stringOfCurrentCSV
                    .append(testArray[rowsFound.get(i)][0])
                    .append(" -- ")
                    .append(testArray[rowsFound.get(i)][1])
                    .append("\n")
            ;
        }
        System.out.println(stringOfCurrentCSV);


        //make a method that records the entire content of the row of the array to a string array
        //use string builder for that too.

//        ArrayList<String> allInstancesOfName = new ArrayList<>();
//
//        int tempARow = 0;
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < testArray.length; i++) {
//            for (int j = 0; j < testArray[i].length; j++) {
//
//                if (containsCaseInsensitive("EstiNien", testArray[i][j])) {
////                    rowsFound.add(tempIntRow, i);
//                    //add the contents of the entire row ( the strings ) to the all instances of name arraylist
//                    //use string builder to add the col 0 and col 1 together seperated by a "--"
//                    stringBuilder
//                            .append(testArray[i][0] + " -- " + testArray[i][1])
//                    ;
////                    allInstancesOfName.add(testArray[i][j]);
//                    allInstancesOfName.add(stringBuilder.toString());
//                    stringBuilder.replace(0, stringBuilder.length(), "");
//                    tempARow++;
//                }
//            }
//        }
//        tempARow = 0;
//
//        for (int i = 0; i < allInstancesOfName.size(); i++) {
//            System.out.println(allInstancesOfName.get(i));
//        }
//
//
//        System.out.println();
//        System.out.println();
//        //Example of the string builder inside of the loop as well.
//
//        ArrayList<String> allStances = new ArrayList<>();
//
//        int temp = 0;
//
//        for (int i = 0; i < testArray.length; i++) {
//            for (int j = 0; j < testArray[i].length; j++) {
//                StringBuilder sb = new StringBuilder();
//                if (containsCaseInsensitive("EstiNien", testArray[i][j])) {
////                    rowsFound.add(tempIntRow, i);
//                    //add the contents of the entire row ( the strings ) to the all instances of name arraylist
//                    //use string builder to add the col 0 and col 1 together seperated by a "--"
//
//                    sb
//                            .append(testArray[i][0] + " -- " + testArray[i][1])
//                    ;
////                    allInstancesOfName.add(testArray[i][j]);
//                    allStances.add(stringBuilder.toString());
//                    tempARow++;
//                }
//            }
//        }
//        tempARow = 0;
//
//        for (int i = 0; i < allStances.size(); i++) {
//            System.out.println(allStances.get(i));
//        }
//


        System.out.println(
        );
        System.out.println();
        System.out.println("Using method:");
        //example using a method:
        ArrayList<String> testingArraylistOfString = getAllInstancesInsideOfCSV_EXAMPLE(testArray);
        for (int i = 0; i < testingArraylistOfString.size(); i++) {
            System.out.println(testingArraylistOfString.get(i));
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("----");

        //doing this with multiple arrays.


        //todo
        ////instantiate second array//////
        String[][] notherArray = new String[26][2];

        //////initialize the array/////
        for (int i = 0; i < notherArray.length; i++) {
            notherArray[i][0] = "Speaker:";
        }
        //assign the col 1 contents.
        for (int i = 0; i < notherArray.length; i++) {
            notherArray[i][1] = "  This is the string for row " + i;
        }

        notherArray[9][0] = "ESTINIEN:";
        notherArray[5][1] = "Estinien? He's out doing his thing.";
        notherArray[1][0] = "ESTINIEN";
        notherArray[20][1] = "I am Estinien.";
        notherArray[2][1] = "Oh, estinien. shut it.";

        /////////////////////////////////////////////

        //i want to combine two of the method results into one string built result.

        //arraylist of csv's?
        //for loop through the arraylist of csv's?


        //very basic:
        StringBuilder finalResult = new StringBuilder();

        ArrayList<String> one = getAllInstancesInsideOfCSV_EXAMPLE(testArray);
        ArrayList<String> two = getAllInstancesInsideOfCSV_EXAMPLE(notherArray);
//        ArrayList<String>[] ahh = new ArrayList[];
        for (String s : one) {
            finalResult.append(s).append("\n");

        }
        for (String s : two) {
            finalResult.append(s).append("\n");
        }

        System.out.println(finalResult);

        //but hwo can THIS be turned into a method?
        //why would i make this a method?
        //because it is repetitive.


        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("V V V V V V");
        StringBuilder resultingString = new StringBuilder();
        ArrayList<ArrayList<String>> allcsvsinstancesofname = new ArrayList<>();
        allcsvsinstancesofname.add(one);
        //this add method would be like adding a detected CSV to the arraylist of total csvs that the program is going to look through.
        allcsvsinstancesofname.add(two);

        for (ArrayList<String> strings : allcsvsinstancesofname) {
            for (String s : strings) {
                resultingString.append(s).append("\n");
            }
        }
        System.out.println(resultingString);


        //next to do:
        //turn the above text into a method.
        //make it so that speaking text and speaker text are separated

        //would have to return 2 arraylists of strings, by making another arraylist containing an arraylist of strings.
        //assigning contents of 0 to "speaking" and 1 to "spoken of"
        //beingTalkedAbout

        //what methods go inside of which?
        //make the methods first.

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("!!!!!!!!!!!!!!!!");

        //turn into a method.
        //
        //csv detector make a list of csvs . arraylist to be precise. This will be put into a method. this is that method.

        System.out.println(makeFinalString(allcsvsinstancesofname));
        //method to compile all csvs together.
        //what does it take in? what does it return?
        //ideally, doesn't take in anything. it calls a method that returns a list of all of the csv file contents.
        StringBuilder sb = new StringBuilder();
        addToSb(sb);
        System.out.println(sb);
        //Yes.
        ///

        ArrayList<ArrayList<String>> arlist = new ArrayList<>();
        ArrayList<String> one_arrlist = new ArrayList<>();
        one_arrlist.add("Eeejuee");


        addToArraylistofArraylists(arlist);
        addToArraylistofArraylists(arlist, one_arrlist);
        System.out.println(arlist);


    }

    public static void addToArraylistofArraylists(ArrayList<ArrayList<String>> arrayLists, ArrayList<String> additive) {
        arrayLists.add(additive);
    }

    public static void addToArraylistofArraylists(ArrayList<ArrayList<String>> arrayLists) {
        ArrayList<String> two_arrlist = new ArrayList<>();
        two_arrlist.add("Aeeojhh");

        arrayLists.add(two_arrlist);
    }

    public static void addToSb(StringBuilder sb) {
        sb.append("Aaaa");
    }

    public static void method() {

//        createFullCsvList();
//        StringBuilder resultingString = new StringBuilder();
//        ArrayList<ArrayList<String>> superweird = new ArrayList<>();
//        superweird.add(one);
//        //this add method would be like adding a detected CSV to the arraylist of total csvs that the program is going to look through.
//        superweird.add(two);

        //if i make a method that adds to the string builder, does it affect the original result??


    }
    public static String makeFinalString(ArrayList<ArrayList<String>> csvs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ArrayList<String> strings : csvs) {
            for (String s : strings) {
                stringBuilder.append(s).append("\n");
            }
        }
        return stringBuilder.toString();
    }

//    public static ArrayList<ArrayList<String>> getAllInstancesInsideOfCSV_EXAMPLE(String[][] csvTest) {
////make a method that records the entire content of the row of the array to a string array
//        //use string builder for that too.
//
//        ArrayList<String> allInstancesOfName = new ArrayList<>();
//
//        int tempARow = 0;
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < csvTest.length; i++) {
//            for (int j = 0; j < csvTest[i].length; j++) {
//
//                if (containsCaseInsensitive("EstiNien", csvTest[i][j])) {
////                    rowsFound.add(tempIntRow, i);
//                    //add the contents of the entire row ( the strings ) to the all instances of name arraylist
//                    //use string builder to add the col 0 and col 1 together seperated by a "--"
//                    stringBuilder
//                            .append(csvTest[i][0] + " -- " + csvTest[i][1])
//                    ;
////                    allInstancesOfName.add(testArray[i][j]);
//                    allInstancesOfName.add(stringBuilder.toString());
//                    stringBuilder.replace(0, stringBuilder.length(), "");
//                    tempARow++;
//                }
//            }
//        }
//        tempARow = 0;
//
//        return allInstancesOfName;
//    }

    public static ArrayList<String> getAllInstancesInsideOfCSV_EXAMPLE(String[][] csvTest) {
//make a method that records the entire content of the row of the array to a string array
        //use string builder for that too.

        ArrayList<String> allInstancesOfName = new ArrayList<>();

        int tempARow = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < csvTest.length; i++) {
            for (int j = 0; j < csvTest[i].length; j++) {

                if (containsCaseInsensitive("EstiNien", csvTest[i][j])) {
//                    rowsFound.add(tempIntRow, i);
                    //add the contents of the entire row ( the strings ) to the all instances of name arraylist
                    //use string builder to add the col 0 and col 1 together seperated by a "--"
                    stringBuilder
                            .append(csvTest[i][0] + " -- " + csvTest[i][1])
                    ;
//                    allInstancesOfName.add(testArray[i][j]);
                    allInstancesOfName.add(stringBuilder.toString());
                    reserStringBuilder(stringBuilder);
                }
            }
        }
        tempARow = 0;

        return allInstancesOfName;
    }

    public static void reserStringBuilder(StringBuilder stringBuilder){
        stringBuilder.replace(0, stringBuilder.length(), "");
    }
    public static boolean containsCaseInsensitive(String s, String s2) {
        s2 = s2.toUpperCase(Locale.ROOT);
        s = s.toUpperCase(Locale.ROOT);
        return s2.contains(s);
    }
}
