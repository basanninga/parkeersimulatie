package parkeersimulatie.logic;


public class Location extends AbstractModel {

    private int floor;
    private int row;
    private int place;


     /**
      * @param floor, row, place
      * Constructor Location
      *  Zet de parameters naar de lokale variabelen
     */
    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }


     /**
      * @param obj
      * Methode die kijkt of het object een instance is van location
     */

    public boolean equals(Object obj) {
        if (obj instanceof Location) {
            Location other = (Location) obj;
            return floor == other.getFloor() && row == other.getRow() && place == other.getPlace();
        } else {
            return false;
        }
    }


    /**
     * Geeft de floor, row en place terug
     *@return String
     * */
    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     * Maakt een hashcode van floor, row en place
     *@return int
     * */
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    /**
     * Geeft de floor terug
     *@return int
     * */
    public int getFloor() {
        return floor;
    }

    /**
     * Geeft de row terug
     *@return int
     * */
    public int getRow() {
        return row;
    }

    /**
     * Geeft de place terug
     * @return int
     */
    public int getPlace() {
        return place;
    }

}