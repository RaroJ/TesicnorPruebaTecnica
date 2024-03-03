package com.harrypotterapp.tesicnorpruebatecnica.DB;

public class HPMovie {
    String ID;
    String Title;
    int Year;
    String imgUrl;
    int userRating;
    String sinopsis;

    public HPMovie(String ID, String title, int year, String imgUrl, int userRating, String sinopsis) {
        this.ID = ID;
        Title = title;
        Year = year;
        this.imgUrl = imgUrl;
        this.userRating = userRating;
        this.sinopsis = sinopsis;
    }
    public HPMovie(String ID, String title, int year, String imgUrl,String sinopsis) {
        this.ID = ID;
        Title = title;
        Year = year;
        this.imgUrl = imgUrl;
        this.userRating = 0;
        this.sinopsis = sinopsis;
    }

    public HPMovie() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @Override
    public String toString() {
        return "HPMovie{" +
                "ID='" + ID + '\'' +
                ", Title='" + Title + '\'' +
                ", Year=" + Year +
                ", imgUrl='" + imgUrl + '\'' +
                ", userRating=" + userRating +
                '}';
    }
}
