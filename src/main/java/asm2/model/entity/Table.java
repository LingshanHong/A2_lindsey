package asm2.model.entity;

// observer
public class Table implements Entity{

    private String colour;
    private Long tableX;
    private Long tableY;
    private Double friction;

    private Pocket pockets[];

    public Table() {
        pockets = new Pocket[6];
        for (int i = 0; i < 6; i++) {
            pockets[i] = new Pocket();
        }
    }

    public Table(Table table) {
        // deep copy
        this.colour = table.getColour();
        this.tableX = table.getTableX();
        this.tableY = table.getTableY();
        this.friction = table.getFriction();
        this.pockets = table.getPockets();
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setTableX(Long tableX) {
        this.tableX = tableX;
    }

    public void setTableY(Long tableY) {
        this.tableY = tableY;
    }

    public void setFriction(Double friction) {
        this.friction = friction;
    }

    public String getColour() {
        return colour;
    }

    public Long getTableX() {
        return tableX;
    }

    public Long getTableY() {
        return tableY;
    }

    public Double getFriction() {
        return friction;
    }
    
    public Pocket[] getPockets() {
        return pockets;
    }
}
