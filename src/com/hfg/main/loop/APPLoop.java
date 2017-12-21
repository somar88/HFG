/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hfg.main.loop;

import com.database.manager.DBM;

/**
 *
 * @author dev
 */
public class APPLoop implements Runnable {

    moUserInput uInput = new moUserInput();

    public boolean running = false;
    public DBM dbm = new DBM();

    @Override
    public void run() {
        startApplication();
    }

    public void init() {
        if (!running) {
            running = true;
            this.run();
        }
    }

    public void stop() {
        if (running) {
            running = false;
        }
    }

    void startApplication() {
        System.out.println("Welcome to Hotfix Patch Processing!");
        loading(3, "Starting Application, please wait! ");
        int login = uInput.askInputInt("Please enter: "
                + "\n(1) to use default login parameter"
                + "\n(2) to enter user, password, URL "
                + "\n(3) Exit the application!\n");
        int loginAttemptsCounter = 0;
        while (loginAttemptsCounter < 3) {
            switch (login) {
                case 1:
                    loginAttemptsCounter++;
                    if (!default_login()) {
                        System.err.print("Error: Login was not successful!");
                        stop();
                    }
                    loginAttemptsCounter = 3;
                    break;
                case 2:
                    loginAttemptsCounter++;
                    if (!login()) {
                        System.err.print("Error: Login was not successful!");
                        if (loginAttemptsCounter >= 3) {
                            System.err.println("Login failed three times, please contact your adminstrator!");
                            stop();
                        }
                    }
                    loginAttemptsCounter = 3;
                    break;
                case 3:
                    stop();
                    break;
                default:
                    System.out.println("Please pick a valid process number!");
            }
        }
        while (running) {
            // TODO: start the logic controller
            int start = uInput.askInputInt("Please enter: "
                    + "\n(1): Create an EP Request"
                    + "\n(2): Start Processing EP Requests"
                    + "\n(3): Exit the application!\n");
            switch (start) {
                case 1:
                    while (running) {
                        // TODO: start the logic controller
                        int input = uInput.askInputInt("Please enter: "
                                + "\n(1): for EP Processing"
                                + "\n(2): for VP Processing"
                                + "\n(3): for HFC Processing"
                                + "\n(4): to Exit the application!\n");
                        switch (input) {
                            case 1:
                                startEmergencyPatchProcessing();
                                break;
                            case 2:
                                startVerificationPatchProcessing();
                                break;
                            case 3:
                                startHotfixCollectionPatchProcessing();
                                break;
                            case 4:
                                stop();
                                break;
                            default:
                                System.out.println("Please pick a valid process number!");
                        }
                    }
                case 2:
                    break;
                case 3:
                    stop();
                    break;
                default:
                    System.out.println("Please pick a valid process number!");
                    break;
            }
        }
        System.out.println("See you soon, bye!");
    }

    void startEmergencyPatchProcessing() {
        System.out.println("Starting the EP Processing!");
        while (running) {
            // TODO: start the logic controller
            int input = uInput.askInputInt("Please pick a number: "
                    + "\n(1): if it is a Standard Downtime EP"
                    + "\n(2): if it is an Extraordinary Downtime/Low activity period"
                    + "\n(3): to go back to main menu"
                    + "\n(4): to Exit the application!\n");

            switch (input) {
                case 1:
                    startEmergencyPatchProcessing();
                    break;
                case 2:
                    startVerificationPatchProcessing();
                    break;
                case 3:
                    return;
                case 4:
                    stop();
                    break;
                default:
                    System.out.println("Please pick a valid process number!");
            }
        }
    }

    void startVerificationPatchProcessing() {
        System.out.println("Starting the VP Processing!");
        while (running) {
            // TODO: start the logic controller
            int input = uInput.askInputInt("Please pick a number: "
                    + "\n(1) if it is a normal VP"
                    + "\n(2) if it is an EP in VP landscape"
                    + "\n(3) to go back to main menu"
                    + "\n(4) to Exit the application!\n");
            switch (input) {
                case 1:
                    startEmergencyPatchProcessing();
                    break;
                case 2:
                    startVerificationPatchProcessing();
                    break;
                case 3:
                    return;
                case 4:
                    stop();
                    break;
                default:
                    System.out.println("Please pick a valid process number!");
            }
        }
    }

    void startHotfixCollectionPatchProcessing() {
        System.out.println("Starting the HFC Processing!");
        while (running) {
            // TODO: start the logic controller
            int input = uInput.askInputInt("Please pick a number: "
                    + "\n(1): to start HFC Processing"
                    + "\n(2): to start HFC without translation processing"
                    + "\n(3): to go back to main menu"
                    + "\n(4): to Exit the application!\n");
            switch (input) {
                case 1:
                    startEmergencyPatchProcessing();
                    break;
                case 2:
                    startVerificationPatchProcessing();
                    break;
                case 3:
                    return;
                case 4:
                    stop();
                    break;
                default:
                    System.out.println("Please pick a valid process number!");
            }
        }
    }

    private boolean login() {
        String username = uInput.askInputString("Please enter username:").trim();
        String password = uInput.askInputString("Please enter passowrd:").trim();
        String dbname = uInput.askInputString("Please enter DB name:").trim();
        boolean loginsuccessful = dbm.login(username, password, "jdbc:mysql://localhost:3306/" + dbname + "?useSSL=true");
        loading(3, "Checking DB connection, please wait\n");
        if (dbm.conn != null) {
            System.out.println("Connection successful: " + dbm.conn);
        } else {
            System.err.println("Connection to DB was not successful! ");
        }
        loading(3, "Starting Application");
        return loginsuccessful;
    }

    private boolean default_login() {
        boolean loginsuccessful = dbm.login("management", "dev@man", "jdbc:mysql://localhost:3306/HFG?useSSL=true");
        loading(3, "");
        System.out.println("Connection successful: " + dbm.conn);
        return loginsuccessful;
    }

    public static void loading(int seconds, String message) {
        long starttime = System.currentTimeMillis();
        boolean done = false;
        long now;
        int loadingendline = 0;
        System.out.print(message);
        while (!done) {
            now = System.currentTimeMillis();
            if (now - starttime >= 500) {
                seconds--;
                System.out.print(".");
                loadingendline++;
                if (loadingendline == 20) {
                    System.out.println();
                    loadingendline = 0;
                }
                starttime = now;
            }
            if (seconds == 0) {
                System.out.println();
                done = true;

            }
        }
    }
}
