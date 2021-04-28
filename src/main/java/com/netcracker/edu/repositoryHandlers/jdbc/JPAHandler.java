package com.netcracker.edu.repositoryHandlers.jdbc;

import com.netcracker.edu.contracts.Contract;
import com.netcracker.edu.repository.ContractsRepository;
import com.netcracker.edu.repositoryHandlers.IHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Objects;
import java.util.Set;


/**
 * JPAHandler implements basic functionality of importing and exporting
 * {@link Contract} classes (such as {@link com.netcracker.edu.contracts.concrete.ContractInternet,
 * {@link com.netcracker.edu.contracts.concrete.ContractTelevision}, {@link com.netcracker.edu.contracts.concrete.ContractMobile})
 * using {@link ContractsRepository} object into DB.
 */
public class JPAHandler implements IHandler {

  private final static Logger logger = LoggerFactory.getLogger(JPAHandler.class);
  private final EntityManager entityManager;

  /**
   * JPAHandler constructor, which defines Contract classes support.
   *
   * @param entityManagerFactory entity manager factory
   */
  JPAHandler(EntityManagerFactory entityManagerFactory) {
    this.entityManager = entityManagerFactory.createEntityManager();
  }


  /**
   * Exports provided repository into DB
   *
   * @param repository repository which needs to be exported to DB
   */
  @Override
  public void exportContractRepository(ContractsRepository repository) {
    if(Objects.nonNull(entityManager)) {
      try {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        repository.toList().forEach(entityManager::persist);
        transaction.commit();
        entityManager.close();
      } catch (Exception e) {
        logger.error(e.toString());
      } finally {
        if (entityManager.isOpen()) {
          entityManager.close();
        }
      }
    }
  }


  /**
   * Imports contracts repository from DB
   *
   * @param contractsIds list of contracts ids which will be imported
   * @return contract repository with contracts, parsed from DB
   */
  @Override
  public ContractsRepository importContractRepository(Set<Integer> contractsIds) {
    ContractsRepository result = new ContractsRepository();
    if (Objects.nonNull(entityManager)) {
      try {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        contractsIds.forEach(id -> {
          Contract contract = entityManager.find(Contract.class, id);
          if (Objects.nonNull(contract)) {
            result.add(contract);
          }
        });
        transaction.commit();
        return result;
      } catch (Exception e) {
        logger.error(e.toString());
      } finally {
        if (entityManager.isOpen()) {
          entityManager.close();
        }
      }
    }
    return result;
  }
}
