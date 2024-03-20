package testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Logger {

    private Logger(){

    }

    private static final HashMap<Object, String> names = new HashMap<>();
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
        writeIndent();
        names.put( object, name);
        System.out.println( name + " created" );
    }

    public static void create( Object object ){
        writeIndent();
        System.out.println( "->" + object.getClass().getSimpleName() + "()" );
        indent++;
    }

    public static void enter( Object object, String func, List<Object> args ){
        writeIndent();
        System.out.print( "->" + names.get(object) + "." + func + "(" );
        for( int i = 0 ; i < args.size() ; i++ ){
            if( args.get( i ) == null ){
                System.out.print( "null" );
            } else if( args.get( i ).getClass() == Integer.class ){
                System.out.print( args.get( i ) );
            } else {
                System.out.print(names.get(args.get(i)));
            }

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
        System.out.println( "<-" + object.getClass().getSimpleName() );
    }

    private static final Scanner in = new Scanner( System.in );
    public static boolean askQuestion( String question ){
        writeIndent();
        System.out.print( question + " [y/n] " );
        while( in.hasNext() ) {
            String answer = in.next();
            if( answer.equals( "y" ) ){
                return true;
            } else if( answer.equals( "n" ) ){
                return false;
            } else {
                writeIndent();
                System.out.print( question + " [y/n] " );
            }
        }
        return false;
    }

    public static boolean askQuestion( String question, Object object ){
        String name = names.get( object );
        String fullQuestion = question.replace( "#", name );
        return askQuestion( fullQuestion );
    }
}
