package org.firstinspires.ftc.teamcode.TestOpModes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@Disabled
@TeleOp(name="Mechtest", group="Linear Opmode")

public class mecanumProofOfConcept extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        DcMotor mA = hardwareMap.dcMotor.get ("motor A");
        DcMotor mB = hardwareMap.dcMotor.get ("motor B");
        DcMotor mC = hardwareMap.dcMotor.get ("motor C");
        DcMotor mD = hardwareMap.dcMotor.get ("motor D");
        mA.setDirection(DcMotor.Direction.REVERSE);
        mD.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while(opModeIsActive()) {
                double iX = -gamepad1.left_stick_x;
                if (Math.abs(iX)<= 0.05) iX = 0;
                double iY = gamepad1.left_stick_y;
                if (Math.abs(iY)<= 0.05) iY = 0;
                double iR = -gamepad1.right_stick_x;
                if (Math.abs(iR)<= 0.05) iR = 0;
                //acquire three desired movement inputs from the
                // driver, y translation, x translation, rotational mostion
                double pA = iR+iY+iX;
                double pB = -iR+iY-iX;
                double pC = -iR+iY+iX;
                double pD = iR+iY-iX;
            //each axis of motion corresponds to a forward
            // or backwards drive of a specific motor.
            // Add or subtract these values to combine all inputs
            double max = Math.max(1, pA);
            max = Math.max(max, pB);
            max = Math.max(max, pC);
            max = Math.max(max, pD);
            pA /= max;
            pB /= max;
            pC /= max;
            pD /= max;
            //as these values can be greater than 1,
            // divide these values by the largest
            // value in order to both maintain a
            // proportional ratio of motor input across the drivtrain
            mA.setPower(pA);
            mB.setPower(pB);
            mC.setPower(pC);
            mD.setPower(pD);
            //set these values as the respective motor powers
        }
        mA.setPower(0);
        mB.setPower(0);
        mC.setPower(0);
        mD.setPower(0);
    }
}
