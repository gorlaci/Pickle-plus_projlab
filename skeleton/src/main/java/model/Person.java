package model;

import testing.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Person implements ItemHandler, TimeSensitive{

    protected Room location;
    protected final List<Item> itemsInHand = new ArrayList<>();

    public void setLocation( Room room ){
        Logger.enter(this, "setLocation", List.of( room ));
        location = room;
        Logger.exit(this, "setLocation");
    }

    @Override
    public void addItem(Item item) {
        Logger.enter(this, "addItem", List.of(item));
        itemsInHand.add(item);
        //TODO
        Logger.exit(this, "addItem");
    }

    @Override
    public void removeItem(Item item) {

    }

    @Override
    public void timeElapsed(int time) {

    }

    public void enterRoom( Room roomTo ){
        Logger.enter( this, "enterRoom", List.of(roomTo) );

        if( Logger.askQuestion( "Is # stunned?", this ) ){
            Logger.exit( this, "enterRoom" );
            return;
        }

        boolean succes = location.movePerson( this, roomTo );

        if( succes ){
            for( Item item : itemsInHand ){
                item.setLocation( roomTo, this );
            }
        }

        Logger.exit( this, "enterRoom" );
    }

    public void dropItem( Item item ){

    }

    // Ellenőrizni kell majd, hogy nem önmaga
    public abstract void meet( Person person );

    public void stun(){
        Logger.enter( this, "stun");

        boolean saved = false;
        for( Item item : itemsInHand ){
            if(item.saveFromGas() ){
                saved = true;
                break;
            }
        }
        if( !saved ){
            for( Item item : itemsInHand ){
                dropItem( item );
            }
        }

        Logger.exit( this, "stun" );
    }

    public abstract void kill(Person killer);

    public abstract void slip();

    public abstract void pickedUpSlideRule( SlideRule slideRule );

    public abstract void greet( Person greeter );

}
