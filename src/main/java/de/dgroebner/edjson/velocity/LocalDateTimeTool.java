package de.dgroebner.edjson.velocity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.velocity.tools.generic.DateTool;

/**
 * Erweitertung des {@link DateTool} zur Verarbeitung von {@link LocalDateTime}
 * 
 * @author dgroebner
 */
public class LocalDateTimeTool extends DateTool {

    @Override
    public String format(final Object obj) {
        if (obj instanceof LocalDateTime) {
            return ((LocalDateTime) obj).format(DateTimeFormatter.ofPattern("dd.mm.yyyy HH:mm:ss"));
        }
        return super.format(obj);
    }

}
