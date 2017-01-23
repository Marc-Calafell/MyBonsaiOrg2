/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inici;

import Vista.Gui;
import Model.Model;
import Conrtolador.Controlador;

/**
 *
 * @author profe
 */
public class Start {
    
    static Model model=new Model();
    static Gui vista=new Gui();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Controlador(model, vista);
    }
    
}
