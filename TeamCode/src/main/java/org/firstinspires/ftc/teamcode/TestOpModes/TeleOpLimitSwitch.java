package org.firstinspires.ftc.teamcode.TestOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name= "TeleOp Switch Tutroial", group = "Tutorial")
public class TeleOpLimitSwitch extends LinearOpMode {
    private DcMotor Motorleft;
    private DcMotor Motorright;
    DigitalChannel digitalTouch;  // Hardware Device Object


    @Override
    public void runOpMode() throws InterruptedException {
        digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");
        digitalTouch.setMode(DigitalChannel.Mode.INPUT);
        Motorleft = hardwareMap.dcMotor.get("motorleft");
        Motorright = hardwareMap.dcMotor.get("motorright");

        Motorleft.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive()) {
            idle();
            if (digitalTouch.getState() == true) {
                Motorleft.setPower(-gamepad1.left_stick_y);
                Motorright.setPower(-gamepad1.right_stick_y);
                telemetry.addData("Digital Touch", "Is Not Pressed");
            } else {
                telemetry.addData("Digital Touch", "Is Pressed");
            }

            telemetry.update();
        }


    }

}