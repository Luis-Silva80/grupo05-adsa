package com.example.tdd.Model;

import java.time.LocalDate;

public class BookingModel {
    private String id;
    private String reserverName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int numberGuest;

    public BookingModel(String id, String reserverName, LocalDate checkIn, LocalDate checkOut, int numberGuest) {
        this.id = id;
        this.reserverName = reserverName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numberGuest = numberGuest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReserverName() {
        return reserverName;
    }

    public void setReserverName(String reserverName) {
        this.reserverName = reserverName;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getNumberGuest() {
        return numberGuest;
    }

    public void setNumberGuest(int numberGuest) {
        this.numberGuest = numberGuest;
    }
}
