package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name = "telOp", group = "Linear OpMode")
public class Teleop extends LinearOpMode {
    public BP bp = new BP();
    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, false);
        double[] powers;
        boolean clawServoPos = false;
        boolean global = true;
        boolean armServo = true;
        double heading = 0.0;
        waitForStart();
        ElapsedTime runtime = new ElapsedTime();
        runtime.startTime();
        bp.HW.armServo.setPosition(0);
        while(opModeIsActive()) {
           // if (gamepad1.left_bumper) {
             //   global = !global;
               // sleep(200);
           // }
            heading = bp.getHeading();
            if (global) {
                //an input of postiive one leads to forward translation, right sideways motion, and clockwise rotation
                powers = bp.mecPowerField(gamepad1.left_stick_x, -gamepad1.left_stick_y,gamepad1.right_stick_x,0,this, heading);
            } else {
                powers = bp.mecPower(gamepad1.left_stick_x, -gamepad1.left_stick_y,gamepad1.right_stick_x);
            }

            bp.HW.mA.setPower(powers[0]);
            bp.HW.mB.setPower(powers[1]);
            bp.HW.mC.setPower(powers[2]);
            bp.HW.mD.setPower(powers[3]);
            if (gamepad1.a) {
                clawServoPos = true;
            }
            if(gamepad1.b) {
                clawServoPos = false;
            }
            if (gamepad1.x) {
                armServo = true;
            }
            else if (gamepad1.y) {
                armServo = false;
            }
            if (gamepad1.right_bumper) bp.HW.capServo.setPosition(Math.abs(bp.HW.capServo.getPosition() - 1));
            telemetry.addData("Servo pos", bp.HW.capServo.getPosition());
            if(gamepad1.left_trigger >= 0.1) {
                bp.HW.elevator.setPower(.3);
            } else if (gamepad1.right_trigger >= 0.1) {
                bp.HW.elevator.setPower(-.3);
            } else {
                bp.HW.elevator.setPower(0);
            }
           // if (gamepad1.x) bp.HW.elevator.setPower(1);
            //else if (gamepad1.y)  bp.HW.elevator.setPower(-1);
            //else bp.HW.elevator.setPower(0);
            if (clawServoPos == true) bp.HW.clawServo.setPosition(0);
            else bp.HW.clawServo.setPosition(.9);

            if (armServo == true) bp.HW.armServo.setPosition(.2);
            else bp.HW.armServo.setPosition(.8);
            telemetry.addData("angle",heading);
            telemetry.addData("global is", global);
            telemetry.addData("input", "%7f:%7f:%7f",gamepad1.left_stick_x, -gamepad1.left_stick_y,gamepad1.right_stick_x);
            telemetry.addData("armpos", armServo);
            telemetry.addData("armpos", clawServoPos);
           telemetry.addData("X,y","%7f,%7f", powers[4],powers[5]);
            telemetry.update();
        }
        bp.HW.mA.setPower(0);
        bp.HW.mB.setPower(0);
        bp.HW.mC.setPower(0);
        bp.HW.mD.setPower(0);
        bp.HW.elevator.setPower(0);
    }
}