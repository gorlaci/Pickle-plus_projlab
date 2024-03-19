import testing.ITest;
import testing.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    private static void testSkeleton() {
        final HashMap<Integer, ITest> tests=new HashMap<>();
        tests.put(1, new Test1());
        //TODO continue with all tests

        System.out.println("Testing the skeleton");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while(true) {
            try {
                System.out.print("\tChoose a test case (1-"+tests.size()+"): ");
                String line = br.readLine();
                if(line==null||line.equals("exit")) {
                    System.out.println("Testing finished");
                    break;
                }
                int testNum = Integer.parseInt(line);
                ITest test = tests.get(testNum);
                if (test != null) {
                    test.run();
                    System.out.println("Test Over.");
                }
                else {
                    System.out.println("Invalid number, try again!");
                }
            }
            catch(IOException e) {
                System.out.println("IOException happened:");
                e.printStackTrace();
            }
            catch (NumberFormatException e) {
                System.out.println("Not a number, try again!");
            }
        }
    }

    public static void main( String[] args ) {

        TestA a1 = new TestA();
        Logger.register( a1, "a1" );

        TestB b1 = new TestB();
        Logger.register( b1, "b1");

        b1.foo( a1 );

        TestB b2 = new TestB();
        Logger.register( b2, "b2");

        a1.foo2( b1, b2 );

        boolean answer = Logger.askQuestion( "Működik?" );
        System.out.println( answer ? "Igen" : "Nem" );

        testSkeleton();
    }
}
