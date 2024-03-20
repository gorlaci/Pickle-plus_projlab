package testing;

import model.Room;
import model.Student;
import model.Camembert;
import model.TVSZ;


public class Test8 implements ITest {
    @Override
    public void run() {
        Room r = new Room();
        Logger.register(r, "r");
        Student s = new Student();
        Logger.register(s, "s");
        TVSZ tv = new TVSZ();
        Logger.register(tv, "tv");
        Camembert c = new Camembert();
        Logger.register(c, "c");
        r.addPerson(s);
        s.setLocation(r);
        s.initItem(tv);
        tv.initLocation(r, s);
        s.initItem(c);
        c.initLocation(r, s);
        s.activateItem(c);
    }
}