public class Einwohner implements Comparable<Einwohner>{
    int id;
    String name;
    String bundesland;
    int geburtsjahr;

    public Einwohner(int id, String name, String bundesland, int geburtsjahr) {
        this.id = id;
        this.name = name;
        this.bundesland = bundesland;
        this.geburtsjahr = geburtsjahr;
    }

    @Override
    public String toString() {
        return "Einwohner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Bundesland='" + bundesland + '\'' +
                ", Geburtsjahr=" + geburtsjahr +
                '}';
    }

    @Override
    public int compareTo(Einwohner o) {
        if (name.compareTo(o.name) == 0) {
            return Double.compare(id, o.id);
        }
        return name.compareTo(o.name);
    }
}
