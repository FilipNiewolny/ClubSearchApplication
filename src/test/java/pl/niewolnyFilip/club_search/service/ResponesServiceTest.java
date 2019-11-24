package pl.niewolnyFilip.club_search.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
class ResponesServiceTest {

    @Autowired
    ResponesService responesService;

    @Test
    void getResponseFromWikipedia() throws MalformedURLException {
        String urlTest = "https://en.wikipedia.org/?curid=18119";
        URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srlimit=10&srsearch=football+intitle:liverpool");
        String responseFromWikipedia = responesService.getResponseFromWikipedia(url);
        assertEquals(responseFromWikipedia, urlTest);
    }
}