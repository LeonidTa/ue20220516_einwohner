import java.util.Comparator;

public class GeburtsjahrDescNameAsc implements Comparator<Einwohner> {
    @Override
    public int compare(Einwohner o1, Einwohner o2) {
        if (Double.compare(o1.geburtsjahr, o2.geburtsjahr) == 0) {
           return o1.name.compareTo(o2.name);
        }
           return Double.compare(o2.geburtsjahr, o1.geburtsjahr);
    }
}
