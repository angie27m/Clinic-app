/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Examen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.service.IExamenService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author familia manrique
 */
@Stateless
@Path("/examenes")
public class ExamenController {
    
    @EJB
    private IExamenService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar")
    public Response buscar(){      
        List<Examen> listaExamen = service.listarTodos();
        return Response.status(Response.Status.OK).entity(listaExamen).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{id}")
    public Response buscar(@PathParam("id") Integer id) throws NotFoundModelException{      
        Examen examen = service.listar(id);
        return Response.status(Response.Status.OK).entity(examen).build();
    }   
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardar")
    public Response guardar(@Valid Examen examen) {  
        service.guardar(examen);
        return Response.status(Response.Status.CREATED).build();
    }      
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/eliminar/{id}")
    public Response eliminar(@PathParam("id") Integer id) throws NotFoundModelException{      
        service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }   
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editar")
    public Response editar(ExamenDto dto) throws NotFoundModelException{  
        service.editar(dto);
        return Response.status(Response.Status.OK).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarNombre/{nombre}")
    public Response buscarNombre(@PathParam("nombre") String nombre){      
        Examen listaExamen = service.listarNombre(nombre);
        return Response.status(Response.Status.OK).entity(listaExamen.getId()).build();
    }
    
}
