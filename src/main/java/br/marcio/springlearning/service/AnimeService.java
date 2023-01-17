package br.marcio.springlearning.service;

import br.marcio.springlearning.dto.anime.AnimePutRequestBody;
import br.marcio.springlearning.exception.BadRequestException;
import br.marcio.springlearning.dto.anime.AnimeMapper;
import br.marcio.springlearning.dto.anime.AnimePostRequestBody;
import br.marcio.springlearning.model.Anime;
import br.marcio.springlearning.repository.AnimeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;

    public AnimeService(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        Anime anime = AnimeMapper.INSTANCE.postRequestBodytoAnime(animePostRequestBody);
        return animeRepository.save(anime);
    }

    public void deleteById(Long id){
        animeRepository.deleteById(id);
    }

    public Page<Anime> findAll(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThorwBadRequestException(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

//    @Transactional(noRollbackFor = Exception.class)
//    normalmente não da rollback pra exceções do tipo checked (exception, que não é runtime)
    @Transactional
    public Anime saveWithoutBeNaruto(AnimePostRequestBody animePostRequestBody) {
        Anime anime = animeRepository.save(AnimeMapper.INSTANCE.postRequestBodytoAnime(animePostRequestBody));

        if(animePostRequestBody.getName().equals("Naruto"))
            throw new RuntimeException("Nao pode salvar naruto xD");

        return anime;
    }

    public List<Anime> findAllNonPageable() {
        return animeRepository.findAll();
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThorwBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.putRequestBodytoAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
