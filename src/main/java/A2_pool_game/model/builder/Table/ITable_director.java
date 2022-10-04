package A2_pool_game.model.builder.Table;

public interface ITable_director {
    void callBuilder(ITable_builder builder);
    void construct();
}
