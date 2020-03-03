package org.firstinspires.ftc.teamcode.AutonsPlan;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BP;
@Disabled
@Autonomous(name = "Lv3QuarryRed", group = "Linear OpMode")
public class Level3QuarryRed extends LinearOpMode {
    public BP bp = new BP();

    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, true);
        waitForStart();
        //Lv3:
//Curve claw forward
        bp.curveClaw(0.25);
        sleep(2000);
//Extend claw
        bp.Claw('-');
        sleep(2000);
//Move forward x meters
        bp.encDriveFin(1, 30,this);
//Scan for Skystone
        int dis = bp.scanforStone('+', this);
//Claw Skystone
        bp.Claw('+');
        sleep(2000);
//Drive back 5 inches for clearance
        bp.encDriveFin(1, -20, this);
//Drive x inches towards skybridge
        bp.encDriveSin(1, 60 - dis,this);
//Extend claw
        bp.Claw('-');
        sleep(2000);
//Drive back to line
        bp.encDriveSin(1, -18,this);

    }
}