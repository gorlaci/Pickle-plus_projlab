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
        s.setLocation(r2);
        r2.addPerson(s);
        r2.addNeighbour(r1);
        r1.addNeighbour(r2);
        r1.addItem(tv);
        t.setLocation(r1);
        r1.addPerson(t);
        s.addItem(m);
        s.addItem(b);
        s.enterRoom(r1);
    }
}
