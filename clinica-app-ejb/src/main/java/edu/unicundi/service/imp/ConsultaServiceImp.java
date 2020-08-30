/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.ConsultaExamenDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.entity.Medico;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.repo.IConsultaRepo;
import edu.unicundi.repo.IMedicoRepo;
import edu.unicundi.service.IConsultaExamenService;
import edu.unicundi.service.IConsultaService;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ASUS-PC
 */
@TransactionManagement(TransactionManagementType.CONTAINER)
@Stateless
public class ConsultaServiceImp implements IConsultaService {

    @EJB
    private IConsultaRepo repo;

    @EJB
    private IMedicoRepo repoM;

    @EJB
    private IConsultaExamenService service;

    @Override
    public List<Consulta> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public Consulta listar(int id) throws NotFoundModelException {
        Consulta consulta = repo.listar(id);

        if (consulta == null) {
            throw new NotFoundModelException("Objeto consulta no encontrado");
        }

        Consulta consultaAux = new Consulta();
        consultaAux.setId(consulta.getId());
        for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
            dc.setConsulta(consultaAux);
        }
        return consulta;
    }

    @Override
    public int guardar(Consulta consulta) {
        if (consulta.getDetalleConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                dc.setConsulta(consulta);
            }
        }
        return repo.guardar(consulta);
    }

    @Override
    public void eliminar(int id) throws NotFoundModelException {
        Consulta consulta = repo.listar(id);
        if (consulta == null) {
            throw new NotFoundModelException("Objeto consulta no encontrado");
        }
        repo.eliminar(consulta);
    }

    @Override
    public void editar(ConsultaDto consultaAux) throws NotFoundModelException {
        Consulta consulta = repo.listar(consultaAux.getId());
        SimpleDateFormat formato = new SimpleDateFormat("M/d/yyyy");
        Date fechaDate = new Date();
        if (consulta.getMedico() != null) {
            Medico medico = repoM.listar(consultaAux.getMedico().getId());
            consulta.setMedico(medico);
        }
        if ("00/undefined/00 00:00:00".equals(consultaAux.getFecha()) || consultaAux.getFecha().equals(null) || consultaAux.getFecha().equals("undefined")) {
            fechaDate = consulta.getFecha();
        } else {
            try {
                fechaDate = formato.parse(consultaAux.getFecha());
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        consulta.setFecha(fechaDate);
        repo.editar(consulta);

    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarTransaccion(ConsultaExamenListaDto dto) throws NotFoundModelException {
        Integer idConsulta = null;

        ModelMapper modelMapper = new ModelMapper();
        Consulta consulta = modelMapper.map(dto.getConsulta(), Consulta.class);
        if (consulta.getDetalleConsulta() != null) {
            for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                dc.setConsulta(consulta);
            }
        }
        idConsulta = repo.guardar(consulta);
        //idConsulta = 27;         
        ConsultaExamenDto ceDto = new ConsultaExamenDto();

        for (ExamenDto examen : dto.getListaExamen()) {

            ceDto.setIdConsulta(idConsulta);
            ceDto.setIdExamen(examen.getId());
            ceDto.setInfoAdicional(examen.getInfoAdicional());
            service.guardar(ceDto);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void guardarTransaccion2(ConsultaExamenListaDto dto) throws NotFoundModelException, SQLException{
        Integer idConsulta = null;
        ModelMapper modelMapper = new ModelMapper();
        try {
            Consulta consulta = modelMapper.map(dto.getConsulta(), Consulta.class);
            if (consulta.getDetalleConsulta() != null) {
                for (DetalleConsulta dc : consulta.getDetalleConsulta()) {
                    dc.setConsulta(consulta);
                }
            }
            idConsulta = repo.guardar(consulta);
            idConsulta = 27;
            //idConsulta = repo.obtenerUltimoIdPorMedico(consulta.getMedico().getId());            
            ConsultaExamenDto ceDto = new ConsultaExamenDto();

            for (ExamenDto examen : dto.getListaExamen()) {

                ceDto.setIdConsulta(idConsulta);
                ceDto.setIdExamen(examen.getId());
                ceDto.setInfoAdicional(examen.getInfoAdicional());
                service.guardar(ceDto);
            }
        } catch (Exception ex) {
            throw new SQLException("Error existente al guardar");
        }

    }
}
