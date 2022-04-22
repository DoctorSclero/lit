package it.iisvittorioveneto.lit;

import it.iisvittorioveneto.lit.model.User;

import java.util.List;

public class CLI {

    public static void main(String[] args) {
        CLI cli = new CLI();

        System.out.flush();
        int input = 0;
        while(input != -1) {
            System.out.println(cli.menu0());
            input = Integer.parseInt(System.console().readLine());
            switch(input){
                case 1:
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
                    System.out.flush();
                    System.out.println("Inserisci percorso di apertura magazzino: ");
                    String path = System.console().readLine();
                    Application.getInstance().openWarehouse(path);
                    break;
                case 3:
                    System.out.flush();
                    System.out.println("Inserisci id del magazzino da chiudere: ");
                    int id = Integer.parseInt(System.console().readLine());
                    Application.getInstance().closeWarehouse(id);
                    break;
                case 4:
                    System.out.flush();
                    //print all warehouses
                    System.out.println(cli.listOpenedWarehouses(Application.getInstance().getOpenedWarehouses()));
                    break;
            }
        }
    }


    private String menu0(){
        StringBuilder strb = new StringBuilder();
        //           1234567890123456789012345678901234567890123456789012345
        strb.append("-------------------------------------------------------\n");
        strb.append(" 1. Crea un nuovo Magazzino\n");
        strb.append(" 2. Apri Magazzino\n");
        strb.append(" 3. Chiudi Magazzino\n");
        strb.append(" 4. Gestisci Magazzino\n");
        strb.append("|                                                     |\n");
        strb.append("-------------------------------------------------------\n");
        return strb.toString();
    }

    private String wareHouseMenu(){
        StringBuilder strb = new StringBuilder();
        //           1234567890123456789012345678901234567890123456789012345
        strb.append("-------------------------------------------------------\n");
        strb.append(" 1. Importa File\n");
        strb.append(" 2. Salva Versione\n");
        strb.append(" 3. Ripristina Versione\n");
        strb.append(" 4. Visualizza Statistiche\n");
        strb.append(" 5. Visualizza informazioni Magazzino\n");
        strb.append(" 6. Esporta Magazzino\n");
        strb.append("|                                                     |\n");
        strb.append("-------------------------------------------------------\n");
        return strb.toString();
    }

    /**
     * Prints the list of opened warehouses
     * @param openedWarehouses list of opened warehouses
     * @return string representation of the opened warehouses
     */
    private String listOpenedWarehouses(List<Warehouse> openedWarehouses) {
        StringBuilder strb = new StringBuilder();
        //           1234567890123456789012345678901234567890123456789012345
        strb.append("-------------------------------------------------------\n");
        for (int i = 0; i < openedWarehouses.size(); i++) {
            strb.append("| " + (i + 1) + ". " + openedWarehouses.get(i).getName() + " |\n");
        }
        strb.append("|                                                     |\n");
        strb.append("-------------------------------------------------------\n");
        return strb.toString();
    }

    private String statisticsMenu(){
        StringBuilder strb = new StringBuilder();
        //           1234567890123456789012345678901234567890123456789012345
        strb.append("-------------------------------------------------------\n");
        strb.append(" 1. Visualizza Statistiche Generali\n");
        strb.append(" 2. Visualizza Collaboratori\n");
        strb.append(" 3. Visualizza Statisctiche File\n");
        strb.append("|                                                     |\n");
        strb.append("-------------------------------------------------------\n");
        return strb.toString();
    }
}
