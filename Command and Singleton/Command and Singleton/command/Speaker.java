package command;

// REFACTOR ME. I SHOULD BE A SINGLETON!

public class Speaker {
    private static Speaker uniqueInstance;
    private Speaker() {}
    public static Speaker getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new Speaker();
        }
        return uniqueInstance;
    }
    public void beep() {
	System.out.println("Speaker BEEP!");
    }
}