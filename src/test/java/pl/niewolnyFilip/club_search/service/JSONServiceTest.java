package pl.niewolnyFilip.club_search.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class JSONServiceTest {
    final String testString = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\ExampleJSON")), StandardCharsets.UTF_8);

    @Autowired
    private JSONService jsonService;

    JSONServiceTest() throws IOException {
    }

    @Test
    void getSearchResult() {
        String searchResult = jsonService.getSearchResult(testString);
        assertNotNull(searchResult);
    }

    @Test
    void createRedirectURL() {
        String testPageID = "1234";
        String redirectURL = jsonService.createRedirectURL(testPageID);
        String result = "https://en.wikipedia.org/wiki/1234";

        assertEquals( result,redirectURL);
    }


}