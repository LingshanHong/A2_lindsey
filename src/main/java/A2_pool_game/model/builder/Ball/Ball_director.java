package A2_pool_game.model.builder.Ball;

public class Ball_director implements IBall_director {
    private IBall_builder builder;
    
    public void callBuilder(IBall_builder builder) {
        this.builder = builder; 
    }

    public void construct() {
        builder.setColour()
                .setXPos()
                .setyPos()
                .setxVel()
                .setyVel()
                .setMass()
                .setStrategy();
    }
}
