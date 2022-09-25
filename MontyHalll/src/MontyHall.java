import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MontyHall {
    int rightNumber;
    int input;
    int wrongnumber;

    public MontyHall() {
        start();
    }

    public void start() {
        System.out.println("Welcom to the Monty Hall problem");
        System.out.print("\nYour options are: [a = auto] [s = specific]: ");
        String option = readString();
        if (option.equals("a")) {
            System.out.print("\nLength: ");
            loop(readInt());
        } else if (option.equals("s")) {
            calculate(false);
        } else {
            start();
        }
    }

    public void loop(double length) {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            if (calculate(true)) {
                sum++;
            }
        }
        System.out.println("\nProbability in (%): " + (sum/(length/100)));
    }

    public boolean calculate(boolean auto) {
        rightNumber = getRandomNumber(3, 1);
        input = getInput(auto);
        wrongnumber = getWrongNumber();
        switchInput(auto);
        return output();
    }

    public int getInput(boolean auto) {
        if (auto) {
            return getRandomNumber(3, 1);
        } else {
            System.out.print("\nPut in your number: ");
            int input = readInt();
            if (input == 1 || input == 2 || input == 3) {
                return input;
            } else {
                return getInput(false);
            }
        }
    }

    public int getWrongNumber() {
        for (int i = 1; true; i++) {
            if (i != rightNumber && i != input) {
                return i;
            }
        }
    }

    public void switchInput(boolean auto) {
        if (!auto) {
            System.out.print("\nSwitch?: ");
            if (readString().equals("y")) {
                getNewInput();
            }
        } else {
            getNewInput();
        }
    }

    public void getNewInput() {
        int oldInput = input;
        for (int i = 1; i < 4; i++) {
            if (i != wrongnumber && i != oldInput) {
                input = i;
            }
        }
    }

    public boolean output() {
        System.out.println("\ninput: " + input);
        System.out.println("Right number: " + rightNumber);
        return input == rightNumber;
    }

    public int getRandomNumber(int max, int min) {
        return ((int) (Math.random()*max))+min;
    }

    public String readString() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e) {
            return "";
        }
    }

    public int readInt() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            return 0;
        }
    }

}
