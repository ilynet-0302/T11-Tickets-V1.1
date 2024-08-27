package sit.tu_varna.functionality;
import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private static final long serialVersionUID = 1L;
    private int row;
    private int seat;
    private Date date;
    private String eventName;
    private String note;
    private boolean booked;
    private boolean purchased;
    private String code; // Уникален код на билета

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

    // Метод за резервация на билет
    public void book(String note) {
        this.booked = true;
        this.note = note;
    }

    // Метод за отмяна на резервация
    public void unbook() {
        this.booked = false;
        this.note = "";
    }

    // Метод за закупуване на билет
    public void purchase() {
        this.purchased = true;
        this.booked = false;  // Ако билетът е бил резервиран, променяме статуса му на закупен
        this.code = generateCode();  // Генериране на уникален код за билета
    }

    public String getCode() {
        return code;
    }

    private String generateCode() {
        return "TICKET-" + row + "-" + seat + "-" + date.getTime() + "-" + eventName.hashCode();
    }

    // Гетъри за атрибутите
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public boolean isBooked() {
        return booked;
    }

    public boolean isPurchased() {
        return purchased;
    }
}
