/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.view;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ASUS-PC
 */
@Entity
//@Table(name = "examen_detalles_consulta_view")
public class ExamenConsultaId implements Serializable{
         
    @Id
    private Integer id;
        
    private String nombre;
    
    private String descripcion;
    
    @Column(name = "info_adicional")
    private String infoAdicional;

    public Integer getIdExamen() {
        return id;
    }

    public void setIdExamen(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }   
    
}
