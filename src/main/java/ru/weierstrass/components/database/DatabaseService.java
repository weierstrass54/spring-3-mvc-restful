package ru.weierstrass.components.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

abstract public class DatabaseService {

    private static final Logger _log = LoggerFactory.getLogger( DatabaseService.class );

    private DataSource _db;

    @Autowired
    DatabaseService( DataSource db ) {
        _db = db;
    }

    protected <E> String array( List<E> params ) {
        List<String> stringParams = params.stream().map( String::valueOf ).collect( Collectors.toList() );
        return "ARRAY[" + String.join( ",", stringParams ) + "]";
    }

    protected Integer loadInt( String query, Object... params ) {
        return Integer.valueOf( loadString( query, params ) );
    }

    protected Double loadDouble( String query, Object... params ) {
        return Double.valueOf( loadString( query, params ) );
    }

    protected String loadString( String query, Object... params ) {
        return loadColumn( String.class, query, params ).get( 0 );
    }

    protected <E> List<E> loadColumn( Class<E> clazz, String query, Object... params ) {
        return _load( rs -> clazz.getDeclaredConstructor( String.class ).newInstance( rs.getString( 0 ) ),
            query, params
        );
    }

    protected <E> List<E> _load( ObjectFactory<E> factory, String query, Object... params ) {
        List<E> result = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int i = 0;
            statement = _db.getConnection().prepareStatement( query );
            for( Object param : params ) {
                statement.setObject( ++i, param );
            }
            _log.info( "Executing query {} with params {}", query, params );
            resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                result.add( factory.create( resultSet ) );
            }
            return result;
        }
        catch( SQLException e ) {
            _log.error( "SQL Error: {}", e.getLocalizedMessage(), e );
            return new ArrayList<>();
        }
        catch( InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e ) {
            _log.error( "Object mapping error: {}", e.getLocalizedMessage(), e );
            return new ArrayList<>();
        }
        finally {
            _close( statement );
            _close( resultSet );
        }
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

    protected interface ObjectFactory<E> {

        E create( ResultSet rs )
            throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException,
            InstantiationException;
    }

}
