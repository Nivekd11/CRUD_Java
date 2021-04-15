/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;

/**
 *
 * @author adan
 */
public class Carrera implements Serializable {
    private int idCarrera; 
private String nombreCarrera;
private int duracion ;

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public int getDuracion() {
        return duracion;
    }
    
    
}
