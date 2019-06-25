package it.wcc.postcode.dao;

import javax.persistence.Query;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

public class JpaUtils implements Serializable {

     
	private static final long serialVersionUID = -1546080761585757745L;

	public static void setQueryParameters(Query query, Map<String, Object> parameters) {

        if (parameters != null) {
            Iterator<String> iterator = parameters.keySet().iterator();
            while (iterator.hasNext()) {
                Object key = iterator.next();

                if (key instanceof Integer) {
                    query.setParameter(((Integer) key).intValue(), parameters.get(key));
                } else {
                    query.setParameter((String) key, parameters.get(key));
                }
            }

        }

    }

    public static void setFirstAndMaxResults(Query query, Integer firstResult, Integer maxResults) {
        if (firstResult != null) {
            query.setFirstResult(firstResult.intValue());
        }
        if (maxResults != null) {
            query.setMaxResults(maxResults.intValue());
        }
    }

}
