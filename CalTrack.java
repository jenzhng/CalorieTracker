/*
CalTrack records food item and the calories
    linked to the food. Then it outputs the total of the daily calories.

 */

import java.io.*;
import java.util.*;


/**
 * Main method called to give user options to create, edit, or view
 */
public class CalTrack {
    public static void main(String[] args) {
        /* Setup input scanner*/
        Scanner input = new Scanner(System.in);

        int repeat; // condition for repeating do/while loop

        do {
            /* Prompt user to enter date to work in*/
            System.out.println("Enter the date you would like to add, edit or view (ex: 2000-02-25)");
            String date = input.nextLine();
            input.reset();

            /* Prompt user to create new file or edit older file*/
            System.out.println("\nWould you like to make a new file, edit a file, or view file\n" +
                    "(1 - new, 2 - edit, 3 - view ): ");
            switch (input.nextInt()) {

                /* Create new food object */
                case 1:
                    do {
                        input.nextLine();

                        Food newFood = new Food();
                        newFood.setDate(date);

                        System.out.println("Enter food name: ");
                        newFood.setFoodName(input.nextLine());

                        System.out.println("Enter Calories");
                        newFood.setCalIn(input.nextInt());

                        CalTrackdb.setFood(newFood);

                        System.out.println("Enter more food? (1- Yes, 2- No)");
                        repeat = input.nextInt();

                    } while (repeat == 1);

                    CalTrackdb.getFoodByDate(date);
                    break;

                /* Edit food item by the date given */
                case 2:
                    CalTrackdb.getFoodByDate(date);
                    int delete; // condition variable for do while loop

                    do {
                        input.nextLine();
                        Food newFood = new Food();
                        newFood.setDate(date);

                        System.out.println("\n\nEnter the name of the food you would like to edit: ");
                        newFood.setFoodName(input.nextLine());

                        System.out.println("Would you like to delete or edit calorie value? (1- Delete, 2 - Edit Calories): ");
                        delete = input.nextInt();
                        if (delete == 1) {
                            CalTrackdb.deleteFoodByDate(newFood);
                            CalTrackdb.getFoodByDate(date);
                        }
                        else if (delete == 2) {
                            System.out.println("Please enter the new calorie value for " + newFood.getFoodName() + ": ");
                            newFood.setCalIn(input.nextInt());
                            CalTrackdb.modifyFoodCal(newFood);
                            CalTrackdb.getFoodByDate(date);
                        }
                        System.out.println("\nEdit more food? (1- Yes, 2- No)");
                        repeat = input.nextInt();
                        input.reset();

                    } while (repeat == 1);
                    break;

                /* View food data by given date */
                case 3:
                    CalTrackdb.getFoodByDate(date);
                    break;
                default: {
                    System.out.print("\n------------------\n Invalid input\n------------------\n");
                }
            }
            System.out.println("\nMore to do? (1 - Yes, 2 - No): ");
            repeat = input.nextInt();
            System.out.println();
            input.nextLine(); // catch dangling output

        } while (repeat == 1);
    }
}
