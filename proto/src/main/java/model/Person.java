package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Person absztrakt osztály a játék karaktereinek működéséért felelős, főbb adatait tárolja. Tárolja a személy tartózkodási
 * szobáját és tárgyait.
 * Az osztály megvalósítja az ItemHandler és TimeSensitive interfészeket.
 */
public abstract class Person implements ItemHandler, TimeSensitive{

    protected Room location;
    protected final List<Item> itemsInHand = new ArrayList<>();

    /**
     * A személy tartózkodási helyének beállítása.
     * A függvényhívást és visszatérést logolja.
     * @param room a tartózkodási hely
     */
    public void setLocation( Room room ){
    }

    /**
     * Az inicializásás során a személy kezébe ad egy tárgyat.
     * A függvényhívást és visszatérést logolja.
     * @param item a tárgy, amit a kezébe ad
     */
    public void initItem( Item item ) {
    }

    /**
     * Egy tárgy felvétele, amennyiben a személy képes rá (nincs elkábulva és van nála hely).
     * Azt, hogy fel tudja-e venni, a felhasználótól kérdezi meg.
     * A függvényhívást és visszatérést logolja.
     * @param item a felvenni kívánt tárgy
     */
    @Override
    public void addItem(Item item) {
    }

    /**
     * Egy tárgy törlése a személy kezéből.
     * A függvényhívást és visszatérést logolja.
     * @param item a törölni kívánt tárgy
     */
    @Override
    public void removeItem(Item item) {
    }

    /**
     * Továbbítja az eltelt időt (time) a nála lévő tárgyaknak.
     * A függvényhívást és visszatérést logolja.
     * @param time az eltelt idő
     */
    @Override
    public void timeElapsed(int time) {
    }

    /**
     * A személy mozgását végrehajtó metódus. Ha a személy nincs elkábulva, továbbítja a jelenlegi szobájának az átlépés igényét. A két szoba
     * felelőssége, hogy a személyt beengedi-e. Amennyiben sikeresen átlép a másik
     * szobába, frissíti a saját és a tárgyainak tartózkodási helyét is.
     * A felhasználótól kérdezi meg, hogy el van a kábilva személy
     * A függvényhívást és visszatérést logolja.
     * @param roomTo az a szoba, ahova át akar lépni
     */
    public void enterRoom( Room roomTo ){
    }

    /**
     * Egy tárgy eldobása. A személy kezéből eltávolítja a tárgyat éd hozzáadja a szobához.
     * A függvényhívást és visszatérést logolja.
     * @param item az eldobni kívánt tárgy
     */
    public void dropItem( Item item ){
    }

    /**
     * Egy személlyel való találkozást kezeli le.
     * @param person a személy, akivel találkozik
     */
    public abstract void meet( Person person );

    /**
     * A személy mérgező gáz általi megbénulását hajtja végre. Először is
     * végigkérdezi a tárgyait, hogy képesek-e megóvni őt a mérgező gáz általi veszélytől.
     * Ha legalább egy megvédi, akkor nem történik a személlyel semmi. Ha egy sem védi
     * meg, akkor eldobja az összes tárgyát és adott ideig elkábul.
     * A függvényhívást és visszatérést logolja.
     */
    public void stun(){
    }

    /**
     * A személy kibuktatását/halálát hajtja végre
     * @param killer az a személy, aki ki akarja buktatni
     */
    public abstract void kill(Person killer);

    /**
     * A személy táblatörlő rongy általi megbénulását hajtja végre.
     */
    public abstract void slip();

    /**
     * A személy felvette a Logarlécet.
     * @param slideRule a logarléc
     */
    public abstract void pickedUpSlideRule( SlideRule slideRule );

    /**
     * A személynek köszönt a másik személy.
     * @param greeter a másik személy
     */
    public abstract void greet( Person greeter );

}
