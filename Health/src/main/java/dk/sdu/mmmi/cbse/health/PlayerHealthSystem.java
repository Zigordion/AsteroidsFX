package dk.sdu.mmmi.cbse.health;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.AEventListener;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IUIProcessingService;
import dk.sdu.mmmi.cbse.playersystem.Player;

public class PlayerHealthSystem implements IUIProcessingService, IGamePluginService, AEventListener {
    /*
       Common health module, which is simply responsible for keeping track of health and send signal when health is 0
       Player stat module, requires common health & player, would then listen? to the onhit and give common health module
       the players current health and to decrease health, the common health module would then return the new health stat.

       Would a player stat module be too generic?


       OnHit would require some form of callback/event trigger, perhaps add an interface in common, which would provide
       a method which returns null and takes an entity as a parameter.
       Alternatively an EventListener or a subset of Consumers would be used instead of an interface
       The base entity class could keep a list of all listeners, and call all of them when hit.
       Each subclass of entity would then override and call the base method first

       This approach would add a lot of fluff to the common module tho

       Alternatively, make health only a player attribute and add it as a variable on the player entity. Or make the player
       module require the health module. The latter is not ideal as the game should not be dependent upon external plugins
        */
    private static int playerHealth;
    private final int maxPlayerHealth = 3;
    private UiTextElement playerHealthTitle = new UiTextElement("Lives", 0,0,255,255,255);
    private UiTextElement playerHealthIndicator = new UiTextElement("",0,0,255,255,255);

    private static UiTextElement gameOverText;

    private static Entity player;
    private static GameData GameData;
    @Override
    public void processUI(GameData gameData, GameUi gameUi) {
        GameData = gameData;
        playerHealthTitle.setY(20);
        playerHealthTitle.setX(gameData.getDisplayWidth()-80);
        playerHealthIndicator.setY(40);
        playerHealthIndicator.setX(gameData.getDisplayWidth()-80);

        playerHealthIndicator.setText("❤".repeat(playerHealth));
        if(gameOverText!= null){
            gameOverText.setY(gameData.getDisplayHeight()/2.0);
            gameOverText.setX(gameData.getDisplayWidth()/2.0);
            gameOverText.setRGB(255,0,0);
            gameUi.addUiTextElement(gameOverText);
        }
        gameUi.addUiTextElement(playerHealthTitle);
        gameUi.addUiTextElement(playerHealthIndicator);
    }

    @Override
    public void start(GameData gameData, World world) {
        playerHealth = maxPlayerHealth;
        for (Entity entity : world.getEntities()) {

        }
    }

    @Override
    public void stop(GameData gameData, World world) {

    }


    @Override
    public void onTrigger(EventType eventType) {

    }

    @Override
    public void onTrigger(EventType eventType, Entity entity, Entity other) {
        if(eventType != EventType.COLLISION && !(entity instanceof Player) ){
            return;
        }
        playerHealth--;
        player.setX(GameData.getDisplayWidth()/2.0);
        player.setY(GameData.getDisplayHeight()/2.0);
        if(playerHealth<=0){
            player.setActive(false);
            gameOverText = new UiTextElement("Game Over",0,0,255,0,0);
        }
    }
}
