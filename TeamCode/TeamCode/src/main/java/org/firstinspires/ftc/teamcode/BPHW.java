package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class BPHW {

  public  DcMotor mA = null;
    DcMotor mB = null;
    DcMotor mC = null;
    DcMotor mD = null;
    BNO055IMU imu = null;
    Servo clawServo = null;

    NormalizedColorSensor colorSensor = null;
    Servo armServo = null;
    Servo capServo = null;
    DcMotorSimple elevator = null;
   // NormalizedColorSensor colorSensor = null;

    public void initHW(LinearOpMode OM, boolean initGyro) {

        mA=OM.hardwareMap.get(DcMotor.class, "motor A");
        mB=OM.hardwareMap.get(DcMotor.class, "motor B");
        mC=OM.hardwareMap.get(DcMotor.class, "motor C");
        mD=OM.hardwareMap.get(DcMotor.class, "motor D");
        imu =OM.hardwareMap.get(BNO055IMU.class, "imu");
        clawServo=OM.hardwareMap.get(Servo.class, "servo");
        colorSensor = OM.hardwareMap.get(NormalizedColorSensor.class, "color");
        elevator= OM.hardwareMap.get(DcMotorSimple.class,"elevator");
        armServo = OM.hardwareMap.get(Servo.class, "arm");
        capServo = OM.hardwareMap.get(Servo.class, "cap");
       // mE = OM.hardwareMap.get(DcMotor.class, "elevatormotor");
        //colorSensor = OM.hardwareMap.get(NormalizedColorSensor.class, "color");
        mB.setDirection(DcMotor.Direction.REVERSE);
        mC.setDirection(DcMotor.Direction.REVERSE);
        mA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //mE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mC.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mD.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if(initGyro) {
            BNO055IMU.Parameters parametersGyro = new BNO055IMU.Parameters();
            imu.initialize(parametersGyro);
        }
       // mE.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
