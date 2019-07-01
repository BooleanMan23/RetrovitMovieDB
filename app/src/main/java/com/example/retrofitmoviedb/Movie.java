package com.example.retrofitmoviedb;

public class Movie {

    String title;
    String overview;
    String posterLink;
    String releaseDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterLink() {
        return posterLink;
    }

    public void setPosterLink(String posterLink) {
        this.posterLink = posterLink;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie(String title, String overview, String posterLink, String releaseDate) {
        this.title = title;
        this.overview = overview;
        this.posterLink = posterLink;
        this.releaseDate = releaseDate;
    }
}
