package com.harrypotterapp.tesicnorpruebatecnica.DB;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.time.Year;
import java.util.List;

public class OMDBAPICall {
    public static List<HPMovie> call() {
        String title = "Harry+Potter";
        int year = 2000;
        int thisYear = Year.now().getValue();
        for (int i = year; i <= thisYear; i++) {
            String urlRequest = "https://www.omdbapi.com/?t=" + title + "&y=" + i + "&apikey=731e41f";
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
            //Para filtrar resultados Response==True en JSON y Genre = Adventure, Family, Fantasy
            System.out.println(response.body());
        }
        return null;
    }

}

