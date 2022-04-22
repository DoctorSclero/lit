package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.model.User;

import java.util.List;

public class CLI {

    /**
     * Contains the logic of the program and
     * the cli menu
     */
    public static void main() {
        CLI cli = new CLI();

        System.out.flush();
        int input = -1;
        while(input != 0) {
            System.out.println(cli.menu0());
            System.out.print("Scelta: ");
            input = Integer.parseInt(System.console().readLine());
            switch(input){
                case 1:
                    //Create Warehouse
                    System.out.flush();
                    System.out.println("Inserisci nome del magazzino: ");
                    String wareHouseName = System.console().readLine();

                    System.out.flush();

                    System.out.println("Inserisci percorso di creazione magazzino: ");
                    String wareHousePath = System.console().readLine();

                    System.out.flush();

                    System.out.println("Inserisci descrizione magazzino: ");
                    String wareHouseDescription = System.console().readLine();

                    System.out.flush();

                    System.out.println("Inserisci email del creatore: ");
                    String email = System.console().readLine();

                    System.out.flush();

                    System.out.println("Inserisci nome completo del creatore: ");
                    String fullName = System.console().readLine();

                    User user = new User(fullName, email, System.getProperty("user.name"));

                    Application.getInstance().createWarehouse(wareHouseName, wareHousePath, wareHouseDescription, user);
                    break;
                case 2:
                    //Open Warehouse
                    System.out.flush();
                    System.out.println("Inserisci percorso di apertura magazzino: ");
                    String path = System.console().readLine();

                    System.out.flush();

                    System.out.println("Magazzino aperto: " + Application.getInstance().openWarehouse(path));
                    int input1 = -1;
                    while(input1 != 0) {
                        System.out.println(cli.wareHouseMenu());
                        System.out.println("Scelta: ");
                        input = Integer.parseInt(System.console().readLine());
                        switch (input) {
                            case 1:
                                //Import File
                                System.out.flush();

                                System.out.println("Path: ");
                                String path1 = System.console().readLine();

                                Application.getInstance().importFile(path1);
                                break;
                            case 2:
                                //Save Version
                                System.out.flush();
                                break;
                            case 3:
                                //Restore Version
                                System.out.flush();
                                break;
                            case 4:
                                //Print Statistics
                                System.out.flush();
                                break;
                            case 5:
                                //Print Warehouse info
                                System.out.flush();
                                break;
                            case 6:
                                //Export Warehouse
                                System.out.flush();
                                break;
                            case 0:
                                //Go Back
                                System.out.flush();
                                break;
                            default:
                                System.out.flush();
                        }
                    }
                    break;
                case 3:
                    //Close Warehouse
                    System.out.flush();

                    System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
                    System.out.println("Inserisci id del magazzino da chiudere: ");
                    int id = Integer.parseInt(System.console().readLine());
                    Application.getInstance().closeWarehouse(id);
                    break;
                case 4:
                    //List all opened warehouses
                    System.out.flush();

                    System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
                    break;
                case 0:
                    System.out.flush();
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
    private String menu0(){
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
}
