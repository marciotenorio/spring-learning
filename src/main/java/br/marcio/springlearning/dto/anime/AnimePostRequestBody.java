package br.marcio.springlearning.dto.anime;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AnimePostRequestBody {

    @NotEmpty
    private String name;

//    @URL(message = "The URL is not valid")
//    @NotEmpty
//    private String url;
}
