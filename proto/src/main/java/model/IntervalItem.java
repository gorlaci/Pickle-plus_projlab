package model;

/**
 * Az IntervalItem egy absztrakt osztály
 * olyan tárgyak viselkedésének és működésének modellezésére,
 * amik egy adott ideig fejtik ki hatásukat. Az osztály
 * felelőssége tudni, hogy a tárgy aktiválva van-e és meddig érvényes.
 * Az Item absztrakt osztály leszármazottja.
 */
public abstract class IntervalItem extends Item {

    protected boolean activated;
    protected int timeRemaining;

    /**
     * Az IntervalItem osztály konstruktora.
     * Inicializál egy Item objektumot a megadott értékekkel.
     *
     * @param location a szoba, amiben a tárgy van
     * @param holder a személy, akinél a tárgy van
     * @param activated a tárgy aktiválva van-e
     * @param timeRemaining a hátralévő idő, amíg a tárgy aktív
     */
    public IntervalItem(Room location, Person holder, boolean activated, int timeRemaining){
        super(location, holder);
        this.activated = activated;
        this.timeRemaining = timeRemaining;
    }

    /**
     * A tárgy aktiválása.
     */
    @Override
    public void activate() {
        activated = true;
    }

    public boolean isActivated(){
        return activated;
    }

    public void setActivated(boolean activated){
        this.activated = activated;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining( int timeRemaining ){
        this.timeRemaining = timeRemaining;
    }
}
