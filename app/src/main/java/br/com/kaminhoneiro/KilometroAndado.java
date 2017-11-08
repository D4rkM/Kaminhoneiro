package br.com.kaminhoneiro;

import com.orm.SugarRecord;

/**
 * Created by 16254861 on 30/10/2017.
 */

public class KilometroAndado extends SugarRecord {

    private String mes;
    private float km;
    private int mesId;
    //Construtores obrigatórios para ORM

    public KilometroAndado(){}

    public KilometroAndado(String mes, float km, int mesId){
        this.mes = mes;
        this.km = km;
        this.mesId = mesId;
    }

    //Getters & Setters

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public float getKm() {
        return km;
    }

    public void setKm(float km) {
        this.km = km;
    }


    public int getMesId() {
        return mesId;
    }

    public void setMesId(int mesId) {
        this.mesId = mesId;
    }
}
