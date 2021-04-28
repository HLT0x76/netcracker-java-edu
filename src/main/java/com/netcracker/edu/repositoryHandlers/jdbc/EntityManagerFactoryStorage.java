package com.netcracker.edu.repositoryHandlers.jdbc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * EntityManager factory
 */
public class EntityManagerFactoryStorage {

  public static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("contractDB");

}
