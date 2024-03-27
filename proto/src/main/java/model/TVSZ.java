package model;

/**
 * A TVSZ osztály a TVSZ denevérbőrre írt pédányának működését
 * és viselkedését modellezi. Háromszor képes megvédeni a hallgatót az oktató támadásától.
 * Ha elhasználódik, akkor megsemmisül.
 * Az Item absztrakt osztály leszármazottja.
 */
public class TVSZ extends Item{
    /**
     * A TVSZ osztály konstruktora.
     * Létrehoz és inicializás egy TVSZ objektumot és ennek tényét logolja.
     */
    public TVSZ(){
    }

    /**
     * A TVSZ-t manuálisan nem lehet aktiválni, nem történik vele semmi.
     * A függvényhívást és visszatérést logolja.
     */
    @Override
    public void activate() {
    }

    /**
     * Nem csinál semmit. a földön nem tud megvédeni senkit.
     * A függvényhívást és visszatérést logolja.
     * @param person a személy, akivel találkozik a tárgy
     */
    @Override
    public void meet(Person person) {
    }

    /**
     * A tárgy megvédve a birtokosát és csökkenti a
     * usesRemaining-et 1-gyel. Amennyiben a változó értéke 0 lesz megsemmisül. Logikai
     * igazzal tér vissza. Azt, hogy még lehet-e használni, a felhasználótól kérdezi meg.
     * A függvényhívást és visszatérést logolja.
     * @param killer a támadó személy
     * @return {@code true} minden esetben
     */
    @Override
    public boolean saveFromDeath(Person killer) {
        return false;
    }

    /**
     * A tárgy nem nyújt védelmet a gáz ellen.
     * A függvényhívást és visszatérést logolja.
     * @return {@code false} minden esetben
     */
    @Override
    public boolean saveFromGas() {
        return false;
    }

    /**
     * Mivel egyszerhasználatos tárgy, így nem történik vele
     * semmi az idő múlásával.
     * A függvényhívást és visszatérést logolja.
     * @param time az eltelt idő
     */
    @Override
    public void timeElapsed(int time) {
    }
}
