package A2_pool_game.view;

import A2_pool_game.model.builder.Table.Pocket;
import A2_pool_game.model.builder.Table.Table;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Table_view {
    
    private Table table;
    private Rectangle tableShape;
    private Rectangle tableFrame[];
    private final int frameWidth = 10;

    private Circle pockets[];

    public Table_view(Table table) {
        this.table = table;
        this.tableShape = new Rectangle(table.getTableX(), table.getTableY());
        this.tableFrame = new Rectangle[4];
        this.pockets = new Circle[6];
    }

    // purpose: draw table UI 
    public void drawTable(Pane pane, double xTableOffset, double yTableOffset) {

        // table colour
        tableShape.setFill(Color.FORESTGREEN);
 
        // round corners
        tableShape.setArcHeight(30.0d);
        tableShape.setArcWidth(30.0d);
       
        tableShape.setX(xTableOffset);
        tableShape.setY(yTableOffset);

        tableShape.setViewOrder(1000);
        pane.getChildren().add(tableShape);

        /* build up table frame */
        tableFrame[0] = new Rectangle(xTableOffset - frameWidth, yTableOffset, frameWidth, table.getTableY()); // left
        tableFrame[1] = new Rectangle(xTableOffset, yTableOffset - frameWidth, table.getTableX(), frameWidth); // top
        tableFrame[2] = new Rectangle(table.getTableX() + xTableOffset, yTableOffset, frameWidth, table.getTableY()); // right
        tableFrame[3] = new Rectangle(xTableOffset, table.getTableY() + yTableOffset, table.getTableX(), frameWidth); // bottom


        // add the frame around the table
        for (Rectangle wood_frame : tableFrame) {
            wood_frame.setStroke(Color.SANDYBROWN);
            wood_frame.setStrokeType(StrokeType.OUTSIDE);
            wood_frame.setFill(Color.SANDYBROWN);
            wood_frame.setStrokeWidth(5);
            wood_frame.setViewOrder(1000);
            pane.getChildren().add(wood_frame);
        }


        /* Pockets Properties */
        Pocket pocketsData[] = table.getPockets();

        pockets[0] = new Circle(xTableOffset, yTableOffset, 17); // top left
        // set pocketsData field as the pockets above
        pocketsData[0].setXPos(0.0d);
        pocketsData[0].setyPos(0.0d);

        pockets[1] = new Circle(table.getTableX() + xTableOffset, yTableOffset, 17); // top right
        // set pocketsData field as the pockets above
        pocketsData[1].setXPos(table.getTableX() + 0.0d);
        pocketsData[1].setyPos(0.0d);


        pockets[2] = new Circle(xTableOffset, table.getTableY() + yTableOffset, 17); // bottom left
        // set pocketsData field as the pockets above
        pocketsData[2].setXPos(0.0d);
        pocketsData[2].setyPos(table.getTableY() + 0.0d);


        pockets[3] = new Circle(table.getTableX() + xTableOffset, table.getTableY() + yTableOffset, 17); // bottom right
        // set pocketsData field as the pockets above
        pocketsData[3].setXPos(table.getTableX() + 0.0d);
        pocketsData[3].setyPos(table.getTableY() + 0.0d);


        pockets[4] = new Circle(xTableOffset + table.getTableX() / 2, yTableOffset - 10, 17); // top middle
        // set pocketsData field as the pockets above
        pocketsData[4].setXPos(table.getTableX() / 2 + 0.0d);
        pocketsData[4].setyPos(0.0d);


        pockets[5] = new Circle(xTableOffset + table.getTableX() / 2, table.getTableY() + yTableOffset + 10, 17); // bottom middle
        // set pocketsData field as the pockets above
        pocketsData[5].setXPos(table.getTableX() / 2 + 0.0d);
        pocketsData[5].setyPos(table.getTableY() + 0.0d);

        
        for (Circle pocket : pockets) {
            pocket.setRadius(17);
            pocket.setFill(Color.BLACK);
            pocket.setStroke(Color.SANDYBROWN);
            pocket.setStrokeType(StrokeType.INSIDE);
            pocket.setViewOrder(1000);
            pane.getChildren().add(pocket);
        }

    }
}
