import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JnJ {
    public static void main(String[] args) throws IOException {

        String path = "/pathcsv/VoiceMan_03003.csv";
        InputStream is = JnJ.class.getResourceAsStream(path);
        assert is != null;
//        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
//        BufferedReader bufferedReader = new BufferedReader(streamReader);

        Reader targetReader = new InputStreamReader(is);
//        targetReader.close();


//VoiceMan_03003.csv
        CSVReader reader = new CSVReaderBuilder(targetReader).build();
        List<String[]> myEntries = reader.readAll();

        for (int i = 0; i < myEntries.size(); i++) {
//            System.out.println(Arrays.toString(myEntries.get(i))); //TODO USEFUL.
        }

        //array list of string arrays.
        //how do you get the content of a string array?

        for (int i = 0; i < myEntries.size(); i++) {

            String[] currentArray = myEntries.get(i);
            System.out.println(currentArray[2]);
            //Done! Now, what did i want to do with this information?
            //Assign it to  a multidimensional array of Strings.
            //Why? Don't I already have what I want?
            //Search for the string "Estinien". If it does have it, save that row.

            //if "estinien" is found, record the entire row. Assign the contents of everything in that row to a large arrayList of every quote containing that characters name.

        }

//        File fiel = new File(is);
//        System.out.println(fiel.length());

    }

    //split by commas.
    public static String[] csvReaderMethod(String path) {
        String[] returnvalue;
        ArrayList<String> thest = new ArrayList<>();

        String line = "";

        try {
            InputStream is = JnJ.class.getResourceAsStream(path);
            assert is != null;
            InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            while ((line = bufferedReader.readLine()) != null) {

                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (values.length == 1) {
                    thest.set(thest.size() - 1, values[0]);
                } else {
                    thest.add(values[0]);
                }
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
}
