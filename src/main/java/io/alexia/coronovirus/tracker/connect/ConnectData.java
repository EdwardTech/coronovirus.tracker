package io.alexia.coronovirus.tracker.connect;

import java.io.IOException;

public interface ConnectData {

    void dataRequest(String link);

    Object dataResponse() throws IOException, InterruptedException;

}
