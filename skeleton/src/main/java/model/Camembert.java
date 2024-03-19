package model;

public class Camembert extends Item{
    @Override
    public void activate() {

    }

    @Override
    public void meet(Person person) {

    }

    @Override
    public boolean saveFromDeath(Person killer) {
        return false;
    }

    @Override
    public boolean saveFromGas() {
        return false;
    }

    @Override
    public void timeElapsed(int time) {

    }
}
