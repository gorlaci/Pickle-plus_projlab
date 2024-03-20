package model;

import testing.Logger;

public abstract class IntervalItem extends Item {

    protected boolean activated;
    protected int timeRemaining;
    @Override
    public void activate() {
        Logger.enter(this, "activate");
        Logger.exit(this, "activate");
    }
}
