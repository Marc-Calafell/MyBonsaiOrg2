package Model;

import java.sql.Array;
import java.sql.Date;

/**
 *
 * @author mark
 */
public class Bonsai {
    
    private int _1_id;
    private String _2_nom;
    private String _3_nomBotanic;
    private String _4_familia;
    private int _5_edat;
    private Date _6_dataAlta;
    private Date _7_dataBaixa;
    private Array _8_Propietaris;
  
    public Bonsai(String _2_nom, String _3_nomBotanic, String _4_familia, int _5_edat, Date _6_dataAlta, Date _7_dataBaixa, Array _8_Propietaris) {
        this._2_nom = _2_nom;
        this._3_nomBotanic = _3_nomBotanic;
        this._4_familia = _4_familia;
        this._5_edat = _5_edat;
        this._6_dataAlta = _6_dataAlta;
        this._7_dataBaixa = _7_dataBaixa;
        this._8_Propietaris=_8_Propietaris;
    }

    public Bonsai(int _1_id, String _2_nom, String _3_nomBotanic, String _4_familia, int _5_edat, Date _6_dataAlta, Date _7_dataBaixa, Array _8_Propietaris) {
        this._1_id = _1_id;
        this._2_nom = _2_nom;
        this._3_nomBotanic = _3_nomBotanic;
        this._4_familia = _4_familia;
        this._5_edat = _5_edat;
        this._6_dataAlta = _6_dataAlta;
        this._7_dataBaixa = _7_dataBaixa;
        this._8_Propietaris=_8_Propietaris;
    }

    public int get1_id() {
        return _1_id;
    }

    public void set1_id(int _1_id) {
        this._1_id = _1_id;
    }

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_nomBotanic() {
        return _3_nomBotanic;
    }

    public void set3_nomBotanic(String _3_nomBotanic) {
        this._3_nomBotanic = _3_nomBotanic;
    }

    public String get4_familia() {
        return _4_familia;
    }

    public void set4_familia(String _4_familia) {
        this._4_familia = _4_familia;
    }

    public int get5_edat() {
        return _5_edat;
    }

    public void set5_edat(int _5_edat) {
        this._5_edat = _5_edat;
    }

    public Date get6_dataAlta() {
        return _6_dataAlta;
    }

    public void set6_dataAlta(Date _6_dataAlta) {
        this._6_dataAlta = _6_dataAlta;
    }

    public Date get7_dataBaixa() {
        return _7_dataBaixa;
    }

    public void set7_dataBaixa(Date _7_dataBaixa) {
        this._7_dataBaixa = _7_dataBaixa;
    }
    
    public Array get8_Propietaris() {
        return _8_Propietaris;
    }

    public void set8_Propietaris(Array _8_Propietaris) {
        this._8_Propietaris = _8_Propietaris;
    }

    
    
}

    
