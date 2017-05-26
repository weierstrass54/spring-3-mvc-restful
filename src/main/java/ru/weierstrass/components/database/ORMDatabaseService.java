package ru.weierstrass.components.database;

import ru.weierstrass.models.commons.DatabaseModel;

import javax.sql.DataSource;
import java.util.List;

abstract public class ORMDatabaseService<T extends DatabaseModel> extends DatabaseService {

    /**
     * Constructor allows to use DataSource determined by child class
     */
    protected ORMDatabaseService( DataSource db ) {
        super( db );
    }

    /**
     * Mark data as related. Any list fetched by ORMDatabaseService<?> could be marked as related and
     * could be attached to DTO.
     */
    protected <E> Relation<E> relation( E relation ) {
        return new Relation<>( relation );
    }

    protected List<T> loadList( Class<T> clazz, String query, Object... params ) {
        return _load( rs -> {
            T entity = clazz.getDeclaredConstructor().newInstance();
            entity.mapping( rs );
            return entity;
        }, query, params );
    }

    protected T loadObject( Class<T> clazz, String query, Object... params ) {
        return loadList( clazz, query, params ).get( 0 );
    }

    /**
     * Internal class of related data
     * <br/><br/>
     * An idea is only ORMDatabaseService<?> class can create instances of this class.
     * That means that only a database data could be set at DTO.
     */
    public class Relation<E> {

        private E _relation;

        private Relation( E relation ) {
            _relation = relation;
        }

        public E get() {
            return _relation;
        }
    }

}
