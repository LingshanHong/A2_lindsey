package A2_pool_game.model.factory;

public class BallReaderFactory implements IConfigReaderFactory {
    public BallReader makeReader() {
        return new BallReader();
    } 
}
