package com.harrypotterapp.tesicnorpruebatecnica.DB;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OMDBAPICall {
    public static List<HPMovie> apiCall() {
        List<HPMovie> movieList = new ArrayList<>();
        int year = 2000;
        for (int i = year; i <= Year.now().getValue(); i++) {
            String urlRequest = "https://www.omdbapi.com/?t=Harry+Potter&y=" + i + "&apikey=731e41f";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlRequest))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = null;
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Para filtrar resultados Response==True en JSON y Genre contains Adventure
            JsonObject jsonObject = JsonParser.parseString(response.body())
                    .getAsJsonObject();
            if (jsonObject.get("Response").getAsString().equals("True")) {
                if (jsonObject.get("Genre").getAsString().contains("Adventure")&&jsonObject.get("Actors").getAsString().contains("Daniel Radcliffe")) {
                    String ID = jsonObject.get("imdbID").getAsString();
                    String receivedTitle = jsonObject.get("Title").getAsString();
                    int movieYear = jsonObject.get("Year").getAsInt();
                    String imgUrl = jsonObject.get("Poster").getAsString();
                    String sinopsis = jsonObject.get("Plot").getAsString();
                    HPMovie auxMovie = new HPMovie(ID,receivedTitle,movieYear,imgUrl,sinopsis);
                    movieList.add(auxMovie);
                }
            }
        }
        return movieList;
    }

}

