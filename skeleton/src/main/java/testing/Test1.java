package testing;

import model.*;

public class Test1 implements ITest {
    @Override
    public void run() {
        Student s = new Student();
        Logger.register( s, "s");
        Teacher t = new Teacher();
        Logger.register( t,"t");
        Room r2 = new Room();
        Logger.register(r2, "r2");
        Room r1 = new Room();
        Logger.register(r1, "r1");
        TVSZ tv = new TVSZ();
        Logger.register( tv, "tv");
        Mask m = new Mask();
        Logger.register( m, "m" );
        BeerGlass b = new BeerGlass();
        Logger.register(b, "b");
        r2.addPerson(s);
        s.setLocation(r2);
        r2.addNeighbour(r1);
        r1.addNeighbour(r2);
        r1.initItem(tv);
        tv.initLocation(r1, null);
        r1.addPerson(t);
        t.setLocation(r1);
        s.initItem(m);
        m.initLocation(r2, s);
        s.initItem(b);
        b.initLocation(r2, s);
        s.enterRoom(r1);
    }
}
