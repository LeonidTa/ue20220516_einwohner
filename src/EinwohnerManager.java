import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class EinwohnerManager {

    public ArrayList<Einwohner> load() {
        ArrayList<Einwohner> alleEinwohner = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Leo\\Desktop\\JAVA\\ue20220516_einwohner\\testdata-einwohner.csv"))) {

            String line;
            while ((line = br.readLine()) != null) {
                String [] values = line.split(";");
                Einwohner einwohner = new Einwohner( Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]));
                alleEinwohner.add(einwohner);
            }

            Collections.sort(alleEinwohner, new GeburtsjahrDescNameAsc());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alleEinwohner;
    }
}
