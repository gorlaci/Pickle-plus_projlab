public class Main {

    public static void main( String[] args ){
        TestA a1 = new TestA();
        Logger.register( a1, "a1" );

        TestB b1 = new TestB();
        Logger.register( b1, "b1");

        b1.foo( a1 );

        TestB b2 = new TestB();
        Logger.register( b2, "b2");

        a1.foo2( b1, b2 );
    }
}
