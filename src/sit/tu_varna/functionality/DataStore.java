package sit.tu_varna.functionality;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Hall> halls;  // Списък със зали
    private List<Event> events;  // Списък със събития

    public DataStore() {
        this.halls = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    // Метод за копиране на данни от друг DataStore
    public void copyFrom(DataStore other) {
        this.halls.clear();
        this.events.clear();
        this.halls.addAll(other.halls);
        this.events.addAll(other.events);
    }

    // Метод за добавяне на нова зала
    public void addHall(Hall hall) {
        halls.add(hall);
    }

    // Метод за връщане на зала по име
    public Hall getHall(String hallName) {
        for (Hall hall : halls) {
            if (hall.getName().equals(hallName)) {
                return hall;
            }
        }
        return null; // Връща null, ако залата не е намерена
    }

    // Метод за добавяне на ново събитие
    public void addEvent(Event event) {
        events.add(event);
    }

    // Метод за проверка дали вече съществува събитие в дадена зала на дадена дата
    public boolean hasEvent(Date date, String hallName) {
        for (Event event : events) {
            if (event.getDate().equals(date) && event.getHall().getName().equals(hallName)) {
                return true;
            }
        }
        return false;
    }

    // Метод за намиране на събитие по дата и име
    public Event getEvent(Date date, String name) {
        for (Event event : events) {
            if (event.getDate().equals(date) && event.getName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    // Метод за намиране на всички събития на дадена дата
    public List<Event> getEventsByDate(Date date) {
        List<Event> eventsByDate = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                eventsByDate.add(event);
            }
        }
        return eventsByDate;
    }

    // Метод за намиране на всички събития в даден времеви интервал
    public List<Event> getEventsInRange(Date fromDate, Date toDate, String hallName) {
        List<Event> eventsInRange = new ArrayList<>();
        for (Event event : events) {
            if (!event.getDate().before(fromDate) && !event.getDate().after(toDate)) {
                if (hallName == null || event.getHall().getName().equals(hallName)) {
                    eventsInRange.add(event);
                }
            }
        }
        return eventsInRange;
    }

    // Метод за извличане на всички събития
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    // Метод за намиране на билет по уникален код
    public Ticket getTicketByCode(String code) {
        for (Event event : events) {
            for (Ticket ticket : event.getTickets().values()) {
                if (ticket.getCode().equals(code)) {
                    return ticket;
                }
            }
        }
        return null;
    }

    // Метод за изчистване на всички данни
    public void clear() {
        halls.clear();
        events.clear();
    }

    // Гетъри за списъците със зали и събития (ако са необходими)
    public List<Hall> getHalls() {
        return new ArrayList<>(halls);
    }

    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }
}

