package map;

import java.util.ArrayList;
import java.util.Arrays;

/* Todos los vagones del tren son contenidos en la clase Tren.
Por defecto hay un conjunto ordenado de vagones.
 También se puede crear un tren con el orden de vagones personalizados.
 2 constructores:
    - Predefinido: vagones ordenados por defecto.
    - Personalizado: vagones ordenados por creación de nuevo array de vagones.
 */

public class Train {

    public static ArrayList<Carriage> arrCarriages = new ArrayList<Carriage>();

    private String name;
    //Constructor predefinido
    public Train(String name) {
        this.name = name;
        arrCarriages = new ArrayList<>(Arrays.asList
                ( new Locomotive("Locomotora","Vagón donde se encuentre el motor del tren junto con sus trabajadores. ", true ),
                        new Coul("Almacén de combustible","Vagón donde se encuentra todo el carbón del tren y sus trabajadores.",true),
                        new Lounge("Vagón salón", "Salón del tren donde se celebran todos los actos relevantes.",true),
                        new DiningRoom("Sala de comidas", "Vagón dedicado para todo tipo de comidas",true),
                        new Kitchen("Cocina","Cocina del tren.",true),
                        new BaggageRoom("Sala de maletas","Se guarda todo equipaje, similares y derivados de estos.",true),
                        new Bedroom("Dormitorio","Habitaciones de los pasajeros",true),
                        new Bathroom("Baños","Lugar donde se hacen todas las necesidades biológicas.",true)
                ));

    }

    //Constructor personalizado por el usuario
    public Train(String name, ArrayList<Carriage> arrCarriages) {
        this.name = name;
        Train.arrCarriages = arrCarriages;

    }

    public ArrayList<Carriage> getArrCarriages() {
        return arrCarriages;
    }

    public void setArrCarriages(ArrayList<Carriage> arrCarriages) {
        Train.arrCarriages = arrCarriages;
    }

    public static int getPositionCarriage(Carriage carriage) {
        return arrCarriages.indexOf(carriage);
    }
}
