package asm2.model.builder;

import asm2.model.entity.Ball;
import asm2.model.entity.Entity;

public interface BallBuilder {

    Entity getProduct();

    BallBuilder setColour();

    BallBuilder setPositionX();

    BallBuilder setPositionY();

    BallBuilder setVelocityX();

    BallBuilder setVelocityY();

    BallBuilder setMass();

    BallBuilder setStrategy();
    
}
