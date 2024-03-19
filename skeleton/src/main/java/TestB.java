import testing.Logger;

import java.util.List;

public class TestB {

    public TestB(){
        Logger.create( this );
        Logger.exitCreate(this);
    }

    public void foo( TestA a ){

        Logger.enter( this, "foo", List.of( a ) );

        a.foo();

        Logger.exit( this, "foo" );
    }
}
