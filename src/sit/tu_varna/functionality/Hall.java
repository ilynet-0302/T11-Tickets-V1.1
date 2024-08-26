package sit.tu_varna.functionality;
import java.io.Serializable;

public class Hall implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int rows;
    private int seatsPerRow;

    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    public String getName() {
        return name;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }
}