import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Interpreter {

    interface Command{
        void execute( String[] args );
    }

    private final HashMap<String,Command> commands = new HashMap<>();
    private final HashMap<String,Object> objects = new HashMap<>();

    private static final String INCORRECT_COMMAND = "Incorrect command";

    Interpreter(){
        commands.put( "create", args ->  {
            if( args.length != 3 ) {
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object newObject = null;
            switch( args[ 1 ] ){
                case "room":
                    newObject = new Room( 3, false, false, 0);
                    break;
                case "student":
                    newObject = new Student(0, null);
                    break;
                case "teacher":
                    newObject = new Teacher( 0, null);
                    break;
                case "cleaner":
                    newObject = new Cleaner( 0, null);
                    break;
                case "rag":
                    newObject = new Rag( null, null, false, 5);
                    break;
                case "beerglass":
                    newObject = new BeerGlass(null, null, false, 5);
                    break;
                case "mask":
                    newObject = new Mask(null, null, false, 6);
                    break;
                case "falsemask":
                    newObject = new FalseMask( null, null, false, 6);
                    break;
                case "sliderule":
                    newObject = new SlideRule( null, null);
                    break;
                case "falsesliderule":
                    newObject = new FalseSlideRule( null, null);
                    break;
                case "tvsz":
                    newObject = new TVSZ( null, null);
                    break;
                case "falsetvsz":
                    newObject = new FalseTVSZ( null, null);
                    break;
                case "airfresher":
                    newObject = new AirFresher( null, null);
                    break;
                case "transistor":
                    newObject = new Transistor( null, null);
                    break;
                case "camembert":
                    newObject = new Camembert( null, null );
                    break;
                default:
                    System.out.println(INCORRECT_COMMAND);
                    break;
            }
            if( commands.containsKey( args[ 2 ] ) ){
                System.out.println("Error: Name" + args[ 2 ] + "already exists");
                return;
            }
            objects.put( args[ 2 ], newObject );
        } );

        commands.put( "addperson", args -> {
            if( args.length != 3 ){
                System.out.println( INCORRECT_COMMAND );
                return;
            }
            Object personObj = objects.get( args[ 1 ] );
            Object roomObj = objects.get( args[ 2 ] );
            if(!(personObj instanceof Person person) || !(roomObj instanceof Room room)){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            room.addPerson( person );
            person.setLocation(room);
        } );

        commands.put( "add", args -> {
            if( args.length != 3 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object itemObj = objects.get( args[ 1 ] );
            Object targetObj = objects.get( args[ 2 ] );
            if( !(itemObj instanceof Item item) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            if( targetObj instanceof Room room ){
                room.initItem( item );
                item.setLocation( room, null );
            } else if (targetObj instanceof Person person) {
                person.initItem( item );
                item.setLocation( person.getLocation(), person );
            } else {
                System.out.println(INCORRECT_COMMAND);
                return;
            }
        } );

        commands.put("remove", args -> {
            if( args.length != 3 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object itemObj = objects.get( args[ 1 ] );
            Object targetObj = objects.get( args[ 2 ] );
            if( !(itemObj instanceof Item item) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            if( targetObj instanceof Room room ){
                room.getItemsInRoom().remove( item );
                item.setLocation( null, null );
            } else if (targetObj instanceof Person person) {
                person.getItemsInHand().remove(item);
                item.setLocation( null, null );
            } else {
                System.out.println(INCORRECT_COMMAND);
                return;
            }
        });

        commands.put("setroom", args -> {
            if( args.length != 4 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object roomObj = objects.get( args[ 1 ] );
            if( !(roomObj instanceof Room room) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            switch (args[ 2 ]){
                case "gas":
                    if( args[3].equals( "true" ) ){
                        room.setGas( true );
                    } else if( args[3].equals("false") ){
                        room.setGas( false );
                    } else {
                        System.out.println( INCORRECT_COMMAND );
                        return;
                    }
                    break;
                case "cursed":
                    if( args[3].equals( "true" ) ){
                        room.setCursed( true );
                    } else if( args[3].equals("false") ){
                        room.setCursed( false );
                    } else {
                        System.out.println( INCORRECT_COMMAND );
                        return;
                    }
                    break;
                case "curseactive":
                    if( args[3].equals( "true" ) ){
                        room.setCurseActive( true );
                    } else if( args[3].equals("false") ){
                        room.setCurseActive( false );
                    } else {
                        System.out.println( INCORRECT_COMMAND );
                        return;
                    }
                    break;
                case "capacity":
                    int newCapacity;
                    try {
                        newCapacity = Integer.parseInt( args[3] );
                    } catch (NumberFormatException exception){
                        System.out.println(INCORRECT_COMMAND);
                        return;
                    }
                    room.setCapacity(newCapacity);
                    break;
                case "stickiness":
                    int newStickiness;
                    try {
                        newStickiness = Integer.parseInt( args[3] );
                    } catch (NumberFormatException exception){
                        System.out.println(INCORRECT_COMMAND);
                        return;
                    }
                    room.setStickiness(newStickiness);
                    break;
                default:
                    System.out.println(INCORRECT_COMMAND);
                    return;
            }
        });

        commands.put("setstun", args -> {
           if( args.length != 3 ){
               System.out.println(INCORRECT_COMMAND);
               return;
           }
           Object personObj = objects.get( args[ 1 ] );
           if( !(personObj instanceof Person person) ){
               System.out.println(INCORRECT_COMMAND);
               return;
           }
            int newStun;
            try {
                newStun = Integer.parseInt( args[2] );
            } catch (NumberFormatException exception){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            person.setStunRemaining(newStun);
        });

        commands.put("neighbour", args -> {
            if( args.length != 3 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object room1Obj = objects.get(args[ 1 ] );
            Object room2Obj = objects.get(args[ 2 ]);
            if( !(room1Obj instanceof Room room1) || !(room2Obj instanceof Room room2) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            if( room1 == room2 ){
                System.out.println( "Error: Room objects must differ." );
                return;
            }
            if( room1.getNeighbours().contains( room2) ){
                System.out.println( "Error: Rooms " + args[1] +" and " + args[ 2 ] + "are already neighbours." );
                return;
            }
            room1.addNeighbour(room2);
        });

        commands.put("setpair", args -> {
            if( args.length != 3 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object transistor1Obj = objects.get(args[ 1 ] );
            Object transistor2Obj = objects.get(args[ 2 ]);
            if( !(transistor1Obj instanceof Transistor transistor1) || !(transistor2Obj instanceof Transistor transistor2) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            if( transistor1 == transistor2 ){
                System.out.println( "Error: Transistor objects must differ" );
                return;
            }
            if( transistor1.getPair() != null || transistor2.getPair() != null ){
                System.out.println( "Error: Transistor is already paired" );
                return;
            }
            transistor1.setPair(transistor2);
            transistor2.setPair(transistor1);
        });

        commands.put("setremaining", args -> {
            if( args.length != 3 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            int newValue;
            try {
                newValue = Integer.parseInt( args[2] );
            } catch (NumberFormatException exception){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object targetObj = objects.get(args[ 1 ]);
            if( targetObj instanceof Mask mask ){
                mask.setDuration(newValue);
            } else if (targetObj instanceof TVSZ tvsz) {
                tvsz.setUsesRemaining(newValue);
            } else {
                System.out.println(INCORRECT_COMMAND);
                return;
            }
        });

        commands.put( "setinterval", args -> {
            if( args.length != 4 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object intervalItemObj = objects.get( args[ 2 ]);
            if( !(intervalItemObj instanceof IntervalItem intervalItem) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            switch (args[ 1 ]){
                case "activated":
                    if( args[3].equals( "true" ) ){
                        intervalItem.setActivated( true );
                    } else if( args[3].equals("false") ){
                        intervalItem.setActivated( false );
                    } else {
                        System.out.println( INCORRECT_COMMAND );
                        return;
                    }
                    break;
                case "timeremaining":
                    int newTimeRemaining;
                    try {
                        newTimeRemaining = Integer.parseInt( args[3] );
                    } catch (NumberFormatException exception){
                        System.out.println(INCORRECT_COMMAND);
                        return;
                    }
                    intervalItem.setTimeRemaining(newTimeRemaining);
                    break;
                default:
                    System.out.println(INCORRECT_COMMAND);
                    return;
            }
        } );

        commands.put( "setseed", args -> {
            if( args.length != 3 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object personObj = objects.get( args[ 1 ]);
            if(!(personObj instanceof Person person)){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            int newSeed;
            try {
                newSeed = Integer.parseInt( args[2] );
            } catch (NumberFormatException exception){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            person.setSeed(newSeed);
        } );

        commands.put( "load", args -> {
            if( args.length != 2 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Scanner file = null;
            try {
                file = new Scanner(new File(args[ 1 ]));
            } catch (FileNotFoundException e) {
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            while (file.hasNextLine()){
                String line = file.nextLine();
                execute(line);
            }
        } );

        commands.put( "drop", args -> {
            if (args.length != 3){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object itemObj = objects.get(args[ 1 ]);
            Object personObj = objects.get(args[ 2 ]);
            if( !(itemObj instanceof Item item) || !(personObj instanceof Person person)){
                System.out.println( INCORRECT_COMMAND);
                return;
            }
            if( !person.getItemsInHand().contains(item) ){
                System.out.println("Error: Item " + args[ 1 ] +" is not held by " + args[ 2 ]);
                return;
            }
            person.dropItem( item );
        } );

        commands.put("pickup", args -> {
            if (args.length != 3){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object itemObj = objects.get(args[ 1 ]);
            Object personObj = objects.get(args[ 2 ]);
            if( !(itemObj instanceof Item item) || !(personObj instanceof Person person)){
                System.out.println( INCORRECT_COMMAND);
                return;
            }
            if( !person.getLocation().getItemsInRoom().contains(item) ){
                System.out.println("Error: Item "+ args[1] + " and Person " + args[2] +" are not in the same room");
                return;
            }
            person.addItem( item );
        });

        commands.put("enter", args -> {
           if( args.length != 3 ){
               System.out.println(INCORRECT_COMMAND);
               return;
           }
           Object roomObj = objects.get(args[1]);
           Object personObj = objects.get(args[2]);
           if( !(roomObj instanceof Room room) || ! (personObj instanceof Person person) ){
               System.out.println(INCORRECT_COMMAND);
               return;
           }
           if( !person.getLocation().getNeighbours().contains( room ) ){
               System.out.println("Error: Rooms are not neighbours.");
               return;
           }
           person.enterRoom(room);
        });

        commands.put("merge", args -> {
            if( args.length != 4 ){
                System.out.println( INCORRECT_COMMAND );
                return;
            }
            Object room1Obj = objects.get(args[1]);
            Object room2Obj = objects.get(args[2]);
            if( !(room1Obj instanceof Room room1) || !(room2Obj instanceof Room room2) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            if( room1 == room2 ){
                System.out.println("Error: Room objects must differ.");
                return;
            }
            if( !room1.getNeighbours().contains(room2) || !room2.getNeighbours().contains(room1) ){
                System.out.println("Error: Rooms " + args[1] + " and " + args[2] + " are not neighbours.");
                return;
            }
            if( objects.containsKey(args[3]) ){
                System.out.println("Error: Name "+ args[3] + " already exists.");
                return;
            }
            Room newRoom = room1.requestMerge(room2);
            if( newRoom != null ){
                objects.put(args[3], newRoom);
            }
        });

        commands.put("split", args -> {
            if( args.length != 3){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object roomObj = objects.get(args[1]);
            if( !(roomObj instanceof Room room) ){
               System.out.println(INCORRECT_COMMAND);
               return;
            }
            if( objects.containsKey(args[2]) ){
                System.out.println("Error: Name "+ args[2] + " already exists.");
                return;
            }
            Room newRoom = room.split();
            if( newRoom != null ){
                objects.put(args[2], newRoom);
            }
        });

        commands.put("activate", args -> {
            if( args.length != 3 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object itemObj = objects.get(args[1]);
            Object studentObj = objects.get(args[2]);
            if( !(itemObj instanceof Item item) || !(studentObj instanceof Student student) ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            if( !student.getItemsInHand().contains(item) ){
                System.out.println( "Error: Item " + args[ 1 ] +" is not held by " + args[ 2 ] );
                return;
            }
            student.activateItem(item);
        });

        commands.put("elapsetime", args -> {
            if( args.length != 2 ){
                System.out.println( INCORRECT_COMMAND );
                return;
            }
            int time;
            try {
                time = Integer.parseInt( args[1] );
            } catch (NumberFormatException exception){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            for( Object iter : objects.values() ){
                if( iter instanceof Room room ){
                    room.timeElapsed(time);
                }
            }
        });

        commands.put("status", args -> {
            if(args.length == 1) {
                boolean win=false;
                boolean inProgress=false;
                for(Object object: objects.values()) {
                    if(object instanceof Room room) {
                        for(Person person: room.getPeopleInRoom()) {
                            if(person instanceof Student student) {
                                inProgress=true;
                                for(Item item: student.getItemsInHand()) {
                                    if(item instanceof SlideRule && !(item instanceof FalseSlideRule)) {
                                        win=true;
                                    }
                                }
                            }
                        }
                    }
                }
                if(win) System.out.println("Win");
                else if(inProgress) System.out.println("Game in progress");
                else System.out.println("Game over");
                return;
            }
            if( args.length != 2 ){
                System.out.println(INCORRECT_COMMAND);
                return;
            }
            Object object = objects.get(args[1]);
            if( object instanceof Room room ){
                System.out.println(getName(room));
                printList("neighbours", room.getNeighbours());
                printList("peopleInRoom", room.getPeopleInRoom());
                printList("itemsInRoom", room.getItemsInRoom());
                System.out.println("gas: " + room.isGas());
                System.out.println("cursed: " + room.isCursed());
                System.out.println("capacity: " + room.getCapacity());
                System.out.println("curseActive: " + room.isCurseActive());
                System.out.println("stickiness: " + room.getStickiness());
            } else if( object instanceof Person person ){
                System.out.println(getName(person));
                System.out.println("location: " + getName(person.getLocation()));
                printList("itemsInHand", person.getItemsInHand());
                System.out.println("stunRemaining: " + person.getStunRemaining());
            } else if( object instanceof Transistor transistor ){
                System.out.println(getName(transistor));
                System.out.println("location: " + getName(transistor.getLocation()));
                System.out.println("holder: " + getName(transistor.getHolder()));
                System.out.println("pair: " + getName(transistor.getPair()));
            } else if( object instanceof TVSZ tvsz ){
                System.out.println(getName(tvsz));
                System.out.println("location: " + getName(tvsz.getLocation()));
                System.out.println("holder: " + getName(tvsz.getHolder()));
                System.out.println("usesRemaining: " + tvsz.getUsesRemaining());
            } else if( object instanceof Mask mask ){
                System.out.println(getName(mask));
                System.out.println("location: " + getName(mask.getLocation()));
                System.out.println("holder: " + getName(mask.getHolder()));
                System.out.println("timeRemaining: " + mask.getTimeRemaining());
                System.out.println("duration: " + mask.getDuration());
            } else if( object instanceof IntervalItem intervalItem ){
                System.out.println(getName(intervalItem));
                System.out.println("location: " + getName(intervalItem.getLocation()));
                System.out.println("holder: " + getName(intervalItem.getHolder()));
                System.out.println("timeRemaining: " + intervalItem.getTimeRemaining());
            } else if( object instanceof Item item){
                System.out.println(getName(item));
                System.out.println("location: " + getName(item.getLocation()));
                System.out.println("holder: " + getName(item.getHolder()));
            } else {
                System.out.println(INCORRECT_COMMAND);
                return;
            }
        });

        commands.put("exit", args -> {
            System.exit(1);
        });
    }

    private String getName( Object object ){
        if( object == null ){
            return "NULL";
        }
        for( Map.Entry<String,Object> iter : objects.entrySet() ){
            if( object.equals( iter.getValue() ) ){
                return iter.getKey();
            }
        }
        return null;
    }

    private void printList(String name, Collection<?> list){
        System.out.print( name + ": ");
        for( Object i : list ){
            System.out.print(getName(i) + ", ");
        }
        System.out.println();
    }

    public void execute(String line ){
        if(line.isBlank()) {
            return;
        }
        String[] args = line.split(" ");
        Command command = commands.get( args[ 0 ]);
        if( command == null ){
            System.out.println(INCORRECT_COMMAND);
            return;
        }
        command.execute(args);
    }

    public static void main( String[] args ){
        Interpreter interpreter = new Interpreter();
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            String line = in.nextLine();
            interpreter.execute(line);
        }
    }

}
