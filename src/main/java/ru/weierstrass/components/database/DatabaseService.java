package ru.weierstrass.components.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.weierstrass.models.commons.DatabaseModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract public class DatabaseService<T extends DatabaseModel> {

    private static final Logger _log = LoggerFactory.getLogger( DatabaseService.class );

    private DataSource _db;

    /**
     * Constructor allows to use DataSource determined by child class
     * @param db
     */
    protected DatabaseService( DataSource db ) {
        _db = db;
    }

    /**
     * Internal class of related data
     * <br/><br/>
     * An idea is only DatabaseService<?> class can create instances of this class.
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

    /**
     * Mark data as related.
     * Any list fetched by DatabaseService<?> could be marked as related and could be attached to DTO.
     * @param relation
     * @param <E>
     * @return
     */
    protected <E> Relation<E> relation( E relation ) {
        return new Relation<>( relation );
    }

    protected List<T> loadList( Class<T> clazz, String query, Object... params ) throws SQLException, InstantiationException, IllegalAccessException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> result = new ArrayList<>();
        try {
            statement = _db.getConnection().prepareStatement( query );
            int i = 0;
            for( Object param : params ) {
                if( param == null ) {
                    statement.setNull( ++i, Types.NULL );
                }
                else {
                    statement.setObject( ++i, param );
                }
            }
            _log.debug( "Executing query {} with params {}", query, params );
            resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                T entity = clazz.newInstance();
                entity.mapping( resultSet );
                result.add( entity );
            }
            return result;
        }
        finally {
            _close( statement );
            _close( resultSet );
        }
    }

    protected T loadObject( Class<T> clazz, String query, Object... params ) throws SQLException, InstantiationException, IllegalAccessException {
        return loadList( clazz, query, params ).get( 0 );
    }

    private void _close( Statement statement ) {
        if( statement != null ) {
            try {
                statement.close();
            }
            catch( SQLException e ) {
                _log.error( "Cannot close statement by reason: {}", e.getLocalizedMessage(), e );
            }
        }
    }

    private void _close( ResultSet resultSet ) {
        if( resultSet != null ) {
            try {
                resultSet.close();
            }
            catch( SQLException e ) {
                _log.error( "Cannot close resultSet by reason: {}", e.getLocalizedMessage(), e );
            }
        }
    }

}
