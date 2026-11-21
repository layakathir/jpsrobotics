package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.hal.FRCNetComm.tResourceType;

public class DriveSubsystem extends SubsystemBase {
    private final SparkMax leftFront;
    private final SparkMax leftBack;
    private final SparkMax rightFront;
    private final SparkMax rightBack;

    private final DifferentialDrive drive;

    public DriveSubsystem(SparkMax leftFront, SparkMax rightFront) {
        this.leftFront = leftFront;
        this.rightFront = rightFront;
        SparkMaxConfig leftBackConfig = new SparkMaxConfig();
        leftBackConfig.follow(leftFront);
        SparkMaxConfig rightBackConfig = new SparkMaxConfig();
        leftBackConfig.follow(rightFront);
        drive = new DifferentialDrive(leftFront, rightFront);
    }


    public void arcadeDrive(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed, zRotation);
    }

    public void stopDrive() {
        drive.stopMotor();
    }

    public void moveAndTurn() {
        new RunCommand(
                () -> drive.arcadeDrive(0.5, 0)
        ).withTimeout(3.0);

        new FunctionalCommand(
                () -> drive.getGyroAngle() + 90);
    }
}