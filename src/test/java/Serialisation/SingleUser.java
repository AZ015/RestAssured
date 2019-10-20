package Serialisation;

public class SingleUser {

    private float id;
    private String name;
    private float year;
    private String color;
    private String pantone_value;

    public SingleUser() {

    }

    public SingleUser(float id, String name, float year, String color, String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getPantone_value() {
        return pantone_value;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(float year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPantone_value(String pantone_value) {
        this.pantone_value = pantone_value;
    }
}