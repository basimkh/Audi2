package com.example.hp1.audi;

import java.util.Date;

/**
 * Created by Hp1 on 27/02/2018.
 */

public class BookService {
    private long id;
    private String location;
    private String carnum;
    private String time;
    private String date;

    public BookService(String location, String carnum, String time, String date) {
        this.location = location;
        this.carnum = carnum;
        this.time = time;
        this.date = date;
    }

    public BookService(long id, String location, String carnum, String time, String date) {
        this.id = id;
        this.location = location;
        this.carnum = carnum;
        this.time = time;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCarnum() {
        return carnum;
    }

    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
