package io.alexia.coronovirus.tracker.services;

import io.alexia.coronovirus.tracker.connect.ConnectData;
import io.alexia.coronovirus.tracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;



// Что тут делает @Service?

@Service
public class VirusDataService {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    private List<LocationStats> allStats = new ArrayList<>();

    ConnectData connectData;

    // Что тут делает @PostConstruct?
    // IOException - исключение ввода, вывода. InterruptedException
    @PostConstruct
    //@Scheduled(cron = "* * * * * *")
    public void fetchDataVirus() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();

        // HttpClient

        connectData.dataRequest(VIRUS_DATA_URL);

        Object link = connectData.dataResponse();

        StringReader csvBodyReader = new StringReader(connectData.dataResponse());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();

            // Собираем список Штатов
            locationStats.setState(record.get("Province/State"));

            // Собираем список Стран
            locationStats.setCountry(record.get("Country/Region"));

            // Заболеваний на сегоднящний день
            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            // Заболеваний на вчерашний день
            int prevCases = Integer.parseInt(record.get(record.size() - 2));
            locationStats.setLatestTotalCases(latestCases);
            // Получаем количество заболевших за сутки
            locationStats.setChangesPrevDay(latestCases - prevCases);
            newStats.add(locationStats);

        }
        this.allStats = newStats;
    }
}
