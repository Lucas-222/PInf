package parsing;

import parsing.exceptions.*;

public class ExceptionCheck {
    private int amountOfOpeningBracketsWithoutClosingBrackets;
    private StringBuilder allCharacters;
    private String[] inputAsArray;
    private String variableReplacement;

    public ExceptionCheck(String[] inputAsArray, String variableReplacement) {
        this.inputAsArray = inputAsArray;
        this.variableReplacement = variableReplacement;
    }

    public String[] check() {
        amountOfOpeningBracketsWithoutClosingBrackets = 0;
        allCharacters = new StringBuilder();
        try {
            for (String s : inputAsArray) {
                // Illegal character
                illegalCharacter(s);
                // Illegal character after number
                illegalCharacterAfterNumber(s);
                // Illegal Bracket
                illegalBrackets(s);
                // Illegal variable
                illegalVariable(s);
            }
            if (amountOfOpeningBracketsWithoutClosingBrackets != 0) {
                throw new WrongBracketsException(1);
            }
            checkForTwoCharactersInARow();
        } catch (IllegalCharacterException | IllegalCharacterAfterNumberException | WrongBracketsException | TwoCharactersInARowException | IllegalVariableException e) {
            inputAsArray = new String[] {e.getMessage()};
        }
        return inputAsArray;
    }

    public void illegalCharacter(String s) throws IllegalCharacterException {
        for (int i = 0; i < s.length(); i++) {
            allCharacters.append(s.charAt(i));
            // If char is not a character, not an operator and not a number
            if (!CharacterLists.CHARACTERS.contains(s.charAt(i)) && !CharacterLists.OPERATORS.contains(s.charAt(i)) && !CharacterLists.NUMBERS.contains(s.charAt(i)) && !CharacterLists.VARIABLES.contains(s.charAt(i))) {
                throw new IllegalCharacterException(allCharacters);
            }
        }
    }

    public void illegalVariable(String s) throws IllegalVariableException {
        if (s.length() != 1) {
            for (int i = 0; i < s.length(); i++) {
                // If the string contains any number -> return true
                if (CharacterLists.VARIABLES.contains(s.charAt(i))) {
                    throw new IllegalVariableException(allCharacters, 1);
                }
            }
        }
        if (containsVariable() && variableReplacement == null) {
            throw new IllegalVariableException(allCharacters, 2);
        }
    }

    public boolean containsVariable() {
        for (String s : inputAsArray) {
            if (CharacterLists.VARIABLES.contains(s.charAt(0))) {
                return true;
            }
        }
        return false;
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
        for (int i = 0; i < inputAsArray.length; i++) {
            // If the length of string is 1
            if (inputAsArray[i].length() == 1 && i+1 != inputAsArray.length) {
                allCharacters.append(inputAsArray[i]);
                // If two illegal strings are in a row (exclude -) | (+, *)
                if (inputAsArray[i+1].charAt(0) != '-' && CharacterLists.OPERATORSANDCHARACTERS.contains(inputAsArray[i].charAt(0)) && CharacterLists.OPERATORSANDCHARACTERS.contains(inputAsArray[i+1].charAt(0))) {
                    allCharacters.append(inputAsArray[i + 1].charAt(0));
                    throw new TwoCharactersInARowException(allCharacters);
                }
            } else {
                // Loop through every char in string
                for (int j = 0; j < inputAsArray[i].length(); j++) {
                    allCharacters.append(inputAsArray[i].charAt(j));
                    // If two illegal characters are in a row | (..)
                    if (j+1 != inputAsArray[i].length() && CharacterLists.OPERATORSANDCHARACTERS.contains(inputAsArray[i].charAt(j)) && CharacterLists.OPERATORSANDCHARACTERS.contains(inputAsArray[i].charAt(j+1))) {
                        allCharacters.append(inputAsArray[i].charAt(j + 1));
                        throw new TwoCharactersInARowException(allCharacters);
                    }
                }
            }
        }
    }

}
