package org.firstinspires.ftc.teamcode.TestOpModes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.BP;


@TeleOp(name = "Servodebug", group = "Linear OpMode")
public class Servodebug extends LinearOpMode {
    public BP bp = new BP();
    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, false);
        double[] powers;
        boolean foundation = false;
        boolean debug = true;
        int stage = 0;
        int sign = 0;
        int num1 = 2;
        double num2 = 0;
        double num3 = 0;
        double num4 = 0;
        int servo = 0;
        Servo[] servos = {bp.HW.armServo, bp.HW.clawServo, bp.HW.foundationServo};
        String[] names = {"armServo", "clawServo", "foundationServo"};

        waitForStart();

        ElapsedTime runtime = new ElapsedTime();
        runtime.startTime();
        bp.HW.armServo.setPosition(0);
        while(opModeIsActive()) {
            if (gamepad1.left_bumper) debug = !debug;
            if (debug) {
                switch (stage) {
                    case 0:
                        if (gamepad1.dpad_up) sign = 1;
                        else if (gamepad1.dpad_down) sign = -1;
                        else {
                            telemetry.addLine("Direction UP = + DOWN = -");
                            telemetry.update();
                        }
                        if (sign != 0) stage++;
                        sleep(200);
                        break;
                    case 1:
                        if (gamepad1.dpad_up) num1 = 1;
                        else if (gamepad1.dpad_down) num1 = 0;
                        else {
                            telemetry.addLine("num1 UP = 1 DOWN = 0");
                            telemetry.update();
                        }
                        if (num1 == 1) stage = 4;
                        else if (num1 != 2) stage++;
                        sleep(200);
                        break;
                    case 2:
                        if (gamepad1.dpad_up) num2++;
                        else if (gamepad1.dpad_down) num2--;
                        if (num2 == 10) num2 = 0;
                        if (num2 == -1) num2 = 9;
                        if (gamepad1.dpad_right) stage++;
                        else {
                            telemetry.addData("num2 UP = + DOWN = -", num2);
                            telemetry.update();
                        }
                        sleep(200);
                        break;
                    case 3:
                        if (gamepad1.dpad_up) num3++;
                        else if (gamepad1.dpad_down) num3--;
                        if (num3 == 10) num3 = 0;
                        if (num3 == -1) num3 = 9;
                        if (gamepad1.dpad_right) stage++;
                        else {
                            telemetry.addData("num3 UP = + DOWN = -", num3);
                            telemetry.update();
                        }
                        sleep(200);
                        break;
                    case 4:
                        num4 = sign * (num1 + (num2/10) + (num3/100));
                        stage = 0;
                        sign = 0;
                        num1 = 2;
                        num2 = 0;
                        num3 = 0;
                        debug = false;
                        break;
                }
            }
            else {
                if (gamepad1.b) servo++;
                if (servo == 3) servo = 0;

                telemetry.addData("Servo value:", num4);
                telemetry.addData("Servo", names[servo]);
                telemetry.addData("Servo pos", servos[servo].getPosition());
                telemetry.update();

                if (gamepad1.a) servos[servo].setPosition(num4);
            }
        }
    }
}