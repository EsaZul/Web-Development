package command;

public class MotorVibrateCommand implements Command{
    // IMPLEMENT ME!
    private VibrationMotor vibrationMotor;
    public MotorVibrateCommand(VibrationMotor vibrationMotor){
        this.vibrationMotor = vibrationMotor;
    }
    public void execute(){ vibrationMotor.vibrate(); }
}