package parsing;

import parsing.exceptions.IllegalCharacterAfterNumberException;
import parsing.exceptions.IllegalCharacterException;
import parsing.exceptions.TwoCharactesInARowException;
import parsing.exceptions.WrongBracketsException;

import java.util.ArrayList;
import java.util.Arrays;

public class TermParser {
   private String[] arr;
   private final String input;
   private final CharacterLists characterLists = new CharacterLists();

   public TermParser(String input) {
      this.input = input;
   }

   public String[] parse() {
      // Create temp variable for writing | input is only used for reading
      String temp = input;

      // Remove whitespaces from the temp string
      temp = temp.replaceAll("[\\s|\\u00A0]+", "");

      // Loop through the input
      for (int i = 0; i < input.length(); i++) {
         // If current char is an operator or character
         if (characterLists.OPERATORS.contains(input.charAt(i)) || input.charAt(i) == '(' || input.charAt(i) == ')') {
            // Extend operator with two whitespaces around it
            temp = temp.replace(String.valueOf(input.charAt(i)), " " + input.charAt(i) + " ");
            // If there is a minus after the operator or character
            if (input.length() != i + 1 && input.charAt(i + 1) == '-') {
               // Skip the next char and set it as a part of the next number
               i++;
            }
         }
      }

      // replace comma with a point
      temp = temp.replace(",", ".");

      // Split after every whitespace
      arr = temp.split(" ");

      // Creation of new arrayList with values of the array
      ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));

      // Remove Whitespaces
      list.removeIf(s -> s.equals(""));

      // Fill the array with values from the arraylist
      arr = list.toArray(new String[0]);

      checkForAnythingIllegal();

      return arr;
   }

   public void checkForAnythingIllegal() {
      try {
         checkForIllegalCharacters();
      } catch (IllegalCharacterException e) {
         arr = new String[] {e.getMessage()};
         return;
      }
      try {
         checkForTwoCharactersInARow();
      } catch (TwoCharactesInARowException e) {
         arr = new String[] {e.getMessage()};
         return;
      }
      try {
         checkForIllegalCharacterAfterNumber();
      } catch (IllegalCharacterAfterNumberException e) {
         arr = new String[] {e.getMessage()};
         return;
      }
      try {
         checkForWrongBrackets();
      } catch (WrongBracketsException e) {
         arr = new String[] {e.getMessage()};
      }
   }

   public void checkForIllegalCharacters() throws IllegalCharacterException {
      for (String s : arr) {
         // Loop through every char in every string in array
         for (int i = 0; i < s.length(); i++) {
            // If char is not valid
            if (!characterLists.CHARACTERS.contains(s.charAt(i)) && !characterLists.OPERATORS.contains(s.charAt(i)) && !characterLists.NUMBERS.contains(s.charAt(i))) {
               throw new IllegalCharacterException(s.charAt(i));
            }
         }
      }
   }

   public void checkForTwoCharactersInARow() throws TwoCharactesInARowException {
      ArrayList<Character> list = new ArrayList<>();
      list.addAll(characterLists.OPERATORS);
      list.addAll(characterLists.CHARACTERS);

      // Loop through every string in arr
      for (int i = 0; i < arr.length; i++) {
         // Check if two operators are after one another or an operator and a character are | for example (++) or (+.) | Brackets after an operator are allowed
         if (i + 1 != arr.length && list.contains(arr[i].charAt(0)) && list.contains(arr[i + 1].charAt(0)) && arr[i + 1].charAt(0) != '(' && arr[i + 1].charAt(0) != ')') {
            // Check for key siganture (-) and an illegal character in the next string ([+][.4])
            if (arr[i + 1].length() == 1 || characterLists.CHARACTERS.contains(arr[i + 1].charAt(0))) {
               throw new TwoCharactesInARowException(new char[] {arr[i].charAt(0), arr[i + 1].charAt(0)});
            }
         } else {
            // Loop through every char in every string
            for (int j = 0; j < arr[i].length(); j++) {
               // If two same chars are after one another in the same string
               if (j + 1 != arr[i].length() && list.contains(arr[i].charAt(j)) && list.contains(arr[i].charAt(j + 1))) {
                  throw new TwoCharactesInARowException(new char[] {arr[i].charAt(j), arr[i].charAt(j+1)});
               }
            }
         }
      }
   }

   public void checkForIllegalCharacterAfterNumber() throws IllegalCharacterAfterNumberException {
      // Loop through ervery string in array
      for (String s : arr) {
         // If the last char of the string in array is (.) or (,)
         if (s.charAt(s.length() - 1) == '.' || s.charAt(s.length() - 1) == ',') {
            throw new IllegalCharacterAfterNumberException(s.charAt(s.length() - 1));
         }
      }
   }

   public void checkForWrongBrackets() throws WrongBracketsException {
      // Store the amount of openingbracks without closing brackets in a variable
      int amountOfOpeningBracketsWithoutClosingBrackets = 0;
      for (String s : arr) {
         // If string is an opening bracket, add 1 to amountOfOpeningBracketsWithoutClosingBrackets
         if (s.charAt(0) == '(') {
            amountOfOpeningBracketsWithoutClosingBrackets++;
         } else if (s.charAt(0) == ')') {
            // If string is a closing bracket, substract 1 from amountOfOpeningBracketsWithoutClosingBrackets
            amountOfOpeningBracketsWithoutClosingBrackets--;
         }
      }
      if (amountOfOpeningBracketsWithoutClosingBrackets != 0) {
         throw new WrongBracketsException();
      }
   }

   @Override
   public String toString() {
      if (arr == null) return "The array is null";
      StringBuilder stringBuilder = new StringBuilder();

      if (arr.length == 1) {
         for (String s : arr) {
            stringBuilder.append(s);
         }
         return stringBuilder.toString();
      }

      for (String s : arr) {
         stringBuilder.append("[").append(s).append("]");
      }
      return stringBuilder.toString();
   }

}