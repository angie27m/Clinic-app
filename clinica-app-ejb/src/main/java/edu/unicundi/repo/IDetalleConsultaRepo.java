/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.dto.DetalleConsultaDto;
import edu.unicundi.entity.DetalleConsulta;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IDetalleConsultaRepo {
    
    public List<DetalleConsulta> listarTodos();
    
    public DetalleConsulta listar(int id);   
    
    public void guardar(DetalleConsulta detalleConsulta);
    
    public void eliminar(DetalleConsulta detalleConsulta);
    
    public DetalleConsulta editar(DetalleConsulta editar, DetalleConsultaDto aux);     
    
    public DetalleConsulta editarDiagnostico(DetalleConsulta editar, DetalleConsultaDto aux);
    
    public DetalleConsulta editarTratamiento(DetalleConsulta editar, DetalleConsultaDto aux);
    
}
