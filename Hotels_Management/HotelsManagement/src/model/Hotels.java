/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author vyvatrinh
 */
public class Hotels implements Serializable {

    private String hotelId;
    private String hotelName;
    private int hotelRoomAvailable;
    private String hotelAddress;
    private String hotelPhone;
    private int hotelRating;

    public Hotels() {
    }

    public Hotels(String hotelId, String hotelName, int hotelRoomAvailable, 
            String hotelAddress, String hotelPhone, int hotelRating) {    
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelRoomAvailable = hotelRoomAvailable;
        this.hotelAddress = hotelAddress;
        this.hotelPhone = hotelPhone;
        this.hotelRating = hotelRating;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
    
    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getHotelRoomAvailable() {
        return hotelRoomAvailable;
    }

    public void setHotelRoomAvailable(int hotelRoomAvailable) {
        this.hotelRoomAvailable = hotelRoomAvailable;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public int getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(int hotelRating) {
        this.hotelRating = hotelRating;
    }

     public void display() {
        String[] arr = getHotelAddress().trim().split(" ");
        String formatAddress = "";
        if (arr.length <= 5) {
            System.out.printf("|%-10s|%-15s|%-20d|%-27s|%-15s|%-15s|\n", getHotelId(), getHotelName(),
                    getHotelRoomAvailable(), getHotelAddress(), getHotelPhone(), getHotelRating() + " star");
        } else {
            for (int i = 0; i < arr.length; i++) {
                formatAddress += arr[i];
                formatAddress += " ";
                if ((i + 1) == 5) {
                    System.out.printf("|%-10s|%-15s|%-20d|%-30s|%-15s|%-15s|\n", getHotelId(), getHotelName(),
                            getHotelRoomAvailable(), formatAddress, getHotelPhone(), getHotelRating() + " star");
                    formatAddress = "";
                } else if ((i + 1) > 5 && (i + 1) % 5 == 0) {
                    System.out.printf("\t\t\t\t\t\t|%-30s|\n", formatAddress);
                    formatAddress = "";
                }
                if (i == arr.length - 1) {
                    System.out.printf("\t\t\t\t\t\t|%-30s|\n", formatAddress);
                }
            }
        }
    }
}
