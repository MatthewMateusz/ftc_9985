package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

abstract public class FunctionLibrary  extends LinearOpMode {


    Hardware robot = new Hardware();

    //Setups the hardware for the robot
    void setupHardware() {
        robot.init(hardwareMap);
    }

    //Waits until the start button is pressed on the phone
    public void waitForStart() {
        while (!isStarted() && !isStopRequested()) {
            idle();
        }
    }
}