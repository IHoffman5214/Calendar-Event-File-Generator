import java.util.Scanner;
import java.text.DateFormat;

public class Interface
{
	public static void main(String [] args)
	{
		boolean  exitProgram = false;
		String   userInput = "";
		Scanner  userInputScanner = new Scanner(System.in);
		Calendar calendar;

		//if user enters a command argument
		if (args.length == 1)
		{
			//if user enters a valid .ics file
			if (!isIcsFile(args[0]))
			{
				System.out.println("The entered file is not a valid .ics file!");
				return;
			}

			System.out.println("importing .ics file into calendar object...");
			calendar = new Calendar(args[0]);
		}
		//if user enters more than 1 argument
		else if (args.length > 1)
		{
			System.out.println("Too many arguments!");
			return;
		}
		//no command arguments
		else
		{
			System.out.println("No import specified, creating a new .ics file...");
			calendar = new Calendar();
		}

		//get user input
		while(!exitProgram)
		{
			//take user input and converts to lower case
			System.out.print("Command: ");
       		userInput = userInputScanner.nextLine();
       		userInput = userInput.toLowerCase();

       		//list all commands
       		if(userInput.equals("commands"))
       		{
       			printAllCommands();
       		}
       		//add event to calendar
       		else if(userInput.equals("add"))
       		{
       			Vevent vevent = addEventInterface();

       			calendar.addEvent(vevent);
       		}
       		//adds a sample event - currently just for speed of testing/debugging
       		else if(userInput.equals("addsample"))
       		{
       			calendar.addEvent(new Vevent("htms3l9k1rnnadhbfg1oqc46d8@google.com", "20160222T030130Z", "", "20150322T173000Z", "20150322T180000Z", "default event"));
       		}
       		//print all events currently in the calendar
       		else if(userInput.equals("printallevents"))
       		{
       			System.out.println();
       			System.out.println("===================================");
       			calendar.printAllEvents();
       			System.out.println("===================================");
       			System.out.println();
       		}
       		//exit application
       		else if(userInput.equals("exit") || userInput.equals("quit"))
       		{
       			System.out.println("Exporting data to " + calendar.getFileName());
       			calendar.exportIcs();
       			exitProgram = true;
       		}
       		//If their is no known command for user input
       		else
       		{
       			System.out.println("I am not sure what you mean, perhaps type \"Commands\"?");
       		}
		}
	}

	//TODO
	//needs to be more user friendly, but it works
	private static Vevent addEventInterface()
	{
		Vevent  vevent = new Vevent();
		Scanner userInputScanner = new Scanner(System.in);
		String  temp =  "";

        //User sets event UID
		do
		{
			System.out.println("\nEnter a valid UID (or type \"cancel\" to cancel adding an event):\n");
			temp = userInputScanner.nextLine();
       		temp = temp.toLowerCase();
            if (temp.equals("cancel"))
            {
                return null;
            }
		}
        while(!vevent.validUID(temp));
		vevent.setUID(temp);

        //User sets event DTSTAMP
		do
		{
			System.out.println("\nEnter a valid DTSTAMP (or type \"cancel\" to cancel adding an event):\n");
			temp = userInputScanner.nextLine();
       		temp = temp.toLowerCase();
            if (temp.equals("cancel"))
            {
                return null;
            }
		}
		while(!vevent.validDTSTAMP(temp));
		vevent.setDTSTAMP(temp);

        //User sets event ORGANIZER
		do
		{
			System.out.println("\nEnter a valid ORGANIZER (or type \"cancel\" to cancel adding an event):\n");
			temp = userInputScanner.nextLine();
       		temp = temp.toLowerCase();
            if (temp.equals("cancel"))
            {
                return null;
            }
		}
		while(!vevent.validORGANIZER(temp));
		vevent.setORGANIZER(temp);

        //User sets event DTSTART
		do
		{
			System.out.println("\nEnter a valid DTSTART (or type \"cancel\" to cancel adding an event):\n");
			temp = userInputScanner.nextLine();
       		temp = temp.toLowerCase();
            if (temp.equals("cancel"))
            {
                return null;
            }
		}
		while(!vevent.validDTSTART(temp));
		vevent.setDTSTART(temp);

        //User sets event DTEND
		do
		{
			System.out.println("\nEnter a valid DTEND (or type \"cancel\" to cancel adding an event):\n");
			temp = userInputScanner.nextLine();
       		temp = temp.toLowerCase();
            if (temp.equals("cancel"))
            {
                return null;
            }
		}
		while(!vevent.validDTEND(temp));
		vevent.setDTEND(temp);

        //User sets event SUMMARY
		do
		{
			System.out.println("\nEnter a valid SUMMARY (or type \"cancel\" to cancel adding an event):\n");
			temp = userInputScanner.nextLine();
       		temp = temp.toLowerCase();
            if (temp.equals("cancel"))
            {
                return null;
            }
		}
		while(!vevent.validSUMMARY(temp));
		vevent.setSUMMARY(temp);

		return vevent;
	}

	/*
		Returns all possible commands that a user could enter
	*/
	private static void printAllCommands()
	{
		System.out.println();
		System.out.println("=============COMMANDS==============");
		System.out.println("commands       - prints all known commands");
		System.out.println("exit           - exits the program with exporting");
		System.out.println("add            - add an event to current .ics file");
		System.out.println("printallevents - prints every event currently in the working calendar");
		System.out.println("===================================");
		System.out.println();
	}

	/*
		Returns true if input is .ics file, false if otherwise
		modified from http://stackoverflow.com/questions/3571223/how-do-i-get-the-file-extension-of-a-file-in-java
	*/
	private static boolean isIcsFile(String fileName)
	{
		String extension = "";
		int i = fileName.lastIndexOf('.');
		boolean myReturn;

		if (i > 0)
		{
		    extension = fileName.substring(i+1);
		}

		extension = extension.toLowerCase();

		if (extension.equals("ics"))
		{
			myReturn = true;
		}
		else
		{
			myReturn = false;
		}

		return myReturn;
	}
}