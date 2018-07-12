package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * DB接続関係
 *
 */

public class DBUtil {
	private static final String PERSISTENCE_UNIT_NAME = "tasklist";
    private static EntityManagerFactory emf;

    //エンティティマネージャーを返す
    public static EntityManager createEntityManager() {
        return _getEntityManagerFactory().createEntityManager();
    }
    //エンティティマネージャーファクトリーを返す
    private static EntityManagerFactory _getEntityManagerFactory() {
        if(emf == null) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }

        return emf;
    }
}
