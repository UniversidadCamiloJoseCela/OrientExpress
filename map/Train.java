package map;

import java.util.ArrayList;

public class Train {

    private ArrayList<Carriage> arrCarriages = new ArrayList<Carriage>();

    private String name;

    public Train(String name) {
        this.name = name;
    }

    public ArrayList<Carriage> getArrCarriages() {
        return arrCarriages;
    }

    public void setArrCarriages(ArrayList<Carriage> arrCarriages) {
        this.arrCarriages = arrCarriages;
    }
}
