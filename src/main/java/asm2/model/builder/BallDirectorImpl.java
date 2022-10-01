package asm2.model.builder;

public class BallDirectorImpl implements BallDirector {
    private BallBuilder builder; 
    
    public void setBuilder(BallBuilder builder) {
        this.builder = builder; 
    }

    public void construct() {
        builder.setColour()
                .setPositionX()
                .setPositionY()
                .setVelocityX()
                .setVelocityY()
                .setMass()
                .setStrategy();
    }
}
