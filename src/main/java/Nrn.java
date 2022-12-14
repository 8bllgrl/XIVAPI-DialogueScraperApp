import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;

public class Nrn {

    public static void main(String[] args) throws IOException {

        CSVReader reader = new CSVReaderBuilder(new FileReader("yourfile.csv")).build();
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            System.out.println(nextLine[0] + nextLine[1] + "etc...");
        }
    }
}
