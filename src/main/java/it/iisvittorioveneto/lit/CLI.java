package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.model.User;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    // Scanner: used for the input
    private static final Scanner scanner = new Scanner(System.in);
    // Main menu choice
    private static int inputMainMenu;

    private static int inputId;
    // Statistics menu choice
    private static int inputStatistics;
    // Warehouse menu choice
    private static int inputManageWarehouse;

    // New Cli instance
    private static CLI cli = new CLI();

    /**
     * Contains the logic of the program and
     * the cli menu
     */
    public static void run() {
        // Clears the console before starting
        clearConsole();
        // Render the main menu while it is opened
        do {
            // Reading the choice from terminal
            inputMainMenu = secureIntInput(0, 4, cli.mainMenu());
            // Selecting the choosen menu
            switch (inputMainMenu) {
                case 1:
                    // Create Warehouse Option
                    cliCreateWarehouse();
                    break;
                case 2:
                    // Open Warehouse Option
                    cliOpenWarehouse();
                    break;
                case 3:
                    // Manage Warehouse Menu
                    if(Application.getInstance().getOpenedWarehouses().size() > 0) {
                        cliManageWarehouse();
                    } else {
                        System.out.println("Nessun magazzino aperto");
                        sleep();
                    }
                    break;
                case 4:
                    // Close Warehouse Menu
                    if(Application.getInstance().getOpenedWarehouses().size() > 0) {
                        cliCloseWarehouse();
                    } else {
                        System.out.println("Nessun magazzino aperto");
                        sleep();
                    }
                    break;
                case 0:
                    // Close The Application
                    clearConsole();
                    System.out.println("Buona Giornata!");

                    sleep();
                    break;
            }
        } while (inputMainMenu != 0);
    }

    /**
     * This method asks the user to insert an input and checks if it is
     * int the range min --> max if they aren't the method asks again.
     * @param min The minimum number that can be entered
     * @param max The maximum number that can be entered
     * @param output The message to write while asking the number
     * @return The in bounds input
     */
    private static int secureIntInput(int min, int max, String output){
        int input = 0;
        do{
            clearConsole();
            System.out.println(output);
            System.out.print("Scelta: ");
            try{
                input = Integer.parseInt(scanner.nextLine());
                if(input < min || input > max)
                    throw new NumberFormatException();
            }catch(NumberFormatException nfe){
                input = min -1;
                System.out.println("Input non valido");
                sleep();
            }
        } while(input < min || input > max);
        return input;
    }

    /**
     * Renders the main menu
     * @return String Rendering of the main menu
     */
    private String mainMenu(){
        StringBuilder strb = new StringBuilder();
        strb.append("--------------------------------------------------------\n");
        strb.append("| 1. Crea un nuovo Magazzino                           |\n");
        strb.append("| 2. Apri Magazzino                                    |\n");
        strb.append("| 3. Gestisci Magazzino                                |\n");
        strb.append("| 4. Chiudi Magazzino                                  |\n");
        strb.append("| 0. Esci                                              |\n");
        strb.append("--------------------------------------------------------\n");
        return strb.toString();
    }

    /**
     * Renders the warehouse options menu
     * @return String Rendering of the warehouse options menu
     */
    private String wareHouseMenu(){
        StringBuilder strb = new StringBuilder();
        strb.append("--------------------------------------------------------\n");
        strb.append("| 1. Importa File                                      |\n");
        strb.append("| 2. Salva Versione                                    |\n");
        strb.append("| 3. Ripristina Versione                               |\n");
        strb.append("| 4. Visualizza Statistiche                            |\n");
        strb.append("| 5. Visualizza informazioni Magazzino                 |\n");
        strb.append("| 6. Esporta Magazzino                                 |\n");
        strb.append("| 0. Torna Indietro                                    |\n");
        strb.append("--------------------------------------------------------\n");
        return strb.toString();
    }

    /**
     * Prints the list of opened warehouses
     * @param openedWarehouses list of opened warehouses
     * @return string representation of the opened warehouses
     */
    private String listOpenedWarehouses(List<Warehouse> openedWarehouses) {
        StringBuilder strb = new StringBuilder();
        strb.append("--------------------------------------------------------\n");
        for (int i = 0; i < openedWarehouses.size(); i++) {
            strb.append("| " + (i) + ". " + openedWarehouses.get(i).getName());
            if(openedWarehouses.size() < 10){
                for(int j = 0; j < 50 - openedWarehouses.get(i).getName().length(); j++){
                    strb.append(" ");
                }
            }else{
                for(int j = 0; j < 49 - openedWarehouses.get(i).getName().length(); j++){
                    strb.append(" ");
                }
            }
            strb.append("|\n");
        }
        strb.append("--------------------------------------------------------\n");
        return strb.toString();
    }

    /**
     * Prints the statistics menu of the warehouse
     * 0 goes back to the father menu
     * @return String representation of the statistics menu
     */
    private String statisticsMenu(){
        StringBuilder strb = new StringBuilder();
        strb.append("--------------------------------------------------------\n");
        strb.append("| 1. Visualizza Statistiche Generali                   |\n");
        strb.append("| 2. Visualizza Collaboratori                          |\n");
        strb.append("| 3. Visualizza Statisctiche File                      |\n");
        strb.append("| 0. Torna Indietro                                    |\n");
        strb.append("--------------------------------------------------------\n");
        return strb.toString();
    }

    /**
     * This method clears the console
     * @bug: It doesn't clear the screen
     * @deprecated IT DOESN'T WORK
     */
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e) {}
    }

    /**
     * This method asks the user the information about the new warehouse
     * than it uses them to create a new warehouse.
     */
    private static void cliCreateWarehouse(){
        // Clears the screen
        clearConsole();
        // Fetches the information from the console
        System.out.println("Inserisci nome del magazzino: ");                String wareHouseName = scanner.nextLine();
        System.out.println("\nInserisci percorso di creazione magazzino: "); String wareHousePath = scanner.nextLine();
        System.out.println("\nInserisci descrizione magazzino: ");           String wareHouseDescription = scanner.nextLine();
        System.out.println("\nInserisci nome completo del creatore: ");      String fullName = scanner.nextLine();
        System.out.println("\nInserisci email del creatore: ");              String email = scanner.nextLine();

        // Generates the owner of the warehouse
        User wareHouseOwner = new User(fullName, email, System.getProperty("user.name"));

        // Create the new warehouse with the specified info
        Application.getInstance().createWarehouse(wareHouseName, wareHousePath, wareHouseDescription, wareHouseOwner);
        System.out.println("\nMagazzino creato con successo");

        sleep();
        clearConsole();
    }

    /**
     * This method asks the user the path to the warehouse he wants to open
     * than it opens it.
     */
    private static void cliOpenWarehouse(){
        // Clears the screen
        clearConsole();
        // Asking the user the path of the warehouse he wants to open
        System.out.println("Inserisci percorso di apertura magazzino: "); String path = scanner.nextLine();
        // Clearing the console
        clearConsole();
        // Opening the warehouse and log
        Application.getInstance().openWarehouse(path);
        System.out.println("Magazzino aperto: " + path);
        sleep();
    }

    /**
     * Method used to enter the warehouse's management menu
     */
    private static void cliManageWarehouse(){
        // Clears the screen
        clearConsole();

        // List all warehouses
        System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));

        // Inserts the ID of the warehouse to manage
        inputId = secureIntInput(0, Application.getInstance().getOpenedWarehouses().size(), "Inserisci id del magazzino da gestire: ");

        // If the choice isn't valid throw an error
        System.out.println("Id non valido, inserisci un id valido: ");

        clearConsole();
        do {
            inputManageWarehouse = secureIntInput(0, 6, "Id del magazzino: " + inputId + "\n" + cli.wareHouseMenu());
            switch (inputManageWarehouse) {
                case 1:
                    //Import File
                    clearConsole();

                    System.out.println("Path: ");
                    String importPath = scanner.nextLine();

                    try{
                        Application.getInstance().getWarehouse(inputId).importFile(importPath);
                        System.out.println("File importato con successo");
                    } catch( Exception e ) {
                        System.out.println("Errore durante l'importazione del file");
                    }
                    break;
                case 2:
                    // Save Version
                    clearConsole();

                    // Asking the info for the version
                    System.out.println("Inserire il nome della versione: "); String versionName = scanner.nextLine();
                    System.out.println("Quanti collaboratori hanno lavorato alla verisone?"); int collaboratorsCount = Integer.parseInt(scanner.nextLine());

                    // Creating the list with all the collaborators
                    List<User> collaborators = new LinkedList<>();
                    for (int i = 0; i < collaboratorsCount; i++) {
                        System.out.println("Inserisci il nome del " + i + "° collaboratore: "); String collaboratorName = scanner.nextLine();
                        System.out.println("Inserisci il email del " + i + "° collaboratore: "); String collaboratorEmail = scanner.nextLine();
                        System.out.println("Inserisci il username del " + i + "° collaboratore: "); String collaboratorUsername = scanner.nextLine();
                        collaborators.add(new User(
                                collaboratorName,
                                collaboratorEmail,
                                collaboratorUsername
                        ));
                    }
                    // Saving the version
                    Application
                            .getInstance().getWarehouse(inputId).saveVersion(
                                    versionName,
                                    collaborators
                            );
                    System.out.println("Versione salvata");
                    break;
                case 3:
                    //Restore Version
                    clearConsole();

                    System.out.println("Inserire il nome della versione: ");   String versionID = scanner.nextLine();
                    System.out.println("Inserire il proprio nome completo: "); String fullName = scanner.nextLine();
                    System.out.println("Inserire la propria email: ");         String email = scanner.nextLine();

                    User user = new User(fullName, email, System.getProperty("user.name"));

                    try{
                        Application.getInstance().getWarehouse(inputId).restoreVersion(versionID, user);
                        System.out.println("Versione ripristinata con successo");
                    } catch (IOException ie){
                        System.out.println("Errore durante il restore della versione");
                    }
                    break;
                case 4:
                    //Print Statistics
                    clearConsole();
                    do{
                        inputStatistics = secureIntInput(0, 3, cli.statisticsMenu());

                        switch (inputStatistics) {
                            case 1:
                                //Print generic statistics
                                clearConsole();
                                //System.out.println(Application.getInstance().getWarehouse(inputId).getGenericStatistics());
                                System.out.println("Statistiche generiche");

                                break;
                            case 2:
                                //Print collaborators
                                clearConsole();
                                //System.out.println(Application.getInstance().getWarehouse(inputId).getCollaborators());
                                System.out.println("Collaboratori");
                                break;
                            case 3:
                                //Print file statistics
                                clearConsole();
                                //System.out.println(Application.getInstance().getWarehouse(inputId).getFileStatistics());
                                System.out.println("Statistiche file");
                                break;
                            case 0:
                                clearConsole();
                                break;
                        }
                    } while (inputStatistics != 0);
                    break;
                case 5:
                    //Print Warehouse info
                    clearConsole();
                    //Application.getInstance().getWarehouse(inputId).printInfo();
                    System.out.println("Info magazzino");
                    break;
                case 6:
                    //Export Warehouse
                    clearConsole();
                    System.out.println("Inserire il percorso di esportazione: ");
                    String exportPath = scanner.nextLine();
                    try {
                        Application.getInstance().getWarehouse(inputId).exportWarehouse(exportPath);
                    } catch (IOException ioe) {
                        System.err.println("Errore: impossibile esportare il magazzino");
                    }
                    System.out.println("Magazzino esportato");
                    break;
                case 0:
                    // Go Back
                    clearConsole();
                    break;
            }
        } while (inputManageWarehouse != 0);
    }

    /**
     * This method sleeps for a second
     */
    private static void sleep() {
        try{ Thread.sleep(1000); }catch(InterruptedException ie){}
    }

    /**
     * Method used to close a warehouse
     */
    private static void cliCloseWarehouse(){
        // Close Warehouse
        clearConsole();
        int id = 0;
        System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
        id = secureIntInput(0, Application.getInstance().getOpenedWarehouses().size(), "Inserisci id del magazzino da chiudere");

        while(Application.getInstance().getWarehouse(id) == null){
            System.out.println("Id non valido, inserisci un id valido: ");
            id = Integer.parseInt(scanner.nextLine());
        }

        Application.getInstance().closeWarehouse(id);
        System.out.println("Magazzino chiuso");
        //sleep for 1 second
        sleep();
    }
}