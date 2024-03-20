package model;

import testing.Logger;

import java.util.List;
import java.util.stream.Stream;

public class SlideRule extends Item{

    public SlideRule(){
        Logger.create(this);
        Logger.exitCreate(this);
    }

    @Override
    public void activate() {
        Logger.enter( this, "activate");
        Logger.exit( this, "activate");
    }

    @Override
    public void meet(Person person) {
        Logger.enter( this, "meet", List.of(person) );
        Logger.exit( this, "meet");
    }

    @Override
    public boolean saveFromDeath(Person killer) {
        Logger.enter( this, "saveFromDeath", List.of(killer) );
        Logger.exit( this, "saveFromDeath", "false" );
        return false;
    }

    @Override
    public boolean saveFromGas() {
        Logger.enter( this, "saveFromGas");
        Logger.exit( this, "saveFromGas", "false" );
        return false;
    }

    @Override
    public void setLocation( Room room, Person person ){
        Stream<Object> args = Stream.of( room, person );
        Logger.enter( this, "setLocation", args.toList() );

        location = room;
        holder = person;
        if(person!=null){
            person.pickedUpSlideRule(this);
        }

        Logger.exit( this, "setLocation" );
    }

    @Override
    public void timeElapsed(int time) {
        Logger.enter(this, "timeElapsed", List.of(time));
        Logger.enter(this, "timeElapsed");
    }
}
