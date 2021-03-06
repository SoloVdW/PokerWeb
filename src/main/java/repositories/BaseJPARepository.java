package repositories;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * Created by Charl on 2015-01-20.
 */
public class BaseJPARepository<T> {

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    @Transactional
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    @Transactional
    public void merge(T entity) {
        getEntityManager().merge(entity);
    }

    protected EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

    protected Optional<T> getSingleResult(Query query)
    {
        List<T> results= query.getResultList();
        if (results.isEmpty())
            return  Optional.empty();
        if (results.size()==1)
        {
            return Optional.ofNullable(results.get(0));
        }

        throw new NonUniqueResultException();

    }
}
