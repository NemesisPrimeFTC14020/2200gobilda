package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="armtest", group="A")

public class Servo_sample extends LinearOpMode {

    private Servo test = null;

    @Override
    public void runOpMode() {
        Servo servo = hardwareMap.get(Servo.class, "test");
        DcMotor linearMotor = hardwareMap.get(DcMotor.class,"linear");
        double position = servo.getPosition();
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.a) {
                linearMotor.setPower(1);
            } else if(gamepad1.b) {
                linearMotor.setPower(-1);
            } else {
                linearMotor.setPower(0);
            }
            if (gamepad1.x) {
                position = position + .05;
            } else if (gamepad1.y) {
                position = position - .05;
            }
            position = Math.max(0,position);
            position = Math.min(1, position);
            servo.setPosition(position);
            telemetry.addData("postion", position);
            telemetry.addData("servopos", servo.getPosition());
            telemetry.update();
        }

    }
}
