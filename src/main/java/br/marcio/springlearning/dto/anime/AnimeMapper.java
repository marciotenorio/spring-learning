package br.marcio.springlearning.dto.anime;

import br.marcio.springlearning.model.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
    Anime postRequestBodytoAnime(AnimePostRequestBody animePostRequestBody);
    Anime putRequestBodytoAnime(AnimePutRequestBody animePostRequestBody);
}
