import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LocationLuckyCard extends Location {

    private int prices[] = {-500, -400, -300, -200, -100, 100, 200, 300, 400, 500};
    private static List<Integer> luckyCardLocations = new ArrayList<>();

    public LocationLuckyCard(String name, int locationIndex) {
        super(name, locationIndex);
        luckyCardLocations.add(locationIndex);
    }

    public static List<Integer> getLuckyCardLocations() {
        return luckyCardLocations;
    }

    public void imFeelingLucky(Player player, List<Location> locations) {
        int ret = (int) ((Math.random() * 2));
        switch (ret) {
            case 0:
                luckyChangeCash(player);
                break;
            case 1:
                luckyTakePlayerToTheJail(player);
                break;
            case 2:
                luckyTakePlayerToAnotherLocation(player, locations, false);
                break;
            default:
                break;
        }
    }

    public void luckyChangeCash(Player player) {
        int rndIndex = new Random().nextInt(prices.length);
        int rndPrice = prices[rndIndex];
        if (rndPrice < 0) {
            System.out.println("Вы берете счастливую карту, и ваши деньги уменьшаются на "
                    + rndPrice + " тг.");
        } else {
            System.out.println("Вы берете счастливую карту, и ваши деньги увеличиваются на "
                    + rndPrice + " тг.");
        }
        player.setCash(player.getCash() + rndPrice);
    }

    public void luckyTakePlayerToTheJail(Player player) {
        System.out.println("Счастливая карта: Вы сейчас в тюрьме.");
        player.setInJail(true);
        player.setCurrLocationIndex(LocationJail.jailLocationIndex);
    }

    public void luckyTakePlayerToAnotherLocation(Player player, List<Location> locations, boolean isTest) {
        int goToJailLocation = LocationJail.goToJailLocationIndex;
        List<Integer> luckyCardLocations = LocationLuckyCard.getLuckyCardLocations();
        int random;
        do {
            random = (int) ((Math.random() * locations.size()));
        } while ((goToJailLocation == random)
                || luckyCardLocations.contains(random));
        System.out.println("Вы берете счастливую карту, и теперь вы находитесь на: "
                + locations.get(random).getName());
        player.setCurrLocationIndex(random);

        Location playerLocAfterMove = locations.get(random);
        System.out.println("Вы сейчас на " + playerLocAfterMove.getName().toUpperCase());

        if (!isTest) {
            if (playerLocAfterMove instanceof LocationCity) {
                LocationCity playerLocAfterMove1 = (LocationCity) playerLocAfterMove;
                if (playerLocAfterMove1.isLocationOwned()) {
                    int rentAmount = playerLocAfterMove1.getRentPrice(); // calculate rent price
                    player.setCash(player.getCash() - rentAmount); // decrease leaseholder player's cash
                    playerLocAfterMove1.getOwner().setCash(playerLocAfterMove1.getOwner().getCash() + rentAmount); // increase owner player's cash
                } else {
                    Scanner scn2 = new Scanner(System.in);
                    System.out.println("У вас есть: " + player.getCash() + " тг. и цена "
                            + playerLocAfterMove1.getName().toUpperCase()
                            + " " + playerLocAfterMove1.getPrice() + " Тг");
                    System.out.println("Вы хотите купить это место? (Y/n):");
                    String userChoice = scn2.nextLine();
                    if (userChoice.equals("Y") || userChoice.equals("y")) {
                        int price = playerLocAfterMove1.getPrice();
                        if (player.getCash() < price) {
                            System.out.println("Извините, у вас недостаточно денег.");
                        } else {
                            player.setCash(player.getCash() - price);
                            playerLocAfterMove1.setOwner(player);
                            player.getBoughtLocations().add(playerLocAfterMove1);
                            System.out.println(player.getName() + " купил место:" + playerLocAfterMove1.getName() + " и оставшиеся деньги: " + player.getCash() + " тг.");
                        }

                    }
                }
            }
        }
    }
}
