/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Examen;
import edu.unicundi.exception.NotFoundModelException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author familia manrique
 */
@Local
public interface IExamenService {
    
    public List<Examen> listarTodos();
    
    public Examen listar(Integer id) throws NotFoundModelException;   
    
    public void guardar(Examen examen);
    
    public void eliminar(Integer id) throws NotFoundModelException;
    
    public void editar(ExamenDto examendto) throws NotFoundModelException;
    
    public Examen listarNombre(String nombre);
}
