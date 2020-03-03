package org.firstinspires.ftc.teamcode.TestOpModes;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
@Disabled
@TeleOp(name="elevTest", group="Linear Opmode")

public class elevTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        DcMotor mA = hardwareMap.dcMotor.get ("motor A");

        waitForStart();
        while(opModeIsActive()) {
           if (gamepad1.a) mA.setPower(1); else if (gamepad1.b) mA.setPower(2); else mA.setPower(0);
        }
    }
}
