import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {

    private final List<Player> players = new ArrayList<>();
    private final List<Location> locations = new ArrayList<>(Location.buildLocations());

    public void startGame() {
        initPlayersName();
        while (isGameContinue(players)) {
            for (Player player : players) {
                player.move(locations);
            }
        }
    }

    public void initPlayersName() {
        Scanner defaultScanner = new Scanner(System.in);
        Scanner scannerLine = new Scanner(System.in);
        int playerCount;
        do {
            System.out.println("ВВЕДИТЕ ЖЕЛАЕМОЕ КОЛИЧЕСТВО ИГРОКОВ:");
            playerCount = defaultScanner.nextInt();
            if (playerCount < 2 || playerCount > 5) {
                System.out.println("Error: Номер игрока должен быть между 2..5");
            }
        } while (playerCount < 2 || playerCount > 5);


        int i = 1;
        do {
            System.out.println("ВВЕДИТЕ ИМЯ " + i + " ИГРОКА: ");
            String name = scannerLine.nextLine();
            if (containsTheName(players, name)) {
                System.out.println("Ты не можешь взять это имя.");
                continue;
            }
            players.add(new Player(name));
            i++;
        } while (i <= playerCount);
    }


    public boolean isGameContinue(List<Player> players) {
        for (Player player : players) {
            if (player.getCash() < 0) {
                if (player.getBoughtLocations().size() > 0) {
                    System.out.println("Игрок: " + player.getName() +
                            " имеет менее 0 тенге, он/она в долгах, поэтому его/ее принадлежащие помещения будут проданы или лишены права выкупа ");
                    List<LocationCity> playersOwnedLocations = player.getBoughtLocations();
                    Collections.sort(playersOwnedLocations);
                    for (LocationCity city : playersOwnedLocations) {
                        int price = city.getPrice();
                        player.setCash(player.getCash() + price);
                        System.out.println("Расположение игрока Джейка: " + player.getName() + " " +
                                city.getName() + " был продан/заложен в настоящее время, и поэтому у игрока " +
                                player.getName() + " теперь есть " + player.getCash() + " тг.");
                        city.setOwner(null);
                        if (player.getCash() >= 0) {
                            break;
                        }
                    }
                }
                if (player.getCash() < 0) {
                    System.out.println("Player " + player.getName() + " имеет долги и не имеет собственного города, на который можно было бы обратить взыскание, поэтому он/она обанкротился, поэтому ликвидирован");
                }
            }
        }

        if (players.size() == 1) {
            Player winnerPlayer = players.get(0);
            System.out.println("ПОЗДРАВЛЯЕМ!!! ИГРА ОКОНЧЕНА, ИГРОК " + winnerPlayer.getName() + " ВЫИГРАЛ ИГРУ !!!");
            return false;
        } else {
            return true;
        }

    }

    public boolean containsTheName(List<Player> playerList, String givenName) {
        for (Player p : playerList) {
            if (p.getName().equals(givenName)) {
                return true;
            }
        }
        return false;
    }
}
