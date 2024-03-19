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

    }

    @Override
    public void kill(Person killer) {

    }

    @Override
    public void slip() {

    }

    @Override
    public void pickedUpSlideRule(SlideRule slideRule) {

    }

    @Override
    public void greet(Person greeter) {
        Logger.enter(this, "greet", List.of(greeter));

        greeter.kill(this);

        Logger.exit(this, "greet");
    }
}
