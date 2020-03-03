package org.firstinspires.ftc.teamcode.TestOpModes;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@TeleOp(name="Clawtest", group="Linear Opmode")

public class flywheelTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        Servo servo = hardwareMap.servo.get("servo");
        waitForStart();
        servo.setPosition(0.0);
        while(opModeIsActive()) {
           if (gamepad1.a) servo.setPosition(1.0); else if (gamepad1.b) servo.setPosition(0.0);
           telemetry.addData("Servopos", servo.getPosition());
           telemetry.update();
        }
    }
}
