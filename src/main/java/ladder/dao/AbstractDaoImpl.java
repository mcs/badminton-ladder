package ladder.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ladder.model.AbstractEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.orm.jpa.support.JpaDaoSupport;

public class AbstractDaoImpl<T extends AbstractEntity> extends JpaDaoSupport implements AbstractDao<T> {

    private static final Log log = LogFactory.getLog(AbstractDaoImpl.class);
    @PersistenceContext
    protected EntityManager em;

    protected Session getSession() {
        return (Session) em.getDelegate();
    }

    @Override
    public void persist(T entity) {
        em.persist(entity);
        log.debug("Persisted entity " + entity);
    }

    @Override
    public void remove(T entity) {
        em.remove(entity);
    }

    @Override
    public T findById(Long id) {
        return em.find(getEntityClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        log.debug("EntityManager = " + em);
        Criteria crit = getSession().createCriteria(getEntityClass());
        return crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByExample(T entity) {
        Example ex = Example.create(entity);
        return (List<T>) getSession().createCriteria(getEntityClass()).add(ex).list();
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public T findUniqueByExample(T entity) {
        @SuppressWarnings("unchecked")
        List<T> entities = findByExample(entity);
        if (entities.isEmpty()) {
            return null;
        } else if (entities.size() > 1) {
            throw new RuntimeException("Result not unique! Found " + entities);
        }
        return entities.get(0);
    }

    /**
     * Get the class of the entity, specified as generic parameter.
     *
     * @return the entity class for this DAO, or <tt>null</tt> if none was found
     */
    private Class<T> getEntityClass() {
        return getGenericParameterClass(this.getClass());
    }

    /**
     * Some generic magic ;-)
     * <br>
     * Returns the type of the generic declaration of the superclass.
     * Check out Neal Gafter's "Super Type Token" idiom for more information.
     *
     * @return type of generic declaration of superclass
     */
    private Class<T> getGenericParameterClass(Class clazz) {
        Type t = clazz.getGenericSuperclass();

        if (t instanceof ParameterizedType) {
            // Class has generic parmeters
            Type t2 = ((ParameterizedType) t).getActualTypeArguments()[0];
            if (t2 instanceof Class) {
                // found class, return it
                return (Class) t2;
            }
            // clazz was directly generic typed, t2 is generic TypeVariable
            return null;
        }

        if (t instanceof Class) {
            return getGenericParameterClass((Class) t);
        }

        return null;
    }
}
