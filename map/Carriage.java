package map;

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
}
