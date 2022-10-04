public class TermParser {

   public String parse(String input) {
      boolean running = true;
      char[] operators = new char[] {'+', '-', '*', '/', '^'};

      // Leerzeichen entfernen
      input = deleteWhiteSpaces(input);

      // Leerzeichen hinzufügen
      for (int i = 0; running; i++) {
         if (contains(operators, input.charAt(i))) {
            input = input.replace(String.valueOf(input.charAt(i)), " "  + input.charAt(i) + " ");
            running = false;
         }
      }

      // Split
      String[] arr = input.split(" ");

      StringBuilder stringBuilder = new StringBuilder();

      for (int i = 0; i < arr.length; i++) {
         stringBuilder.append(arr[i]);
      }
      System.out.println(arr);

      return stringBuilder.toString();
   }

   public String deleteWhiteSpaces(String input) {
      input = input.replace(" ", "");
      input = input.replace("\n", "");
      input = input.replace("\t", "");
      return input;
   }

   public boolean contains(char[] arr, char input) {
      for (int i = 0; i < arr.length; i++) {
         if (input == arr[i]) {
            return true;
         }
      }
      return false;
   }

}