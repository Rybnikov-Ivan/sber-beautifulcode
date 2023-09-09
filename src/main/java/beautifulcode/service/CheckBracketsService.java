package beautifulcode.service;

import beautifulcode.dto.CheckBracketsDto;
import beautifulcode.dto.CheckBracketsResponseDto;

public interface CheckBracketsService {
    CheckBracketsResponseDto checkBrackets(CheckBracketsDto dto);
}
