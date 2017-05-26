package ru.weierstrass.models.catalog.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.weierstrass.components.database.ORMDatabaseService;
import ru.weierstrass.models.Storage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DetailEta extends Eta {

    @JsonIgnore
    protected int storageId;
    protected int quantity;

    protected Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage( ORMDatabaseService<DetailEta>.Relation<Storage> storage ) {
        this.storage = storage.get();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public void mapping( ResultSet rs ) throws SQLException {
        super.mapping( rs );
        storageId = rs.getInt( "storageId" );
        quantity = rs.getInt( "quantity" );
    }

}
