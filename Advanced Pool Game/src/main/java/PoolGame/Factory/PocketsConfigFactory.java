package PoolGame.Factory;

import PoolGame.Config.Configurable;
import PoolGame.Config.PocketConfig;
import PoolGame.Config.PocketsConfig;

import java.util.List;


public class PocketsConfigFactory implements ConfigFactory{
    @Override
    public Configurable make(Object jSONObject) {
        return new PocketsConfig(jSONObject);
    }
}
