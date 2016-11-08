package de.dgroebner.edjson.db.mapper;

import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * Abstrakte Mapperklasse für die Datenbanktabellen
 * 
 * @author dgroebner
 * @param <T> Modellklasse
 */
public abstract class AbstractMapper<T> implements ResultSetMapper<T> {

    public static final String COLUMN_ID = "id";

    protected AbstractMapper() {
    }

}
