package model;

import testing.Logger;

import java.util.List;

public class Student extends Person{

    public Student(){
        Logger.create(this);
        Logger.exitCreate(this);
    }
    @Override
    public void meet(Person person) {
        Logger.enter( this, "meet", List.of(person) );

        person.greet(this);

        Logger.exit( this, "meet" );
    }

    @Override
    public void kill(Person killer) {
        Logger.enter( this, "kill", List.of(killer) );

        boolean saved = false;
        for( Item item : itemsInHand ){
            if(item.saveFromDeath( killer ) ){
                saved = true;
                break;
            }
        }
        if( !saved ){
            location.removePerson(this);
        }

        Logger.exit( this, "kill" );
    }

    @Override
    public void slip() {

    }

    @Override
    public void pickedUpSlideRule(SlideRule slideRule) {

    }

    @Override
    public void greet(Person greeter) {

    }

    public void activateItem( Item item ){

    }
}
