package asm2.model.builder;

import asm2.model.entity.Entity;

public interface TableBuilder {

    Entity getProduct();

    TableBuilderImpl setColour();

    TableBuilderImpl setTableX();

    TableBuilderImpl setTableY();

    TableBuilderImpl setFriction();

    
}
