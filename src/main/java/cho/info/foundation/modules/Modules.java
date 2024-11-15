package cho.info.foundation.modules;

import cho.info.foundation.Foundation;
import cho.info.foundation.modules.playerlogging.PlayerLoggingManager;
import cho.info.foundation.modules.playertags.CustomPlayerData;

public class Modules {

    public Foundation foundation;

    public Modules(Foundation foundation) {
        this.foundation = foundation;
    }


    public PlayerLoggingManager getPlayerLoggingManager() {
        return new PlayerLoggingManager(foundation);
    };

    public CustomPlayerData getCustomPlayerData() {
        return new CustomPlayerData(foundation.getDataFolder());
    }


}
