package beautifulcode.controller;

import beautifulcode.dto.CheckBracketsDto;
import beautifulcode.dto.CheckBracketsResponseDto;
import beautifulcode.service.CheckBracketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class CheckBracketsController {

    private final CheckBracketsService checkBracketsService;

    @Autowired
    public CheckBracketsController(CheckBracketsService checkBracketsService) {
        this.checkBracketsService = checkBracketsService;
    }

    @ResponseBody
    @PostMapping(value = "/checkBrackets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CheckBracketsResponseDto> getText(
            @RequestBody CheckBracketsDto checkBracketsDto
            ) {
        return ResponseEntity.ok(checkBracketsService.checkBrackets(checkBracketsDto));
    }
}
