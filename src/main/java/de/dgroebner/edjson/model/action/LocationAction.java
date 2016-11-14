package de.dgroebner.edjson.model.action;

import static de.dgroebner.edjson.model.data.Location.Fields.ALLEGIANCE;
import static de.dgroebner.edjson.model.data.Location.Fields.ECONOMY_LOCALISED;
import static de.dgroebner.edjson.model.data.Location.Fields.FACTION;
import static de.dgroebner.edjson.model.data.Location.Fields.FACTION_STATE;
import static de.dgroebner.edjson.model.data.Location.Fields.GOVERNMENT_LOCALISED;
import static de.dgroebner.edjson.model.data.Location.Fields.SECURITY_LOCALISED;
import static de.dgroebner.edjson.model.data.Location.Fields.STAR_POS;
import static de.dgroebner.edjson.model.data.Location.Fields.STAR_SYSTEM;

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
        final DBFaction faction = new Faction(dbi).writeOrGetFraction(journalId, model.getValueAsString(FACTION),
                model.getValueAsString(FACTION_STATE));
        final DBStarsystem starSystemForSave = new DBStarsystem();
        starSystemForSave.setFactionId(faction.getId());
        starSystemForSave.setName(model.getValueAsString(STAR_SYSTEM));
        starSystemForSave.setEconomy(model.getValueAsString(ECONOMY_LOCALISED));
        starSystemForSave.setGovernment(model.getValueAsString(GOVERNMENT_LOCALISED));
        starSystemForSave.setSecurity(model.getValueAsString(SECURITY_LOCALISED));
        starSystemForSave.setAllegiance(model.getValueAsString(ALLEGIANCE));
        starSystemForSave.setStarpos(model.getValueAsCoordinates(STAR_POS).getCoordString());
        final DBStarsystem dbStarSystem = new Starsystem(dbi).writeOrGetStarsystem(journalId, starSystemForSave);
        new Properties(dbi).save(ENTRIES.CURRENT_STAR_SYSTEM, dbStarSystem.getId());
    }

}