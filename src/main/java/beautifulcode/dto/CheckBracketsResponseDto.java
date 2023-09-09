package beautifulcode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckBracketsResponseDto {

    @JsonProperty("isCorrect")
    private Boolean isCorrect;
}
