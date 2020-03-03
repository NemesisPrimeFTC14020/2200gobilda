package org.firstinspires.ftc.teamcode.AutonsPlan;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BP;
@Disabled
@Autonomous(name = "Level2FoundationBlue", group = "Linear OpMode")
public class Level2FoundationBlue extends LinearOpMode {
    public BP bp = new BP();

    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, true);
        waitForStart();
        int forwarddis = 36;
        int resistance = 20;
        int backwarddis = -1 * (forwarddis + resistance);
        //Lv2:
        /*bp.encDriveFin(1, 29, this);
        bp.curveClaw(0);
        sleep(2000);
        bp.encDriveFin(1, -30, this);
        bp.curveClaw(0.5);
        sleep(2000);
        bp.encDriveSin(1, -26.25, this);
        bp.curveClaw(0.25);
        sleep(2000);
        bp.encDriveSin(1, -50.75, this);
        */
        // moves right in
        bp.curveClaw(.25);
        bp.encDriveSin(1, -14, this);
        // elevator up 2.5 in
        bp.yClawTime('+', 2.5, this);
        // moves forward 34 in
        bp.encDriveFin(1, forwarddis, this);
        // elevator down 2.5 in
        bp.yClawTime('-', 2.5, this);
        // moves backward 28 in
        bp.encDriveFin(1, backwarddis, this);
        // elevator up 2 in
        bp.yClawTime('+', 2, this);
        bp.encDriveFin(.7,4,this);
        bp.encDriveFin(.7,-3,this);
        bp.gyroTurn(.5,-7.5);

        // moves right 48 in
        bp.encDriveSin(1, 36, this);
        bp.encDriveFin(1, 22, this);
        bp.encDriveSin(1,24,this);

    }
}