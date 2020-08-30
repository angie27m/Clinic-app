/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.Examen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author familia manrique
 */
@Local
public interface IExamenRepo {
    
    public List<Examen> listarTodos();
    
    public Examen listar(Integer id);   
    
    public void guardar(Examen examen);
    
    public void eliminar(Examen examen);
    
    public void editar(Examen examen);
    
    public Examen listarNombre(String nombre);
}
