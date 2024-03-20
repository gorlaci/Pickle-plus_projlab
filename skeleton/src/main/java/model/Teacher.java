package model;

import testing.Logger;

import java.util.List;

public class Teacher extends Person{

    public Teacher(){
        Logger.create(this);
        Logger.exitCreate(this);
    }

    @Override
    public void meet(Person person) {
        Logger.enter(this, "meet", List.of(person));
        person.kill(this);
        Logger.exit(this, "meet");
    }

    @Override
    public void kill(Person killer) {
        Logger.enter(this, "kill", List.of(killer));
        Logger.exit(this, "kill");
    }

    @Override
    public void slip() {
        Logger.enter(this, "slip");
        Logger.exit(this, "slip");
    }

    @Override
    public void pickedUpSlideRule(SlideRule slideRule) {
        Logger.enter(this, "pickedUpSlideRule", List.of(slideRule));
        dropItem(slideRule);
        Logger.exit(this, "pickedUpSlideRule");
    }

    @Override
    public void greet(Person greeter) {
        Logger.enter(this, "greet", List.of(greeter));

        greeter.kill(this);

        Logger.exit(this, "greet");
    }
}
