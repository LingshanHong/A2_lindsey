package asm2.view;

import asm2.model.entity.Pocket;
import asm2.model.entity.Table;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class TableView {
    
    private Table table;
    private Rectangle tableView;
    private Rectangle sideBars[];
    private Region corners[];
    private Circle pockets[];

    private final int SIDEBAR_WIDTH = 25;
    private final int CORNER_WIDTH = 50;
    
    public TableView(Table table) {
        this.table = table;
        this.tableView = new Rectangle(table.getTableX(), table.getTableY());
        this.sideBars = new Rectangle[4];
        this.corners = new Region[4];
        this.pockets = new Circle[6];
    }

    // purpose: draw table UI 
    public void draw(Pane pane, double xTableOffset, double yTableOffset) {

        /* Table Properties */
        // table colour
        tableView.setFill(Color.GREEN);
 
        // round corners
        tableView.setArcHeight(20.0d);
        tableView.setArcWidth(20.0d);

       
        tableView.setX(xTableOffset);
        tableView.setY(yTableOffset);

        tableView.setViewOrder(1000);
        pane.getChildren().add(tableView);

        /* Side Bar Properties */
        sideBars[0] = new Rectangle(xTableOffset - SIDEBAR_WIDTH, yTableOffset, SIDEBAR_WIDTH, table.getTableY()); // left
        sideBars[1] = new Rectangle(xTableOffset, yTableOffset - SIDEBAR_WIDTH, table.getTableX(), SIDEBAR_WIDTH); // top
        sideBars[2] = new Rectangle(table.getTableX() + xTableOffset, yTableOffset, SIDEBAR_WIDTH, table.getTableY()); // right
        sideBars[3] = new Rectangle(xTableOffset, table.getTableY() + yTableOffset, table.getTableX(), SIDEBAR_WIDTH); // bottom
        // set stroke
        for (Rectangle sideBar : sideBars) {
            sideBar.setStroke(Color.BLACK);
            sideBar.setStrokeWidth(2);
            sideBar.setStrokeType(StrokeType.INSIDE);
            sideBar.setFill(Color.SIENNA);
            sideBar.setViewOrder(1000);
            pane.getChildren().add(sideBar);
        }


        /* Corners Properties */
        corners[0] = new Region();
        corners[0].setLayoutX(xTableOffset - SIDEBAR_WIDTH);
        corners[0].setLayoutY(yTableOffset - SIDEBAR_WIDTH);
        corners[0].setStyle("-fx-background-color: gold; -fx-background-radius: 0 0 50 0;");
        
        // top right
        corners[1] = new Region();
        corners[1].setLayoutX(table.getTableX() + xTableOffset - SIDEBAR_WIDTH);
        corners[1].setLayoutY(yTableOffset - SIDEBAR_WIDTH);
        corners[1].setStyle("-fx-background-color: gold; -fx-background-radius: 0 0 0 50;");

        // bottom right
        corners[2] = new Region();
        corners[2].setLayoutX(table.getTableX() + xTableOffset - SIDEBAR_WIDTH);
        corners[2].setLayoutY(table.getTableY() + yTableOffset - SIDEBAR_WIDTH);
        corners[2].setStyle("-fx-background-color: gold; -fx-background-radius: 50 0 0 0;");

        // bottom left
        corners[3] = new Region();
        corners[3].setLayoutX(xTableOffset - SIDEBAR_WIDTH);
        corners[3].setLayoutY(table.getTableY() + yTableOffset - SIDEBAR_WIDTH);
        corners[3].setStyle("-fx-background-color: gold; -fx-background-radius: 0 50 0 0;");

        for (Region corner : corners) {
            corner.setPrefWidth(CORNER_WIDTH);
            corner.setPrefHeight(CORNER_WIDTH);
            corner.setViewOrder(1000);
            pane.getChildren().add(corner);
        }


        /* Pockets Properties */
        Pocket pocketsData[] = table.getPockets();

        pockets[0] = new Circle(xTableOffset, yTableOffset, 17); // top left
        // set pocketsData field as the pockets above
        pocketsData[0].setPositionX(0.0d);
        pocketsData[0].setPositionY(0.0d);
        pocketsData[0].setRadius(17);

        pockets[1] = new Circle(table.getTableX() + xTableOffset, yTableOffset, 17); // top right
        // set pocketsData field as the pockets above
        pocketsData[1].setPositionX(table.getTableX() + 0.0d);
        pocketsData[1].setPositionY(0.0d);
        pocketsData[1].setRadius(17);

        pockets[2] = new Circle(xTableOffset, table.getTableY() + yTableOffset, 17); // bottom left
        // set pocketsData field as the pockets above
        pocketsData[2].setPositionX(0.0d);
        pocketsData[2].setPositionY(table.getTableY() + 0.0d);
        pocketsData[2].setRadius(17);

        pockets[3] = new Circle(table.getTableX() + xTableOffset, table.getTableY() + yTableOffset, 17); // bottom right
        // set pocketsData field as the pockets above
        pocketsData[3].setPositionX(table.getTableX() + 0.0d);
        pocketsData[3].setPositionY(table.getTableY() + 0.0d);
        pocketsData[3].setRadius(17);

        pockets[4] = new Circle(xTableOffset + table.getTableX() / 2, yTableOffset - 10, 17); // top middle
        // set pocketsData field as the pockets above
        pocketsData[4].setPositionX(table.getTableX() / 2 + 0.0d);
        pocketsData[4].setPositionY(0.0d);
        pocketsData[4].setRadius(17);

        pockets[5] = new Circle(xTableOffset + table.getTableX() / 2, table.getTableY() + yTableOffset + 10, 17); // bottom middle
        // set pocketsData field as the pockets above
        pocketsData[5].setPositionX(table.getTableX() / 2 + 0.0d);
        pocketsData[5].setPositionY(table.getTableY() + 0.0d);
        pocketsData[5].setRadius(17);

        // print pocketsData position x and y
        for (Pocket pocket : pocketsData) {
            System.out.println("Pocket Position X: " + pocket.getPositionX());
            System.out.println("Pocket Position Y: " + pocket.getPositionY());
        }
        
        for (Circle pocket : pockets) {
            pocket.setFill(Color.BLACK);
            pocket.setViewOrder(1000);
            pane.getChildren().add(pocket);
        }

    }
}
