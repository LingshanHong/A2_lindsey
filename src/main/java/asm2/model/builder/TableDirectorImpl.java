package asm2.model.builder;

public class TableDirectorImpl implements TableDirector {
    private TableBuilder builder;

    public void setBuilder(TableBuilder builder) {
        this.builder = builder;  
    }
    
    public void construct() {
        builder.setColour()
                .setTableX()
                .setTableY()
                .setFriction();
    }
}
