package model;

public abstract class Person implements ItemHandler, TimeSensitive{
    @Override
    public void addItem(Item item) {

    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void timeElapsed(int time) {

    }

    public void enterRoom( Room roomTo ){

    }

    public void dropItem( Item item ){

    }

    public abstract void meet( Person person );

    public void stun(){

    }

    public abstract void kill(Person killer);

    public abstract void slip();

    public abstract void pickedUpSlideRule( SlideRule slideRule );

    public abstract void greet( Person greeter );
}
