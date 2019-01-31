package parkeersimulatie.logic;


public class Location extends AbstractModel {

    private int floor;
    private int row;
    private int place;


     /**
      ** Constructor Location
      *  Zet de parameters naar de lokale variabelen
      * @param floor, row, place
     */
    public Location(int floor, int row, int place) {
        this.floor = floor;
        this.row = row;
        this.place = place;
    }


     /**
      * Methode die kijkt of het object een instance is van location
      * @param obj van Object
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
     *@return de vloer, row en place als string.
     * */
    public String toString() {
        return floor + "," + row + "," + place;
    }

    /**
     *@return de plaatsen
     * */
    public int hashCode() {
        return (floor << 20) + (row << 10) + place;
    }

    /**
     *@return de vloer
     * */
    public int getFloor() {
        return floor;
    }

    /**
     *@return de rij
     * */
    public int getRow() {
        return row;
    }

    /**
     * @return de plaats
     */
    public int getPlace() {
        return place;
    }

}