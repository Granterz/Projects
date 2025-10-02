import java.util.Date;

// Booking class
class Booking {
    private int bookingId;
    private int roomId;
    private Date date;
    private String time;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;

    // Constructor
    public Booking(int bookingId, int roomId, Date date, String time, String contactPerson, String contactEmail, String contactPhone) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.date = date;
        this.time = time;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }
}