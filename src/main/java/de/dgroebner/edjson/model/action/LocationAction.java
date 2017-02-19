package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Location.Fields.FACTION_STATE;
import static de.dgroebner.edjson.model.data.Location.Fields.STAR_POS;
import static de.dgroebner.edjson.model.data.Location.Fields.STAR_SYSTEM;
import static de.dgroebner.edjson.model.data.Location.Fields.SYSTEM_ALLEGIANCE;
import static de.dgroebner.edjson.model.data.Location.Fields.SYSTEM_ECONOMY_LOCALISED;
import static de.dgroebner.edjson.model.data.Location.Fields.SYSTEM_FACTION;
import static de.dgroebner.edjson.model.data.Location.Fields.SYSTEM_GOVERNMENT_LOCALISED;
import static de.dgroebner.edjson.model.data.Location.Fields.SYSTEM_SECURITY_LOCALISED;

import org.skife.jdbi.v2.DBI;

import de.dgroebner.edjson.db.Faction;
import de.dgroebner.edjson.db.Properties;
import de.dgroebner.edjson.db.Properties.ENTRIES;
import de.dgroebner.edjson.db.Starsystem;
import de.dgroebner.edjson.db.model.DBFaction;
import de.dgroebner.edjson.db.model.DBStarsystem;
import de.dgroebner.edjson.model.data.Location;

/**
 * Action für das JournalModell {@link Location}
 * 
 * @author dgroebner
 */
public class LocationAction extends AbstractAction<Location> {

    @Override
    public void doActionOn(final DBI dbi, final int journalId, final Location model) {
        final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(SYSTEM_FACTION),
                model.getValueAsString(FACTION_STATE));

        /* @formatter:off */
        final DBStarsystem starSystemForSave = DBStarsystem.builder()
                .factionId(faction.getId())
                .name(model.getValueAsString(STAR_SYSTEM))
                .economy(model.getValueAsString(SYSTEM_ECONOMY_LOCALISED))
                .government(model.getValueAsString(SYSTEM_GOVERNMENT_LOCALISED))
                .security(model.getValueAsString(SYSTEM_SECURITY_LOCALISED))
                .allegiance(model.getValueAsString(SYSTEM_ALLEGIANCE))
                .starpos(model.getValueAsCoordinates(STAR_POS).getCoordString())
                .build();
        /* @formatter:on */

        final DBStarsystem dbStarSystem = new Starsystem(dbi).writeOrGetStarsystem(journalId, starSystemForSave);
        new Properties(dbi).save(ENTRIES.CURRENT_STAR_SYSTEM, dbStarSystem.getId());
    }

}
