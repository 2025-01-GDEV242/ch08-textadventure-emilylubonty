/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Emily Lubonty
 * @version 3-29-2025
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom; 
    private Item currentItem; 
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        createItems(); 
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, cafeteria, confrence, tunnel;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a science lab");
        office = new Room("in the computing admin office");
        cafeteria = new Room ("in the campus cafeteria"); 
        confrence = new Room ("in the confrence room"); 
        tunnel = new Room ("in the campus' secret tunnel"); 
        
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", cafeteria); 

        theater.setExit("west", tunnel);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        
        confrence.setExit("south", pub); 
        
        cafeteria.setExit("west", confrence); 
        
        tunnel.setExit("east", outside);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
        
        
        
    }
    
    /**
     * Creates items to be added to rooms. 
     */
    private void createItems()
    {
        Item raincoat, laptop, latte, frog, documents, sandwich, chips, gold; 
        
        // create new items
        raincoat = new Item();
        chips = new Item();
        laptop = new Item();
        latte = new Item(); 
        frog = new Item();
        documents = new Item();
        sandwich = new Item(); 
        gold = new Item();  
        
        raincoat.setItem("a yellow raincoat on the bench", 1);
        chips.setItem("a dozen poker chips stacked neatly on the counter", 4); 
        laptop.setItem("an unassuming laptop with Word open on the table", 600); 
        latte.setItem("a spilled drink on the floor", 50);
        frog.setItem("a frog that is looking to escape it's fate by the window", 1000); 
        documents.setItem("a pile of documents regarding school policies on the desk", 6000); 
        sandwich.setItem("an untouched sandwich behind the counter", 13); 
        gold.setItem("a block of gold fitted into the wall", 777); 
    
        currentItem = raincoat; // begin game with raincoat
    }
    
    
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
    while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;
                
            case BACK:
                previousRoom(command);
                break; 

            case QUIT:
                wantToQuit = quit(command);
                break;
            
            case LOOK:
                look(command);
                break; 
                
            case PICKUP:
                pickup(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
    
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
           currentRoom = nextRoom;
           System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Sends player to the previous room they were in. If there is no exit an
     * error message is printed. 
     */
    private void previousRoom(Command command)
    {
        String direction = command.getSecondWord();
        Room backRoom = currentRoom.getExit(direction);
        CommandWord commandWord = command.getCommandWord();
        
        if (command.hasSecondWord()){
           System.out.println("Invalid command.");
           return;
        }
        if (backRoom != null){
            if (direction.equals("east")){
            currentRoom = currentRoom.getExit("east"); 
            }
            if (direction.equals("west")){
            currentRoom = currentRoom.getExit("west"); 
            }
            if (direction.equals("south")){
            currentRoom = currentRoom.getExit("south"); 
            }
            if (direction.equals("north")){
            currentRoom = currentRoom.getExit("north"); 
            }

            System.out.println(currentRoom.getLongDescription());
        }
        else{
            System.out.println("There is nothing to return to.");
        }
    }

    /**
     * Allows player to view their current surroundings. 
     */
    private void look(Command command)
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void pickup(Command command)
    {
        System.out.println("You have picked up the item."); 
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
