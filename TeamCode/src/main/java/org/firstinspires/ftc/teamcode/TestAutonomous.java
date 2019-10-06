package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="testing", group="testing")
public class TestAutonomous extends FunctionLibrary {

    @Override
    public void runOpMode() throws InterruptedException {

        setupHardware();
        waitForStart();

        servo.vertical();
        sleep(5000);
        servo.horizontal();
        sleep(5000);

    }
}
