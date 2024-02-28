package dk.sdu.mmmi.cbse.health;


public class Main {

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
}