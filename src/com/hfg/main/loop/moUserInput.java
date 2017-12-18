package com.hfg.main.loop;

/**
 * @author Moriel Schottlender
 * @version 1.0
 *
 * A Java class meant to provide easy and consistent validation for
 * user-generated input.
 *
 */
import java.util.Scanner;

public class moUserInput {

    private static Scanner keyboard = new Scanner(System.in);

    /**
     * A method to repeatedly ask the user for input until the input is valid.
     * If condition is used, input is measured against it.
     *
     * @param informationText	The information text to prompt to the user.
     * @return	Returns the final value of the accepted input, as an integer.
     */
    public int askInputInt(String informationText) {
        Boolean error = false;
        String userInp = "";
        do {
            System.out.print(informationText);
            userInp = keyboard.nextLine();
            if (!this.isType(userInp, "int")) {
                error = true;
                System.err.println("Error: must be a whole number.");
            } else {
                error = false;
            }
        } while (error == true);
        return Integer.parseInt(userInp);
    }

    /**
     * A method to repeatedly ask the user for input until the input is valid.
     * If condition is used, input is measured against it.
     *
     * @param informationText	The information text to prompt to the user.
     * @return	Returns the final value of the accepted input, as a double.
     */
    public double askInputDouble(String informationText) {
        Boolean error = false;
        String userInp = "";
        do {
            System.out.print(informationText);
            userInp = keyboard.nextLine();
            if (!this.isType(userInp, "double")) {
                System.err.println("Error: must be a number.");
                error = true;
            } else {
                error = false;
            }

        } while (error == true);
        return Double.parseDouble(userInp);
    }

    /**
     * A method to repeatedly ask the user for input until the input is valid.
     * If condition is used, input is measured against it.
     *
     * @param informationText	The information text to prompt to the user.
     * @return	Returns the final value of the accepted input, as a float.
     */
    public float askInputFloat(String informationText) {
        Boolean error = false;
        String userInp = "";
        do {
            System.out.print(informationText);
            userInp = keyboard.nextLine();
            // validate:
            if (!this.isType(userInp, "float")) {
                System.err.println("Error: must be a number.");
                error = true;
            } else {
                error = false;
            }

        } while (error == true);
        return Float.parseFloat(userInp);
    }

    public String askInputString(String informationText) {
        String userInp = "";
        System.out.println(informationText);
        userInp = keyboard.nextLine();
        return userInp;
    }

    /**
     * Tests if a specific input can be converted to a specific type.
     *
     * @param input The input to test. Accepts String, int, double or float.
     * @param type	Which type to test against. Accepts 'int','float' or
     * 'double'.
     * @return Boolean	True if can be transformed to requested type. False
     * otherwise.
     */
    public Boolean isType(String testStr, String type) {
        try {
            if (type.equalsIgnoreCase("float")) {
                Float.parseFloat(testStr);
            } else if (type.equalsIgnoreCase("int")) {
                Integer.parseInt(testStr);
            } else if (type.equalsIgnoreCase("double")) {
                Double.parseDouble(testStr);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
