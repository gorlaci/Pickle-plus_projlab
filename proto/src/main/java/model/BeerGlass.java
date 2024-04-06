package model;

/**
 * Az BeerGlass osztály a szent söröspoharak működését és viselkedését modellezi.
 * A söröspohár védelmet nyújt egy  adott ideig az oktatókkal szemben, ha aktiválva van.
 * Ha a hatása elmúlik, akkor eltűnik.
 * Az osztály az IntervalItem absztakt osztályból származik.
 */
public class BeerGlass extends IntervalItem{

    /**
     * A BeerGlass osztály konstruktora.
     * Létrehoz és inicializál egy BeerGlass objektumot és ennek tényét logolja.
     */
    public BeerGlass(){
    }

    /**
     * Egy személlyel való találkozást kezeli, ha a földön van.
     * Nincsen semmilyen hatása, mert ha a földön van nem tud senkit megvédeni.
     * A függvényhívást és visszatérést logolja.
     * @param person Az a személy, akivel találkozik
     */
    @Override
    public void meet(Person person) {
    }

    /**
     * Amennyiben aktiválva van a söröspohár, logikai igazzal tér vissza, megvédve a birtokosát. Egyéb esetben logikai hamissal tér vissza.
     * A függvényhívótól kérdezi meg, hogy a söröspohár aktiválva van-e.
     * A függvényhívást és visszatérést logolja.
     * @param killer az a személy, aki megtámadta a BeerGlass objektum tulajdonosát
     * @return {@code true} ha aktiválva van, {@code false} egyébként
     */
    @Override
    public boolean saveFromDeath(Person killer) {
        return false;
    }

    /**
     * A BeerGlass nem nyújt védelmet a gáz ellen.
     * A függvényhívást és visszatérést logolja.
     * @return {@code false} minden esetben
     */
    @Override
    public boolean saveFromGas() {
        return false;
    }

    /**
     * Ha aktiválva van a tárgy, akkor a kapott paraméterrel csökkenti az objektum timeRemaining tagváltozóját.
     * A felhasználótól kérdezi meg, hogy a timeRemaining elérte-e a 0 értéket.
     * Ha a timeRemaining elérte a 0-t, akkor kezdeményezi aktuális birtokosánál a tárgy megsemmisítését.
     * A függvényhívást és visszatérést logolja.
     * @param time az eltelt idő
     */
    @Override
    public void timeElapsed(int time) {
    }
}
