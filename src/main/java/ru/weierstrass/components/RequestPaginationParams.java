package ru.weierstrass.components;

import java.util.ArrayList;
import java.util.Collection;

public class RequestPaginationParams {

    protected int chunk;
    protected int page;

    protected Collection<String> errors;

    public RequestPaginationParams( int chunk, int page ) {
        this.chunk = chunk;
        this.page = page;
        this.errors = new ArrayList<>();
        this.check();
    }

    public RequestPaginationParams( int chunk ) {
        this( chunk, 1 );
    }

    public Collection<String> getErrors() {
        return this.errors;
    }

    public int getOffset() {
        return ( this.page - 1 ) * this.getLimit();
    }

    public int getLimit() {
        return this.chunk;
    }

    public int getPage() {
        return this.page;
    }

    private void check() {
        if( this.chunk < 0 ) {
            this.errors.add( "Parameter `chunk` must be greater or equals 0." );
        }
        if( this.page <= 0 ) {
            this.errors.add( "Parameter `page` must be greater than 0." );
        }
    }

}
