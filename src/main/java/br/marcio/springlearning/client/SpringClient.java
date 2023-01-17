package br.marcio.springlearning.client;

import br.marcio.springlearning.model.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate()
                .getForEntity("http://localhost:8080/animes/{id}", Anime.class, 2);
        log.info(entity);

        Anime anime = new RestTemplate()
                .getForObject("http://localhost:8080/animes/{id}", Anime.class, 2);
        log.info(anime);

        Anime[] animes = new RestTemplate()
                .getForObject("http://localhost:8080/animes/listAll", Anime[].class);
        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/listAll", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Anime>>() {
                });
        log.info(exchange.getBody());

//        Anime someAnime = Anime.builder().name("ANIMEZAO").build();
//        Anime response = new RestTemplate().postForObject("http://localhost:8080/animes", someAnime, Anime.class);
//        log.info(response);

        Anime someAnime = Anime.builder().name("LA VAI MAIS UM").build();
        ResponseEntity<Anime> response = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.POST,
                new HttpEntity<>(someAnime, createJsonHeader()),
                Anime.class);
        log.info(response);

        Anime animeToBeUpdated = response.getBody();
        animeToBeUpdated.setName("LA VAI MAIS UM 2");
        ResponseEntity<Void> putResponse = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class);
        log.info(putResponse);

        ResponseEntity<Void> deleteResponse = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToBeUpdated.getId());
        log.info(deleteResponse);
    }

    public static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
