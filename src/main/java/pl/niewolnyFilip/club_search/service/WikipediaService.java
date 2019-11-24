package pl.niewolnyFilip.club_search.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WikipediaService {

    private URLService urlService;

    @Autowired
    public WikipediaService(URLService urlService) {

        this.urlService = urlService;
    }

    public String getResponseFromWikipedia(String city) {
        String url = urlService.createURL(city);
        return StringUtils.stripAccents(url);
    }


}
