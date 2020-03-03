package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name= "TeleOp Tutroial", group = "Tutorial")
public class TeleopTutorial extends LinearOpMode {
    private DcMotor Motorleft;
    private DcMotor Motorright;
    public Servo armservo;
    DigitalChannel digitalTouch;

    @Override
    public void runOpMode() throws InterruptedException {

        Motorleft = hardwareMap.dcMotor.get("motorleft");
        Motorright = hardwareMap.dcMotor.get("motorright");
        armservo= hardwareMap.servo.get("servotest");
        digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        Motorleft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive()) {
            Motorleft.setPower(-gamepad1.left_stick_y);
            Motorright.setPower(-gamepad1.right_stick_y);
            if (gamepad1.a && digitalTouch.getState()== true) {
                armservo.setPosition(0);

            }else if (gamepad1.b && digitalTouch.getState()== true) {
                armservo.setPosition(1);
            }

            }
            idle();
        }




    }


