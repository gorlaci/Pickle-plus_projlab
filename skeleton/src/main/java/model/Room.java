package model;

import testing.Logger;

import java.util.ArrayList;
import java.util.List;

public class Room implements ItemHandler, TimeSensitive{

    private final List<Person> peopleInRoom = new ArrayList<>();
    private final List<Item> itemsInRoom = new ArrayList<>();

    @Override
    public void addItem(Item item) {

    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void timeElapsed(int time) {

    }

    public boolean acceptPerson( Person person ){
        Logger.enter( this, "acceptPerson", List.of( person ) );

        if( Logger.askQuestion( "Is # full?", this ) ){
            Logger.exit( this, "acceptPerson", "false" );
            return false;
        }
        person.setLocation( this );

        if( Logger.askQuestion( "Is # gassed?" ) ){
            person.stun();
        }

        for( Item item : itemsInRoom ){
            item.meet( person );
        }

        peopleInRoom.add( person );

        for( Person personInRoom : peopleInRoom ){
            person.meet( personInRoom );
        }

        Logger.exit( this, "acceptPerson", "true" );
        return true;
    }

    public Room split(){
        return new Room();
    }

    public Room requestMerge( Room room ){
        return new Room();
    }

    private Room merge( Room room ){
        return new Room();
    }

    public void createGas(){

    }

    public void removePerson( Person person ){
        Logger.enter( this, "removePerson", List.of( person ) );

        peopleInRoom.remove( person );

        Logger.exit( this, "removePerson" );
    }

    public boolean movePerson( Person person, Room roomTo ){
        Logger.enter( this, "movePerson", List.of(person, roomTo ) );

        if( Logger.askQuestion( "Is curse active on #?" ) ){
            Logger.exit( this, "movePerson", "false" );
            return false;
        }
        boolean success = roomTo.acceptPerson( person );

        if( success ){
            removePerson( person );
        }
        Logger.exit( this, "movePerson", success ? "true" : "false" );
        return success;
    }
}
