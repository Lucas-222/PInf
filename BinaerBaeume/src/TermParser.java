public class TermParser {
   String input;
   String[] arr;

   public TermParser(String input) {
      this.input = input;
   }

   public void parse() {
      char[] operators = new char[] {'+', '-', '*', '/', '^'};
      String temp = input;

      // Leerzeichen entfernen
      temp = temp.replaceAll("\\s", "");

      // Leerzeichen um Operator hinzufügen
      for (int i = 0; i < input.length(); i++) {
         if (contains(operators, temp.charAt(i))) {
            temp = temp.replace(String.valueOf(temp.charAt(i)), " "  + temp.charAt(i) + " ");
         }
      }

      // Split
      arr = temp.split(" ");
   }

   public boolean contains(char[] arr, char c) {
      for (char value : arr) {
         if (c == value) {
            return true;
         }
      }
      return false;
   }

   @Override
   public String toString() {
      StringBuilder stringBuilder = new StringBuilder();

      for (String s : arr) {
         stringBuilder.append(s);
      }
      return stringBuilder.toString();
   }
}