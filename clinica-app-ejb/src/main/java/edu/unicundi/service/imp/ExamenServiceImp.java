/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Examen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.repo.IExamenRepo;
import edu.unicundi.service.IExamenService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author familia manrique
 */
@Stateless
public class ExamenServiceImp implements IExamenService{

    @EJB
    private IExamenRepo repo;
   
    @Override
    public List<Examen> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public Examen listar(Integer id) throws NotFoundModelException {
        Examen examen = repo.listar(id);
        if (examen == null) {
            throw new NotFoundModelException("Objeto examen no encontrado");
        }else{
            return examen; 
        }
        //ModelMapper modelMapper = new ModelMapper();
        //ExamenDto examenDto = modelMapper.map(examen, ExamenDto.class);
        
    }

    @Override
    public void guardar(Examen examen) {
        repo.guardar(examen);
    }

    @Override
    public void eliminar(Integer id) throws NotFoundModelException {
        Examen examen = repo.listar(id);
        if (examen == null) {
            throw new NotFoundModelException("Objeto examen no encontrado");
        }
        repo.eliminar(examen);
    }

    @Override
    public void editar(ExamenDto examendto) throws NotFoundModelException {
        Examen examen = repo.listar(examendto.getId());
        if (examen == null) {
            throw new NotFoundModelException("Objeto examen no encontrado");
        }

        if(examendto.getNombre() != null) 
            examen.setNombre(examendto.getNombre());
        if(examendto.getDescripcion() != null)
            examen.setDescripcion(examendto.getDescripcion());
        
        repo.editar(examen);
    }

    @Override
    public Examen listarNombre(String nombre) {
        return repo.listarNombre(nombre);
    }
    
}
