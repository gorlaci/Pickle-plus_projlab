package model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Room osztály a szoba működését és viselkedését modellezi.
 * Számontartja a szobában lévő személyeket és tárgyakat, illetve a szomszédait.
 * Az ItemHandler és TimeSensitive interfészeket implementálja.
 */
public class Room implements ItemHandler, TimeSensitive {

    private int capacity;
    private boolean gas;
    private boolean cursed;
    private boolean curseActive;
    private int stickiness;

    private final List<Person> peopleInRoom = new ArrayList<>();
    private final List<Item> itemsInRoom = new ArrayList<>();

    private final List<Room> neighbours =new ArrayList<>();

    /**
     * A Room osztály konstruktora.
     * Létrehoz és inicializás egy Room objektumot és ennek tényét logolja.
     */
    public Room(int capacity, boolean gas, boolean cursed, int stickiness){
        this.capacity = capacity;
        this.gas = gas;
        this.cursed = cursed;
        this.stickiness = stickiness;
        this.curseActive = false;
    }

    /**
     * A szoba szomszédai közé felvesz egy szobát.
     * A függvényhívást és visszatérést logolja.
     * @param room a szomszédok listájába felvenni kívánt szoba
     */
    public void addNeighbour(Room room) {
    }

    /**
     * A szobában elhelyez egy személyt.
     * A függvényhívást és visszatérést logolja.
     * @param person az elhelyezni kívánt személy
     */
    public void addPerson(Person person) {
    }

    /**
     * A szobában elhelyez egy tárgyat.
     * A függvényhívást és visszatérést logolja.
     * @param item az elhelyezni kívánt tárgy
     */
    public void initItem(Item item) {
    }

    /**
     * Egy tárgyat elhelyez a szobában és annak tartózkodási helyét is beállítja.
     * A függvényhívást és visszatérést logolja.
     * @param item a hozzáadni kívánt tárgy
     */
    @Override
    public void addItem(Item item) {
    }

    /**
     * Egy tárgy törlése a szobából.
     * A függvényhívást és visszatérést logolja.
     * @param item a törölni kívánt tárgy
     */
    @Override
    public void removeItem(Item item) {
    }

    /**
     * Továbbítja az eltelt időt a benne lévő személyeknek és tárgyaknak.
     * A szoba belsőmechanizmusában is részt vesz pl.: egy elátkozott szoba ajtajainak el(ő)tűnése az eltelt idő alapján.
     * A továbbra is szobában tartózkodó tárgyakat összetalálkoztatja minden személlyel és
     * minden személyt kölcsönösen összetalálkoztat egymással.
     * Illetve megkérdezi a felhasználótól, hogy a szoba gázos-e. Ha igen, akkor a szobában tartózkodó
     * személyek megbénítását kezdi.
     * A függvényhívást és visszatérést logolja.
     * @param time az eltelt idő
     */
    @Override
    public void timeElapsed(int time) {
    }

    /**
     * A paraméterként kapott személyt engedi be a szobába.
     * Amennyiben a szoba kapacitása kimerült nem engedi be a személyt. A visszatérési
     * értéke a beengedés sikeressége. Ha beengedi a személyt, felel az új személy és a
     * szobában tartózkodó személyek kölcsönös találkozásáért, illetve az új személy és
     * szobában levő tárgyak találkozásáért. Ha a szoba mérgezett, felel a belépő játékos
     * elkábításáért.
     * Azt, hogy a szoba tele van-e, és hogy el van-e gázosítva a felhasználótól megkérdezi.
     * A függvényhívást és visszatérést logolja.
     * @param person a belépő személy
     * @return {@code true} ha sikeresen belépett a szobába {@code false} egyébként
     */
    public boolean acceptPerson( Person person ){
        return false;
    }

    /**
     * Ezzel a függvénnyel kerül indítványozásra a meghívott szobánál, hogy
     * kettéosztódjon. Amennyiben tartózkodik benne személy NULL-t ad vissza. Különben
     * pedig létrehoz egy új szobát, aminek neighbours-ei a saját neighbours-ei fele és saját
     * maga, az itemsInRoom a saját itemsInRoom-jainak szintén fele lesz. Az átadott
     * szomszédokat és tárgyakat saját magából eltávolítja, az új szobát beállítja saját
     * szomszédjának is. Az átadott tárgyak location-jét frissíti setLocation()-nel. A gas és
     * cursed értékeiből, ha mindkettő logikai igaz volt, akkor az erediben a gas hamis lesz,
     * és az újban lesz a gas igaz. Ha a két értékből nem volt mindkettő logikai igaz, akkor az új
     * szoba mindkét értéke hamis lesz. Az új szoba capacity-je a régiével egyezik meg.
     * A felhasználótól kérdezi meg, hogy vannak-e a szobában.
     * A függvényhívást és visszatérést logolja.
     * @return az új {@code Room} objektum, ha sikeres volt a szétválás, {@code null} egyébként
     */
    public Room split(){
        return null;
    }

    public Room requestMerge( Room room2 ){
        return null;
    }

    /**
     * Két szoba egyesítését elvégző belső függvény.
     * Ha nincs benne egy személy sem, létrehoz egy új szobát.
     * Az új szoba szomszédjait beállítja a sajátjai alapján és az összes benne lévő tárgyat áthelyezi az új szobába.
     * Azt, hogy van-e a szobában személy, a felhasználótól kérdezi meg.
     * A függvényhívást és visszatérést logolja.
     * @param room1 az a szoba, ami össze akar olvasódni
     * @return {@code Room} ha sikeres az összeolvadás, {@code null} egyébként
     */
    private Room merge( Room room1 ){
        return null;
    }

    /**
     * Mérgező gázzal tölti fel a szobát.
     * A függvényhívást és visszatérést logolja.
     */
    public void createGas(){
    }

    /**
     * Egy személy eltávolítása a szobából.
     * A függvényhívást és visszatérést logolja.
     * @param person az eltávolítani kívánt személy
     */
    public void removePerson( Person person ){
    }

    /**
     *Ha a szoba jelenleg nincs aktívan elátkozva, a
     * paraméterként kapott személy a paraméterként kapott szobába léptetésének igényét továbbítja. A kapott szoba
     * értesíti ennek sikerességéről és ő is ezzel tér vissza. Ha igazzal tér vissza, akkor
     * eltávolítja a személyt önmagából. Ha a szoba aktívan elátkozott, rögtön hamissal tér
     * vissza.
     * Azt, hogy a szoba el van-e átkozva a felhasználótól kérdezi meg.
     * A függvényhívást és visszatérést logolja.
     * @param person a személy, aki át akar lépni
     * @param roomTo a szoba, ahova át szeretne lépni
     * @return {@code true} ha sikeresen átlépett, {@code false} egyébként
     */
    public boolean movePerson( Person person, Room roomTo ){
        return false;
    }

    public boolean isCursed() {
        return cursed;
    }

    public void setCursed(boolean cursed) {
        this.cursed = cursed;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isGas() {
        return gas;
    }

    public void setGas(boolean gas) {
        this.gas = gas;
    }

    public boolean isCurseActive() {
        return curseActive;
    }

    public void setCurseActive(boolean curseActive) {
        this.curseActive = curseActive;
    }

    public int getStickiness() {
        return stickiness;
    }

    public void setStickiness(int stickiness) {
        this.stickiness = stickiness;
    }

    public List<Person> getPeopleInRoom() {
        return peopleInRoom;
    }

    public List<Item> getItemsInRoom() {
        return itemsInRoom;
    }

    public List<Room> getNeighbours() {
        return neighbours;
    }
}
