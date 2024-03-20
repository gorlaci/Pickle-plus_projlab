package testing;

import model.Rag;
import model.Room;
import model.Teacher;


public class Test10 implements ITest {
    @Override
    public void run() {
        Teacher t = new Teacher();
        Logger.register(t, "t");
        Room r = new Room();
        Logger.register(r, "r");
        Rag rag = new Rag();
        Logger.register(rag, "rag");
        r.addPerson(t);
        t.setLocation(r);
        r.initItem(rag);
        rag.initLocation(r, null);
        r.timeElapsed(1);
    }
}