package parsing;

import parsing.exceptions.*;

public class ExceptionCheck {
    private int amountOfOpeningBracketsWithoutClosingBrackets;
    private StringBuilder allCharacters;
    private String[] arr;

    public ExceptionCheck(String[] arr) {
        this.arr = arr;
    }

    public String[] check() {
        amountOfOpeningBracketsWithoutClosingBrackets = 0;
        allCharacters = new StringBuilder();
        try {
            for (String s : arr) {
                // Illegal character
                illegalCharacter(s);
                // Illegal character after number
                illegalCharacterAfterNumber(s);
                // Illegal Bracket
                illegalBrackets(s);
            }
            if (amountOfOpeningBracketsWithoutClosingBrackets != 0) {
                throw new WrongBracketsException(1);
            }
            checkForTwoCharactersInARow();
        } catch (IllegalCharacterException | IllegalCharacterAfterNumberException | WrongBracketsException | TwoCharactersInARowException e) {
            arr = new String[] {e.getMessage()};
        }
        return arr;
    }

    public void illegalCharacter(String s) throws IllegalCharacterException {
        for (int i = 0; i < s.length(); i++) {
            allCharacters.append(s.charAt(i));
            // If char is not a character, not an operator and not a number
            if (!CharacterLists.CHARACTERS.contains(s.charAt(i)) && !CharacterLists.OPERATORS.contains(s.charAt(i)) && !CharacterLists.NUMBERS.contains(s.charAt(i))) {
                throw new IllegalCharacterException(allCharacters);
            }
        }
    }

    public void illegalCharacterAfterNumber(String s) throws IllegalCharacterAfterNumberException {
        if (s.charAt(s.length() - 1) == '.') {
            throw new IllegalCharacterAfterNumberException(allCharacters);
        }
    }

    public void illegalBrackets(String s) throws WrongBracketsException {
        // If string is an opening bracket, add 1 to amountOfOpeningBracketsWithoutClosingBrackets
        if (s.equals("(")) {
            amountOfOpeningBracketsWithoutClosingBrackets++;
        } else if (s.equals(")")) {
            // If there is a closing bracket before an opening bracket throw an exception
            if (amountOfOpeningBracketsWithoutClosingBrackets <= 0) {
                throw new WrongBracketsException(allCharacters);
            }
            // If string is a closing bracket, substract 1 from amountOfOpeningBracketsWithoutClosingBrackets
            amountOfOpeningBracketsWithoutClosingBrackets--;
        }
    }

    public void checkForTwoCharactersInARow() throws TwoCharactersInARowException {
        allCharacters = new StringBuilder();

        // Loop through every string in arr
        for (int i = 0; i < arr.length; i++) {
            // If the length of string is 1
            if (arr[i].length() == 1 && i+1 != arr.length) {
                allCharacters.append(arr[i]);
                // If two illegal strings are in a row (exclude -) | (+, *)
                if (arr[i+1].charAt(0) != '-' && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i].charAt(0)) && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i+1].charAt(0))) {
                    allCharacters.append(arr[i + 1].charAt(0));
                    throw new TwoCharactersInARowException(allCharacters);
                }
            } else {
                // Loop through every char in string
                for (int j = 0; j < arr[i].length(); j++) {
                    allCharacters.append(arr[i].charAt(j));
                    // If two illegal characters are in a row | (..)
                    if (j+1 != arr[i].length() && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i].charAt(j)) && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i].charAt(j+1))) {
                        allCharacters.append(arr[i].charAt(j + 1));
                        throw new TwoCharactersInARowException(allCharacters);
                    }
                }
            }
        }
    }

}
