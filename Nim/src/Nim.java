import java.io.BufferedReader;
import java.io.InputStreamReader;

enum Players {
    PLAYER,
    COMPUTER;
}

public class Nim {
    private int amount = 21;
    private int amountLastPickedByPlayer = 0;
    private int amountLastPickedByComputer = 0;
    private boolean running = true;
    private boolean cheatedRun = false;
    private boolean playerfailed = false;
    private Players currentPlayer = Players.PLAYER;

    public Nim() {
       loop();
    }

    public void loop() {
        output();
        while (running) {
            // Player turn
            if (currentPlayer == Players.PLAYER) {
                playerPick();
            } else /* Computer turn */ {
                computerPick();
            }
        }
    }

    public void checkWin() {
        if (amount <= 0) {
            running = false;
            System.out.println("\n" + currentPlayer + " has won");
        }
    }

    public void computerPick() {
        if (running) {
            // Cheated run
            if (cheatedRun) {
                if (amountLastPickedByPlayer != 4 - amountLastPickedByComputer && amountLastPickedByPlayer != 0 && !playerfailed) {
                    playerfailed = true;
                }
                // Default
                int temp = ((int) (Math.random()*3))+1;
                // If player failed
                if (playerfailed) {
                    if (amount % 4 == 0) {
                        temp = 3;
                    } else {
                        temp = amount % 4 - 1;
                    }
                }
                // If Only 4 or less are left
                if (amount <= 4) {
                    if (amount - 1 != 0) {
                        temp = amount - 1;
                    }
                }

                amountLastPickedByComputer = temp;
            } else { /* Normal run */
              amountLastPickedByComputer = 4 - amountLastPickedByPlayer;
            }

            System.out.println("\nComputer picked: " + amountLastPickedByComputer);
            currentPlayer = Players.PLAYER;
            pick(amountLastPickedByComputer);
        }
    }

    public void playerPick() {
        if (running) {
            currentPlayer = Players.COMPUTER;
            System.out.print("\namount: ");
            amountLastPickedByPlayer = readInt();
            // Is run cheated?
            if (amount == 21 && amountLastPickedByPlayer == 4) {
                cheatedRun = true;
                amountLastPickedByPlayer = 0;
                return;
            }
            // Normal
            if (amountLastPickedByPlayer <= 3 && amountLastPickedByPlayer > 0 && amountLastPickedByPlayer <= amount) {
                pick(amountLastPickedByPlayer);
            } else {
                // Wrong input
                System.out.print("Not a valid number try again");
                playerPick();
            }
        }
    }

    public void pick(int amount) {
        this.amount -= amount;
        checkWin();
        output();
    }

    public void output() {
        System.out.println();
        for (int i = 0; i < amount; i++) {
            System.out.print("[*]");
        }
        if (amount > 0) {
            System.out.print(" (" + amount + ")");
        }
    }

    public int readInt() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            if (s.equals("xyzzy")) {
                return 4;
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }

}