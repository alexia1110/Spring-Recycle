package com.unab.ecoqr;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.unab.ecoqr.model.entity.Residuo;
import com.unab.ecoqr.model.entity.Usuario;

public class TestUsuarios {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
    public static void main(String[] args) {
        crearDatos();
        imprimirDatos();
    }

    static void crearDatos(){
        // EntityManager em = emf.createEntityManager();
        // em.getTransaction().begin();
        // Usuario usuario1 = new Usuario(1L, "Eliana", "urrutia", "jeldres", "F", "sismografo", "812", "13", "12", "eliana.alexia@gmail.com", "aryaA", true);
        // em.persist(usuario1);
        // em.persist(new Residuo(1l, "CCU", "plastic", "plastic", false, usuario1));
        // em.persist( new Residuo(2l, "Colun", "carton", "carbor_drink", false, usuario1));

        // em.getTransaction().commit();
        // em.close();

    }

    static void imprimirDatos(){
        // EntityManager em = emf.createEntityManager();
        // Usuario usuario1 = em.find(Usuario.class, 1l);

        // System.out.println(usuario1);
        // em.close();

    }

    
}
