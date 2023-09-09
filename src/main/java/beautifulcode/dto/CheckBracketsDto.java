package beautifulcode.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CheckBracketsDto {

    @JsonProperty("text")
    private String text;
}
