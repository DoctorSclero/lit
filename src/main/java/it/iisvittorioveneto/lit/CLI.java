package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.model.User;

import java.util.List;
import java.util.Scanner;

public class CLI {
    /**
     * Contains the logic of the program and
     * the cli menu
     */
    public static void main(String args[]) {
        CLI cli = new CLI();

        clearConsole();
        int input = -1;
        Scanner scanner = new Scanner(System.in);
        while (input != 0) {
            while(input < 0 || input > 4){
                System.out.println(cli.mainMenu());
                System.out.print("Scelta: ");
                try{
                    input = Integer.parseInt(scanner.nextLine());
                    if(input < 0 || input > 4)
                        throw new NumberFormatException();
                }catch(NumberFormatException nfe){
                    System.out.println("Input non valido");
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException ie){}
                    clearConsole();
                }
            }
            Integer inputId = null;
            switch (input) {
                case 1:
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

                    Application.getInstance().createWarehouse(wareHouseName, wareHousePath, wareHouseDescription, user);
                    break;
                case 2:
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
                case 4:

                    //List all opened warehouses
                    clearConsole();
                    if(inputId == null){
                        System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
                        System.out.println("Inserisci id del magazzino da gestire: ");
                        inputId = Integer.parseInt(scanner.nextLine());
                    }

                    clearConsole();

                    int input1 = -1;
                    while (input1 != 0) {
                        while(input1 < 0 || input > 6){
                            clearConsole();
                            System.out.println("Id del magazzino: " + inputId);
                            System.out.println(cli.wareHouseMenu());
                            System.out.println("Scelta: ");
                            try{
                                input = Integer.parseInt(scanner.nextLine());
                                if(input1 < 0 || input > 6)
                                    throw new NumberFormatException();
                            }catch(NumberFormatException nfe){
                                try{
                                    Thread.sleep(1000);
                                }catch(InterruptedException ie){}
                            }
                        }
                        switch (input) {
                            case 1:
                                //Import File
                                clearConsole();

                                System.out.println("Path: ");
                                String importPath = scanner.nextLine();

                                //Application.getInstance().importFile(importPath);
                                break;
                            case 2:
                                //Save Version
                                clearConsole();
                                System.out.println("Inserire il nome della versione: ");
                                String versName = scanner.nextLine();
                                Application.getInstance().getWarehouse(inputId).saveVersion(versName, null);
                                break;
                            case 3:
                                //Restore Version
                                clearConsole();
                                System.out.println("Inserire il nome della versione: ");
                                String resName = scanner.nextLine();
                                //Application.getInstance().getWarehouse(inputId).restoreVersion(resName);
                                break;
                            case 4:
                                //Print Statistics
                                clearConsole();
                                break;
                            case 5:
                                //Print Warehouse info
                                clearConsole();
                                break;
                            case 6:
                                //Export Warehouse
                                clearConsole();
                                break;
                            case 0:
                                //Go Back
                                clearConsole();

                                break;
                        }
                    }
                    break;
                case 3:
                    //Close Warehouse
                    clearConsole();

                    System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
                    System.out.println("Inserisci id del magazzino da chiudere: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    Application.getInstance().closeWarehouse(id);
                    break;

                case 0:
                    clearConsole();
                    System.out.println("Arrivederci!");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
            System.exit(0);
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
}