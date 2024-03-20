package testing;

import model.*;

public class Test13 implements ITest {
    @Override
    public void run() {
        Room r1 = new Room();
        Logger.register(r1, "r1");
        Room r2 = new Room();
        Logger.register(r2, "r2");
        Student s = new Student();
        Logger.register( s, "s");
        Transistor t1 = new Transistor();
        Logger.register(t1, "t1");
        Transistor t2 = new Transistor();
        Logger.register(t2, "t2");
        r1.initItem(t2);
        t2.initLocation(r1, null);
        r2.addPerson(s);
        s.setLocation(r2);
        s.initItem(t1);
        t1.initLocation(r2, s);
        t1.setPair(t2);
        t2.setPair(t1);
        t1.activate();
    }
}
