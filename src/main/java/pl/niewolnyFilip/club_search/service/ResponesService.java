package pl.niewolnyFilip.club_search.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.niewolnyFilip.club_search.exception.MyException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ResponesService {
    private JSONService jsonService;

    @Autowired
    public ResponesService(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    public String getResponseFromWikipedia(URL url) {
        HttpURLConnection connection = null;
        String content;
        try {
            connection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            content = parseContent(in);
        } catch (IOException e) {
            throw new MyException("Nie udało się połączyć");
        } finally {
            assert connection != null;
            connection.disconnect();
        }
        return jsonService.getSearchResult(content);
    }

    private String parseContent(BufferedReader in) throws IOException {
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}
