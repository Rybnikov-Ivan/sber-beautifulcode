package beautifulcode.controller;

import beautifulcode.dto.CheckBracketsDto;
import beautifulcode.dto.CheckBracketsResponseDto;
import beautifulcode.service.CheckBracketsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CheckBracketsController.class)
public class CheckBracketsControllerTest {
    private static final String TEST_TEXT = "test";
    @MockBean
    private CheckBracketsServiceImpl checkBracketsService;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new CheckBracketsController(checkBracketsService))
                .build();
    }

    @Test
    void postTextWithStatus200() throws Exception {
        CheckBracketsDto checkBracketsDto = createCheckBracketsDto();
        CheckBracketsResponseDto checkBracketResponseDto = createCheckBracketResponseDto();

        Mockito.when(checkBracketsService.checkBrackets(checkBracketsDto)).thenReturn(checkBracketResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/checkBrackets")
                        .content(objectMapper.writeValueAsString(checkBracketsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.isCorrect").value(checkBracketResponseDto.getIsCorrect()));
    }

    @Test
    void postTextWithStatus404() throws Exception {
        CheckBracketsDto checkBracketsDto = createCheckBracketsDto();
        CheckBracketsResponseDto checkBracketResponseDto = createCheckBracketResponseDto();

        Mockito.when(checkBracketsService.checkBrackets(checkBracketsDto)).thenReturn(checkBracketResponseDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/non")
                        .content(objectMapper.writeValueAsString(checkBracketsDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(status().isNotFound());
    }

    private CheckBracketsDto createCheckBracketsDto() {
        return new CheckBracketsDto(TEST_TEXT);
    }

    private CheckBracketsResponseDto createCheckBracketResponseDto() {
        return new CheckBracketsResponseDto(true);
    }
}
