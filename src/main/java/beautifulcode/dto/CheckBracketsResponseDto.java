package beautifulcode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckBracketsResponseDto {

    @JsonProperty("isCorrect")
    private Boolean isCorrect;
}
