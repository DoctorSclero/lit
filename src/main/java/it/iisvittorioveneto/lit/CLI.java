package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.model.User;

import java.awt.desktop.AppForegroundListener;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static CLI cli = new CLI();

    /**
     * Contains the logic of the program and
     * the cli menu
     */
    public static void main(String args[]) {
        clearConsole();
        int inputMainMenu = -1;
        while (inputMainMenu != 0) {
            inputMainMenu = secureIntInput(0, 4, cli.mainMenu());
            int inputId = -1;
            switch (inputMainMenu) {
                case 1:
                    cliCreateWarehouse();
                    break;
                case 2:
                    cliOpenWarehouse(inputId);
                    cliManageWarehouse(inputId);
                    break;
                case 3:
                    cliCloseWarehouse();
                    break;
                case 4:
                    cliManageWarehouse(inputId);
                    break;
                case 0:
                    clearConsole();
                    System.out.println("Buona Giornata!");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                    break;
            }
            inputMainMenu = -1;
        }
    }

    /**
     * Used to verify if the input is valid between bounds
     * @param min
     * @param max
     * @param output
     * @return
     */
    private static int secureIntInput(int min, int max, String output){
        int input = -1;
        while(input < min || input > max){
            clearConsole();
            System.out.println(output);
            System.out.print("Scelta: ");
            try{
                input = Integer.parseInt(scanner.nextLine());
                if(input < min || input > max)
                    throw new NumberFormatException();
            }catch(NumberFormatException nfe){
                System.out.println("Input non valido");
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException ie){}
                clearConsole();
            }
        }
        return input;
    }

    /**
     * Prints the main menu
     * 0 closes the program
     * @return String representing the main menu
     */
    private String mainMenu(){
        StringBuilder strb = new StringBuilder();
        strb.append("╔══════════════════════════════════════════════════════╗\n");
        strb.append("║ 1. Crea un nuovo Magazzino                           ║\n");
        strb.append("║ 2. Apri Magazzino                                    ║\n");
        strb.append("║ 3. Chiudi Magazzino                                  ║\n");
        strb.append("║ 4. Gestisci Magazzino                                ║\n");
        strb.append("║ 0. Esci                                              ║\n");
        strb.append("╚══════════════════════════════════════════════════════╝\n");
        return strb.toString();
    }

    /**
     * Prints the menu for the warehouse
     * 0 goes back to the main menu
     * @return String representing the warehouse menu
     */
    private String wareHouseMenu(){
        StringBuilder strb = new StringBuilder();
        strb.append("╔══════════════════════════════════════════════════════╗\n");
        strb.append("║ 1. Importa File                                      ║\n");
        strb.append("║ 2. Salva Versione                                    ║\n");
        strb.append("║ 3. Ripristina Versione                               ║\n");
        strb.append("║ 4. Visualizza Statistiche                            ║\n");
        strb.append("║ 5. Visualizza informazioni Magazzino                 ║\n");
        strb.append("║ 6. Esporta Magazzino                                 ║\n");
        strb.append("║ 0. Torna Indietro                                    ║\n");
        strb.append("╚══════════════════════════════════════════════════════╝\n");
        return strb.toString();
    }

    /**
     * Prints the list of opened warehouses
     * @param openedWarehouses list of opened warehouses
     * @return string representation of the opened warehouses
     */
    private String listOpenedWarehouses(List<Warehouse> openedWarehouses) {
        StringBuilder strb = new StringBuilder();
        strb.append("╔══════════════════════════════════════════════════════╗\n");
        for (int i = 0; i < openedWarehouses.size(); i++) {
            strb.append("║ " + (i + 1) + ". " + openedWarehouses.get(i).getName() + " ║\n");
        }
        strb.append("║                                                      ║\n");
        strb.append("╚══════════════════════════════════════════════════════╝\n");
        return strb.toString();
    }

    /**
     * Prints the statistics menu of the warehouse
     * 0 goes back to the father menu
     * @return String representation of the statistics menu
     */
    private String statisticsMenu(){
        StringBuilder strb = new StringBuilder();
        strb.append("╔══════════════════════════════════════════════════════╗\n");
        strb.append("║ 1. Visualizza Statistiche Generali                   ║\n");
        strb.append("║ 2. Visualizza Collaboratori                          ║\n");
        strb.append("║ 3. Visualizza Statisctiche File                      ║\n");
        strb.append("║ 0. Torna Indietro                                    ║\n");
        strb.append("╚══════════════════════════════════════════════════════╝\n");
        return strb.toString();
    }

    /**
     * Clears the console
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
     * Method used to create a new warehouse
     */
    private static void cliCreateWarehouse(){
        //Create Warehouse
        clearConsole();
        System.out.println("Inserisci nome del magazzino: ");
        String wareHouseName = scanner.nextLine();

        System.out.println("\nInserisci percorso di creazione magazzino: ");
        String wareHousePath = scanner.nextLine();

        System.out.println("\nInserisci descrizione magazzino: ");
        String wareHouseDescription = scanner.nextLine();

        System.out.println("\nInserisci email del creatore: ");
        String email = scanner.nextLine();

        System.out.println("\nInserisci nome completo del creatore: ");
        String fullName = scanner.nextLine();

        User user = new User(fullName, email, System.getProperty("user.name"));

        //Application.getInstance().createWarehouse(wareHouseName, wareHousePath, wareHouseDescription, user);
        System.out.println("\nMagazzino creato con successo");
    }

    /**
     * Method used to open a warehouse
     * @param inputId
     */
    private static void cliOpenWarehouse(int inputId){
        //Open Warehouse
        clearConsole();
        System.out.println("Inserisci percorso di apertura magazzino: ");
        String path = scanner.nextLine();

        clearConsole();

        Application.getInstance().openWarehouse(path);
        System.out.println("Magazzino aperto: " + path);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException ignored) {}
        inputId = Application.getInstance().getOpenedWarehouses().size()-1;
    }

    /**
     * Method used to enter the warehouse's management menu
     * @param inputId
     */
    private static void cliManageWarehouse(int inputId){
        //List all opened warehouses
        clearConsole();
        while(Application.getInstance().getWarehouse(inputId) == null){
            System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
            System.out.println("Inserisci id del magazzino da gestire: ");
            inputId = Integer.parseInt(scanner.nextLine());
            if(Application.getInstance().getWarehouse(inputId) == null){
                System.out.println("Id non valido, inserisci un id valido: ");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ignored) {}
                clearConsole();
            }
        }

        clearConsole();

        int inputOpenedWarehouses = -1;
        while (inputOpenedWarehouses != 0) {

            inputOpenedWarehouses = secureIntInput(0, 6, "Id del magazzino: " + inputId + "\n" + cli.wareHouseMenu());

            switch (inputOpenedWarehouses) {
                case 1:
                    //Import File
                    clearConsole();

                    System.out.println("Path: ");
                    String importPath = scanner.nextLine();

                    //Application.getInstance().importFile(importPath);
                    System.out.println("File importato");
                    break;
                case 2:
                    //Save Version
                    clearConsole();
                    System.out.println("Inserire il nome della versione: ");
                    String versName = scanner.nextLine();
                    //Application.getInstance().getWarehouse(inputId).saveVersion(versName, null);
                    System.out.println("Versione salvata");
                    break;
                case 3:
                    //Restore Version
                    clearConsole();
                    System.out.println("Inserire il nome della versione: ");
                    String resName = scanner.nextLine();
                    //Application.getInstance().getWarehouse(inputId).restoreVersion(resName);
                    System.out.println("Versione ripristinata");
                    break;
                case 4:
                    //Print Statistics
                    clearConsole();

                    int inputStatistics = secureIntInput(0, 3, cli.statisticsMenu());
                    System.out.println("Scelta: ");

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
                    inputStatistics = -1;
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
                    //Application.getInstance().getWarehouse(inputId).exportWarehouse(exportPath);
                    System.out.println("Magazzino esportato");
                    break;
                case 0:
                    //Go Back
                    clearConsole();

                    break;
            }
            inputOpenedWarehouses = -1;
        }
    }

    /**
     * Method used to close a warehouse
     */
    private static void cliCloseWarehouse(){
        //Close Warehouse
        clearConsole();

        //System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
        System.out.println("Inserisci id del magazzino da chiudere: ");
        int id = 0;
        while(Application.getInstance().getWarehouse(id) == null){
            System.out.println("Id non valido, inserisci un id valido: ");
            id = Integer.parseInt(scanner.nextLine());
        }
        //Application.getInstance().closeWarehouse(id);
        System.out.println("Magazzino chiuso");
    }
}