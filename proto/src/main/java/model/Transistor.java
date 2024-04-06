package model;

import java.util.HashMap;

/**
 * A Transistor osztály modellezi a tranzisztorok műkösését és viselkedését a játékban.
 * Ahhoz hogy egy tranzisztort teleportálásra lehessen használni
 * kettő összepárosított tranzisztorra van szükség. A tranzisztor a párját
 * a pair tagváltozójában tartja nyilván. Ha két tranziszto összekapcsolódott, már nem kapcsolódhatnak szét.
 * Az Item absztrakt osztály leszármazottja.
 */
public class Transistor extends Item{

    private Transistor pair=null;

    private static final HashMap<Person,Transistor> pairingRequests = new HashMap<>();

    /**
     * A Transistor osztály konstruktora.
     * Létrehoz és inicializál egy Transistor objektumot és ennek tényét logolja.
     */
    public Transistor(){
    }

    /**
     * A paraméterben kapott tranzisztorral összepárosítja az objektumot.
     * A függvényhívást és visszatérést logolja.
     * @param transistor a párosítani kívánt tranzisztor
     */
    public void setPair(Transistor transistor) {
    }

    /**
     * Két tranzisztor összekapcsolását illetve azok használatát is lehetővé
     * tevő metódus. Amennyiben egy t1 tranzisztort úgy aktiválunk, hogy a hallgatónál még
     * nincs másik aktivált tranzisztor és a t1.pair NULL, akkor a t1 párosítási szándékát
     * eltároljuk. Amennyiben egy t2 tranzisztort úgy aktiválunk, hogy a t2.pair értéke
     * NULL, de a hallgató aktuálisan is rendelkezik egy már párosítási szándékra bejegyzett
     * t1 tranzisztorral, akkor a t1-et és a t2-t összekapcsolja, és kiveszi őket a párosítási
     * jegyzékből. Amennyiben egy t1 tranzisztort úgy aktiválunk, hogy a t1.pair értéke nem
     * NULL, akkor t1-et eldobja a játékos az eredeti szobájában és a t1 birtokosát átlépteti
     * az enterRoom() metódus segítségével, t2 tartózkodási helyébe, amit paraméterként ad
     * át a személy metódusába.
     * Azt, hogy párosítva van-e a tranzisztor, a felhasználótól kérdezi meg.
     */
    @Override
    public void activate() {
    }

    /**
     * Nem csinál semmit.
     * A függvényhívást és visszatérést logolja.
     * @param person a személy, akivel találkozik a tárgy
     */
    @Override
    public void meet(Person person) {
    }

    /**
     * Nem véd meg a kibukástól.
     * A függvényhívást és visszatérést logolja.
     * @param killer a támadó személy
     * @return {@code false} minden esetben
     */
    @Override
    public boolean saveFromDeath(Person killer) {
        return false;
    }

    /**
     * Nem véd meg a gáztól.
     * A függvényhívást és visszatérést logolja.
     * @return {@code false} minden esetben
     */
    @Override
    public boolean saveFromGas() {
        return false;
    }

    /**
     * Nem történik vele semmi.
     * A függvényhívást és visszatérést logolja.
     * @param time az eltelt idő
     */
    @Override
    public void timeElapsed(int time) {
    }
}
