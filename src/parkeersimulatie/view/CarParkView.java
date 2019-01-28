package parkeersimulatie.view;

import parkeersimulatie.Main.Simulator;
import parkeersimulatie.logic.AbstractModel;
import parkeersimulatie.logic.Car;
import parkeersimulatie.logic.CarPark;
import parkeersimulatie.logic.Location;

import java.awt.*;

public class CarParkView extends AbstractView {

    private Dimension size;
    private Image carParkImage;

    private Simulator simulator;

    public CarParkView(CarPark model) {
        super(model);
        size = new Dimension(0, 0);

    }



    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
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

    public void updateView() {
        // Create a new car park image if the size has changed.

        CarPark carPark = (CarPark) super.model;

        if (!size.equals(getSize())) {
            size = getSize();
            carParkImage = createImage(size.width, size.height);
        }
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
                10 - 1); // TODO use dynamic size or constants
    }
}
