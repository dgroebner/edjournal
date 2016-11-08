package de.dgroebner.edjson.model.action;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.model.JournalModel;

/**
 * Interface für Actions auf Journal Einträgen
 * 
 * @author dgroebner
 * @param <M> Modellimplementierung
 */
public interface JournalEventAction<M extends JournalModel> {

    /**
     * Schreibt einen Journaleintrg für das übergebene Modell und gibt den zugehörigen Datenbankeintrag zurück
     * 
     * @param dbi {@link DBI} Datenbankverbindungsobjekt
     * @param journalFileId int
     * @param model {@link JournalModel}
     * @return int id des Journaleintrags
     */
    int writeJournalToDatabase(DBI dbi, int journalFileId, M model);

    /**
     * Führt die Aktion auf dem übergebenen Modell aus
     * 
     * @param dbi {@link DBI} Datenbankverbindungsobjekt
     * @param journalId int ID des Journaleintrags
     * @param model {@link JournalModel}
     */
    void doActionOn(DBI dbi, int journalId, M model);

}
