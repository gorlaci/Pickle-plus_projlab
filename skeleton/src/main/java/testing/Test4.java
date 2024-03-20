package testing;

import model.Room;
import model.Student;
import model.Teacher;

public class Test4 implements ITest {
    @Override
    public void run() {
        Teacher t = new Teacher();
        Logger.register(t, "t");
        Student s = new Student();
        Logger.register(s, "s");
        Room r = new Room();
        Logger.register(r, "r");
        r.addPerson(t);
        t.setLocation(r);
        r.addPerson(s);
        s.setLocation(r);
        t.meet(s);
    }
}
