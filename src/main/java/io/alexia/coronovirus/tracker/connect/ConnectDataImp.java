package io.alexia.coronovirus.tracker.connect;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ConnectDataImp implements ConnectData {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    HttpResponse<String> httpResponse;

    @Override
    public void dataRequest(String link) {
        request = HttpRequest.newBuilder().uri(URI.create(link)).build();
    }

    @Override
    public void dataResponse() throws IOException, InterruptedException {
        httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
