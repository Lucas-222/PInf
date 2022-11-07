package parsing;

import parsing.exceptions.*;
import java.util.*;

public class TermParser {
   private int amountOfOpeningBracketsWithoutClosingBrackets;
   private StringBuilder allCharacters;
   private String[] arr;
   private String input;

   public TermParser(String input) {
      this.input = input;
   }

   public String[] parse() {
      // Remove whitespaces from the input string
      input = input.replaceAll("[\\s|\\u00A0]+", "");

      // Create temp variable for writing | input is only used for reading
      String temp = input;
       for (int i = 0; i < input.length(); i++) {
         // Check for minus and a number after
         if (input.charAt(i) == '-' && CharacterLists.NUMBERS.contains(input.charAt(i + 1))) {
            // Check if there is an opertor in front of the minus or if it is the first char in input
            if (input.charAt(0) == '-' || CharacterLists.OPERATORS.contains(input.charAt(i-1))) {
               i++;
            }
         }
         // If current char is an operator or a bracket
         if (CharacterLists.OPERATORS.contains(input.charAt(i)) || input.charAt(i) == '(' || input.charAt(i) == ')') {
            // Extend operator with two whitespaces around it
            temp = temp.replace(String.valueOf(input.charAt(i)), " " + input.charAt(i) + " ");
         }
      }

      // replace comma with a point
      temp = temp.replace(",", ".");

      // Split after every whitespace
      arr = temp.split(" ");

      // Creation of new arrayList with values of the array
      ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));

      // Remove empty strings
      list.removeIf(s -> s.equals(""));

      // Fill the array with values from the arraylist
      arr = list.toArray(new String[0]);

      // Check for illegal input
      illegalInput();

      return arr;
   }

   public String postfix() {
      // Create a LIFO stack
      Stack<String> stack = new Stack<>();
      // Create an output list
      List<String> output = new LinkedList<>();
      // Parse the input
      parse();

      // Loop through every string in arr
      for (String token : arr) {
         // Check for number
         for (int j = 0; j < token.length(); j++) {
            // If token is a number
            if (CharacterLists.NUMBERS.contains(token.charAt(j))) {
               output.add(token);
            }
         }

         // If token is an operator
         if (token.length() == 1 && CharacterLists.OPERATORS.contains(token.charAt(0))) {
            // While stack is not empty and operators contains stack peek
            while (!stack.empty() && CharacterLists.OPERATORS.contains(stack.peek().charAt(0))) {
               output.add(stack.pop());
            }
            stack.add(token);
         }

         // If token is a opnening bracket
         if (token.equals("(")) {
            stack.push(token);
         }

         // If token is a closing bracket
         if (token.equals(")")) {
            // While stack peek is not an opening bracket
            while (!stack.peek().equals("(")) {
               output.add(stack.pop());
            }
            // Remove the opening bracket
            stack.pop();
         }
      }

      // Fill the output with the rest of stack
      while (!stack.empty()) {
         output.add(stack.pop());
      }

      // Return list as string
      return output.toString();
   }

   public void illegalInput() {
      amountOfOpeningBracketsWithoutClosingBrackets = 0;
      allCharacters = new StringBuilder();
      try {
         for (String s : arr) {
            // Illegal character
            illegalCharacter(s);
            // Illegal character after number
            illegalCharacterAfterNumber(s);
            // Illegal Bracket
            illeagalBrackets(s);
         }
         if (amountOfOpeningBracketsWithoutClosingBrackets != 0) {
            throw new WrongBracketsException(1);
         }
         checkForTwoCharactersInARow();
      } catch (IllegalCharacterException | IllegalCharacterAfterNumberException | WrongBracketsException | TwoCharactesInARowException e) {
         arr = new String[] {e.getMessage()};
      }
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
      if (s.charAt(s.length() - 1) == '.' || s.charAt(s.length() - 1) == ',') {
         throw new IllegalCharacterAfterNumberException(allCharacters);
      }
   }

   public void illeagalBrackets(String s) throws WrongBracketsException {
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

   public void checkForTwoCharactersInARow() throws TwoCharactesInARowException {
      allCharacters = new StringBuilder();

      for (int i = 0; i < arr.length; i++) {
         // Check if two operators are after one another or an operator and a character are | for example (++) or (+.) | Brackets after an operator are allowed
         if (arr[i].length() == 1 && i + 1 != arr.length && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i].charAt(0)) && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i + 1].charAt(0)) && arr[i + 1].charAt(0) != '(' && arr[i + 1].charAt(0) != ')') {
            allCharacters.append(arr[i]);
            // Check for (-) and an illegal character in the next string ([+][.4])
            if (arr[i + 1].length() == 1 || CharacterLists.CHARACTERS.contains(arr[i + 1].charAt(0))) {
               allCharacters.append(arr[i + 1].charAt(0));
               throw new TwoCharactesInARowException(allCharacters);
            }
         } else {
            // Loop through every char in every string
            for (int j = 0; j < arr[i].length(); j++) {
               allCharacters.append(arr[i].charAt(j));
               // If two same chars are after one another in the same string
               if (j + 1 != arr[i].length() && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i].charAt(j)) && CharacterLists.OPERATORSANDCHARACTERS.contains(arr[i].charAt(j + 1))) {
                  allCharacters.append(arr[i].charAt(j + 1));
                  throw new TwoCharactesInARowException(allCharacters);
               }
            }
         }
      }
   }

   @Override
   public String toString() {
      if (arr == null) return "The array is null";

      StringBuilder stringBuilder = new StringBuilder();
      for (String s : arr) {
         if (arr.length != 1) {
            stringBuilder.append("[").append(s).append("]");
         } else {
            stringBuilder.append(s);
         }
      }
      return stringBuilder.toString();
   }

}