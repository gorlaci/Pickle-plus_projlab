package model;

import testing.Logger;

import java.util.ArrayList;
import java.util.List;

public class Room implements ItemHandler, TimeSensitive{

    private final List<Person> peopleInRoom = new ArrayList<>();
    private final List<Item> itemsInRoom = new ArrayList<>();

    private final List<Room> neighbours =new ArrayList<>();

    public Room(){
        Logger.create(this);
        Logger.exitCreate(this);
    }

    public void addNeighbour(Room room) {
        Logger.enter(this, "addNeighbour", List.of(room));
        neighbours.add(room);
        Logger.exit(this, "addNeighbour");
    }

    public void addPerson(Person person) {
        Logger.enter(this, "addPerson", List.of(person));
        peopleInRoom.add(person);
        Logger.exit(this, "addPerson");
    }

    @Override
    public void addItem(Item item) {
        Logger.enter(this, "addItem", List.of(item));
        itemsInRoom.add(item);
        //TODO
        Logger.exit(this, "addItem");
    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void timeElapsed(int time) {

    }

    public boolean acceptPerson( Person person ){
        Logger.enter( this, "acceptPerson", List.of( person ) );

        if( Logger.askQuestion( "Is # full?", this) ){
            Logger.exit( this, "acceptPerson", "false" );
            return false;
        }
        person.setLocation( this );

        if( Logger.askQuestion( "Is # gassed?", this ) ){
            person.stun();
        }

        for( Item item : itemsInRoom ){
            item.meet( person );
        }

        peopleInRoom.add( person );

        for( Person personInRoom : peopleInRoom ){
            if(personInRoom==person) continue;
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

        if( Logger.askQuestion( "Is curse active on #?", this ) ){
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
