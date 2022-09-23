import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nim {
    private int amount = 21;
    private int amountLastPickedByPlayer = 0;
    private int amountLastPickedByComputer = 0;
    private boolean runing = true;
    private boolean cheatedRun = false;
    private boolean playerfailed = false;
    String currentTurn = "Player";

    public Nim() {
       loop();
    }

    public void loop() {
        output();
        while (runing) {
            // Player turn
            if (currentTurn.equals("Player")) {
                playerPick();
            } else /* Computer turn */ {
                computerPick();
            }
            checkWin(currentTurn);
        }
    }

    public void checkWin(String winner) {
        if (amount <= 0) {
            runing = false;
            System.out.println(winner + " has won");
        }
    }

    public void computerPick() {
        if (runing) {
            if (amountLastPickedByPlayer != 4 - amountLastPickedByComputer && amountLastPickedByPlayer != 0 && !playerfailed) {
                playerfailed = true;
            }
            // Cheated run
            if (cheatedRun) {
                // Default
                int temp = ((int) (Math.random()*3))+1;
                // When player failed
                if (playerfailed) {
                    if (amount % 4 == 0) {
                        temp = 3;
                    } else {
                        temp = amount % 4 - 1;
                    }
                }
                // When Only 4 or less are left
                if (amount <= 4) {
                    if (amount - 1 != 0) {
                        temp = amount - 1;
                    } else {
                        temp = 1;
                    }
                }

                amountLastPickedByComputer = temp;
            } else /* Normal run */ {
              amountLastPickedByComputer = 4 - amountLastPickedByPlayer;
            }

            System.out.println("\nComputer picked: " + amountLastPickedByComputer);
            pick(amountLastPickedByComputer);
        }
        currentTurn = "Player";
    }

    public void playerPick() {
        if (runing) {
            System.out.print("\namount: ");
            amountLastPickedByPlayer = readInt();
            if (amount == 21 && amountLastPickedByPlayer == 0) {
                cheatedRun = true;
                return;
            }
            if (amountLastPickedByPlayer <= 3 && amountLastPickedByPlayer > 0 && amountLastPickedByPlayer <= amount) {
                pick(amountLastPickedByPlayer);
            } else {
                System.out.print("Not a valid number try again");
                playerPick();
            }
        }
        currentTurn = "Computer";
    }

    public void pick(int amount) {
        this.amount -= amount;
        output();
    }

    public void output() {
        System.out.println();
        for (int i = 0; i < amount; i++) {
            System.out.print("[*]");
        }
        if (amount != 0) {
            System.out.print(" (" + amount + ")");
        }
    }

    public int readInt() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = reader.readLine();
            if (s.equals("xyzzy")) {
                return 0;
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}