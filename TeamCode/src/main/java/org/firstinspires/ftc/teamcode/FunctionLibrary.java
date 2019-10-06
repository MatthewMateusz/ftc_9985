package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

abstract public class FunctionLibrary  extends LinearOpMode {

    //Variables ...

    enum motorOrientation {
        forward,
        reverse
    }

    motorOrientation frontLeft = motorOrientation.forward;
    motorOrientation frontRight = motorOrientation.forward;
    motorOrientation rearLeft = motorOrientation.forward;
    motorOrientation rearRight = motorOrientation.forward;


    Hardware hardware = new Hardware();
    servo servo = new servo();

    //Setups the hardware for the hardware
    void setupHardware() {
        hardware.init(hardwareMap);
    }

    //Waits until the start button is pressed on the phone
    public void waitForStart() {
        while (!isStarted() && !isStopRequested()) {
            idle();
        }
    }



    class servo {
        public void vertical() {
            hardware.servo_frontLeft.setPosition(0);
            hardware.servo_frontRight.setPosition(1);
            hardware.servo_rearLeft.setPosition(0);
            hardware.servo_rearRight.setPosition(1);

            frontLeft = motorOrientation.forward;
            frontRight = motorOrientation.reverse;
            rearLeft = motorOrientation.forward;
            rearRight = motorOrientation.reverse;
        }

        public void horizontal() {
            hardware.servo_frontLeft.setPosition(0.5);
            hardware.servo_frontRight.setPosition(0.5);
            hardware.servo_rearLeft.setPosition(0.5);
            hardware.servo_rearRight.setPosition(0.5);

            frontLeft = motorOrientation.forward;
            frontRight = motorOrientation.forward;
            rearLeft = motorOrientation.forward;
            rearRight = motorOrientation.forward;
        }
    }

    class motor {

    }
}