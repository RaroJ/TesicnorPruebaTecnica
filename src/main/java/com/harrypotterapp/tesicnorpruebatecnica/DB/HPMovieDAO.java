package com.harrypotterapp.tesicnorpruebatecnica.DB;

import java.sql.SQLException;
import java.util.List;

public interface HPMovieDAO {
    public int add(HPMovie movie)
            throws SQLException;
    public void delete(String id)
            throws SQLException;
    public HPMovie getMovie(String id)
            throws SQLException;
    public List<HPMovie> getMovies()
            throws SQLException;
    public void update(HPMovie emp)
            throws SQLException;
}
