package asm2.model.builder;

import asm2.model.entity.Entity;
import asm2.model.entity.Table;

public class TableBuilderImpl implements TableBuilder {

    private Table table;
    private String colour;
    private Long tableX;
    private Long tableY;
    private Double friction;

    public TableBuilderImpl(String colour, Long tableX, Long tableY, Double friction) {

        this.colour = colour;
        this.tableX = tableX;
        this.tableY = tableY;
        this.friction = friction;
        table = new Table();
    }

    // setter methods
    public TableBuilderImpl setColour() {
        this.table.setColour(colour);
        return this;
    }

    public TableBuilderImpl setTableX() {
        this.table.setTableX(tableX);
        return this;
    }

    public TableBuilderImpl setTableY() {
        this.table.setTableY(tableY);
        return this;
    }

    public TableBuilderImpl setFriction() {
        this.table.setFriction(friction);
        return this;
    }

    public Entity getProduct() {
        return this.table;
    }
}
