package map;

public class Carriage {

    private String carriageName;
    private String carriageDescription;
    private boolean carriageLight;

    private Object[][] mapping = new Object[3][4];

    //private boolean window;
    //private boolean doors;

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
}
