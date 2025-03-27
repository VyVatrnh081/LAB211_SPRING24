/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Manage;
import controller.Validation;
import java.util.List;

/**
 *
 * @author vyvatrinh
 */
public class HotelManagement {
    public static void main(String[] args) {
        final String FILE = "Hotel.dat";
        // TODO code application logic here
        Manage manageHotels = new Manage();
        manageHotels.readFile(FILE);
        
        do {
            System.out.println("================MENU================");
            System.out.println("1) Adding new Hotel. \n"
                    + "2) Checking exits Hotel. \n"
                    + "3) Updating Hotel information. \n"
                    + "4) Deleting Hotel. \n"
                    + "5) Searching Hotel. \n"
                    + "6) Displaying a hotel list (descending by Hotel_Name). \n"
                    + "7) Others Quit.");
            int choice = Validation.inputInt("Enter your choice: ", "Please enter 1->7",
                    "Please enter integer number", 1, 7);
            switch (choice) {
                case 1:
                    manageHotels.addNewHotel();
                    manageHotels.saveFile(FILE);
                    break;
                case 2:
                    String choose;
                    while (true) {
                        String hotel_id = Validation.inputString("Enter hotel Id: ",
                                "Please enter id hotel (Example H01): ", "^[A-Z]\\d+$");
                        if (manageHotels.getHotel(hotel_id) == null) {
                            System.out.println("No Hotel Found!");
                        } else {
                            System.out.println("Exist");
                        }
                        choose = Validation.inputString("You want return to main menu?(Y/N): ",
                                "Just Y or N", "[YNyn]");
                        if (choose.equalsIgnoreCase("Y")) {
                            break;
                        }
                    }
                    break;
                case 3:
                    List listUpdated = manageHotels.updateHotel();
                    if (listUpdated == null) {
                        System.out.println("Update Failed!");
                    } else {
                        System.out.println("Update Successful!");
                        System.out.println("After UpDating: ");;
                        manageHotels.printListHotels(listUpdated);
                    }
                    manageHotels.saveFile(FILE);
                    break;
                case 4:
                    String hotelId = Validation.inputString("Enter Hotel Id: ",
                            "Please enter hotel id (Example H01): ", "[A-Z]\\d+$");
                    List listDeleted = manageHotels.deleteHotel(hotelId);
                    if (listDeleted == null) {
                        System.out.println("Delete Failed!");
                    } else {
                        System.out.println("Delete Successful!");
                        System.out.println("After Deleting: ");
                        manageHotels.printListHotels(listDeleted);
                    }
                    manageHotels.saveFile(FILE);
                    break;
                case 5:
                    int choice2;
                    do {
                        System.out.println("1. Searching by Hotel_id. \n"
                                + "2. Searching by Hotel_name. \n"
                                + "3. Return first menu");
                        choice2 = Validation.inputInt("Enter your choice: ", "Please enter 1->3",
                                "Please enter integer number", 1, 3);
                        switch (choice2) {
                            case 1:
                                String keyID = Validation.inputString("Enter Hotel Id: ",
                                        "Please enter string not empty!", "^(?!\\s*$).+");
                                List searchID = manageHotels.searchByID(keyID);
                                if (searchID == null) {
                                    System.out.println("Search fail!Not found!");
                                } else {
                                    manageHotels.printListHotels(searchID);
                                }
                                break;
                            case 2:
                                String hotel_Name = Validation.inputString("Enter Hotel Name: ",
                                        "Invalid!", "^(?!\\s*$).+");
                                List searchName = manageHotels.searchByName(hotel_Name);
                                if (searchName == null) {
                                    System.out.println("Search failed! Not found!");
                                } else {
                                    manageHotels.printListHotels(searchName);
                                }
                                break;
                        }
                    } while (choice2 < 3);
                    break;
                case 6:
                    manageHotels.displayDescendingByHotelName();
                    break;
                case 7:
                    return;
            }
        } while (true);


    }
}
