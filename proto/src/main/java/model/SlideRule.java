package model;

/**
 * A SlideRule osztály a Logarléc működését és viselkedését modellezi.
 * A Logarléc a hallgatő győzelmének kulcsa. Amint egy hallgató felvétel után aktiválódik, a hallgatók győznek.
 * Az Item absztrakt osztály leszármazottja.
 */
public class SlideRule extends Item{

    /**
     * A SlideRule osztály kontruktora.
     * Létrehoz és inicializál egy SlideRule objektumot és ennek tényét logolja.
     */
    public SlideRule(){
    }

    /**
     * Nem történik semmi.
     * A függvényhívást és visszatérést logolja.
     */
    @Override
    public void activate() {
    }

    /**
     * Nem csinál semmit, mert ha földön van nincs funkciója.
     * A függvényhívást és visszatérést logolja.
     * @param person a személy, akivel találkozik a tárgy
     */
    @Override
    public void meet(Person person) {
    }

    /**
     * A tárgy nem nyújt védelmet a kibukás ellen.
     * @param killer a támadó személy
     * @return {@code false} minden esetben
     */
    @Override
    public boolean saveFromDeath(Person killer) {
        return false;
    }

    /**
     * A tárgy nem nyújt védelmet a gáz ellen.
     * @return {@code false} minden esetben
     */
    @Override
    public boolean saveFromGas() {
        return false;
    }

    /**
     * A tárgy tartózkodási helyének és birtokosának beállítása.
     * @param room a szoba ahova a tárgyat elhelyezi
     * @param person a személy akinek a kezébe adja, {@code null} esetén nem adja senki kezébe
     */
    @Override
    public void setLocation( Room room, Person person ){
    }

    /**
     * Mivel egyszerhasználatos tárgy, így nem történik vele semmi az idő múlásával.
     * @param time az eltelt idő
     */
    @Override
    public void timeElapsed(int time) {
    }
}
