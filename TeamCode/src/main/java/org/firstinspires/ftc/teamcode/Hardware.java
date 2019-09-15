package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {

    public DcMotor.Direction forwardDirection = DcMotor.Direction.FORWARD;
    public DcMotor.Direction reverseDirection = DcMotor.Direction.REVERSE;

    public DcMotor.ZeroPowerBehavior brakeZero = DcMotor.ZeroPowerBehavior.BRAKE;
    public DcMotor.ZeroPowerBehavior floatZero = DcMotor.ZeroPowerBehavior.FLOAT;

    public double initPosition = 0.5;

    //Hardware
    public DcMotor motor_frontLeft = null;
    public DcMotor motor_frontRight = null;
    public DcMotor motor_rearLeft = null;
    public DcMotor motor_rearRight = null;

    public Servo servo_frontLeft = null;
    public Servo servo_frontRight = null;
    public Servo servo_rearLeft = null;
    public Servo servo_rearRight = null;

    HardwareMap hwMap = null;

    public Hardware() {

    }

    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

        motor_frontLeft = setupMotor(hwMap,"motor_frontLeft", forwardDirection, brakeZero);
        motor_frontRight = setupMotor(hwMap,"motor_frontRight", forwardDirection, brakeZero);
        motor_rearLeft = setupMotor(hwMap,"motor_rearLeft", reverseDirection, brakeZero);
        motor_rearRight = setupMotor(hwMap,"motor_rearRight", reverseDirection, brakeZero);

        servo_frontLeft = setupServo(hwMap, "servo_frontLeft", initPosition);
        servo_frontRight = setupServo(hwMap, "servo_frontLeft", initPosition);
        servo_rearLeft = setupServo(hwMap, "servo_frontLeft", initPosition);
        servo_rearRight = setupServo(hwMap, "servo_frontLeft", initPosition);

    }

    private DcMotor setupMotor(HardwareMap hwMap, String phoneName, DcMotor.Direction motorDirection, DcMotor.ZeroPowerBehavior zeroPower) {
        DcMotor motor = hwMap.get(DcMotor.class, phoneName);
        motor.setDirection(motorDirection);
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(zeroPower);
        return motor;
    }

    public Servo setupServo(HardwareMap hwMap, String phoneName, double position) {
        Servo servo = hwMap.get(Servo.class, phoneName);
        servo.setPosition(position);
        return servo;

    }
}
