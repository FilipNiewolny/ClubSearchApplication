package pl.niewolnyFilip.club_search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pl.niewolnyFilip.club_search.service.WikipediaService;


@RestController
@RequestMapping
public class WikipediaController {
    private WikipediaService wikipediaService;

    @Autowired
    public WikipediaController(WikipediaService wikipediaService) {
        this.wikipediaService = wikipediaService;
    }

    @RequestMapping(value = "/{cityName}")
    public RedirectView redirectToFootballClubPage(@PathVariable String cityName) {
        String url = wikipediaService.getResponseFromWikipedia(cityName);
        return new RedirectView(url);
    }
}
