
package org.example;

import org.feature.dbhelper.*;
import org.feature.entity.Client;
import org.feature.prefs.Prefs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class App {

    private static Connection connection;

    public static void main(String[] args) {

        String connectionUrl = new Prefs().getString(Prefs.DB_JDBC_CONNECTION_URL);
        try {
            new DatabaseInitService().initDb(connectionUrl);
            connection = DriverManager
                    .getConnection(connectionUrl);
        }catch (SQLException e){
            System.out.println("Error getConnection "+connectionUrl);
            e.printStackTrace();
        }


        ADOClient adoClient = new ADOClient(connection);
        long idClient = adoClient.create("Client new");
        System.out.println("new Client id = " + idClient);

        String name = adoClient.getById(idClient);
        System.out.println("Client name = " + name);

        adoClient.setName(idClient, "Rename Client");
        System.out.println("Client rename = " + adoClient.getById(idClient));

         for (Client client : adoClient.listAll()){
             System.out.println(client);
         }

        adoClient.deleteById(idClient);

        System.out.println("*********** Delete ****************");
        for (Client client : adoClient.listAll()){
            System.out.println(client);
        }
    }
}
