/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.exception.NotFoundModelException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IConsultaService {
    
    public List<Consulta> listarTodos();
    
    public Consulta listar(int id) throws NotFoundModelException;   
    
    public int guardar(Consulta consulta);
    
    public void eliminar(int id) throws NotFoundModelException;
    
    public void editar(ConsultaDto obj) throws NotFoundModelException;
    
    public void guardarTransaccion(ConsultaExamenListaDto dto) throws NotFoundModelException;
    
    public void guardarTransaccion2(ConsultaExamenListaDto dto) throws NotFoundModelException, SQLException;
}
