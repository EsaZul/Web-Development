package command;

import java.net.*;
import java.io.*;

/* 
 * We create one ChatListener for each socket; that is, there a separate thread for each friend's
 * incoming messages/commands. The ChatListener is passed the socket, and it creates in input stream
 * from that socket so it can receive the messages from the other side. It needs to receive the
 * messages as Strings (I've done this for you), then encapsulate them as commands and "deal with them."
 */

public class ChatListener implements Runnable {

    private BufferedReader in = null;
    private boolean stopped = false;
    private ChatClient chatClient;

    ChatListener(Socket mySocket, ChatClient chatClient){
	this.chatClient = chatClient;
	try{
	    in = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
	}
	catch(IOException ioe){
	    ioe.printStackTrace();
	}
    }
    
    public void run(){
	String fromServer = null;
	try{
	    fromServer = in.readLine();
	}
	catch(IOException ioe){
	    ioe.printStackTrace();
	}
	while(!stopped && fromServer != null) {
	    if(fromServer.equals("QUIT")){
		stopped = true;
	    }
	    else if(fromServer.equals("BEEP")){

		// CREATE NEW BeepCommand object
		// what should you do with it after it's created?
		// hint: don't just go executing it. Why not?
		Speaker speaker = Speaker.getInstance();
			SpeakerBeepCommand speakerBeepCommand = new SpeakerBeepCommand(speaker);
			chatClient.enqueueCommand(speakerBeepCommand);

	    }
	    else if(fromServer.equals("VIBRATE")){

		// CREATE NEW VibrateCommand object
			VibrationMotor vibrationMotor = VibrationMotor.getInstance();
			MotorVibrateCommand vibrateCommand = new MotorVibrateCommand(vibrationMotor);
			chatClient.enqueueCommand(vibrateCommand);

	    }
	    else{
		
		// CREATE NEW ConsoleCommand object
		// You'll have to do something slightly different here since you
		// need to print the string fromServer
			Console console = Console.getInstance();
			ConsoleCommand consoleCommand = new ConsoleCommand(console);
			consoleCommand.setToPrint(fromServer);
			chatClient.enqueueCommand(consoleCommand);
	    }
	    try{
		fromServer = in.readLine();
	    }
	    catch(IOException ioe){
		ioe.printStackTrace();
	    }
	}
    }
}