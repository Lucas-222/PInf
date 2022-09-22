import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nim {
    private int amountOfCards = 21;
    private int amountLastPickedByPlayer = 0;
    private int amountLastPickedByComputer = 0;
    private boolean b = true;
    private boolean cheatedRun = false;
    public String currentTurn = "Player";

    public Nim() {
       loop();
    }

    public String loop() {
        output();
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
            // Cheated run
            if (cheatedRun) {
                // Default
                amountLastPickedByComputer = ((int) (Math.random()*3))+1;
                // When player failed
                if (amountLastPickedByPlayer != 4 - amountLastPickedByComputer && amountLastPickedByPlayer != 0) {
                    if (amountOfCards % 4 != 0) {
                        amountLastPickedByComputer = amountOfCards % 4;
                    } else {
                        amountLastPickedByComputer = 4 - amountLastPickedByPlayer;
                    }
                }
                // When Only 3 or less are left
                if (amountOfCards <= 3) {
                    if (amountOfCards-1 != 0) {
                        amountLastPickedByComputer = amountOfCards - 1;
                    } else {
                        amountLastPickedByComputer = 1;
                    }
                }
            }
            // Normal run
            if (!cheatedRun) {
              amountLastPickedByComputer = 4 - amountLastPickedByPlayer;
            }

            System.out.println("\nComputer picked: " + amountLastPickedByComputer);
            pick(amountLastPickedByComputer);
        }
    }

    public void playerPick() {
        if (b) {
            System.out.print("\namount: ");
            amountLastPickedByPlayer = readInt();
            if (amountOfCards == 21 && amountLastPickedByPlayer == 72) {
                cheatedRun = true;
                amountLastPickedByPlayer = 0;
                return;
            }
            if (amountLastPickedByPlayer <= 3 && amountLastPickedByPlayer > 0 && amountLastPickedByPlayer <= amountOfCards) {
                pick(amountLastPickedByPlayer);

            } else {
                System.out.print("Not a valid number try again");
                playerPick();
            }
        }
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
            if (s.equals("xyzzy")) {
                return 72;
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}