package PoolGame.Config;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;
/** A config class that will contain all the pockets configuration */

public class PocketsConfig implements Configurable{
    private List<PocketConfig> pockets;
    /**
     * Initialise the instance with the provided JSONArray of pocket
     * @param obj A JSONArray containing all the pocket configuration
     */
    public PocketsConfig(Object obj) {
        this.parseJSON(obj);
    }

    /**
     * Initialise the instance with the provided pocket configs
     * @param pocketList A list of pocket configs
     */
    public PocketsConfig(List<PocketConfig> pocketList) {
        this.init(pocketList);
    }

    private void init(List<PocketConfig> pocketList) {
        this.pockets = pocketList;

    }

    public Configurable parseJSON(Object obj) {
        List<PocketConfig> list = new ArrayList<>();
        JSONArray json = (JSONArray) obj;

        for (Object p : json) {
            list.add(new PocketConfig(p));
        }
        this.init(list);
        return this;
    }

    /**
     * Get the pocket list as defined in the config.
     * @return A list of pockets
     */
    public List<PocketConfig> getPocketConfigs() {

        return this.pockets;
    }
}
