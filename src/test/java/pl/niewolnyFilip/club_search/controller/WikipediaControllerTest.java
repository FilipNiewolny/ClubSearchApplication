package pl.niewolnyFilip.club_search.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.niewolnyFilip.club_search.service.WikipediaService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WikipediaController.class)
public class WikipediaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WikipediaService service;


    @Test
    void redirectToFootballClubPage() throws Exception {
        String result = "https://en.wikipedia.org/?curid=677622";
        given(service.getResponseFromWikipedia("poznan")).willReturn(result);

        mvc.perform(get("/poznan" )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());

        mvc.perform(get("/poznan/city" )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}