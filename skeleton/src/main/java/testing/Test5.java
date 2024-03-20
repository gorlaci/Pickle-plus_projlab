package testing;

import model.Room;
import model.Student;
import model.TVSZ;

public class Test5 implements ITest {
    @Override
    public void run() {
        Room r = new Room();
        Logger.register(r, "r");
        Student s = new Student();
        Logger.register(s, "s");
        TVSZ tv = new TVSZ();
        Logger.register(tv, "tv");
        r.addPerson(s);
        s.setLocation(r);
        r.initItem(tv);
        tv.initLocation(r, null);
        s.addItem(tv);
    }
}
