package ladder.dao;

import java.util.List;
import ladder.model.AbstractEntity;

/**
 *
 * @author Marcus Krassmann
 */
public interface AbstractDao<T extends AbstractEntity> {

    /**
     * Persists a business object. Persists all kinds of BusinessObjects.
     *
     * @param obj the object to be persisted
     */
    void persist(T obj);

    /**
     * Deletes a business object. Deletes all kinds of BusinessObjects.
     *
     * @param obj the object to be deleted
     */
    void remove(T obj);

    /**
     * Finds a T by its id.
     *
     * @param id the id
     * @return the T
     */
    T findById(Long id);

    /**
     * Finds all existing T.
     *
     * @return all T
     */
    List<T> findAll();

    /**
     * Finds a special T by an example instance.
     * @param entity the example instance of the T
     * @return the matching, persisted instances
     */
    List<T> findByExample(T entity);

    /**
     * Finds a unique special T by an example instance.
     * @param entity the example instance of the T
     * @return the unique matching, persisted instance
     */
    T findUniqueByExample(T entity);

    /**
     * Synchronize the persistence context to the underlying database.
     */
    void flush();
}
