package pl.niewolnyFilip.club_search.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.niewolnyFilip.club_search.exception.MyException;
import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class JSONService {

    public String getSearchResult(String responseFromWikipedia) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode;
        try {
            rootNode = mapper.readTree(responseFromWikipedia);
        } catch (JsonProcessingException e) {
            throw new MyException("Coś nie tak z odpowiedzią z api wikipedii");
        }
        if (rootNode == null)
            throw new MyException("Coś nie tak z odpowiedzią z api wikipedii");
        return searchForFootballClub(rootNode.findValue("search"));
    }

    public String searchForFootballClub(JsonNode searchResult) {
        Optional<JsonNode> first ;
        first = getJsonNode(searchResult, "title", "^.*(fc|f.c.|cf|c.f.).*$");
        if (!first.isPresent()) {
            first = getJsonNode(searchResult, "snippet", "^.*(football club|futbol club ).*$");
        }
        if (!first.isPresent()) {
            first = getJsonNode(searchResult, "snippet", "^.*(club).*$");
        }
        if (first.isPresent()) {
            return createRedirectURL(first.get().get("title").textValue().replace(" " , "_"));
        }
        throw new MyException("Nie ma klubu w tym miescie w pierwszych 10 wynikach");
    }

    private Optional<JsonNode> getJsonNode(JsonNode searchResult, String title, String s) {
        Optional<JsonNode> first;
        first = StreamSupport.stream(searchResult.spliterator(), false)
                .filter(node -> node.get(title).toString().toLowerCase().matches(s))
                .findFirst();
        return first;
    }

    public String createRedirectURL(String title) {
            return "https://en.wikipedia.org/wiki/" + title;
    }
}
