package beautifulcode.service;

import beautifulcode.dto.CheckBracketsDto;
import beautifulcode.dto.CheckBracketsResponseDto;
import beautifulcode.utill.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class CheckBracketsServiceImpl implements CheckBracketsService {

    @Override
    public CheckBracketsResponseDto checkBrackets(CheckBracketsDto dto) {
        String text = dto.getText();
        if (text == null || text.isEmpty()) {
            return new CheckBracketsResponseDto(false);
        }

        List<Character> bracketsList = getBracketSequence(text.toCharArray());
        boolean result = isCorrect(bracketsList);
        return new CheckBracketsResponseDto(result);
    }

    private List<Character> getBracketSequence(char[] chars) {
        List<Character> bracketsList = new ArrayList<>();

        for (char symbol : chars) {
            if (isBracket(symbol))
                bracketsList.add(symbol);
        }

        return bracketsList;
    }

    private boolean isBracket(char symbol) {
        return symbol == Constants.OPENING_ROUND_PARENTHESIS ||
                symbol == Constants.CLOSING_ROUND_PARENTHESIS ||
                symbol == Constants.OPENING_SQUARE_PARENTHESIS ||
                symbol == Constants.CLOSING_SQUARE_PARENTHESIS ||
                symbol == Constants.OPENING_CURLY_PARENTHESIS ||
                symbol == Constants.CLOSING_CURLY_PARENTHESIS;
    }

    private boolean isCorrect(List<Character> bracketList) {
        Stack<Character> bracketsStack = new Stack<>();
        for (char bracket : bracketList) {
            if (bracket == Constants.OPENING_ROUND_PARENTHESIS
                    || bracket == Constants.OPENING_SQUARE_PARENTHESIS
                    || bracket == Constants.OPENING_CURLY_PARENTHESIS
            ) {
                bracketsStack.push(bracket);
            } else {
                if (bracketsStack.empty()) {
                    return false;
                } else {
                    char openBracket = bracketsStack.peek();
                    if (openBracket == Constants.OPENING_ROUND_PARENTHESIS &&
                            bracket == Constants.CLOSING_ROUND_PARENTHESIS) {
                        bracketsStack.pop();
                    } else if(openBracket == Constants.OPENING_SQUARE_PARENTHESIS &&
                            bracket == Constants.CLOSING_SQUARE_PARENTHESIS) {
                        bracketsStack.pop();
                    } else if (openBracket == Constants.OPENING_CURLY_PARENTHESIS &&
                            bracket == Constants.CLOSING_CURLY_PARENTHESIS) {
                        bracketsStack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        return bracketsStack.empty();
    }
}
