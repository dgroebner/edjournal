package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Docked.Fields.FACTION_STATE;
import static de.dgroebner.edjson.model.data.Docked.Fields.STAR_SYSTEM;
import static de.dgroebner.edjson.model.data.Docked.Fields.STATION_ALLEGIANCE;
import static de.dgroebner.edjson.model.data.Docked.Fields.STATION_ECONOMY_LOCALISED;
import static de.dgroebner.edjson.model.data.Docked.Fields.STATION_FACTION;
import static de.dgroebner.edjson.model.data.Docked.Fields.STATION_GOVERNMENT_LOCALISED;
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
        final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(STATION_FACTION),
                model.getValueAsString(FACTION_STATE));
        new Properties(dbi).save(ENTRIES.CURRENT_LOCAL_FACTION, faction.getId());

        final DBStarsystem savedStarSystem = new Starsystem(dbi).getByName(model.getValueAsString(STAR_SYSTEM));
        
        /* @formatter:off */
        final DBStarport starPort = DBStarport.builder()
                .factionId(faction.getId())
                .starsystemId(savedStarSystem.getId())
                .name(model.getValueAsString(STATION_NAME))
                .type(model.getValueAsString(STATION_TYPE))
                .economy(model.getValueAsString(STATION_ECONOMY_LOCALISED))
                .government(model.getValueAsString(STATION_GOVERNMENT_LOCALISED))
                .allegiance(model.getValueAsString(STATION_ALLEGIANCE))
                .build();
        /* @formatter:on */

        new Starport(dbi).writeOrGetStarport(journalId, starPort);
    }

}
