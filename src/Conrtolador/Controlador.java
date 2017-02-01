/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conrtolador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import Model.Model;
import Vista.Gui;
import java.sql.Array;
import java.sql.Date;

/**
 *
 * @author profe
 */
public class Controlador {

    private Model odb;
    private Gui vista;
    private int filasel=-1;
    private int id=0;
    private String nom="";
    private String nomBotanic="";
    private String familia="";
    private int edat;
    private Date dataAlta=null;
    private Date dataBaixa=null;
    private Array propietaris= null;

    public Controlador(Model odb, Gui jf) {
        this.odb = odb;
        this.vista = jf;
        carregaTaula(odb.SELECTbonsai());
        ClearJTF();
        vista.setVisible(true);
        control();
    }
    
    public void ClearJTF() {
        
        vista.getNomBonsaiJTF().setText("");
        vista.getNomBotanicJTF().setText("");
        vista.getDataAltaJTF().setText("");
        vista.getDataBaixaJTF().setText("");
        vista.getEdatJTF().setText("");
        vista.getFamiliaJTF().setText("");
        vista.getPropietarisJTF().setText("");
        
        id=0;
        nom="";
        nomBotanic="";
        familia="";
        edat=0;
        dataAlta=null;
        dataBaixa=null;
        propietaris= null;
        
    }

    public void carregaTaula(ArrayList resultSet) {
        filasel = -1;

        
        if (resultSet.size() != 0) {
            Vector columnNames = new Vector();
            Vector data = new Vector();
            DefaultTableModel model;

            Class<?> classe = resultSet.get(0).getClass();
            int ncamps = classe.getDeclaredClasses().length;
            for (Field f : classe.getDeclaredFields()) {
                columnNames.addElement(f.getName());
            }

            Vector<Method> methods = new Vector(resultSet.size());
            try {
                for (PropertyDescriptor pD : Introspector.getBeanInfo(classe).getPropertyDescriptors()) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }
            } catch (IntrospectionException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Object m : resultSet) {
                Vector row = new Vector(ncamps);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                data.addElement(row);
            }

            model = new DefaultTableModel(data, columnNames);
            vista.getTaulaBonsais().setModel(model);

            TableColumn column;
            for (int i = 0; i < vista.getTaulaBonsais().getColumnCount(); i++) {
                column = vista.getTaulaBonsais().getColumnModel().getColumn(i);
                column.setMaxWidth(250);
            }
        }

    }

    public void control() {
        
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource().equals(vista.getDeleteBonsaiBTN())) {
                    if (filasel!=-1){
                            odb.DELETEFROMbonsai(id);
                            ClearJTF();
                            carregaTaula(odb.SELECTbonsai());
                    }
                    else JOptionPane.showMessageDialog(null, "Per borrar un bonsai primer l'has de seleccionar!!", "Error", JOptionPane.ERROR_MESSAGE);                
                } 
                else 
                    if (actionEvent.getSource().equals(vista.getInsertBTN())) {
                        if (!nom.equals("") || !nomBotanic.equals("")) {
                            odb.INSERTbonsai(nom, nomBotanic, familia, edat, dataAlta, dataBaixa, propietaris);
                            ClearJTF();
                            carregaTaula(odb.SELECTbonsai());
                        }
                        else JOptionPane.showMessageDialog(null, "Falta nom i/o nom botanic", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    } else {
                        if (actionEvent.getSource().equals(vista.getUpdateBTN())) {
                            if (filasel!=-1 && (!nom.equals("") || !nomBotanic.equals(""))){
                                odb.UPDATEbonsai(id, nom, nomBotanic, familia, edat, dataAlta, dataBaixa, propietaris);
                                ClearJTF();
                                carregaTaula(odb.SELECTbonsai());
                            }
                            else JOptionPane.showMessageDialog(null, "Primer has de marcar l'arbre", "Error", JOptionPane.ERROR_MESSAGE);                
                        }
                        else {
                            try {
                                    odb.finalize();
                            } catch (Throwable e) {
                                System.out.println("Error tancant la base de dades!!" + e);
                            }
                            System.exit(0);
                        }
                    }    
            }
        };
        vista.getExitBTN().addActionListener(actionListener);
        vista.getInsertBTN().addActionListener(actionListener);
        vista.getUpdateBTN().addActionListener(actionListener);
        vista.getDeleteBonsaiBTN().addActionListener(actionListener);
        
        MouseAdapter mouseAdapter=new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); 
                
                try {
                    filasel = vista.getTaulaBonsais().getSelectedRow();
                    if (filasel != -1) {
                        
                        id = Integer.parseInt(vista.getTaulaBonsais().getValueAt(filasel, 0).toString());
                        
                        nom = (String) vista.getTaulaBonsais().getValueAt(filasel, 1);
                        vista.getNomBonsaiJTF().setText(nom);
                        
                        nomBotanic = (String) vista.getTaulaBonsais().getValueAt(filasel, 2);
                        vista.getNomBotanicJTF().setText(nomBotanic);
                        
                        familia = (String) vista.getTaulaBonsais().getValueAt(filasel, 3);
                        vista.getFamiliaJTF().setText(familia);
                        
                        edat = Integer.parseInt(vista.getTaulaBonsais().getValueAt(filasel, 4).toString());
                        
                        dataAlta = (Date) vista.getTaulaBonsais().getValueAt(filasel, 5);
                        vista.getDataAltaJTF().setText(dataAlta.toString());
                        
                        dataBaixa = (Date) vista.getTaulaBonsais().getValueAt(filasel, 6);
                        vista.getDataBaixaJTF().setText(dataBaixa.toString());
                        
                        propietaris = (Array) vista.getTaulaBonsais().getValueAt(filasel, 7);
                        vista.getPropietarisJTF().setText(propietaris.toString());
                        
                        
                    }else ClearJTF();
                } catch (NumberFormatException ex) {
                    System.out.println("Ha petat: " + ex);
                }
            }
        
        };
        vista.getTaulaBonsais().addMouseListener(mouseAdapter);
        
        FocusAdapter focusAdapter=new FocusAdapter(){
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(e.getSource().equals(vista.getNomBonsaiJTF())){
                    nom = vista.getNomBonsaiJTF().getText().trim();
                }
                if(e.getSource().equals(vista.getNomBotanicJTF())){
                    nomBotanic = vista.getNomBotanicJTF().getText().trim();
                }
                
                if(e.getSource().equals(vista.getFamiliaJTF())){
                    familia = vista.getFamiliaJTF().getText().trim();
                }
                
                if(e.getSource().equals(vista.getEdatJTF())){
                    edat = Integer.parseInt(vista.getEdatJTF().getText());
                }
//                
//                if(e.getSource().equals(vista.getDataAltaJTF())){
//                    dataAlta = (Date) vista.getDataAltaJTF();
//                }
//                
//                if(e.getSource().equals(vista.getDataBaixaJTF())){
//                    dataBaixa = (Date) vista.getDataBaixaJTF();
//                }
//                     
                if(e.getSource().equals(vista.getPropietarisJTF())){
                    propietaris = (Array) vista.getPropietarisJTF();
                }
            }
        
        };
        
        vista.getNomBonsaiJTF().addFocusListener(focusAdapter);
        vista.getNomBotanicJTF().addFocusListener(focusAdapter);
        vista.getFamiliaJTF().addFocusListener(focusAdapter);
        vista.getEdatJTF().addFocusListener(focusAdapter);
        vista.getDataAltaJTF().addFocusListener(focusAdapter);
        vista.getDataBaixaJTF().addFocusListener(focusAdapter);
        vista.getPropietarisJTF().addFocusListener(focusAdapter);
        
        WindowAdapter windowAdapter =new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent evt) {
                try {
                    odb.finalize();
                } catch (Throwable ex) {
                    System.out.println("Error tancant la base de dades!!");
                }
                System.exit(0);
            }
        };
        
        vista.addWindowListener(windowAdapter);
    }

}
