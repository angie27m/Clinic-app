/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ASUS-PC
 */
@Embeddable
public class ConsultaExamenPK implements Serializable {
    
    @Column(name = "id_consulta", nullable = false)
    private Integer idConsulta;
    
    @Column(name = "idExamen", nullable = false)
    private Integer id;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idConsulta);
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConsultaExamenPK other = (ConsultaExamenPK) obj;
        if (!Objects.equals(this.idConsulta, other.idConsulta)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
