package parsing;

import java.util.*;

public class InfixToPostfix {
   private String[] inputAsArray;
   private String input;

   public InfixToPostfix(String input) {
      this.input = input;
   }

   public String[] parse() {
      // Remove whitespaces from the input string
      input = input.replaceAll("[\\s|\\u00A0]+", "");

      // replace comma with a point
      input = input.replace(",", ".");

      // Split input after every ""
      inputAsArray = input.split("");

      // Soround operators and brackets with whitespaces
      soroundOperatorsAndBracketsWithWhitespaces();

      // Convert inputAsArray to stringbuilder
      StringBuilder string = new StringBuilder(" ");
      for (String s : inputAsArray) string.append(s);

      // Split after every whitespace
      inputAsArray = string.toString().split(" ");

      // Creation of new arrayList with values of the array
      ArrayList<String> list = new ArrayList<>(Arrays.asList(inputAsArray));

      // Remove empty strings
      list.removeIf(s -> s.equals(""));

      // Fill the array with values from the arraylist
      inputAsArray = list.toArray(new String[0]);

      // Check for illegal input
      inputAsArray = new ExceptionCheck(inputAsArray).check();

      return inputAsArray;
   }

   public void soroundOperatorsAndBracketsWithWhitespaces() {
      for (int i = 0; i < inputAsArray.length; i++) {
         char currentChar = inputAsArray[i].charAt(0);

         // If the first char in input is a minus
         if (i == 0 && currentChar == '-') {
            i++;
            continue;
         }

         // If currentchar is a number
         if (!CharacterLists.OPERATORS.contains(currentChar) && currentChar != '(' && currentChar != ')') {
            continue;
         }

         // If currentchar is not a minus and next char is not a number
         if (i+1 > inputAsArray.length || i-1 < 0 || currentChar != '-' || !CharacterLists.isNumber(inputAsArray[i+1])) {
            inputAsArray[i] = " " + inputAsArray[i] + " ";
            continue;
         }

         char lastchar = inputAsArray[i-1].charAt(0);

         // If there is an operator a bracket or a whitespace in front of the minus
         if (!CharacterLists.OPERATORS.contains(lastchar) && !CharacterLists.CHARACTERS.contains(lastchar) && lastchar != ' ') {
            inputAsArray[i] = " " + inputAsArray[i] + " ";
            continue;
         }

         i++;

      }
   }
   
   public String[] postfix() {
      // Create a LIFO stack
      Stack<String> stack = new Stack<>();
      // Create a nodes list
      List<String> nodesAsString = new LinkedList<>();
      // Parse the input
      parse();

      // Loop through every string in arr
      for (String token : inputAsArray) {
         // Check for number
         if (CharacterLists.isNumber(token)) nodesAsString.add(token);

         // If token is an operator
         if (token.length() == 1 && CharacterLists.OPERATORS.contains(token.charAt(0))) {
            // While stack is not empty AND operators contains stack peek AND token is left associative AND the precedency of token is smaller or equal to the precedency of stack peek
            while (!stack.empty() && isLeftAssociative(token) && getPrecedenty(token) <= getPrecedenty(stack.peek())) {
               nodesAsString.add(stack.pop());
            }
            stack.push(token);
         }

         // If token is a opnening bracket
         if (token.equals("(")) stack.push(token);

         // If token is a closing bracket
         if (token.equals(")")) {
            // While stack peek is not an opening bracket
            while (!stack.peek().equals("(")) {
               nodesAsString.add(stack.pop());
            }
            // Remove the opening bracket
            stack.pop();
         }
      }

      // Fill nodesAsString with the rest of stack
      while (!stack.empty()) {
         nodesAsString.add(stack.pop());
      }

      // Return a string array with the reversed polish notation and the solution
      return new String[] {nodesAsString.toString(), String.valueOf(new PostfixToNode(nodesAsString).calcNode().getValue())};
   }

   public boolean isLeftAssociative(String s) {
      if (s.length() == 1) {
         return s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '/' || s.charAt(0) == '*';
      }
      return false;
   }

   public int getPrecedenty(String s) {
      if (s.length() == 1) {
         if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            return 1;
         } else if (s.charAt(0) == '*' || s.charAt(0) == '/') {
            return 2;
         } else if (s.charAt(0) == '^') {
            return 3;
         }
      }
      return -1;
   }

   @Override
   public String toString() {
      // If inputAsArray is null
      if (inputAsArray == null) return "The array is null";

      // If there was an exception thrown
      if (inputAsArray.length == 1) return inputAsArray[0];

      StringBuilder stringBuilder = new StringBuilder();
      for (String s : inputAsArray) {
         stringBuilder.append("[").append(s).append("]");
      }
      return stringBuilder.toString();
   }

}