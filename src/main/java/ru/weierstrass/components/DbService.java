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

    public List<T> loadList( Class<T> clazz, String query, Object... params ) throws Exception {
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

    //loadString
    //loadInt

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
