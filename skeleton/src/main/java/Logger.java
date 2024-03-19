import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Logger {

    private static HashMap<Object, String> names = new HashMap<>();
    private static int indent = 0;

    private static void writeIndent(){
        for( int i = 0 ; i < indent ; i++ ){
            System.out.print( '\t' );
        }
    }

    public static void startNew(){
        names.clear();
        indent = 0;
    }

    public static void register( Object object, String name ){
        names.put( object, name);
        System.out.println( name + " created" );
    }

    public static void create( Object object ){
        writeIndent();
        System.out.println( "->" + object.getClass().getName() + "()" );
        indent++;
    }

    public static void enter( Object object, String func, List<Object> args ){
        writeIndent();
        System.out.print( "->" + names.get(object) + "." + func + "(" );
        for( int i = 0 ; i < args.size() ; i++ ){
            System.out.print( names.get(args.get(i)) );
            if( i < args.size() - 1 ){
                System.out.print( ", ");
            }
        }
        System.out.println(")");
        indent++;
    }

    public static void enter( Object object, String func ){
        List<Object> list = new ArrayList<>();
        enter( object, func, list );
    }

    public static void exit( Object object, String func, String value ){
        indent--;
        writeIndent();
        System.out.print( "<-" + names.get(object) + "." + func );
        if( value != null ){
            System.out.println(": " + value );
        } else {
            System.out.println();
        }
    }

    public static void exit( Object object, String func ){
        exit( object, func, null );
    }

    public static void exitCreate( Object object ){
        indent--;
        writeIndent();
        System.out.println( "<-" + object.getClass().getName() );
    }
}
