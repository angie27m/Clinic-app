/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.entity;

import edu.unicundi.view.ExamenConsultaId;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author familia manrique
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Examen.listarTodos", query = "SELECT d FROM Examen d")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Examen.listarNombre", query = "SELECT * from examen c where c.nombre=?", resultClass = Examen.class)
})
public class Examen implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "El nombre del examen es requerido")
    @Size(min = 3, max = 49,  message = "El nombre del examen debe estar entre 3 y 50 caracteres")    
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    
    @NotNull(message = "La descripcion es requerida")
    @Size(min = 3, max = 150,  message = "La descripcion debe estar entre 3 y 150 caracteres")    
    @Column(name = "descripcion", nullable = false, length = 25)
    private String descripcion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    
    
}
