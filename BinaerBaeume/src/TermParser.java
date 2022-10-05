import java.util.ArrayList;
import java.util.Arrays;

public class TermParser {
   String input;
   String[] arr;
   ArrayList<Character> operators = fillListWithOperators();
   ArrayList<Character> characters = fillListWithCharacters();
   ArrayList<Character> numbers = fillListWithNumbers();

   public TermParser(String input) {
      this.input = input;
   }

   public void parse() {
      // Create temp variable for writing | input is only used for reading
      String temp = input;

      // Remove whitespaces from the temp string
      temp = temp.replaceAll("[\\s|\\u00A0]+", "");

      // Loop through the input
      for (int i = 0; i < input.length(); i++) {
         // If current char is an operator or character
         if (operators.contains(input.charAt(i)) || input.charAt(i) == '(' || input.charAt(i) == ')') {
            // Extend operator with two whitespaces around it
            temp = temp.replace(String.valueOf(input.charAt(i)), " " + input.charAt(i) + " ");
            // If there is a minus after the operator or character
            if (input.length() != i + 1 && input.charAt(i + 1) == '-') {
               // Skip the next char and set it as a part of the number
               i++;
            }
         }
      }

      // replace komma with point
      temp = temp.replace(",", ".");

      // Split after every whitespace
      arr = temp.split(" ");

      // Remove whitespaces
      deleteWhitespacesFromArray();
   }

   public boolean checkForIllegalCharacters() {
      // Loop through array
      for (String s : arr) {
         // For every String in array loop through every char
         for (int i = 0; i < s.length(); i++) {
            // Create temp variable
            char temp = s.charAt(i);
            // If char is not valid
            if (!characters.contains(temp) && !operators.contains(temp) && !numbers.contains(temp)) {
               return true;
            }
         }
      }
      // Check for two same characters in a row
      if (checkForTwoCharactersInARow(characters)) {
         return true;
      }
      // Check for two same operators in a row
      return checkForTwoCharactersInARow(operators);
   }

   public boolean checkForTwoCharactersInARow(ArrayList<Character> list) {
      // Loop through every String in arr
      for (int i = 0; i < arr.length; i++) {
         // Check if two operators are after one another | for example (++)
         if (i+1 != arr.length && list.contains(arr[i].charAt(0)) && list.contains(arr[i+1].charAt(0))) {
            // Exclude key siganture (-)
            if (arr[i+1].length() == 1) {
               return true;
            }
         } else {
            // Loop through every char in every String
            for (int j = 0; j < arr[i].length(); j++) {
               // If two same chars are after one another in the same String
               if (j+1 != arr[i].length() && list.contains(arr[i].charAt(j)) && list.contains(arr[i].charAt(j+1))) {
                  return true;
               }
            }
         }
      }
      // Everything is ok
      return false;
   }

   public ArrayList<Character> fillListWithOperators() {
      ArrayList<Character> list = new ArrayList<>();
      list.add('+');
      list.add('-');
      list.add('*');
      list.add('/');
      list.add('^');
      return list;
   }

   public ArrayList<Character> fillListWithCharacters() {
      ArrayList<Character> list = new ArrayList<>();
      list.add('(');
      list.add(')');
      list.add(',');
      list.add('.');
      return list;
   }

   public ArrayList<Character> fillListWithNumbers() {
      ArrayList<Character> list = new ArrayList<>();
      list.add('0');
      list.add('1');
      list.add('2');
      list.add('3');
      list.add('4');
      list.add('5');
      list.add('6');
      list.add('7');
      list.add('8');
      list.add('9');
      return list;
   }

   public void deleteWhitespacesFromArray() {
      // Creation of new arrayList with values of the array
      ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));

      // Remove Whitespaces
      list.removeIf(s -> s.equals(""));

      // Fill the array with values from the arraylist
      arr = list.toArray(new String[0]);
   }

   @Override
   public String toString() {
      // If input contains illgal characters
      if (checkForIllegalCharacters()) {
         return "Illegal argument found";
      }
      // If array is null
      if (arr == null) {
         return "The array is null";
      }

      // Normal
      StringBuilder stringBuilder = new StringBuilder();
      // Fill the Stringbuilder with the operators numbers and brackets
      for (String s : arr) {
         stringBuilder.append("[").append(s).append("]");
      }
      return stringBuilder.toString();
   }
}