package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Docked.Fields.ALLEGIANCE;
import static de.dgroebner.edjson.model.data.Docked.Fields.ECONOMY_LOCALISED;
import static de.dgroebner.edjson.model.data.Docked.Fields.FACTION;
import static de.dgroebner.edjson.model.data.Docked.Fields.FACTION_STATE;
import static de.dgroebner.edjson.model.data.Docked.Fields.GOVERNMENT_LOCALISED;
import static de.dgroebner.edjson.model.data.Docked.Fields.SECURITY_LOCALISED;
import static de.dgroebner.edjson.model.data.Docked.Fields.STAR_SYSTEM;
import static de.dgroebner.edjson.model.data.Docked.Fields.STATION_NAME;
import static de.dgroebner.edjson.model.data.Docked.Fields.STATION_TYPE;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Starport;
import de.dgroebner.edjson.db.Starsystem;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.db.model.DBStarport;
import de.dgroebner.edjson.db.model.DBStarsystem;
import de.dgroebner.edjson.model.data.Docked;

/**
 * Action für das JournalModell {@link Docked}
 * 
 * @author dgroebner
 */
public class DockedAction extends AbstractAction<Docked> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Docked model) {
        final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(FACTION),
                model.getValueAsString(FACTION_STATE));
        new Properties(dbi).save(ENTRIES.CURRENT_LOCAL_FACTION, faction.getId());

        final DBStarsystem savedStarSystem = new Starsystem(dbi).getByName(model.getValueAsString(STAR_SYSTEM));
        
        final DBStarport starPort = new DBStarport();
        starPort.setFactionId(faction.getId());
        starPort.setStarsystemId(savedStarSystem.getId());
        starPort.setName(model.getValueAsString(STATION_NAME));
        starPort.setType(model.getValueAsString(STATION_TYPE));
        starPort.setEconomy(model.getValueAsString(ECONOMY_LOCALISED));
        starPort.setGovernment(model.getValueAsString(GOVERNMENT_LOCALISED));
        starPort.setSecurity(model.getValueAsString(SECURITY_LOCALISED));
        starPort.setAllegiance(model.getValueAsString(ALLEGIANCE));
        new Starport(dbi).writeOrGetStarport(journalId, starPort);
    }

}
