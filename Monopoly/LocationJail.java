public class LocationJail extends Location {

    public static int jailLocationIndex;
    public static int goToJailLocationIndex;
    private int type;

    public LocationJail(String name, int locationIndex, int type) {
        super(name, locationIndex);
        this.type = type;
        if (type == 0) {
            jailLocationIndex = locationIndex;
        } else {
            goToJailLocationIndex = locationIndex;
        }
    }

    public int getType() {
        return type;
    }
}
