package testing;

import model.*;


public class Test11 implements ITest {
    @Override
    public void run() {
        Teacher t = new Teacher();
        Logger.register(t, "t");
        Student s = new Student();
        Logger.register(s, "s");
        Room r = new Room();
        Logger.register(r, "r");
        BeerGlass b = new BeerGlass();
        Logger.register(b, "b");
        r.addPerson(t);
        t.setLocation(r);
        r.addPerson(s);
        s.setLocation(r);
        s.initItem(b);
        b.initLocation(r, s);
        r.timeElapsed(1);
    }
}