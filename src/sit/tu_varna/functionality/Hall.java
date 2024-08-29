package sit.tu_varna.functionality;
import java.io.Serializable;

/**
 * Класът Hall представлява зала, в която се провеждат събития.
 * Този клас съдържа информация за името на залата, броя на редовете и броя на местата на всеки ред.
 */
public class Hall implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int rows;
    private int seatsPerRow;

    /**
     * Конструктор за Hall, който инициализира залата с име, брой редове и брой места на ред.
     *
     * @param name името на залата
     * @param rows броят на редовете в залата
     * @param seatsPerRow броят на местата на всеки ред
     */
    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }

    /**
     * Връща името на залата.
     *
     * @return името на залата
     */
    public String getName() {
        return name;
    }

    /**
     * Връща броя на редовете в залата.
     *
     * @return броят на редовете
     */
    public int getRows() {
        return rows;
    }

    /**
     * Връща броя на местата на всеки ред.
     *
     * @return броят на местата на ред
     */
    public int getSeatsPerRow() {
        return seatsPerRow;
    }
}
