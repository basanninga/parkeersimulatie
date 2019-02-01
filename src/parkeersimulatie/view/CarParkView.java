package parkeersimulatie.view;

import parkeersimulatie.logic.Car;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.logic.Location;

import java.awt.*;


/**
 * Class CarParkView
 *
 * @author Bas Anninga, Victor Vrancianu, Jens Slauerhoff
 * @version 1.0
 */
public class CarParkView extends AbstractView {

    private Dimension size;
    private Image carParkImage;



    /**
     *  Constructor van CarQueueView met een model van CarPark
     *  @param model CarPark dat hoort bij deze view.
     */
    public CarParkView(CarPark model) {
        super(model);
        this.size = new Dimension(800, 330);

    }



    public void paintComponent(Graphics g) {
        if (carParkImage == null) {
            return;
        }

        Dimension currentSize = getSize();
        if (size.equals(currentSize)) {
            g.drawImage(carParkImage, 0, 0, null);
        }
        else {
            // Rescale the previous image.
            g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
        }
    }

    /**
     Methode voor het updaten van de view.
     */

    public void updateView() {
        // Create a new car park image if the size has changed.

        CarPark carPark = (CarPark) super.model;



        carParkImage = createImage(size.width, size.height);

        Graphics graphics = carParkImage.getGraphics();
        for(int floor = 0; floor < carPark.getNumberOfFloors(); floor++) {
            for(int row = 0; row < carPark.getNumberOfRows(); row++) {
                for(int place = 0; place < carPark.getNumberOfPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = carPark.getCarAt(location);
                    Color color = car == null ? Color.white : car.getColor();
                    drawPlace(graphics, location, color);
                }
            }
        }
        repaint();
    }


    private void drawPlace(Graphics graphics, Location location, Color color) {
        graphics.setColor(color);
        graphics.fillRect(
                location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                60 + location.getPlace() * 10,
                20 - 1,
                10 - 1); //
    }
}
