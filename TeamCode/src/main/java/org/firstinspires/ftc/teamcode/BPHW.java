package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class BPHW {

  public  DcMotor mA = null;
  public DcMotor mB = null;
  public DcMotor mC = null;
  public DcMotor mD = null;
    public DcMotor leftEncoder, rightEncoder, frontEncoder;
  public  BNO055IMU imu = null;
    public  Servo foundationServo = null;
    public Servo clawServo = null;
    public Servo armServo = null;
    public DigitalChannel limitSwitch = null;

    public  DcMotorSimple elevator = null;

    public void initHW(LinearOpMode OM, boolean initGyro) {

        mA=OM.hardwareMap.get(DcMotor.class, "motor A");
        mB=OM.hardwareMap.get(DcMotor.class, "motor B");
        mC=OM.hardwareMap.get(DcMotor.class, "motor C");
        mD=OM.hardwareMap.get(DcMotor.class, "motor D");
        imu =OM.hardwareMap.get(BNO055IMU.class, "imu");
        leftEncoder = OM.hardwareMap.dcMotor.get("leftEncoder");
        rightEncoder = OM.hardwareMap.dcMotor.get("rightEncoder");
        frontEncoder = OM.hardwareMap.dcMotor.get("frontEncoder");
        foundationServo=OM.hardwareMap.get(Servo.class, "wing");
        elevator= OM.hardwareMap.get(DcMotor.class,"elevator");
        armServo = OM.hardwareMap.get(Servo.class, "arm");
        clawServo = OM.hardwareMap.get(Servo.class, "claw");
        limitSwitch = OM.hardwareMap.get(DigitalChannel.class, "Limit");
        mA.setDirection(DcMotor.Direction.REVERSE);
        mD.setDirection(DcMotor.Direction.REVERSE);
      /*  mA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mC.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mD.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        mA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mC.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mD.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/
        mA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mC.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mD.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if(initGyro) {
            BNO055IMU.Parameters parametersGyro = new BNO055IMU.Parameters();
            imu.initialize(parametersGyro);
        }
    }
}
