package testing;

import model.*;

public class Test14 implements ITest {
    @Override
    public void run() {
        Room r1 = new Room();
        Logger.register(r1, "r1");
        Room r2 = new Room();
        Logger.register(r2, "r2");
        Mask m = new Mask();
        Logger.register( m, "m" );
        TVSZ tv = new TVSZ();
        Logger.register( tv, "tv");
        r1.addNeighbour(r2);
        r1.initItem(m);
        m.initLocation(r1, null);
        r2.addNeighbour(r1);
        r2.initItem(tv);
        tv.initLocation(r2, null);
        r1.requestMerge(r2);
    }
}
