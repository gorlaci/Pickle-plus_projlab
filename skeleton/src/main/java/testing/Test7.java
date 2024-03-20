package testing;

import model.Room;
import model.Student;
import model.Camembert;
import model.Mask;
import model.TVSZ;


public class Test7 implements ITest {
    @Override
    public void run() {
        Room r = new Room();
        Logger.register(r, "r");
        Student s = new Student();
        Logger.register(s, "s");
        Camembert c = new Camembert();
        Logger.register(c, "c");
        Mask m = new Mask();
        Logger.register(m, "m");
        TVSZ tv = new TVSZ();
        Logger.register(tv, "tv");
        r.addPerson(s);
        s.setLocation(r);
        s.initItem(c);
        c.initLocation(r, s);
        s.initItem(m);
        m.initLocation(r, s);
        s.initItem(tv);
        tv.initLocation(r, s);
        s.activateItem(c);
    }
}