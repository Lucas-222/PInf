package parsing;

import java.util.*;

public class InfixToPostfix {
   private String[] arr;
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
      arr = input.split("");

      // Soround operators and brackets with whitespaces
      soroundOperatorsAndBracketsWithWhitespaces();

      // Split after every whitespace
      StringBuilder string = new StringBuilder(" ");
      for (String s : arr) string.append(s);
      arr = string.toString().split(" ");

      // Creation of new arrayList with values of the array
      ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));

      // Remove empty strings
      list.removeIf(s -> s.equals(""));

      // Fill the array with values from the arraylist
      arr = list.toArray(new String[0]);

      // Check for illegal input
      arr = new ExceptionCheck(arr).check();

      return arr;
   }

   public void soroundOperatorsAndBracketsWithWhitespaces() {
      for (int i  = 0; i < arr.length; i++) {
         char currentChar = arr[i].charAt(0);
         // If i, is not the first character in input, and currentchar is an operator or a bracket
         if (i != 0 && CharacterLists.OPERATORS.contains(currentChar) || currentChar == '(' || currentChar == ')') {
            if (i+1 <= arr.length && i-1 >= 0 && currentChar == '-' && CharacterLists.isNumber(arr[i+1])) {
               char lastchar = arr[i-1].charAt(0);
               // If there is an operator, a bracket or a whitespace before a minus
               if (CharacterLists.OPERATORS.contains(lastchar) || CharacterLists.CHARACTERS.contains(lastchar) || lastchar == ' ') {
                  i++;
                  // Extend the operator with whitespaces around it | (+) --> ( + )
               } else arr[i] = " " + arr[i] + " ";
            } else arr[i] = " " + arr[i] + " ";
         } else if (currentChar == '-') i++;
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
      for (String token : arr) {
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