package sit.tu_varna.functionality;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класът DataStore отговаря за управлението на данните в системата, включително списъци със зали и събития.
 * Той предоставя методи за добавяне, търсене, копиране и изчистване на данни.
 */
public class DataStore implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Hall> halls;
    private List<Event> events;

    /**
     * Конструктор за DataStore, който инициализира празни списъци със зали и събития.
     */
    public DataStore() {
        this.halls = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    /**
     * Копира данните от друг DataStore, като изчиства текущите списъци и добавя всички зали и събития от подадения DataStore.
     *
     * @param other друг DataStore, от който се копират данните
     */
    public void copyFrom(DataStore other) {
        this.halls.clear();
        this.events.clear();
        this.halls.addAll(other.halls);
        this.events.addAll(other.events);
    }

    /**
     * Добавя нова зала в списъка със зали.
     *
     * @param hall залата, която ще бъде добавена
     */
    public void addHall(Hall hall) {
        halls.add(hall);
    }

    /**
     * Връща зала по дадено име.
     *
     * @param hallName името на залата, която се търси
     * @return Hall обектът на залата, ако бъде намерен, или null, ако залата не съществува
     */
    public Hall getHall(String hallName) {
        for (Hall hall : halls) {
            if (hall.getName().equals(hallName)) {
                return hall;
            }
        }
        return null; // Връща null, ако не е намерена
    }

    /**
     * Добавя ново събитие в списъка със събития.
     *
     * @param event събитието, което ще бъде добавено
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Проверява дали вече съществува събитие в дадена зала на определена дата.
     *
     * @param date датата на събитието
     * @param hallName името на залата
     * @return true, ако събитието вече съществува, или false, ако не съществува
     */
    public boolean hasEvent(Date date, String hallName) {
        for (Event event : events) {
            if (event.getDate().equals(date) && event.getHall().getName().equals(hallName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Намира събитие по дата и име.
     *
     * @param date датата на събитието
     * @param name името на събитието
     * @return Event обектът на събитието, ако бъде намерен, или null, ако събитието не съществува
     */
    public Event getEvent(Date date, String name) {
        for (Event event : events) {
            if (event.getDate().equals(date) && event.getName().equals(name)) {
                return event;
            }
        }
        return null;
    }

    /**
     * Намира всички събития на дадена дата.
     *
     * @param date датата, за която се търсят събитията
     * @return списък със събития, които се провеждат на дадената дата
     */
    public List<Event> getEventsByDate(Date date) {
        List<Event> eventsByDate = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().equals(date)) {
                eventsByDate.add(event);
            }
        }
        return eventsByDate;
    }

    /**
     * Намира всички събития в даден времеви интервал, като по избор може да се филтрира по зала.
     *
     * @param fromDate началната дата на периода
     * @param toDate крайната дата на периода
     * @param hallName името на залата (по избор)
     * @return списък със събития, които се провеждат в зададения времеви интервал и, ако е указано, в конкретната зала
     */
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

    /**
     * Извлича всички събития от системата.
     *
     * @return списък с всички събития
     */
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }

    /**
     * Намира билет по уникален код.
     *
     * @param code уникалният код на билета
     * @return Ticket обектът на билета, ако бъде намерен, или null, ако билетът не съществува
     */
    public Ticket getTicketByCode(String code) {
        for (Event event : events) {
            for (Ticket ticket : event.getTickets().values()) {
                if (code.equals(ticket.getCode())) {
                    return ticket;
                }
            }
        }
        return null;
    }

    /**
     * Изчиства всички данни от системата, включително списъците със зали и събития.
     */
    public void clear() {
        halls.clear();
        events.clear();
    }

    /**
     * Връща списък със зали.
     *
     * @return списък със зали
     */
    public List<Hall> getHalls() {
        return new ArrayList<>(halls);
    }

    /**
     * Връща списък със събития.
     *
     * @return списък със събития
     */
    public List<Event> getEvents() {
        return new ArrayList<>(events);
    }
}
