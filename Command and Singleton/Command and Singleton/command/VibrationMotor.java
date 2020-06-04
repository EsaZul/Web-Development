package command;

// REFACTOR ME. I SHOULD BE A SINGLETON!

public class VibrationMotor {
    private static VibrationMotor uniqueInstance;
    private VibrationMotor() {}
    public static VibrationMotor getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new VibrationMotor();
        }
        return uniqueInstance;
    }
    public void vibrate() {
	System.out.println("Motor VIBRATE");
    }
}