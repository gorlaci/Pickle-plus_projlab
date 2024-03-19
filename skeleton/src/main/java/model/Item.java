package model;

import testing.Logger;

import java.util.List;

public abstract class Item implements TimeSensitive {

    protected Room location;
    protected Person holder;

    public abstract void activate();

    public abstract void meet( Person person );

    public void setLocation( Room room, Person person ){
        Logger.enter( this, "setLocation", List.of(room, person) );

        location = room;
        holder = person;

        Logger.exit( this, "setLocation" );
    }

    public abstract boolean saveFromDeath( Person killer );

    public abstract  boolean saveFromGas();
}
