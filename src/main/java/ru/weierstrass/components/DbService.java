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
     * Внутренний класс связанности.
     * <br/><br/>
     * Идея в том, что только DbService<?> может создавать экземпляры этого класса.
     * А значит только данные из СУБД могут быть переданы в объекты-представления
     * @param <E>
     */
    public class RelatedList<E> extends ArrayList<E> {

        private RelatedList( List<E> list ) {
            super( list );
        }

    }

    /**
     * Метод, помечающий данные какого-либо другого DbService<?> как связанные.
     * <br/><br/>
     * Идея в том, что только класс-потомок может определить, что какие-то данные являются связанными.
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
