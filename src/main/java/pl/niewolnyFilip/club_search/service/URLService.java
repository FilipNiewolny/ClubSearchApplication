package pl.niewolnyFilip.club_search.service;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.niewolnyFilip.club_search.exception.MyException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class URLService {
    private ResponesService responesService;

    @Autowired
    public URLService(ResponesService responesService) {
        this.responesService = responesService;
    }

    public String createURL(String cityName) {
        URL result;
        try {
            String url = "https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srlimit=100&srsearch=" + cityName.replace(" ", "_");
            URIBuilder uri = new URIBuilder(url);
            result = uri.build().toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            throw new MyException("Nie udało stworzyć się linku do szukania w api wikipedii");
        }
        return responesService.getResponseFromWikipedia(result);
    }
}
