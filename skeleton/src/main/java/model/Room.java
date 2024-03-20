package model;

import testing.Logger;

import java.util.ArrayList;
import java.util.List;

public class Room implements ItemHandler, TimeSensitive {

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

    public void initItem(Item item) {
        Logger.enter(this, "initItem", List.of(item));
        itemsInRoom.add(item);
        Logger.exit(this, "initItem");
    }

    @Override
    public void addItem(Item item) {
        Logger.enter(this, "addItem", List.of(item));
        itemsInRoom.add(item);
        item.setLocation( this, null );
        Logger.exit(this, "addItem");
    }

    @Override
    public void removeItem(Item item) {
        Logger.enter( this, "removeItem", List.of(item));

        itemsInRoom.remove( item );

        Logger.exit( this, "removeItem" );
    }

    @Override
    public void timeElapsed(int time) {
        Logger.enter( this, "timeElapsed", List.of( time ) );

        List<Item> itemsInRoomCopy = new ArrayList<>(itemsInRoom);
        for( Item item : itemsInRoomCopy ){
            item.timeElapsed( time );
        }
        for( Person person : peopleInRoom ){
            person.timeElapsed( time );
        }
        if( Logger.askQuestion( "Is # gassed?", this ) ){
            for( Person person : peopleInRoom ){
                person.stun();
            }
        }
        for( Person person : peopleInRoom ){
            for( Item item : itemsInRoom ) {
                item.meet( person );
            }
        }
        List<Person> peopleInRoomCopy = new ArrayList<>(peopleInRoom);
        for( int i = 0 ; i < peopleInRoomCopy.size() ; i++ ){
            for( int j = i + 1 ; j < peopleInRoomCopy.size() ; j++ ){
                peopleInRoomCopy.get( i ).meet( peopleInRoomCopy.get( j ) );
            }
        }

        Logger.exit( this, "timeElapsed" );
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
        Logger.enter( this, "split" );

        if( Logger.askQuestion( "Is anyone in #?", this ) ){
            Logger.exit( this, "split", "null");
            return null;
        }
        Room r2 = new Room();
        Logger.register( r2, "r2" );

        int desiredSize = itemsInRoom.size() / 2;

        while( itemsInRoom.size() > desiredSize ){
            r2.addItem( itemsInRoom.get( 0 ) );
            itemsInRoom.remove( 0 );
        }

        Logger.exit( this, "split", "r2" );
        return r2;
    }

    public Room requestMerge( Room room2 ){
        Logger.enter( this, "requestMerge", List.of(room2));
        if( Logger.askQuestion( "Is anyone in #?", this ) ){
            Logger.exit( this, "requestMerge", "null");
            return null;
        }
        Room r3 = room2.merge(this);
        if(r3==null) {
            Logger.exit( this, "requestMerge", "null");
            return null;
        }
        for(Item item: this.itemsInRoom) {
            r3.addItem(item);
        }
        Logger.exit( this, "requestMerge", "r3");
        return r3;
    }

    private Room merge( Room room1 ){
        Logger.enter( this, "merge", List.of(room1));
        if( Logger.askQuestion( "Is anyone in #?", this ) ){
            Logger.exit( this, "merge", "null");
            return null;
        }
        Room r3 = new Room();
        Logger.register(r3, "r3");
        for(Item item: itemsInRoom) {
            r3.addItem(item);
        }
        Logger.exit( this, "merge", "r3");
        return r3;
    }

    public void createGas(){
        Logger.enter( this, "createGas" );
        Logger.exit( this, "createGas" );
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
