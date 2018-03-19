package com.negafuma.gui.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class GenericDao {
    private static final String PERSISTENCE_UNIT_NAME = "WorkManagerJPA";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static EntityManager em = emf.createEntityManager();
    /**
     * Enregistre une collection d'objets mappés via des annotations JPA
     * @param data
     */
    public static void persist(Collection<Serializable> collection){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        for (Serializable serializable : collection) {
            em.persist(serializable);
        }
        tx.commit();
    }
    /**
     * Enregistre un objet mappé via des annotations JPA
     * @param data
     */
    public static void persist(Serializable data){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(data);
        tx.commit();
    }
     /**
     * Retourne toutes les instances d'une classe donnée
     * @param className : le nom d'une classe annotée par JPA
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List load(String className){
        Query q = em.createQuery("from " + className);
        return q.getResultList();
    }

}
