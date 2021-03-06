package org.firstinspires.ftc.teamcode.AutonsPlan;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BP;
@Disabled
@Autonomous(name = "Lv2QuarryBlue", group = "Linear OpMode")
public class Level3QuarryBlue extends LinearOpMode {
    public BP bp = new BP();

    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, true);
        waitForStart();
        //Lv2:
//Curve claw forward
        bp.curveClaw(.2);
        sleep(2000);
// Open claw
        bp.Claw('-');
        sleep(2000);
//Move forward x meters
        bp.encDriveFin(1, 32,this);
// Scan for skystone
        int dis = bp.scanforStone('-', this);
//Collect stone with claw
        bp.Claw('+');
        sleep(2000);
//Lift claw 0.3 with servo
        bp.curveClaw(0.25);
        sleep(2000);
// Drive back 12 inches for clearance
        bp.encDriveFin(1, -12, this);
// Drive 54 inches towards skybridge
        bp.encDriveSin(1, -54 - dis,this);
//Extend claw
        bp.Claw('-');
        sleep(2000);
// Drive back to line
        bp.encDriveSin(1, 18,this);
        bp.Claw('+');

    }
}