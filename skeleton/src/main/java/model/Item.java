package model;

public abstract class Item implements TimeSensitive {

    public abstract void activate();

    public abstract void meet( Person person );

    public void setLocation( Room room, Person person ){

    }

    public abstract boolean saveFromDeath( Person killer );

    public abstract  boolean saveFromGas();
}
