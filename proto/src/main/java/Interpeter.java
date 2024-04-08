import model.*;

import java.util.HashMap;

public class Interpeter {

    interface Command{
        void execute( String[] args );
    }

    private final HashMap<String,Command> commands = new HashMap<>();
    private final HashMap<String,Object> objects = new HashMap<>();

    private static final String INCORRECT_COMMAND = "Incorrect command";

    Interpeter(){
        commands.put( "create", args ->  {
            if( args.length != 3 ) {
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            if( commands.containsKey( args[ 2 ] ) ){
                System.out.println("Error: Name" + args[ 2 ] + "already exists");
                return;
            }
            Object newObject = null;
            switch( args[ 1 ] ){
                case "room":
                    newObject = new Room( 3, false, false, 3);
                    break;
                case "student":
                    newObject = new Student();
                    break;
                case "teacher":
                    newObject = new Teacher();
                    break;
                case "cleaner":
                    newObject = new Cleaner();
                    break;
                case "rag":
                    newObject = new Rag();
                    break;
                case "beerglass":
                    newObject = new BeerGlass();
                    break;
                case "mask":
                    newObject = new Mask();
                    break;
                case "falsemask":
                    newObject = new FalseMask();
                    break;
                default:
                    System.out.println(INCORRECT_COMMAND);
                    break;
            }
            if( newObject != null ){
                objects.put( args[ 2 ], newObject );
            }
        } );
    }

}
