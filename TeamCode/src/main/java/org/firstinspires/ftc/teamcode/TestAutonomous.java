package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="testing", group="testing")
public class TestAutonomous extends FunctionLibrary {

    @Override
    public void runOpMode() throws InterruptedException {

        setupHardware();
        waitForStart();

        servo.vertical();
        sleep(1000);
        motor.encoderDriveDistance(speed_half,0.25 * one_tile, tLong);
        servo.horizontal();
        sleep(1000);
        motor.encoderDriveDistance(speed_half, -3 * one_tile, tLong);
        servo.vertical();
        sleep(1000);
        motor.runToHit(hardware.armLimitRotateUp, speed_half, tLong);

    }
}