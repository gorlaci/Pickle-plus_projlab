package testing;

import model.*;

public class Test15 implements ITest {
    @Override
    public void run() {
        Room r1 = new Room();
        Logger.register(r1, "r1");
        Rag r = new Rag();
        Logger.register( r, "r");
        Mask m = new Mask();
        Logger.register( m, "m" );
        r1.initItem(r);
        r.initLocation(r1, null);
        r1.initItem(m);
        m.initLocation(r1, null);
        r1.split();
    }
}
