package sit.tu_varna.functionality;
import java.io.Serializable;
import java.util.Date;

/**
 * Класът Ticket представлява билет за събитие.
 * Този клас съдържа информация за реда, мястото, датата на събитието, името на събитието, бележка, статус на резервация и покупка, и уникален код на билета.
 */
public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private int row;
    private int seat;
    private Date date;
    private String eventName;
    private String note;
    private boolean booked;
    private boolean purchased;
    private String code;

    /**
     * Конструктор за Ticket, който инициализира билет с ред, място, дата и име на събитието.
     *
     * @param row редът на мястото
     * @param seat номерът на мястото
     * @param date датата на събитието
     * @param eventName името на събитието
     */
    public Ticket(int row, int seat, Date date, String eventName) {
        this.row = row;
        this.seat = seat;
        this.date = date;
        this.eventName = eventName;
        this.note = "";
        this.booked = false;
        this.purchased = false;
        this.code = null;
    }

    /**
     * Резервира билета и задава бележка към резервацията.
     *
     * @param note бележката към резервацията
     */
    public void book(String note) {
        this.booked = true;
        this.note = note;
    }

    /**
     * Отменя резервацията на билета.
     */
    public void unbook() {
        this.booked = false;
        this.note = "";
    }

    /**
     * Закупува билета, променяйки статуса му на закупен и генерира уникален код за билета.
     */
    public void purchase() {
        this.purchased = true;
        this.booked = false;  // Ако билетът е бил резервиран, променяме статуса му на закупен
        this.code = generateCode();  // Генериране на уникален код за билета
    }

    /**
     * Връща уникалния код на билета.
     *
     * @return уникалния код на билета
     */
    public String getCode() {
        return code;
    }

    /**
     * Генерира уникален код за билета на базата на реда, мястото, датата и името на събитието.
     *
     * @return уникален код за билета
     */
    private String generateCode() {
        return "TICKET-" + row + "-" + seat + "-" + date.getTime() + "-" + eventName.hashCode();
    }

    /**
     * Връща реда на мястото.
     *
     * @return редът на мястото
     */
    public int getRow() {
        return row;
    }

    /**
     * Връща номера на мястото.
     *
     * @return номерът на мястото
     */
    public int getSeat() {
        return seat;
    }

    /**
     * Проверява дали билетът е резервиран.
     *
     * @return true, ако билетът е резервиран, иначе false
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * Проверява дали билетът е закупен.
     *
     * @return true, ако билетът е закупен, иначе false
     */
    public boolean isPurchased() {
        return purchased;
    }
}
