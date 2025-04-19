package map;

import characters.Comander;
import characters.Detective;
import characters.Person;

import java.util.Arrays;
import java.util.Objects;

public class Carriage {

    private String carriageName;
    private String carriageDescription;
    private boolean carriageLight;

    private Object[][] mapping = new Object[3][7];

    public boolean isCarriageLight() {
        return carriageLight;
    }

    public Object[][] getMapping() {
        return mapping;
    }

    public void setMapping(Object[][] mapping) {
        this.mapping = mapping;
    }

    //constructor
    public Carriage(String carriageName, String carriageDescription, boolean carriageLight) {
        this.carriageName = carriageName;
        this.carriageDescription = carriageDescription;
        this.carriageLight = carriageLight;
    }

    public String getCarriageName() {
        return carriageName;
    }

    public String getCarriageDescription() {
        return carriageDescription;
    }

    public void setCarriageName(String carriageName) {
        this.carriageName = carriageName;
    }

    public void setCarriageDescription(String carriageDescription) {
        this.carriageDescription = carriageDescription;
    }

    public void setCarriageLight(boolean carriageLight) {
        this.carriageLight = carriageLight;
    }
    public boolean getCarriageLight() {
        return carriageLight;
    }

    public void printMap() {
        for (Object[] row : this.mapping) {
            for (Object cell : row) {
                if(cell instanceof Person){
                    System.out.print(((Person) cell).getEmoji() + " ");
                }else{
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    public int[] findPosition() {
        int rowIndex = 0;
        int colIndex = 0;
        for (Object[] row : this.mapping) {
            for (Object cell : row) {
                if(cell instanceof Detective){
                    //retorna la posición en formato [x,y]
                    return new int[]{rowIndex, colIndex};
                }
                colIndex++;
            }
            colIndex = 0;
            rowIndex++;
        }
        //Si no encuentra personaje retorna [0,0]
        return new int[]{rowIndex, colIndex};
    }

    public void updatePosition(int[] position) {
        int rowIndex = findPosition()[0];
        int colIndex = findPosition()[1];
        System.out.println(Arrays.toString(position));
        this.mapping[position[0]][position[1]] = this.mapping[rowIndex][colIndex];
        if(!(position[0] == rowIndex && position[1] == colIndex)){
            this.mapping[rowIndex][colIndex] = "X";
        }

    }

    public boolean checkObjectCollision(int[] position) {
        return this.mapping[position[0]][position[1]] instanceof Comander;
    }




}
