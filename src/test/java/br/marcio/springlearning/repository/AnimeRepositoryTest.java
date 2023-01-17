package br.marcio.springlearning.repository;

import br.marcio.springlearning.model.Anime;
import br.marcio.springlearning.util.AnimeCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Testes para o anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    // Poderia usar setUp do JUnit...?
    private Anime createAnime(){
        return Anime.builder()
                .name("Anime de test")
                .build();
    }

    @Test
    @DisplayName("Save persists anime when successful")
    void save_PersistAnime_WhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime saved = this.animeRepository.save(anime);
        Assertions.assertNotNull(saved);
        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(saved.getName(), anime.getName());
    }

    @Test
    @DisplayName("Save updates anime when successful")
    void save_UpdatesAnime_WhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime saved = this.animeRepository.save(anime);
        saved.setName("Tonhão");

        Anime updated = this.animeRepository.save(saved);
        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertEquals(updated.getName(), saved.getName());
    }

    @Test
    @DisplayName("Delete removes anime when successful")
    void delete_RemovesAnime_WhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime saved = this.animeRepository.save(anime);

        this.animeRepository.delete(saved);
        Optional<Anime> animeOptional = this.animeRepository.findById(saved.getId());
        Assertions.assertTrue(animeOptional.isEmpty());
    }

    @Test
    @DisplayName("Find by name return list of anime when successful")
    void findByName_ReturnListOfAnime_WhenSuccessful(){
        Anime anime = AnimeCreator.createAnimeToBeSaved();
        Anime saved = this.animeRepository.save(anime);

        String name = saved.getName();
        List<Anime> animes = animeRepository.findByName(name);

        org.assertj.core.api.Assertions.assertThat(animes)
                .contains(saved)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Find by name return empty list when no anime is found")
    void findByName_ReturnEmpty_WhenAnieIsNotFound(){
        List<Anime> animes = animeRepository.findByName("xaxaxaxa");

        org.assertj.core.api.Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty(){
        Anime anime = new Anime();
//        Só funciona um pois depois que lança a exceção não executa mais nada.
//        org.assertj.core.api.Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
//                .isInstanceOf(ConstraintViolationException.class);

        org.assertj.core.api.Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.animeRepository.save(anime))
                .withMessageContaining("The anime name cannot be empty");
    }
}