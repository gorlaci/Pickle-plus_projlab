package testing;

import model.Room;
import model.SlideRule;
import model.Teacher;

public class Test17 implements ITest {
    @Override
    public void run() {
        Room r = new Room();
        Logger.register(r, "r");
        Teacher t = new Teacher();
        Logger.register(t, "t");
        SlideRule sr = new SlideRule();
        Logger.register(sr, "sr");
        r.addPerson(t);
        t.setLocation(r);
        r.initItem(sr);
        sr.initLocation(r, null);
        t.addItem(sr);
    }
}
