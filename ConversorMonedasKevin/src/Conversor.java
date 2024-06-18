import com.google.gson.Gson;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    private static String llave = "64a0f6faddf339af81e84f7f";

    public Moneda convertirMoneda(String monedaBase, String divisa) {




        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + llave + "/pair/" + monedaBase.substring(0,3)+"/"+divisa.substring(0,3));

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            try {
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                return new Gson().fromJson(response.body(), Moneda.class);
            } catch (Exception e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }



    }
}

