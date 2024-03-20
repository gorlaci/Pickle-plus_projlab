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
        Logger.enter(this, "slip");
        Logger.exit(this, "slip");
    }

    @Override
    public void pickedUpSlideRule(SlideRule slideRule) {
        Logger.enter(this, "pickedUpSlideRule");
        Logger.exit(this, "pickedUpSlideRule");
    }

    @Override
    public void greet(Person greeter) {
        Logger.enter(this, "greet");
        Logger.exit(this, "greet");
    }

    public void activateItem( Item item ){
        Logger.enter(this, "activateItem");
        item.activate();
        Logger.exit(this, "activateItem");
    }
}
