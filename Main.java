import characters.*;
import map.*;

import java.util.Arrays;

public class Main {
    public static void main (String[] argumentos) {

        // Char...
        Detective detective = new Detective("Hercules Poirot", 38, false, " Famoso detective belga,\n" +
                " resuelve un robo en la iglesia del Santo Sepulcro en Jerusalen\n" +
                " tambien estuvo en Nadiekistan resolviendo un caso hace 9 anyos donde con la ayuda de un alcade\n" +
                " pudo resolver un triple homicidio en el rio que pasaba por la midad de toda la ciudad, el caso\n" +
                " se conocio como el Asesinato de los Naipes, y forma parte de Scotland Yard donde conocio a Sherlock Holmes",
                "Meticuloso, Vanidoso, Cortes, Maquiavelico");


        DetectiveAssistant detectiveAssistant = new DetectiveAssistant("Robin Retos",18,false,"Lleva trabajando en una granja desde muy pequeño, \n"+
                "es superdotado y empezó a estudiar en la universidad a los 14 años. Terminó la carrera en dos años.\n"
                + "Descubrió La Ley de la Gravedad","Calculador, superdotado, precavido, carismatico, intuitivo");


        LocomotiveDriver locomotiveDriver = new LocomotiveDriver("Louis",45,false,"Conduce trenes desde los 20 años\n"+
                "Pidio dinero a un prestamista para su hija enferma.\n" + 
                "Trabaja todo el día para darle a su hija la vida que el nunca tuvo.", "Luchador, confiable, humilde, adaptativo");//Dicho prestamista fue mas adelante encontrado culpable por tramites ilegales si sus clientes no llegaban a devolder el dinero con interes.

        TrainCoalman trainCoalman = new TrainCoalman("Bob",56,false, "Desde pequeño tuvo que trabajar para comer, no tiene familia. \n" +
                "Siempre ha estado solo, no se sabe mucho de el, su vida es un misterio.","Enclenque, reservado, desconfiado y aprensivo " );

        Comander comander = new Comander("Juan Carlos", 87, false, "Un gran comandante condecorado,\n" +
                " con grandes hazanyas en Nadiekistan  hace 50 anyos, que duro por 5 anyos.\n" +
                " alli rescato de un metro a un ninyo abandonado por su madre, el cual se apiado del ninyo y lo llevo a un orfanato,\n desde" +
                "entonces no supo nada mas de el\n" +
                "Durante 3 anyos estuvo en Las vegas como un ludopata empedernido,\n" +
                "en donde ocurrio un asesinato en el casino en el que se encontraba.", "PTSD, Soberbio, Culto, Abordable");// en el casino conoce a maria por el caso en el que la persona asesinada es el prestamista de Louis


        Mayor mayor = new Mayor("Paco Fiestas", 50, false, "Alcalde de VillaNadie." +
                "\n Ganador del premio mejor alcalde del país Nadiekistan." +
                "\n Sustenta el cargo desde los 20 anyos, y desde encontonces no ha habido contendiente " +
                "que le haya hecho frente gracias a sus sofismas y medias verdades. " +
                "\n Su mayor hazanya politica ha sido la de implementar bolsas de perro en los parques." +
                "Acto seguido, para paliar dicho gasto subió los impuestos un 20%." +
                "Sus votantes principales se encuentran entre 50 y 90 anyos.", "Sosegado, amoral, narcisista y lujurioso.");


        Criminologist criminologist = new Criminologist("Maria", 29, true, "Experiencia laboral: " +
                "Ha trabajado en CSI Las Vegas durante 4 anyos,\n" +
                "habla 4 idiomas diferentes" + " y ha hecho" +
                " conferencias en 20 paises \n sobre sus investigaciones criminalisticas", "Extrovertida, curiosa, detallista y honesta");//en el caso del casino sigue las pistas y llega hasta Louis por ser un posible sospechoso y a Juan Carlos por ser un testigo


        Novelist novelist = new Novelist("Anne with a E", 43, true, "Ha escrito 10 novelas, tiene un premio Nobel de literatura,\n" + //es huerfana
                "El premio Nobel lo gano por una Novela Negra", "Rigurosa, imaginativa, esceptica, cautelosa, solitaria e introvertida");//la novelista necesita informacion sensible y recurre a la estacion donde se encuentra nuestra criminologa en su año de novata, al ser novata le pasan el marron a ella.asi se conocen

        BaggageRoom baggageRoom= new BaggageRoom("Deja maletas","Es todo un vagon preparado exclusivamente con la intencion  de martener las \n" +
                "pertenencias a salvo durante todo el viaje.\n" +
                "Se trata de dos filas con una altura suficiente para 2 maletas en vertical con un grosor de tres manos y seis de longitud,\n" +
                "cada una de dichas filas estan repartidas en tres columnas y señalizadas con la habitacion a la que corresponden. ",false);
        
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
        Viewpoint Viewpoint = new Viewpoint("Mirador 'El balcón de los poetas'", "Paraje donde podrás navegar las olas de la imaginación, observar la naturaleza \n"+
                "e inspirarte. Es el espacio más seguro y privado en ciertos momentos del día.", false);

        int[][] mapping = new int[3][7];
        int a = 0;
        for (int i = 0; i <3; i++) {
            for (int j = 0; j < 4; j++) {
                mapping[i][j] = 0;
            }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(mapping[i]));
        }
    }
}
