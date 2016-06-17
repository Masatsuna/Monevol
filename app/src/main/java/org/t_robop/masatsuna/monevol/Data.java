package org.t_robop.masatsuna.monevol;

/**
 * Created by yuusuke on 16/06/14.
 */
public class Data {



    private int id;
    private int year;
    private int month;
    private int date;
    private String appname;
    private int billing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  int getBilling() {
        return billing;
    }

    public void setBilling(int billing) {
        this.billing = billing;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }


}
