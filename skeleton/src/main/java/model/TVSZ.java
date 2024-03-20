package model;

import testing.Logger;

import java.util.List;

public class TVSZ extends Item{

    public TVSZ(){
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
        if( !Logger.askQuestion( "Are there charges remaining of #?", this ) ){
            holder.removeItem( this );
        }
        Logger.exit( this, "saveFromDeath", "true" );
        return true;
    }

    @Override
    public boolean saveFromGas() {
        Logger.enter(this, "saveFromGas");
        Logger.exit(this, "saveFromGas", "false");
        return false;
    }

    @Override
    public void timeElapsed(int time) {
        Logger.enter(this, "timeElapsed");
        Logger.exit(this, "timeElapsed");
    }
}
