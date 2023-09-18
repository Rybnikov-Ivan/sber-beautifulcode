package beautifulcode.service;

import beautifulcode.dto.CheckBracketsDto;
import beautifulcode.dto.CheckBracketsResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckBracketsServiceImplTest {
    private CheckBracketsService checkBracketsService;

    @BeforeEach
    void setUp() {
        checkBracketsService = new CheckBracketsServiceImpl();
    }

    @Test
    void checkSequenceIfTextIsEmpty() {
        CheckBracketsResponseDto result = checkBracketsService.checkBrackets(createCheckBracketsDto(""));
        Assertions.assertFalse(result.getIsCorrect());
    }

    @Test
    void checkSequenceIfTextIsNull() {
        CheckBracketsResponseDto result = checkBracketsService.checkBrackets(createCheckBracketsDto(null));
        Assertions.assertFalse(result.getIsCorrect());
    }

    @Test
    void checkSequenceWithInputText() {
        String inputText = "Вчера я отправился в поход в лес " +
                "(это мое любимое место для отдыха) вместе с друзьями. " +
                "Мы выбрали маршрут, который проходил через горные потоки и поля (для разнообразия). " +
                "В начале пути погода была отличной, солнце светило ярко, и птицы радостно пели. " +
                "Однако, когда мы подошли ближе к вершине горы, небо стало покрываться облаками, " +
                "(как будто природа готовила нам небольшой сюрприз). " +
                "Несмотря на это, виды были захватывающими, особенно когда мы достигли высшей точки и увидели прекрасный вид на долину " +
                "(я почувствовал, что все усилия стоили того).";

        CheckBracketsResponseDto result = checkBracketsService.checkBrackets(createCheckBracketsDto(inputText));
        Assertions.assertTrue(result.getIsCorrect());
    }

    @Test
    void checkServiceIfSequenceNotCorrect() {
        String firstText = "(()";
        String secondText = "{]{)()";
        String thirdText = "((())";
        String fourthText = "({[]}]";
        String fifthText = "(])";

        CheckBracketsResponseDto firstResult = checkBracketsService.checkBrackets(createCheckBracketsDto(firstText));
        CheckBracketsResponseDto secondResult = checkBracketsService.checkBrackets(createCheckBracketsDto(secondText));
        CheckBracketsResponseDto thirdResult = checkBracketsService.checkBrackets(createCheckBracketsDto(thirdText));
        CheckBracketsResponseDto fourthResult = checkBracketsService.checkBrackets(createCheckBracketsDto(fourthText));
        CheckBracketsResponseDto fifthResult = checkBracketsService.checkBrackets(createCheckBracketsDto(fifthText));

        Assertions.assertFalse(firstResult.getIsCorrect());
        Assertions.assertFalse(secondResult.getIsCorrect());
        Assertions.assertFalse(thirdResult.getIsCorrect());
        Assertions.assertFalse(fourthResult.getIsCorrect());
        Assertions.assertFalse(fifthResult.getIsCorrect());
    }

    @Test
    void checkServiceIfSequenceIsCorrect() {
        String firstText = "(())";
        String secondText = "{[{}]}";
        String thirdText = "((()))";
        String fourthText = "({[]})";
        String fifthText = "([])";

        CheckBracketsResponseDto firstResult = checkBracketsService.checkBrackets(createCheckBracketsDto(firstText));
        CheckBracketsResponseDto secondResult = checkBracketsService.checkBrackets(createCheckBracketsDto(secondText));
        CheckBracketsResponseDto thirdResult = checkBracketsService.checkBrackets(createCheckBracketsDto(thirdText));
        CheckBracketsResponseDto fourthResult = checkBracketsService.checkBrackets(createCheckBracketsDto(fourthText));
        CheckBracketsResponseDto fifthResult = checkBracketsService.checkBrackets(createCheckBracketsDto(fifthText));

        Assertions.assertTrue(firstResult.getIsCorrect());
        Assertions.assertTrue(secondResult.getIsCorrect());
        Assertions.assertTrue(thirdResult.getIsCorrect());
        Assertions.assertTrue(fourthResult.getIsCorrect());
        Assertions.assertTrue(fifthResult.getIsCorrect());
    }

    private CheckBracketsDto createCheckBracketsDto(String text) {
        return new CheckBracketsDto(text);
    }
}
