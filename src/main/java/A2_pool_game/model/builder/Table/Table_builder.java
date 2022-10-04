package A2_pool_game.model.builder.Table;


public class Table_builder implements ITable_builder {

    private Table table;
    private String colour;
    private Long tableX;
    private Long tableY;
    private Double friction;

    public Table_builder(String colour, Long tableX, Long tableY, Double friction) {

        this.colour = colour;
        this.tableX = tableX;
        this.tableY = tableY;
        this.friction = friction;
        table = new Table();
    }

    // setter methods
    public Table_builder setColour() {
        this.table.setColour(colour);
        return this;
    }

    public Table_builder setTableX() {
        this.table.setTableX(tableX);
        return this;
    }

    public Table_builder setTableY() {
        this.table.setTableY(tableY);
        return this;
    }

    public Table_builder setFriction() {
        this.table.setFriction(friction);
        return this;
    }

    public Table getTable() {

        return this.table;
    }
}
