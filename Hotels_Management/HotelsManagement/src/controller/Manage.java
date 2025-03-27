/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import model.Hotels;

/**
 *
 * @author vyvatrinh
 */
public class Manage {

    private List<Hotels> listHotels = new ArrayList<>();

    public List<Hotels> getListHotels() {
        return listHotels;
    }
    
    public Hotels getHotel(String id) {
        for (Hotels hotel : listHotels) {
            if (id.equalsIgnoreCase(hotel.getHotelId())) {
                return hotel;
            }
        }
        return null;
    }

    public void addNewHotel() {
        String choose;
        while (true) {
            String hotelId = Validation.inputString("Enter Hotel ID: ",
                                "Please enter id hotel (Example H01): ", "^[A-Z]\\d+$");
            if (getHotel(hotelId) != null) {
                System.err.println("ID Hotel is areadly exist!");
                continue;
            }
            String hotelName = Validation.inputString("Enter Hotel Name: ",
                    "Invalid!", "^(?!\\s*$).+");
            int hotelRoomAvailable = Validation.inputInt("Enter Hotel Room Available: ",
                    "Please enter 1->550 room", "Please enter integer number!", 1, 550);
            String hotelAddress = Validation.inputString("Enter Hotel Address: ",
                    "Invalid!", "^(?!\\s*$).+");
            String hotelPhone = Validation.inputString("Enter Hotel Phone: ",
                    "Please enter phone format: 0xxxxxxxxx(10 digits)", "^0\\d{9}$");
            int hotelRating = Validation.inputInt("Enter Hotel_Rating(number stars): ",
                    "Please enter 1->6 stars", "Please enter integer number!", 1, 6);
            
            Hotels newHotel = new Hotels(hotelId, hotelName, hotelRoomAvailable,
                    hotelAddress, hotelPhone, hotelRating);
            
            listHotels.add(newHotel);
            System.out.println("Adding successful! Continue entering new hotel");
            System.out.println("--------------CONTINUE ADDING-------------");
            choose = Validation.inputString("Do you want to continue (Y/N): ",
                    "Just Y or N", "[YNyn]");
            if (choose.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    public void printListHotels(List<Hotels> list) {
        if(list.isEmpty()) {
            System.out.println("No hotel!");
            return;
        }
        System.out.println("==========================================LIST HOTELS====================================================");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-10s|%-15s|%-20s|%-30s|%-15s|%-15s|\n", 
                "Hotel_id", "Hotel_Name", "Hotel_Room_Available", 
                "Hotel_Address", "Hotel_Phone", "Hotel_Rating");
        System.out.println("---------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < list.size(); i++) {
            list.get(i).display();
        }
        
    }

    public void saveFile(String file) {
        if (listHotels.isEmpty()) {
            System.err.println("List is empty!");
            return;
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (int i = 0; i < listHotels.size(); i++) {
                    oos.writeObject(listHotels.get(i));
                }
                oos.close();
                fos.close();
            } catch (IOException e) {
                System.err.println("Error saving data to File: " + file);
            }
        }
    }

    public void readFile(String file) {
        try {
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream oos = new ObjectInputStream(fos);
            while (true) {
                try {
                    Hotels hotel = (Hotels) oos.readObject();
                    listHotels.add(hotel);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (Exception i) {
            System.out.println("Error to read File:" + file);
        }
    }
    
    public List<Hotels> updateHotel() {
        if (listHotels.isEmpty()) {
            System.err.println("You have not entered information for any hotels.");
            return null;
        }

        String hotelId = Validation.inputString("Enter Hotel ID: ", 
                "Please enter a valid hotel ID (Example: H01): ", "^[a-zA-Z]\\d+$");
        Hotels hotelUpdate = getHotel(hotelId);

        if (hotelUpdate == null) {
            System.err.println("Hotel does not exist.");
            return null;
        }

        System.out.println("Before Updating: ");
        printListHotels(Collections.singletonList(hotelUpdate));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new Hotel Name (Press Enter to keep the old information): ");
        String newName = scanner.nextLine().trim();

        String newHotelRoomAvailable;
        newHotelRoomAvailable = Validation.inputString("Enter new Hotel Room Available (Press Enter to keep the old information): ",
                "Please enter an integer greater than 0.", "^([1-9][0-9]*)?");
        if (!newHotelRoomAvailable.isEmpty() && Integer.parseInt(newHotelRoomAvailable) > 500) {
            System.out.println("Please enter a number <= 500");
            return null;
        }

        System.out.print("Enter new Hotel Address (Press Enter to keep the old information): ");
        String newHotelAddress = scanner.nextLine().trim();

        String newHotelPhone = Validation.inputString("Enter new Hotel Phone (Press Enter to keep the old information): ",
                "Please enter a valid phone number.", "^$|^0\\d{9}$");

        String newHotelRating = Validation.inputString("Enter new Hotel Rating (Press Enter to keep the old information): ",
                "Please enter an integer between 1 and 6.", "^([1-6])?");

        if (!newName.isEmpty()) {
            hotelUpdate.setHotelName(newName);
        }
        if (!newHotelRoomAvailable.isEmpty()) {
            hotelUpdate.setHotelRoomAvailable(Integer.parseInt(newHotelRoomAvailable));
        }
        if (!newHotelAddress.isEmpty()) {
            hotelUpdate.setHotelAddress(newHotelAddress);
        }
        if (!newHotelPhone.isEmpty()) {
            hotelUpdate.setHotelPhone(newHotelPhone);
        }
        if (!newHotelRating.isEmpty()) {
            hotelUpdate.setHotelRating(Integer.parseInt(newHotelRating));
        }

        System.out.println("After Updating:  ");
        printListHotels(Collections.singletonList(hotelUpdate));

        return Collections.singletonList(hotelUpdate);
}

    public List<Hotels> deleteHotel(String hotelId) {
        if (listHotels.isEmpty()) {
        System.err.println("No hotel information has been entered.");
        return null;
        }

        Hotels hotelDelete = getHotel(hotelId);
        if (hotelDelete == null) {
            System.err.println("The hotel does not exist.");
            return null;
        }

        List<Hotels> oldHotel = new ArrayList<>();
        oldHotel.add(hotelDelete);
        printListHotels(oldHotel);

        String choice = Validation.inputString("Do you ready want to delete this hotel?",
                                "Just Y or N", "[YNyn]");

        if (choice.equalsIgnoreCase("Y")) {
            listHotels.removeIf(hotel -> hotel.getHotelId().equalsIgnoreCase(hotelDelete.getHotelId()));
            return listHotels;
        } else {
            return null;
        }
        
    }

    public List<Hotels> searchByID(String keyID) {
        List<Hotels> hotelSearch = new ArrayList<>();
        for (int i = 0; i < listHotels.size(); i++) {
            if (listHotels.get(i).getHotelId().toLowerCase().
                    contains(keyID.toLowerCase())) {
                hotelSearch.add(listHotels.get(i));
            }
        }
        if (hotelSearch.isEmpty()) {
            return null;
        } else {
            Comparator<Hotels> e = new Comparator<Hotels>() {
                @Override
                public int compare(Hotels o1, Hotels o2) {
                    return -o1.getHotelId().compareTo(o2.getHotelId());
                }
            };
            Collections.sort(hotelSearch, e);
            return hotelSearch;
        }
    }

    public List<Hotels> searchByName(String hotelName) {
        List<Hotels> hotelSearch = new ArrayList<>();
        for (int i = 0; i < listHotels.size(); i++) {
            if (listHotels.get(i).getHotelName().toLowerCase().contains(hotelName.toLowerCase())) {
                hotelSearch.add(listHotels.get(i));
            }
        }
        if (hotelSearch.isEmpty()) {
            return null;
        } else {
            return hotelSearch;
        }
    }

    public void displayDescendingByHotelName() {
        List copyList = listHotels;
        Comparator<Hotels> e = new Comparator<Hotels>() {
            @Override
            public int compare(Hotels o1, Hotels o2) {
                return -o1.getHotelName().compareTo(o2.getHotelName());
            }
        };
        Collections.sort(copyList, e);
        printListHotels(copyList);
    }
}
