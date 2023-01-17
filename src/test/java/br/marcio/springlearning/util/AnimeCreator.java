package br.marcio.springlearning.util;

import br.marcio.springlearning.model.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved(){
        return Anime.builder()
                .name("Test")
                .build();
    }

    public static Anime createValidAnime(){
        return Anime.builder()
                .name("Test")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime(){
        return Anime.builder()
                .name("Test 2")
                .id(1L)
                .build();
    }
}
