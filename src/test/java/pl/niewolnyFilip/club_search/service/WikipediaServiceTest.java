package pl.niewolnyFilip.club_search.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.niewolnyFilip.club_search.exception.MyException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class WikipediaServiceTest {

    @Autowired
    WikipediaService wikipediaService;

    @Test
    void getResponseFromWikipedia_Positive() {
        String cityTest = "liverpool";
        String resultTest = "https://en.wikipedia.org/wiki/Liverpool_F.C.";
        String responseFromWikipedia = wikipediaService.getResponseFromWikipedia(cityTest);
        assertEquals(responseFromWikipedia , resultTest);
    }

 }