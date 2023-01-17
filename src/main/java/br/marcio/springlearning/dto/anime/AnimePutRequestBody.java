package br.marcio.springlearning.dto.anime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePutRequestBody {

    private Long id;
    private String name;
}
