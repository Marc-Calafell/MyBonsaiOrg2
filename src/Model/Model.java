/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
/**
 *
 * @author profe
 */
public class Model {
    
    private static Connection connexio = null;  
    private static ResultSet resultSet = null;
        
    public Model() {
        Properties props = new Properties();
        String url=null;
        String user=null;
        String password=null;
        
        try(FileInputStream in = new FileInputStream("database.properties")) {
            props.load(in);
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            
            System.out.println(url + "\n" + user + "\n" + password);
            
            
            crearConnexio(url, user, password);
        } catch (IOException e) {
            System.err.println("No s'ha pogut establir la connexió a la BD...");
            System.exit(0);
        }   
    }

    public void finalize() throws Throwable {
        if(resultSet!=null) resultSet.close();
        if(connexio!=null) connexio.close();
        System.out.println("Tancant la connexió a la BD...");
        super.finalize();
    }


    private void crearConnexio(String url, String usuari, String password){
        
        try {
                connexio = DriverManager.getConnection(url, usuari, password);
               
        } catch (SQLException e) {
            System.err.println("No s'ha pogut establir la connexió a la BD...");
            System.exit(0);
        }

    }
    
    public ArrayList<Bonsai> SELECTbonsai(){
            
        ArrayList llista=new ArrayList();
        String sql = "SELECT * FROM bonsai ORDER BY 1;";
        try(PreparedStatement SELECT=connexio.prepareStatement(sql)) {
            this.resultSet=SELECT.executeQuery();
            
            if(this.resultSet!=null){
            
                while(resultSet.next()){
                    int id=resultSet.getInt(1);
                    String nom=resultSet.getString(2);
                    String nomBotanic=resultSet.getString(3);
                    String familia=resultSet.getString(4);
                    int edat=resultSet.getInt(5);
                    Date dataAlta=resultSet.getDate(6);
                    Date dataBaixa=resultSet.getDate(7);
                    llista.add(new Bonsai(id, nom, nomBotanic, familia, edat, dataAlta , dataBaixa));                
                }
            
            
            }
        } catch (SQLException ex) {
            System.err.println("No s'han pogut mostrar els bonsais \n" + ex);
            
        }  
        return llista;    
    }
    
    
    
    
    public void INSERTbonsai(String nom, String nomBotanic, String familia, int edat, Date dataAlta, Date dataBaixa){
            
        String sql = "INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement INSERT=connexio.prepareStatement(sql)) {
            INSERT.setString(1, nom);
            INSERT.setString(2, nomBotanic);
            INSERT.setString(3, familia);
            INSERT.setInt(4, edat);
            INSERT.setDate(5,dataAlta);
            INSERT.setDate(6, dataBaixa);
            INSERT.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("No s'ha pogut afetgir el bonsai a la taula \n" + e);
        }  
    
    }
    
    public void UPDATEbonsai(int id, String nom, String nomBotanic, String familia, int edat, Date dataAlta, Date dataBaixa){
            
        String sql = "UPDATE bonsai SET nom=?, nomBotanic=?, familia=?, edat=?, dataAlta=?, dataBaixa=? WHERE id=?";
        try(PreparedStatement UPDATE=connexio.prepareStatement(sql)) {
            UPDATE.setString(1, nom);
            UPDATE.setString(2, nomBotanic);
            UPDATE.setString(3, familia);
            UPDATE.setInt(4, edat);
            UPDATE.setDate(5, dataAlta);
            UPDATE.setDate(6, dataBaixa);
            UPDATE.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("No s'ha pogut modificar el bonsai a la base de dades \n" + ex);
        }  
    
    }
    
    public void DELETEFROMbonsai(int id){
            
        String sql = "DELETE FROM bonsai WHERE id=?";
        try(PreparedStatement DELETEFROM=connexio.prepareStatement(sql)) {
            DELETEFROM.setInt(1, id);
            DELETEFROM.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("No s'ha pogut esborrar el bonsai de la taula \n" + ex);
        }  
    
    }
 
}
