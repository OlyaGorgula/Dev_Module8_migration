package org.feature.dbhelper;

import org.feature.entity.Client;
import org.feature.error.thisError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ADOClient {
    private PreparedStatement insertSt;
    private PreparedStatement selectMaxIdSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement deleteIdSt;
    private PreparedStatement renameSt;
    private PreparedStatement getAllSt;

    public ADOClient(Connection connection) {
        try{
            selectMaxIdSt = connection.prepareStatement(
                    "SELECT max(id) AS maxId FROM client"
            );

            insertSt = connection.prepareStatement(
                    "INSERT INTO client (name) VALUES(?)"
            );

            getByIdSt = connection.prepareStatement(
                    "SELECT name FROM client WHERE id = ?"
            );

            renameSt = connection.prepareStatement(
                    "UPDATE client SET name = ? WHERE id = ?"
            );

            deleteIdSt = connection.prepareStatement(
                    "DELETE FROM client WHERE id = ?"
            );

            getAllSt = connection.prepareStatement(
                    "SELECT * FROM client"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*
    додає нового клієнта з іменем name. Повертає ідентифікатор щойно створеного клієнта.
     */
    public long create(String name){
        try {
            if (name.length() < 2 || name.length() > 1000){
                new thisError.IllegalArgumentSizeException();
                return -1;
            }

            insertSt.setString(1, name);
            insertSt.executeUpdate();

            long id;
            try(ResultSet rs = selectMaxIdSt.executeQuery()) {
                rs.next();
                id = rs.getLong("maxId");
            }
            return id;
        }catch (SQLException e){
            System.out.println("Error: create client");
            e.printStackTrace();
        }
        return -1;
    }

    /*
    повертає назву клієнта з ідентифікатором id
     */
    public String getById(long id){
        try {
            getByIdSt.setLong(1, id);

            try(ResultSet rs = getByIdSt.executeQuery()) {
                if (!rs.next()){
                    return  null;
                }
                return rs.getString("name");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    встановлює нове ім'я name для клієнта з ідентифікатором id
     */
    public void setName(long id, String name){
        try{

            if (name.length() < 2 || name.length() > 1000){
                new thisError.IllegalArgumentSizeException();
                return;
            }

            renameSt.setString(1, name);
            renameSt.setLong(2, id);

            renameSt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    видаляє клієнта з ідентифікатором id
     */
    public void deleteById(long id){
        try{
            deleteIdSt.setLong(1, id);
            deleteIdSt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    повертає всіх клієнтів з БД у вигляді колекції об'єктів типу Client (цей клас створи сам, у ньому має бути 2 поля - id та name)
     */
    public List<Client> listAll(){
        try(ResultSet rs = getAllSt.executeQuery()) {
            List<Client> listWorker = new ArrayList<>();

            while (rs.next()){
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                listWorker.add(client);
            }

            return listWorker;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
