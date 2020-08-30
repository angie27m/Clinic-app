/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.dto.DetalleConsultaDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import edu.unicundi.repo.IConsultaRepo;
import edu.unicundi.repo.IDetalleConsultaRepo;
import edu.unicundi.service.IDetalleConsultaService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;
/**
 *
 * @author ASUS-PC
 */
@Stateless
public class DetalleConsultaServiceImp implements IDetalleConsultaService {

    @EJB
    private IDetalleConsultaRepo repo;

    @EJB
    private IConsultaRepo repoConsulta;

    @Override
    public List<DetalleConsulta> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public DetalleConsultaDto listar(int id) throws NotFoundModelException {
        DetalleConsulta detalleConsulta = repo.listar(id);
        if (detalleConsulta == null) {
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");
        }

        ModelMapper modelMapper = new ModelMapper();
        DetalleConsultaDto dto = modelMapper.map(detalleConsulta, DetalleConsultaDto.class);
        dto.getConsulta().setDetalleConsulta(null);
        return dto;
    }

    @Override
    public void guardar(DetalleConsulta detalleConsulta) throws ObjetcRequiredException, NotFoundModelException {
        if (detalleConsulta.getConsulta() == null || detalleConsulta.getConsulta().getId() == null) {
            throw new ObjetcRequiredException("IdConsulta es requerido");
        } else {
            Consulta consulta = repoConsulta.listar(detalleConsulta.getConsulta().getId());
            if (consulta == null) {
                throw new NotFoundModelException("Objeto consulta no encontrado");
            } else {
                repo.guardar(detalleConsulta);
            }
        }
    }

    @Override
    public void eliminar(int id) throws NotFoundModelException {
        DetalleConsulta detalleConsulta = repo.listar(id);
        if (detalleConsulta == null) {
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");
        }
        repo.eliminar(detalleConsulta);
    }

    @Override
    public void editar(DetalleConsultaDto consultaAux) throws NotFoundModelException {
        DetalleConsulta detalle = repo.listar(consultaAux.getId());
        if (detalle == null) {
            throw new NotFoundModelException("Objeto consulta no encontrado");
        } else {
            if (consultaAux.getDiagnostico() == null && consultaAux.getTratamiento() == null) {
                throw new NotFoundModelException("No hay campos para editar");
            } else {
                if (consultaAux.getDiagnostico() == null) {
                    consultaAux.setDiagnostico(detalle.getDiagnostico());
                }
                if(consultaAux.getTratamiento()==null){
                    consultaAux.setTratamiento(detalle.getTratamiento());
                }
            }
            repo.editar(detalle, consultaAux);
        }
    }
}
