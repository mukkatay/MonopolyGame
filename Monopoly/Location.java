import java.util.ArrayList;
import java.util.List;

public class Location {

    private String name;
    private int locationIndex = 0;

    public Location(String name, int locationIndex) {
        this.name = name;
        this.locationIndex = locationIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
    }

    public String toString() {
        return this.name;
    }

    public static List<Location> buildLocations() {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("СТАРТ", 0));
        locations.add(new LocationCity("УЛИЦА КОНАЕВ", 1, 600_000, 100_000, null));
        locations.add(new LocationLuckyCard("ШАНС", 2));
        locations.add(new LocationCity("АЛАТАУ", 3, 600_000, 100_000, null));
        locations.add(new LocationCity("ВОКЗАЛ", 5, 2000_000, 400_000, null));
        locations.add(new LocationCity("РАЙОН БАЗАР", 6, 1000_000, 200_000, null));
        locations.add(new LocationLuckyCard("ШАНС", 7));
        locations.add(new LocationCity("УЛИЦА АШЕКЕЕВА", 8, 1_000_000, 200_000, null));
        locations.add(new LocationCity("УЛИЦА МАКАТАЕВА", 9, 1_200_000, 250_000, null));
        locations.add(new LocationJail("ТЮРЬМА", 10, 0));
        locations.add(new LocationCity("ДОМ КУЛЬТУРЫ", 11, 1_400_000, 300_000, null));
        locations.add(new LocationCity("ЖИБЕК-ЖОЛЫ", 13, 1400_000, 300_000, null));
        locations.add(new LocationCity("УШКОНЫР АВЕНЮ", 14, 1600_000, 350_000, null));
        locations.add(new LocationCity("СТАНЦИЯ ШАМАЛГАН", 15, 2000_000, 400_000, null));
        locations.add(new LocationCity("УЛИЦА АДИЛЬБЕКОВА", 16, 1800_000, 350_000, null));
        locations.add(new LocationLuckyCard("ШАНС", 17));
        locations.add(new LocationCity("УЛИЦА БАЦТУРСЫНОВА", 18, 1800_000, 350_000, null));
        locations.add(new LocationCity("УЛИЦА КАРАСАЙ-БАТЫР", 19, 2000_000, 400_000, null));
        locations.add(new LocationCity("АУЕЗОВ", 21, 2200_000, 450_000, null));
        locations.add(new LocationLuckyCard("ШАНС", 22));
        locations.add(new LocationCity("УЛИЦА АЛЖАН-АНА", 23, 2200_000, 450_000, null));
        locations.add(new LocationCity("НАБЕРЕЖНАЯ", 24, 2400_000, 500_000, null));
        locations.add(new LocationCity("СТАНЦИЯ УШКОНЫР", 25, 2000_000, 400_000, null));
        locations.add(new LocationCity("ПЛОЩАДЬ НАУРЫЗБАЙ-БАТЫР", 26, 2600_000, 550_000, null));
        locations.add(new LocationCity("УЛИЦА ЮЖНАЯ", 27, 2600_000, 550_000, null));
        locations.add(new LocationCity("АРДАГЕРЛЕР", 29, 2800_000, 600_000, null));
        locations.add(new LocationJail("СЕСТЬ В ТЮРЬМУ", 30, 10_000));
        locations.add(new LocationCity("УЛИЦА АБИШ", 31, 3000_000, 650_000, null));
        locations.add(new LocationCity("УЛИЦА ОТЕГЕН-БАТЫР", 32, 3000_000, 650_000, null));
        locations.add(new LocationLuckyCard("ШАНС", 33));
        locations.add(new LocationCity("УЛИЦА АКСАЙ", 34, 3200_000, 700_000, null));
        locations.add(new LocationCity("СТАНЦИЯ ЖАНДОСОВ", 35, 2000_000, 400_000, null));
        locations.add(new LocationLuckyCard("ШАНС", 36));
        locations.add(new LocationCity("АТАМЕКЕН", 37, 3500_000, 750_000, null));
        locations.add(new LocationCity("КАРАСАЙ", 39, 4000_000, 800_000, null));
        return locations;
    }

}
