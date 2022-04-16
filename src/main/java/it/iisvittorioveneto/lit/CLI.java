package it.iisvittorioveneto.lit;

import com.sun.security.auth.module.NTSystem;

public class CLI {

    public static void main(String[] args) {
        //system out print menu
        CLI cli = new CLI();
        System.out.flush();
        //input
        int input = 0;
        while(input != -1){
            System.out.println(cli.menu0());
            //system input read
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
                    //get windows current user
                    String user = System.getProperty("user.name");
                    Application.CreateWarehouse(wareHouseName, wareHousePath, wareHouseDescription, user);
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
        return strb.toString();
    }
}
