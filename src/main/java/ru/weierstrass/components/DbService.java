package ru.weierstrass.components;

import org.springframework.beans.factory.annotation.Autowired;
import ru.weierstrass.models.commons.DbModel;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

abstract public class DbService<T extends DbModel> {

    @Autowired
    private DataSource _db;

    /**
     * Internal class of related data
     * <br/><br/>
     * An idea is only DbService<?> class can create instances of this class.
     * That means that only a database data could be set at DTO.
     */
    public class RelatedList<E> extends ArrayList<E> {
        private RelatedList( List<E> list ) {
            super( list );
        }
    }

    /**
     * Mark data as related.
     * Any list fetched by DbService<?> could be marked as related and could be attached to DTO.
     * @param related
     * @param <E>
     * @return
     */
    protected <E> RelatedList<E> asRelated( List<E> related ) {
        return new RelatedList<>( related );
    }

    protected List<T> loadList( Class<T> clazz, String query, Object... params ) throws Exception {
        List<T> result = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            int i = 0;
            st = _db.getConnection().prepareStatement( query );
            for( Object param : params ) {
                if( param == null ) {
                    st.setNull( ++i, Types.NULL );
                }
                else {
                    st.setObject( ++i, param );
                }
            }
            rs = st.executeQuery();
            while( rs.next() ) {
                T instance = clazz.newInstance();
                instance.bind( rs );
                result.add( instance );
            }
            return result;
        }
        finally {
            _close( st );
            _close( rs );
        }
    }

    protected T loadObject( Class<T> clazz, String query, Object... params ) throws Exception {
        return loadList( clazz, query, params ).get( 0 );
    }

    private void _close( AutoCloseable acl ) {
        if( acl != null ) {
            try {
                acl.close();
            }
            catch( Exception e ) {
                /* ignored */
            }
        }
    }

}
