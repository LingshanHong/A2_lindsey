package asm2.model.strategy;

import asm2.model.GameEngine;
import asm2.model.entity.Ball;

public class WhiteBallStrategy implements BallStrategy{
    @Override
    public void behave(Ball ball, GameEngine gameEngineImpl) {
        ball.setInHole(true);

        // reset game if white ball in pocket
        gameEngineImpl.resetGame();
    }
}
