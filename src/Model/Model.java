/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;
/**
 *
 * @author mark
 */
public class Model {
    
    private static Connection connexio = null;  
    private static ResultSet resultSet = null;
        
    public Model() {
        Properties props = new Properties();
               
        try(FileInputStream in = new FileInputStream("database.properties")) {
            props.load(in);
           String url = props.getProperty("db.url");
           String user = props.getProperty("db.user");
           String password = props.getProperty("db.password");
            
            System.out.println(url + "\n" + user + "\n" + password);
            
            
            crearConnexio(url, user, password);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No s'ha pogut connectara la BD", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
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
            JOptionPane.showMessageDialog(null, "No s'ha pogut connectar a la BD", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
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
                    Array propietaris=resultSet.getArray(8);
                    llista.add(new Bonsai(id, nom, nomBotanic, familia, edat, dataAlta ,dataBaixa, propietaris));                
                }
            
            
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No s'han pogut mostrar els bonsais \n", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
            
        }  
        return llista;    
    }
    
    
    
    
    public void INSERTbonsai(String nom, String nomBotanic, String familia, int edat, Date dataAlta, Date dataBaixa, Array propietaris){
            
        String sql = "INSERT INTO bonsai (nom, nomBotanic, familia, edat, dataAlta , dataBaixa, propietaris) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement INSERT=connexio.prepareStatement(sql)) {
            INSERT.setString(1, nom);
            INSERT.setString(2, nomBotanic);
            INSERT.setString(3, familia);
            INSERT.setInt(4, edat);
            INSERT.setDate(5,dataAlta);
            INSERT.setDate(6, dataBaixa);
            INSERT.setArray(7, propietaris);
            INSERT.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No s'ha pogut afetgir el bonsai a la taula \n", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }  
    
    }
    
    public void UPDATEbonsai(int id, String nom, String nomBotanic, String familia, int edat, Date dataAlta, Date dataBaixa, Array propietaris){
            
        String sql = "UPDATE bonsai SET nom=?, nomBotanic=?, familia=?, edat=?, dataAlta=?, dataBaixa=?, propietaris=? WHERE id=?";
        try(PreparedStatement UPDATE=connexio.prepareStatement(sql)) {
            UPDATE.setString(1, nom);
            UPDATE.setString(2, nomBotanic);
            UPDATE.setString(3, familia);
            UPDATE.setInt(4, edat);
            UPDATE.setDate(5, dataAlta);
            UPDATE.setDate(6, dataBaixa);
            UPDATE.setArray(7, propietaris);
            UPDATE.setInt(8, id);
            
            System.out.println(id);
            System.out.println(sql);
            UPDATE.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No s'ha pogut modificar el bonsai a la base de dades", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }  
    
    }
    
    public void DELETEFROMbonsai(int id){
            
        String sql = "DELETE FROM bonsai WHERE id=?";
        try(PreparedStatement DELETEFROM=connexio.prepareStatement(sql)) {
            DELETEFROM.setInt(1, id);
            DELETEFROM.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No s'ha pogut esborrar el bonsai de la taula \n", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println(e);
        }  
    
    }
 
}
