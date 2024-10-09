package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerAdmin {
    private static final String PERSISTENCE_UNIT_NAME = "test_db";

    private static EntityManager instance ;

    private EntityManagerAdmin() {
    }

    public static EntityManager getInstance() {
        if(instance == null || !instance.isOpen()) {
            EntityManagerFactory ef = Persistence.
                    createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            instance = ef.createEntityManager();
        }
        return instance;
    }
}