package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "teleOp", group = "Linear OpMode")
public class Teleop extends LinearOpMode {
    public BP bp = new BP();
    public Sounds sound = new Sounds();
    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, false);
        double[] powers;
        boolean foundation = false;
        boolean global = false;
        boolean armServo = true;
        boolean clawServo = false;
       // boolean cap = true;
        double heading = 0.0;
        double delta = .4;
        double armtime = 0;
        double foundationtime = 0;
        double captime = 0;
        double globaltime = 0;
        double elevatorpos = 0;

        waitForStart();

        ElapsedTime runtime = new ElapsedTime();
        runtime.startTime();
        bp.HW.armServo.setPosition(0);
        while(opModeIsActive()) {
            heading = bp.getHeading();
            if (global) {
                //an input of postiive one leads to forward translation, right sideways motion, and clockwise rotation
                powers = bp.mecPowerField(Math.pow(gamepad1.left_stick_x, 3), Math.pow(-gamepad1.left_stick_y, 3) , gamepad1.right_stick_x,0,this, heading);
            } else {
                powers = bp.mecPower(Math.pow(gamepad1.left_stick_x, 3), Math.pow(-gamepad1.left_stick_y, 3), gamepad1.right_stick_x);
            }

            bp.HW.mA.setPower(powers[0]);
            bp.HW.mB.setPower(powers[1]);
            bp.HW.mC.setPower(powers[2]);
            bp.HW.mD.setPower(powers[3]);
            if (gamepad1.a && runtime.seconds() - foundationtime >= delta) {
                foundation = !foundation;
                foundationtime = runtime.seconds();
            }
            if (gamepad1.x && runtime.seconds() - armtime >= delta) {
                armServo = !armServo;
                armtime = runtime.seconds();
            }
            if (gamepad1.b && runtime.seconds() - globaltime >= delta) {
                clawServo = !clawServo;
               // global = !global;
                globaltime = runtime.seconds();
            }
            if (gamepad1.y && runtime.seconds() - captime >= delta) {
                 global = !global;
                captime = runtime.seconds();
            }
            if(gamepad1.left_trigger >= 0.1 && bp.HW.limitSwitch.getState() == true) {
                bp.HW.elevator.setPower(.5);
            } else if (gamepad1.right_trigger >= 0.1) {

                bp.HW.elevator.setPower(-.5);
            } else {
                bp.HW.elevator.setPower(0);
            }
            if (foundation) bp.HW.foundationServo.setPosition(.9);
            else bp.HW.foundationServo.setPosition(0);

            if (armServo) bp.HW.armServo.setPosition(.78);
            else bp.HW.armServo.setPosition(0.2);

            if (clawServo) bp.HW.clawServo.setPosition(0.2);
            else bp.HW.clawServo.setPosition(0.5);

           // if (cap) bp.HW.capServo.setPosition(.5);
            //else bp.HW.capServo.setPosition(0);
            telemetry.addData("angle",heading);
            telemetry.addData("global is", global);
            telemetry.addData("input", "%7f:%7f:%7f",gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            telemetry.addData("armpos", armServo);
            telemetry.addData("foundationpos", foundation);
            telemetry.addData("motorOutputs", "%7f:%7f:%7f:%7f", powers[0], powers[1], powers[2], powers[3]);
            //telemetry.addData("capPos", cap);
           telemetry.addData("X,y","%7f,%7f", powers[4],powers[5]);
           telemetry.addData("runtime", runtime.seconds());
            telemetry.update();
        }
        bp.HW.mA.setPower(0);
        bp.HW.mB.setPower(0);
        bp.HW.mC.setPower(0);
        bp.HW.mD.setPower(0);
        bp.HW.elevator.setPower(0);
    }
}