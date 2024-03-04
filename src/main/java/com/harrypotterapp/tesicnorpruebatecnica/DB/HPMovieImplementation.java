package com.harrypotterapp.tesicnorpruebatecnica.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class HPMovieImplementation implements HPMovieDAO{
    static Connection con
            = DatabaseConnection.getConnection();
    @Override
    public int add(HPMovie movie) throws SQLException {
        String query
                = "insert into harrypottermovies(IMDBID, Title, Year, ImgUrl, userRating,"
                + "plot) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, movie.getID());
        ps.setString(2, movie.getTitle());
        ps.setInt(3, movie.getYear());
        ps.setString(4, movie.getImgUrl());
        ps.setInt(5, movie.getUserRating());
        ps.setString(6, movie.getSinopsis());

        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void delete(String id) throws SQLException {
        String query
                = "delete from harrypottermovies where IMDBID =?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    @Override
    public HPMovie getMovie(String id) throws SQLException {
        String query
                = "select * from harrypottermovies where IMDBID= ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, id);
        HPMovie movie = new HPMovie();
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        while (rs.next()) {
            check = true;
            movie.setID(rs.getString("IMDBID"));
            movie.setTitle(rs.getString("Title"));
            movie.setYear(rs.getInt("Year"));
            movie.setImgUrl(rs.getString("ImgUrl"));
            movie.setUserRating(rs.getInt("userRating"));
            movie.setSinopsis(rs.getString("plot"));
        }
        if (check) {
            return movie;
        }
        else
            return null;
    }

    @Override
    public List<HPMovie> getMovies() throws SQLException {
        String query = "select * from harrypottermovies";
        PreparedStatement ps
                = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<HPMovie> ls = new ArrayList();

        while (rs.next()) {
            HPMovie movie = new HPMovie();
            movie.setID(rs.getString("IMDBID"));
            movie.setTitle(rs.getString("Title"));
            movie.setYear(rs.getInt("Year"));
            movie.setImgUrl(rs.getString("ImgUrl"));
            movie.setUserRating(rs.getInt("userRating"));
            movie.setSinopsis(rs.getString("plot"));
            ls.add(movie);
        }
        return ls;
    }

    @Override
    public void update(HPMovie movie) throws SQLException {
        String query
                = "update harrypottermovies set Title=?, Year=?, ImgUrl=?, userRating=?, "
                + " plot= ? where IMDBID = ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, movie.getTitle());
        ps.setInt(2, movie.getYear());
        ps.setString(3, movie.getImgUrl());
        ps.setInt(4, movie.getUserRating());
        ps.setString(5, movie.getSinopsis());
        ps.setString(6, movie.getID());
        ps.executeUpdate();
    }
    public void updateRating(String id, int rating) throws SQLException {
        String query
                = "update harrypottermovies set userRating=? where IMDBID = ?";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setInt(1, rating);
        ps.setString(2, id);
        ps.executeUpdate();
    }
}
