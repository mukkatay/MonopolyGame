import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

    private static final int EVERY_ROUND_BANK_CASH = 500_000;
    private static final int INIT_PLAYER_CASH = 2_000_000_000;

    private String name;
    private int cash = INIT_PLAYER_CASH;
    private List<LocationCity> playerLocations = new ArrayList<>();
    private boolean isInJail = false;
    private int currLocationIndex = 0;

    public Player(String name) {
        this.name = name;
    }

    public int getCurrentLocationIndex() {
        return currLocationIndex;
    }

    public void setCurrLocationIndex(int currLocationIndex) {
        this.currLocationIndex = currLocationIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public List<LocationCity> getBoughtLocations() {
        return playerLocations;
    }

    public boolean isInJail() {
        return isInJail;
    }

    public void setInJail(boolean inJail) {
        isInJail = inJail;
    }

    public void move(List<Location> locations) {
        Scanner scannerUnused = new Scanner(System.in);
        System.out.println("Ход игрока: " + getName());
        if (isInJail()) {
            System.out.println(getName() + " находится в тюрьме.");
            setInJail(false);
            return;
        }
        System.out.println("Нажмите любую клавишу, чтобы бросить кости");
        scannerUnused.nextLine();

        int dice1 = generateRandomDiceValue();
        int dice2 = generateRandomDiceValue();

        int diceResult = dice1 + dice2;
        System.out.println("Результат броска кости: " + diceResult);
        int playerNewLocationIndex = getCurrentLocationIndex() + diceResult;

        if (playerNewLocationIndex >= locations.size()) {
            setCash(getCash() + EVERY_ROUND_BANK_CASH);
            playerNewLocationIndex = playerNewLocationIndex % locations.size();
        }
        setCurrLocationIndex(playerNewLocationIndex);
        int playerLocationIndexAfterMove = getCurrentLocationIndex();
        Location playerLocationAfterMove = locations.get(playerLocationIndexAfterMove);
        System.out.println("Вы сейчас на " + playerLocationAfterMove.getName());

        if (playerLocationAfterMove instanceof LocationJail) {
            LocationJail playerLocAfterMove1 = (LocationJail) playerLocationAfterMove;
            if (playerLocAfterMove1.getType() != 0) {
                setInJail(true);
                setCurrLocationIndex(LocationJail.jailLocationIndex);
            }
        } else if (playerLocationAfterMove instanceof LocationLuckyCard) {
            LocationLuckyCard playerLocAfterMove1 = (LocationLuckyCard) playerLocationAfterMove;
            playerLocAfterMove1.imFeelingLucky(this, locations);
        } else if (playerLocationAfterMove instanceof LocationCity) {
            LocationCity playerLocationAfterMove1 = (LocationCity) playerLocationAfterMove;
            if (playerLocationAfterMove1.isLocationOwned()) {
                int rentAmount = playerLocationAfterMove1.getRentPrice();
                setCash(getCash() - rentAmount);
                playerLocationAfterMove1.getOwner().setCash(playerLocationAfterMove1.getOwner().getCash() + rentAmount);
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.println("У вас есть: " + getCash() + " тг. и цена "
                        + playerLocationAfterMove1.getName().toUpperCase()
                        + " " + playerLocationAfterMove1.getPrice() + " тг.");
                System.out.println("Вы хотите купить это место? (Y/n):");
                String userChoice = scanner.nextLine();
                if (userChoice.toLowerCase().equals("y")) {
                    int price = playerLocationAfterMove1.getPrice();
                    if (getCash() < price) {
                        System.out.println("Извините, у вас недостаточно денег.");
                    } else {
                        setCash(getCash() - price);
                        playerLocationAfterMove1.setOwner(this);
                        getBoughtLocations().add(playerLocationAfterMove1);
                        System.out.println(getName() + " купил место: " + playerLocationAfterMove1.getName() + " и оставшиеся деньги: " + getCash() + " тг.");
                    }

                }
            }
        }
        System.out.println("\n**********************\n");
    }

    public int generateRandomDiceValue() {
        return (int) ((Math.random() * 5) + 1);
    }
}
