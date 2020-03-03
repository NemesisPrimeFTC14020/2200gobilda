package org.firstinspires.ftc.teamcode.TestOpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.BP;

@Disabled
@Autonomous(name = "encCalibSide", group = "Linear OpMode")
public class OdomCalib extends LinearOpMode {
    public BP bp = new BP();
    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, true);
        bp.HW.leftEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bp.HW.rightEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bp.HW.frontEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bp.HW.leftEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bp.HW.rightEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bp.HW.frontEncoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addLine("Ready to Start");
        telemetry.update();
        waitForStart();
        bp.encDriveS(1,500,this);
        telemetry.addData("leftencoderval",   bp.HW.leftEncoder.getCurrentPosition());
        telemetry.addData("rightencoderval",   bp.HW.rightEncoder.getCurrentPosition());
        telemetry.addData("frontencoderval",   bp.HW.frontEncoder.getCurrentPosition());
        telemetry.update();
    }
}
