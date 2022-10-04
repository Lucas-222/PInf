import java.util.HashMap;

public class TermParser {
   String input;
   String[] arr;
   HashMap<Character, Operators> operators = fillMap();

   public TermParser(String input) {
      this.input = input;
   }

   public void parse() {
      // Es wird ein temporärer Wert erzeugt
      String temp = input;

      // Leerzeichen entfernen
      temp = temp.replaceAll("\\s", "");

      // Leerzeichen um Operator hinzufügen
      for (int i = 0; i < input.length(); i++) {
         // Wenn der aktuelle char ein Operator ist
         if (operators.containsKey(input.charAt(i))) {
            // Ersetzen des Operators mit dem Operator und zwei Leerzeichen herum
            temp = temp.replace(String.valueOf(input.charAt(i)), " "  + input.charAt(i) + " ");
            // Wenn nach dem Operator ein Vorzeichen kommt
            if (input.charAt(i+1) == '-') {
               i++;
            }
         }
      }

      // Punkt mit Komma ersetzen
      temp = temp.replace(".", ",");

      // Split nach jedem Leerzeichen
      arr = temp.split(" ");
   }

   public HashMap<Character, Operators> fillMap() {
      HashMap<Character, Operators> map = new HashMap<>();
      map.put('+', Operators.ADD);
      map.put('-', Operators.SUBTRACT);
      map.put('*', Operators.MULTIPLY);
      map.put('/', Operators.DIVIDE);
      map.put('^', Operators.POWER);
      return map;
   }

   @Override
   public String toString() {
      // Array was created
      if (arr != null) {
         StringBuilder stringBuilder = new StringBuilder();
         // Fill the Stringbuilder with the operators numbers and brackets
         for (String s : arr) {
            // Only use chars wich are not "" or " "
            if (!s.equals("") && !s.equals(" ")) {
               stringBuilder.append("[").append(s).append("]");
            }
         }
         return stringBuilder.toString();
      }
      // Array was not created
      return "The array is null";
   }
}