package testing;

import model.*;


public class Test12 implements ITest {
    @Override
    public void run() {
        Teacher t = new Teacher();
        Logger.register(t, "t");
        Student s = new Student();
        Logger.register(s, "s");
        TVSZ tv = new TVSZ();
        Logger.register(tv, "tv");
        s.initItem(tv);
        tv.initLocation(null, s);
       t.meet(s);
    }
}