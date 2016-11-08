package de.dgroebner.edjson.db.binder;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.Binder;

/**
 * Binder für den LocaleDateTime Datentyp
 * 
 * @author dgroebner
 */
public class LocalDateTimeBinder implements Binder<Bind, LocalDateTime> {

    @Override
    public void bind(SQLStatement<?> q, Bind bind, LocalDateTime localDateTime) {
        q.bind(bind.value(), Timestamp.valueOf(localDateTime));
    }

}
