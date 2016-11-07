package de.dgroebner.edjson.model;

import org.json.JSONObject;

/**
 * Interface für Elite Dangerous Journal Events
 * 
 * @author dgroebner
 */
public interface EDJournalEventInterface {

    /**
     * Liefert das {@link JournalModel} für das übergebene {@link JSONObject}
     * 
     * @param json {@link JSONObject}
     * @return {@link JournalModel}
     */
    JournalModel getModel(final JSONObject json);
}
