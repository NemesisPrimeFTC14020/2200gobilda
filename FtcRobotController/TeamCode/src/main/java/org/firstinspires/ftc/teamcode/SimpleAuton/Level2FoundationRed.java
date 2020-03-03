package org.firstinspires.ftc.teamcode.AutonsPlan;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BP;
@Disabled
@Autonomous(name = "Level2FoundationRed", group = "Linear OpMode")
public class Level2FoundationRed extends LinearOpMode {
    public BP bp = new BP();

    @Override
    public void runOpMode() throws InterruptedException {
        bp.HW.initHW(this, true);
        waitForStart();
        int forwarddis = 38;
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
        // moves left 12 in
        bp.curveClaw(0.25);
        bp.encDriveSin(1, 6, this);
        // elevator up 2.5 in
        bp.yClawTime('+', 2.5, this);
        // moves forward 34 in
        bp.encDriveFin(1, forwarddis, this);
        // elevator down 2.5 in
        bp.yClawTime('-', 1.25, this);
        // moves backward 28 in
        bp.encDriveFin(1, backwarddis, this);
        // elevator up 2 in
        bp.yClawTime('+', 2, this);
        bp.gyroTurn(1, 10);
        // moves right 48 in
        bp.encDriveSin(1, -30, this);
        bp.encDriveFin(1, 27, this);
        bp.encDriveSin(1, -21, this);
    }
}