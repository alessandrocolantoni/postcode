package it.wcc.postcode.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractJpaDAO<T extends Serializable> {

//	@PersistenceContext
//	protected EntityManager entityManager;

    protected abstract EntityManager getEntityManager();

    private Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractJpaDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    protected T findByPrimaryKey(Object pkValue) {
        return this.getEntityManager().find(this.persistentClass, pkValue);
    }

    protected <E> E findByPrimaryKey(Class<E> realClass, Object pkValue) {
        return this.getEntityManager().find(realClass, pkValue);
    }


    protected void persist(Object object) {
        this.getEntityManager().persist(object);
    }

    @SuppressWarnings("unchecked")
    protected List<T> findAll() {
        return getEntityManager().createQuery("from " + this.persistentClass.getName()).getResultList();
    }

    @SuppressWarnings("unchecked")
    protected <E> List<E> findAll(Class<E> realClass) {
        return getEntityManager().createQuery("from " + realClass.getName()).getResultList();
    }


    protected <E> List<E> findCollectionByQueryString(String queryString) {
        return findCollectionByQueryString(queryString, new HashMap<String, Object>(), null, null);
    }


    protected <E> List<E> findCollectionByQueryString(String queryString, Integer firstResult, Integer maxResults) {
        return findCollectionByQueryString(queryString, new HashMap<String, Object>(), firstResult, maxResults);
    }


    protected <E> List<E> findCollectionByQueryString(String queryString, String parameterName, Object parameterValue) {
        return findCollectionByQueryString(queryString, parameterName, parameterValue, null, null);
    }


    protected <E> List<E> findCollectionByQueryString(String queryString, Map<String, Object> parameters) {
        return findCollectionByQueryString(queryString, parameters, null, null);
    }


    protected <E> List<E> findCollectionByQueryString(String queryString, String parameterName, Object parameterValue, Integer firstResult, Integer maxResults) {
        List<E> result = new ArrayList<E>();
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(parameterName, parameterValue);
        result = findCollectionByQueryString(queryString, parameters, firstResult, maxResults);
        return result;
    }


    protected <E> List<E> findCollectionByQueryString(String queryString, Map<String, Object> parameters, Integer firstResult, Integer maxResults) {

        Query query = getEntityManager().createQuery(queryString);

        JpaUtils.setQueryParameters(query, parameters);
        JpaUtils.setFirstAndMaxResults(query, firstResult, maxResults);

        @SuppressWarnings("unchecked")
        List<E> result = query.getResultList();

        return result;
    }

    @Deprecated
    protected void deleteByNativeQueryString(String queryString, Map<String,Object> parameters){
        Query query = getEntityManager().createNativeQuery(queryString);

        JpaUtils.setQueryParameters(query, parameters);

        query.executeUpdate();
    }


    //////////////////


    protected <E> E findObjectByQueryString(String queryString) {
        return findObjectByQueryString(queryString, new HashMap<String, Object>());
    }

    protected <E> E findObjectByQueryString(String queryString, String parameterName, Object parameterValue) {

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(parameterName, parameterValue);
        E result = findObjectByQueryString(queryString, parameters);
        return result;
    }

    protected <E> E findObjectByQueryString(String queryString, Map<String, Object> parameters) {
        Query query = getEntityManager().createQuery(queryString);
        JpaUtils.setQueryParameters(query, parameters);

        @SuppressWarnings("unchecked")
        E result = (E) this.getSingleResult(query);
        return result;
    }


    protected <E> E findObjectByNativeQueryString(String queryString) {
        return findObjectByNativeQueryString(queryString, new HashMap<String, Object>());
    }

    protected <E> E findObjectByNativeQueryString(String queryString, String parameterName, Object parameterValue) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(parameterName, parameterValue);
        E result = findObjectByNativeQueryString(queryString, parameters);
        return result;
    }

    protected <E> E findObjectByNativeQueryString(String queryString, Map<String, Object> parameters) {
        Query query = getEntityManager().createNativeQuery(queryString);
        JpaUtils.setQueryParameters(query, parameters);
        @SuppressWarnings("unchecked")
        E result = (E) this.getSingleResult(query);
        return result;
    }


    /////////////////////

    protected <E> List<E> findCollectionByNativeQueryString(String queryString) {
        return findCollectionByNativeQueryString(queryString, new HashMap<String, Object>(), null, null);
    }

    protected <E> List<E> findCollectionByNativeQueryString(String queryString, Integer firstResult, Integer maxResults) {
        return findCollectionByNativeQueryString(queryString, new HashMap<String, Object>(), firstResult, maxResults);
    }

    protected <E> List<E> findCollectionByNativeQueryString(String queryString, String parameterName, Object parameterValue) {
        return findCollectionByNativeQueryString(queryString, parameterName, parameterValue, null, null);
    }

    protected <E> List<E> findCollectionByNativeQueryString(String queryString, Map<String, Object> parameters) {
        return findCollectionByNativeQueryString(queryString, parameters, null, null);
    }

    protected <E> List<E> findCollectionByNativeQueryString(String queryString, String parameterName, Object parameterValue, Integer firstResult, Integer maxResults) {
        List<E> result;
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(parameterName, parameterValue);
        result = findCollectionByNativeQueryString(queryString, parameters, firstResult, maxResults);
        return result;
    }

    protected <E> List<E> findCollectionByNativeQueryString(String queryString, Map<String, Object> parameters, Integer firstResult, Integer maxResults) {


        Query query = getEntityManager().createNativeQuery(queryString);

        JpaUtils.setQueryParameters(query, parameters);
        JpaUtils.setFirstAndMaxResults(query, firstResult, maxResults);

        @SuppressWarnings("unchecked")
        List<E> result = query.getResultList();
        return result;
    }


    protected int updateByNativeQueryString(String queryString) {
        final Query query = getEntityManager().createNativeQuery(queryString);
        return query.executeUpdate();
    }

    protected int updateByNativeQueryString(String queryString, String parameterName, Object parameterValue) {

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(parameterName, parameterValue);
        return updateByNativeQueryString(queryString, parameters);
    }


    protected int updateByNativeQueryString(String queryString, Map<String, Object> parameters) {
        final Query query = getEntityManager().createNativeQuery(queryString);
        JpaUtils.setQueryParameters(query, parameters);
        return query.executeUpdate();
    }


    ///Map<String,Object> parameters = new HashMap<String,Object>();
//	parameters.put(parameterName, parameterValue);

    private Object getSingleResult(Query query) {
        try {

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        }

    }


}
