/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.repo.IConsultaRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class ConsultaRepoImp implements IConsultaRepo {

    @PersistenceContext(unitName = "edu.unicundi_clinica-app-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    public List<Consulta> listarTodos() {
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.listarTodos", Consulta.class);
        List<Consulta> listaConsulta = query.getResultList();
        //Para que no genere bucle infinito, la otra opción es utilizar @JsonIgnore
        /*for (Consulta cs : listaConsulta) {
         Consulta consultaAuliar = new Consulta();
         consultaAuliar.setId(cs.getId());
         for (DetalleConsulta dc : cs.getDetalleConsulta()) {
         dc.setConsulta(consultaAuliar);
         }            
         }*/

        //Para que traiga el objeto sin detalleConsulta
        /*for (Consulta con : listaConsulta) {
         con.setDetalleConsulta(null);
         }*/
        return listaConsulta;
    }

    @Override
    public Consulta listar(int id) {
        this.em.getEntityManagerFactory().getCache().evictAll();
        Consulta consultaAux =  em.find(Consulta.class, id);
        Consulta aux = new Consulta();
        for (DetalleConsulta col : consultaAux.getDetalleConsulta()) {
            aux.setId(id);
            col.setConsulta(aux);
        }
         return consultaAux;
    }

    @Override
    public int guardar(Consulta consulta) {
        em.persist(consulta);
        em.flush();
        return consulta.getId();
    }

    @Override
    public void eliminar(Consulta consulta) {
        em.remove(consulta);
    }
    
    @Override
    public Consulta editar(Consulta editar) {
        em.merge(editar);
        return editar;
    }

    @Override
    public Integer obtenerUltimoIdPorMedico(int id_medico) {
        this.em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createNamedQuery("Consulta.listarUltimoIdPorMedico", Integer.class).setParameter("medico_id", id_medico);
        return (Integer) query.getSingleResult();
    }

}
