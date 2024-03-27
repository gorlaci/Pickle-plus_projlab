package model;

/**
 * A Teacher osztály az oktatók működését és viselkedését modellezi.
 * A Person absztrakt osztály leszármazottja.
 */
public class Teacher extends Person{

    /**
     * A Teacher osztály konstruktora.
     * Létrehoz és inicializál egy Teacher objektumot és logolja ennek tényét
     */
    public Teacher(){
    }

    /**
     * Egy személlyel való találkozást kezeli le. A személyt, akivel találkozik, megpróbálja megölni, amennyiben nincs lebénülva.
     * A függvényhívást és visszatérést logolja.
     * @param person a személy, akivel találkozik
     */
    @Override
    public void meet(Person person) {
    }

    /**
     * Nem történik semmi, mert az oktató nem halhat meg.
     * A függvényhívást és visszatérést logolja.
     * @param killer az a személy, aki ki akarja buktatni
     */
    @Override
    public void kill(Person killer) {
    }

    /**
     * Az oktató lebénul a táblatörlő rongy hatására.
     * A függvényhívást és visszatérést logolja.
     */
    @Override
    public void slip() {
    }

    /**
     * Az oktató kisobja a logarlécet.
     * A függvényhívást és visszatérést logolja.
     * @param slideRule a logarléc
     */
    @Override
    public void pickedUpSlideRule(SlideRule slideRule) {
    }

    /**
     * A közönést kezeli le. Az oktató megrpóbálja meggyilkolni azt a személyt, aki köszönt neki.
     * A függvényhívást és visszatérést logolja.
     * @param greeter a másik személy
     */
    @Override
    public void greet(Person greeter) {
    }
}
