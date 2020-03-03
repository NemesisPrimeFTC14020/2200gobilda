// Container for all of the code our team creates for the robot.
package org.firstinspires.ftc.teamcode;

// Imports code from other classes. This allows us to use the FTC hardware in our software.
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

// Sets the name and group (order) that the mode will show in the list on the phone. "TeleOp" means manual driving.
@TeleOp(name = "Teleoperation (TeleOp)", group = "A")

// Class which contains our robot code. "extends" means applying pre-existing classes to the code, which @Override will ensure we can
// replace the samples with our own code.
public class kitbotOp extends LinearOpMode {

    private Sounds sounds = new Sounds();

    // This creates our motor objects. We can reference this to perform actions or gather data.
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    // Method which runs when you press "INIT". The second part states a specific error can be thrown here (don't worry about this).
    public void runOpMode() throws InterruptedException {
        // This is telemetry, data that we can see on the phone while the code is running.
        // This is most useful as a debugging tool to determine software issues and fix them.
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // This maps our motor objects which connects our code to the motors on the robot.
        leftMotor = hardwareMap.get(DcMotor.class, "left motor");
        rightMotor = hardwareMap.get(DcMotor.class, "right motor");

        // The right motor is configured to run the powers the other way; we can fix this in the code.
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        double pastDrive = 0;
        double pastTurn = 0;
        double pastLeftPower = 0;
        double pastRightPower = 0;
        double drive;
        double turn;
        double x = 0;
        double y = 0;

        // Waits until the "Play' button is pressed.
        waitForStart();

        // Count down & directions.
        for (int time = 9; time >= 0; time--) {
            telemetry.addLine("Hold left stick UP to drive forward, and DOWN to drive backward.");
            telemetry.addLine("Hold right stick LEFT to drive left, and RIGHT to drive right.");
            telemetry.addLine(String.format("Game starting in %1$s seconds...", time));
            telemetry.update();
            if (time == 9) sounds.playSound("ss_roger_roger", hardwareMap, 1, false, 0, 1);
            if (time == 7) sounds.playSound("ss_laser_burst", hardwareMap, 1, true, 0, 1);
            if (time == 6) sounds.playSound("ss_light_saber", hardwareMap, 1, true, 0, 1);
            if (time == 3) sounds.playSound("ss_power_up", hardwareMap, 1, true, 0, 1);
            sleep(1000);
            if (time == 0) sounds.playSound("ss_bb8_up", hardwareMap, 1, true, 0, 1);
        }

        runtime.reset();

        // While the opMode is running, the code in this "while" loop will run
        while (opModeIsActive()) {

            // This records the current joystick input on the gamepad.
            drive = gamepad1.left_stick_y / 1.25;
            turn = gamepad1.right_stick_x / 1.25;

            // This is the method which sets the power on the motors (causes it to drive!)
            // Turning works by giving the left motors a positive power and the right motors a negative power to go right.
            // If the turn value is negative, then the robot turns the other way.
            // Range normalizes, or sets into an acceptable range, the values the motors can be set into.
            // For example, if drive and turn are both 1, this gives 2 for the left motor.
            // The motor can only be set power from (-1, 1) so it is set to 1 instead.
            leftMotor.setPower(Range.clip(drive - turn, -1.0, 1.0));
            rightMotor.setPower(Range.clip(drive + turn, -1.0, 1.0));

            if (leftMotor.getPower() == 1 && rightMotor.getPower() == 1) x++;
            else x = 0;
            if (x == 10000 && !sounds.isPlaying("ss_fly_by")) sounds.playSound("ss_fly_by", hardwareMap, 0.5f, false, 0 , 1);

            if (leftMotor.getPower() == 0 && rightMotor.getPower() == 0) y++;
            else y = 0;
            if (y == 10000 && !sounds.isPlaying("ss_darth_vader")) sounds.playSound("ss_darth_vader", hardwareMap, 0.5f, false, 0 , 1);

            if (turn != 0 && !sounds.isPlaying("ss_alarm")) sounds.playSound("ss_alarm", hardwareMap, 0.5f, false, 0, 1);

            if (drive != 0 && !sounds.isPlaying("ss_light_saber_short")) sounds.playSound("ss_light_saber_short", hardwareMap, 1, false, 0, 1);

            /*if (drive != 0 || turn != 0) {
                pastDrive = drive;
                pastTurn = turn;
                pastLeftPower = leftMotor.getPower();
                pastRightPower = rightMotor.getPower();
            }
            else {
                telemetry.addLine("MOTOR POWER IS 0. Previous motion data:");
            }
            telemetry.addData("Left stick force", pastDrive);
            telemetry.addData("Right stick force", pastTurn);
            telemetry.addData("Left Power", pastLeftPower);
            telemetry.addData("Right Power", pastRightPower); */
            telemetry.addLine("Hold left stick UP to drive forward, and DOWN to drive backward.");
            telemetry.addLine("Hold right stick LEFT to drive left, and RIGHT to drive right.");
            telemetry.addLine(String.format("Time left: %1$ss", (60 - runtime.seconds())));
            telemetry.update();

            if (runtime.seconds() > 40 && runtime.seconds() < 41 && !sounds.isPlaying("ss_siren")) sounds.playSound("ss_siren", hardwareMap, 1, false, 0, 1);
            if (runtime.seconds() >= 60) {
                sounds.playSound("ss_mine", hardwareMap, 1, false, 0, 1);
                telemetry.addLine("Game finished! Thanks for playing. See you soon!");
                telemetry.update();
                leftMotor.setPower(0);
                rightMotor.setPower(0);
                sleep(500);
                telemetry.addLine("Game finished! Thanks for playing. See you soon!");
                telemetry.update();
                sleep(4500);
                break;
            }
        }
        // Stops motors when stop is pressed.
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
}