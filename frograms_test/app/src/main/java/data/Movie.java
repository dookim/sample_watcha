package data;

/**
 * Created by dookim on 10/8/15.
 */
public class Movie {
    public String title;
    public int year;
    public String poster;
    public String stillcut;

    Movie(String title, int year, String poster, String stillcut) {
        this.title = title;
        this.year = year;
        this.poster = poster;
        this.stillcut = stillcut;
    }

    @Override
    protected Movie clone()  {
        return new Movie(title, year, poster, stillcut);
    }
}
