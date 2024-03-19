package model;

public class Room implements ItemHandler, TimeSensitive{

    @Override
    public void addItem(Item i) {

    }

    @Override
    public void removeItem(Item i) {

    }

    @Override
    public void timeElapsed(int t) {

    }

    public boolean acceptPerson( Person p ){
        return true;
    }

    public Room split(){
        return new Room();
    }

    public Room requestMerge( Room r ){
        return new Room();
    }

    private Room merge( Room r ){
        return new Room();
    }

    public void createGas(){

    }

    public void removePerson( Person p ){

    }

    public boolean movePerson( Person p, Room to ){
        return true;
    }
}
