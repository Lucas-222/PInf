import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nim {
    private int amountOfCards = 21;
    private int amountLastPicked = 0;
    private boolean b = true;
    private boolean cheatedRun = false;
    public String currentTurn = "Player";

    public Nim() {
        if (normalLoop().equals("cheated")) {
            cheatedLoop();
        }
    }

    public void cheatedLoop() {
        currentTurn = "Computer";
        while (b) {
            // Player turn
            if (currentTurn.equals("Player")) {
                playerPick();
                currentTurn = "Computer";
                // Computer turn
            } else {
                computerPick();
                currentTurn = "Player";
            }
            checkWin(currentTurn);
        }
    }

    public String normalLoop() {
        output();
        while (b) {
            // Player turn
            if (currentTurn.equals("Player")) {
                playerPick();
                if (cheatedRun) {
                    return "cheated";
                }
                currentTurn = "Computer";
                // Computer turn
            } else {
                computerPick();
                currentTurn = "Player";
            }
            checkWin(currentTurn);
        }
        return "";
    }

    public void checkWin(String winner) {
        if (amountOfCards <= 0) {
            b = false;
            System.out.println(winner + " has won");
        }
    }

    public void computerPick() {
        if (b) {
            int amount;
            if (cheatedRun) {
                if (amountOfCards == 21) {
                    amount = 2;
                } else {
                    amount = 4 - amountLastPicked;
                }
            } else {
                amount = 4 - amountLastPicked;
            }

            System.out.println("\nComputer picked: " + amount);
            pick(amount);
        }
    }

    public void playerPick() {
        if (b) {
            System.out.print("\namount: ");
            amountLastPicked = readInt();
            if (amountOfCards == 21 && amountLastPicked == 72) {
                cheatedRun = true;
                return;
            }
            if (amountLastPicked <= 3 && amountLastPicked > 0 && amountLastPicked <= amountOfCards) {
                pick(amountLastPicked);

            } else {
                System.out.print("Not a valid number try again");
                playerPick();
            }
        };
    }

    public void pick(int amount) {
        amountOfCards -=amount;
        System.out.println();
        output();
    }

    public void output() {
        for (int i = 0; i < amountOfCards; i++) {
            System.out.print("[*]");
        }
    }

    public int readInt() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            if (s.equals("l")) {
                return 72;
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}