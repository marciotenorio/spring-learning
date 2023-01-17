package br.marcio.springlearning.controller;

import br.marcio.springlearning.dto.anime.AnimePostRequestBody;
import br.marcio.springlearning.dto.anime.AnimePutRequestBody;
import br.marcio.springlearning.model.Anime;
import br.marcio.springlearning.service.AnimeService;
import br.marcio.springlearning.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/animes")
public class AnimeController {

    private final AnimeService animeService;
    private final DateUtil dateUtil;

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @PostMapping("/saveWithoutNaruto")
    public ResponseEntity<Anime> saveWithoutBeNaruto(@RequestBody AnimePostRequestBody animePostRequestBody){
        return ResponseEntity.ok(animeService.saveWithoutBeNaruto(animePostRequestBody));
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Anime> findById(@PathVariable Long id){
        return ResponseEntity.ok(animeService.findByIdOrThorwBadRequestException(id));
    }

    @GetMapping
    public ResponseEntity<Page<Anime>> findAll(Pageable pageable){
        return ResponseEntity.ok(animeService.findAll(pageable));
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Anime>> findAllNonPageable(){
        return ResponseEntity.ok(animeService.findAllNonPageable());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Anime>> findByName(
            @RequestParam(required = false, defaultValue = "") String name){
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        animeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
