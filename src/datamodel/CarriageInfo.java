package datamodel;

public class CarriageInfo {
    private final int capacity, tatkalPercentage;
    private final String type, layout;

    public CarriageInfo(int capacity, int tatkalPercentage, String type, String layout) {
        this.capacity = capacity;
        this.tatkalPercentage = tatkalPercentage;
        this.type = type;
        this.layout = layout;
    }

    public int getCapacity() {
        return capacity;
    }


    public int getTatkalPercentage() {
        return tatkalPercentage;
    }

    public String getType() {
        return type;
    }

    public String getLayout() {
        return layout;
    }
}
