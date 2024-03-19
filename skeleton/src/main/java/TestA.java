import java.util.List;

public class TestA {

    public TestA(){
        Logger.create( this );
        Logger.exitCreate(this);
    }

    public int foo(){
        Logger.enter( this, "foo");

        int res = 2;

        Logger.exit( this, "foo", Integer.toString(res) );
        return res;
    }

    public void foo2( TestB b1, TestB b2 ){
        Logger.enter( this, "foo2", List.of(b1, b2) );

        Logger.exit(this, "foo2");
    }

}
