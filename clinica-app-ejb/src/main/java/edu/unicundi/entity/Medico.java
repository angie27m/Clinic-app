/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author ASUS-PC
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Medico.listarTodos", query = "SELECT c FROM Medico c")
})
public class Medico implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "Nombre medico es requerido")
    @Size(min = 3, max = 49,  message = "Nombre medico debe estar entre 3 y 49 caracteres")
    @Column(name = "nombre_medico", nullable = false, length = 50)
    private String nombreMedico;
    
    @NotNull(message = "Objeto direccion es requerido")
    @OneToOne(mappedBy = "medico", cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Direccion direccion;
    
    /*@OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
    @MapsId
    private Consulta consulta;

    @JsonIgnore
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }            
}
