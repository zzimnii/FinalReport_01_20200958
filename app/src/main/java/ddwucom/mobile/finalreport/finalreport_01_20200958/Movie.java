package ddwucom.mobile.finalreport.finalreport_01_20200958;
import java.io.Serializable;

public class Movie implements Serializable {
    long _id;
    String title;
    String actor;
    String director;
    String review;

    public Movie(String title, String actor, String director, String review) {
        this.title = title;
        this.actor = actor;
        this.director = director;
        this.review = review;
    }

    public Movie(long _id, String title, String actor, String director, String review) {
        this._id = _id;
        this.title = title;
        this.actor = actor;
        this.director = director;
        this.review = review;
    }

    public long get_id() { return _id; }

    public void set_id(long _id) { this._id = _id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getActor() { return actor; }

    public void setActor(String actor) { this.actor = actor; }

    public String getDirector() { return director; }

    public void setDirector(String director) { this.director = director; }

    public String getReview() { return review; }

    public void setReview(String review) { this.review = review; }
}

