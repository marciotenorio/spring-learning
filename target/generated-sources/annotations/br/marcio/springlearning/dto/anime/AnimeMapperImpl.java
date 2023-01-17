package br.marcio.springlearning.dto.anime;

import br.marcio.springlearning.model.Anime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-24T07:29:57-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Amazon.com Inc.)"
)
public class AnimeMapperImpl implements AnimeMapper {

    @Override
    public Anime postRequestBodytoAnime(AnimePostRequestBody animePostRequestBody) {
        if ( animePostRequestBody == null ) {
            return null;
        }

        Anime.AnimeBuilder anime = Anime.builder();

        anime.name( animePostRequestBody.getName() );

        return anime.build();
    }

    @Override
    public Anime putRequestBodytoAnime(AnimePutRequestBody animePostRequestBody) {
        if ( animePostRequestBody == null ) {
            return null;
        }

        Anime.AnimeBuilder anime = Anime.builder();

        anime.id( animePostRequestBody.getId() );
        anime.name( animePostRequestBody.getName() );

        return anime.build();
    }
}
