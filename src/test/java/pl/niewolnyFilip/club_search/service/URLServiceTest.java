package pl.niewolnyFilip.club_search.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class URLServiceTest {

    @Autowired
    URLService urlService;

    @Test
    void createURL() {
        String resultTest = "https://en.wikipedia.org/wiki/Lech_Poznań";
        String poznan = urlService.createURL("poznan");
        assertEquals(resultTest , poznan);

    }
}