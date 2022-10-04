package A2_pool_game.model.builder.Table;

public class Table_director implements ITable_director {
    private ITable_builder builder;

    public void callBuilder(ITable_builder builder) {
        this.builder = builder;  
    }

    public void construct() {
        builder.setColour()
                .setTableX()
                .setTableY()
                .setFriction();
    }
}
