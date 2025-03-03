import characters.*;
import map.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main (String[] argumentos) {


        BaggageRoom baggageRoom= new BaggageRoom("Deja maletas","Es todo un vagon preparado exclusivamente con la intencion  de martener las \n" +
                "pertenencias a salvo durante todo el viaje.\n" +
                "Se trata de dos filas con una altura suficiente para 4 maletas en horizontal con un grosor de tres manos,\n" +
                "cada una de dichas filas estan repartidas en cinco columnas y señalizadas con la habitacion a la que corresponden. ",false);
        
        Bathroom bathroom= new Bathroom("Baño de lujo","Curiosamente se trata de uno de los baños mas limpios que veras en tu vida,el cual tiene un riguroso reglaje de limpieza entre viajes.\n" +
                "Aparte de eso se trata de un baño bastante corriente el cual viene preparado para toda 'emergencia posible'.",false);

        Bedroom bedroom= new Bedroom("Habitacion de matrimonio","Es una habitacion decorada con una mesilla de noche y la cama de matrimanio de algodon.\n" +
                "De colores claros y vibrantes para que la estancia no se haga pesada para el viajero, esta habilitada con un pequeño armario para poder guardar objetos 'personales'. ",false);

        Coul coul= new Coul("Punto negro","No es muy necesario decir que se trata del sitio mas sucio de todo el tren por el hecho de almacenar el carbon para alimentar la gran maquina,\n" +
                "no es mal sitio si quieres deshacerte de algo.", false);//creo que le falta algo

        DiningRoom diningRoom= new DiningRoom("El comedor","El comedor esta habilitado con seis mesas semiredondas con vistas por la ventana \n" +
                "y en el medio una mesa rectangular. Esta adornada con pequeños candelabros de cristal y cuberteria de plata.",false);
        
        Kitchen kitchen = new Kitchen("El arte de la alta cocina", "Se trata de una de las zonas más limpias y ordenadas, donde se realizan las preparaciones \n"+
                "de los menús y platos más codiciados, cuenta con una gran variedad de instrumentos culinarios. Es una zona muy protegida y con acceso restringido", false);
        Locomotive locomotive = new Locomotive("Locomotora", "Parte crucial del tren, ya que provoca su movimiento. Es el lugar donde encontraremos al maquinista y todos los componentes \n" +
                "necesarios para el correcto funcionamiento de la máquina, además de las herramientas mecánicas. También nos podemos encontrar a los fogoneros \n "+
                "encargados de echar el carbón en el fogón, el cual se encuentra en este lugar", false);
        Lounge lounge = new Lounge("El gran salón", "Aquí nos encontraremos con un muy plácido y relajante área durante las horas del día, y por la noche se transforma en \n"+
                "un ambiente más sociable y fiestero, para reuniones, bailes y pasar un rato agradable. También hay un barman que se encargará de preparar unos deliciosos cócteles",false);
         //por donde entrara el personaje, primera escena
        Viewpoint viewpoint = new Viewpoint("Mirador 'El balcón de los poetas'", "Paraje donde podrás navegar las olas de la imaginación, observar la naturaleza \n"+
                "e inspirarte. Es el espacio más seguro y privado en ciertos momentos del día.", false);




        Train orientExpress = new Train("El orient express");

        //Creation of empty(null objects) bidimensional array
        Object[][] mapping = new Object[3][7];

        // Constructor to fill the array with "x"
            // Loop through each row
            for (int i = 0; i < mapping.length; i++) {
                // Loop through each column in the current row
                Arrays.fill(mapping[i], "■");
                if(i % 2 != 0){
                    mapping[i][0] = "🚪";
                    mapping[i][mapping[i].length-1] = "🚪";
                }
            }


        // A method to print the 2D array
            for (Object[] row : mapping) {
                for (Object cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }



        viewpoint.setMapping(mapping);

        orientExpress.getArrCarriages().add(viewpoint);
        orientExpress.getArrCarriages().add(baggageRoom);



        for (int i = 0; i < orientExpress.getArrCarriages().size(); i++) {
            System.out.println(orientExpress.getArrCarriages().get(i).getCarriageName());
        }









    }
}
