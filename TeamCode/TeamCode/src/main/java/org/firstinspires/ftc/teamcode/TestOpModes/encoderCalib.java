package org.firstinspires.ftc.teamcode.TestOpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BP;
@Disabled
@Autonomous(name = "encCalib", group = "Linear OpMode")
public class encoderCalib extends LinearOpMode {
    public BP bp = new BP();
    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, true);
        waitForStart();
        bp.encDriveF(1,500,this);
        telemetry.addData("encoderval", bp.HW.mA.getCurrentPosition());
        telemetry.update();
    }
}
