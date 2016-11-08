package de.dgroebner.edjson.model;

import org.json.JSONObject;

import de.dgroebner.edjson.model.action.JournalEventAction;

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

    /**
     * Liefert die Action zurück, die ausgeführt werden soll
     * 
     * @return {@link JournalEventAction}
     */
    JournalEventAction getAction();
}
