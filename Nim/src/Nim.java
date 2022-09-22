import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nim {
    private int amount = 21;
    private int amountLastPickedByPlayer = 0;
    private int amountLastPickedByComputer = 0;
    private boolean bool = true;
    private boolean cheatedRun = false;
    private boolean playerfailed = false;

    public Nim() {
       loop();
    }

    public void loop() {
        String currentTurn = "Player";
        output();
        while (bool) {
            // Player turn
            if (currentTurn.equals("Player")) {
                playerPick();
                currentTurn = "Computer";
            }
            // Computer turn
            else {
                computerPick();
                currentTurn = "Player";
            }
            checkWin(currentTurn);
        }
    }

    public void checkWin(String winner) {
        if (amount <= 0) {
            bool = false;
            System.out.println(winner + " has won");
        }
    }

    public void computerPick() {
        if (bool) {
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
        if (bool) {
            System.out.print("\namount: ");
            amountLastPickedByPlayer = readInt();
            if (amount == 21 && amountLastPickedByPlayer == 72) {
                cheatedRun = true;
                amountLastPickedByPlayer = 0;
                return;
            }
            if (amountLastPickedByPlayer <= 3 && amountLastPickedByPlayer > 0 && amountLastPickedByPlayer <= amount) {
                pick(amountLastPickedByPlayer);
            } else {
                System.out.print("Not a valid number try again");
                playerPick();
            }
        }
    }

    public void pick(int amount) {
        this.amount -=amount;
        System.out.println();
        output();
    }

    public void output() {
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
                return 72;
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

}