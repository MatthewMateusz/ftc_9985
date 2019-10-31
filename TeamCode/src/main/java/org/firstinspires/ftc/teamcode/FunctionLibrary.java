package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

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

    private ElapsedTime runtime = new ElapsedTime();

    Hardware hardware = new Hardware();
    servo servo = new servo();
    motor motor = new motor();

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
        public void encoderDriveDistance(double speed, double distance, double timeout) {
            double encoderDistnace = distance;

            encoderDrive(speed, encoderDistnace, encoderDistnace, encoderDistnace, encoderDistnace, timeout);
        }

        public void encoderDrive(double power,
                                double frontLeftEncoderTicks,
                                double frontRightEncoderTicks,
                                double rearLeftEncoderTicks,
                                double rearRightEncoderTicks,
                                double timeout) {

            //Reset the encoders on the motors that we want to use
            hardware.motor_rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Make targets for encoder(s)
            int newFrontLeftTarget;
            int newFrontRightTarget;
            int newRearLeftTarget;
            int newRearRightTarget;

            if (opModeIsActive()) {
                idle();

                //Assign target values
                newFrontLeftTarget = hardware.motor_frontLeft.getCurrentPosition() + (int) frontLeftEncoderTicks;
                newFrontRightTarget = hardware.motor_frontRight.getCurrentPosition() + (int) frontRightEncoderTicks;
                newRearLeftTarget = hardware.motor_rearLeft.getCurrentPosition() + (int) rearLeftEncoderTicks;
                newRearRightTarget = hardware.motor_rearRight.getCurrentPosition() + (int) rearRightEncoderTicks;

                //Set the targets


                hardware.motor_rearLeft.setTargetPosition(newRearLeftTarget);
                hardware.motor_frontLeft.setTargetPosition(hardware.motor_rearLeft.getCurrentPosition() + (int) rearLeftEncoderTicks * 100);
                if (rearLeft.equals(motorOrientation.reverse)) {
                    hardware.motor_rearRight.setTargetPosition(hardware.motor_frontLeft.getCurrentPosition() + (int) frontLeftEncoderTicks * 100);
                    hardware.motor_frontRight.setTargetPosition(hardware.motor_frontRight.getCurrentPosition() + (int) frontRightEncoderTicks * 100);
                } else {
                    hardware.motor_rearRight.setTargetPosition(hardware.motor_frontLeft.getCurrentPosition() + (int) frontLeftEncoderTicks * -100);
                    hardware.motor_frontRight.setTargetPosition(hardware.motor_frontRight.getCurrentPosition() + (int) frontRightEncoderTicks * -100);
                }



                //Set the mode on encoders to run to position
                hardware.motor_rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                hardware.motor_rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                hardware.motor_frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                hardware.motor_frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                //Reset the timeout and start motion
                runtime.reset();


                //power = Range.clip(Math.abs(power), 0.0, 1.0);
                double vpower = Range.clip(Math.abs(power), 0.0, 1.0);
                hardware.motor_rearLeft.setPower(vpower);
                hardware.motor_rearRight.setPower(vpower);
                hardware.motor_frontLeft.setPower(vpower);
                hardware.motor_frontRight.setPower(vpower);


                idle();

                while (opModeIsActive() && (runtime.seconds() < timeout) && (hardware.motor_rearLeft.isBusy())) {
                    idle();
                }

                hardware.motor_frontLeft.setPower(0);
                hardware.motor_frontRight.setPower(0);
                hardware.motor_rearLeft.setPower(0);
                hardware.motor_rearRight.setPower(0);

                hardware.motor_frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                hardware.motor_frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                hardware.motor_rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                hardware.motor_rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }

        }
    }
}