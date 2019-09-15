package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

abstract public class FunctionLibrary  extends LinearOpMode {


    Hardware robot = new Hardware();
    public void setupHardware() {
        robot.init(hardwareMap);
    }

    public void waitForStart() {
        while (!isStarted() && !isStopRequested()) {
            idle();
        }
    }
}
