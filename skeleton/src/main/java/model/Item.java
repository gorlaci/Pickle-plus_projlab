package model;

import testing.Logger;

import java.util.stream.Stream;

public abstract class Item implements TimeSensitive {

    protected Room location;
    protected Person holder;

    public void initLocation( Room room, Person person ){
        Stream<Object> args = Stream.of( room, person);
        Logger.enter( this, "initLocation", args.toList() );

        location = room;
        holder = person;

        Logger.exit( this, "initLocation" );
    }

    public abstract void activate();

    public abstract void meet( Person person );

    public void setLocation( Room room, Person person ){
        Stream<Object> args = Stream.of( room, person );
        Logger.enter( this, "setLocation", args.toList() );

        location = room;
        holder = person;

        Logger.exit( this, "setLocation" );
    }

    public abstract boolean saveFromDeath( Person killer );

    public abstract  boolean saveFromGas();
}
