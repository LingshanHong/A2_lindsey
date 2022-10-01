package asm2.model.strategy;

import asm2.model.GameEngine;
import asm2.model.entity.Ball;

public interface BallStrategy {

    void behave(Ball ball, GameEngine gameEngineImpl);
}
