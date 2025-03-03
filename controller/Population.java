package controller;
import org.reflections.Reflections;

import characters.*;

import java.util.ArrayList;

public class Population {
    //Creation of reflections variable for the instance of array of characters
    Reflections reflections = new Reflections("characters");  // Especifica el paquete donde están las clases
    Set<Class<? extends Person>> childClasses = reflections.getSubTypesOf(Person.class);

    //Instance for single instance of Population
    private static final Population INSTANCE = new Population();
    private Population() {};
    public static Population getInstance() {
        return INSTANCE;
    }






    //Function that generates the characters of the story
    private  ArrayList<Population> generatePopulation(){
        List<Person> population = new ArrayList<>();

        // Char...
        Detective detective = new Detective("Hercules Poirot", 38, false, " Famoso detective belga,\n" +
                " resuelve un robo en la iglesia del Santo Sepulcro en Jerusalen\n" +
                " tambien estuvo en Nadiekistan resolviendo un caso hace 9 anyos donde con la ayuda de un alcade\n" +
                " pudo resolver un triple homicidio en el rio que pasaba por la midad de toda la ciudad, el caso\n" +
                " se conocio como el Asesinato de los naipes, y forma parte de Scotland Yard donde conocio a Sherlock Holmes",
                "Meticuloso, Vanidoso, Cortes, Maquiavelico");


        DetectiveAssistant detectiveAssistant = new DetectiveAssistant("Robin Retos",18,false,"Lleva trabajando en una granja desde muy pequeño, \n"+
                "es superdotado y empezó a estudiar en la universidad a los 14 años. Terminó la carrera en dos años.\n"
                + "Descubrió La Ley de la Gravedad","Calculador, superdotado, precavido, carismatico, intuitivo");


        LocomotiveDriver locomotiveDriver = new LocomotiveDriver("Louis",45,false,"Conduce trenes desde los 20 años\n"+
                "Pidio dinero a un prestamista para su hija enferma.\n" + "Trabaja todo el día para darle a su hija la vida que el nunca tuvo.", "Luchador, confiable, humilde, adaptativo"
        );

        TrainCoalman trainCoalman = new TrainCoalman("Bob",56,false, "Desde pequeño tuvo que trabajar para comer, no tiene familia. \n" +
                "Siempre ha estado solo, no se sabe mucho de el, su vida es un misterio.","Enclenque, reservado, desconfiado y aprensivo " );

        Comander comander = new Comander("Juan Carlos", 87, false, "Un gran comandante condecorado,\n" +
                " con grandes hazanyas en Nadiekistan  hace 50 anyos, que duro por 5 anyos.\n" +
                " alli rescato de un metro a un ninyo abandonado por su madre, el cual se apiado del ninyo y lo llevo a un orfanato,\n desde" +
                "entonces no supo nada mas de el\n" +
                "Durante 3 anyos estuvo en Las vegas como un ludopata empedernido,\n" +
                "en donde ocurrio un asesinato en el casino en el que se encontraba.", "PTSD, Soberbio, Culto, Abordable");// en el casino conoce a maria por el caso


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
                " conferencias en 20 paises\nsobre sus investigaciones criminalisticas", "Extrovertida, curiosa, detallista y honesta");


        Novelist novelist = new Novelist("Anne with a E", 43, true, "Ha escrito 10 novelas, tiene un premio Nobel de literatura,\n" +
                "El premio Nobel lo gano por una Novela Negra", "Rigurosa, imaginativa, esceptica, cautelosa, solitaria e introvertida");

        // Mostrar las clases hijas encontradas usando reflections
        for (Class<? extends Person> clase : childClasses) {
            System.out.println("Clase hija encontrada: " + clase.getName());
        }

        // Retornar la lista con todas las instancias
        return population;

    }
}
