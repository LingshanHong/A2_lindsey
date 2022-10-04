package A2_pool_game.model.builder.Ball;

public interface IBall_director {
    void callBuilder(IBall_builder builder);
    void construct();
}
