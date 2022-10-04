package A2_pool_game.model.factory;

public class TableReaderFactory implements IConfigReaderFactory {
    public TableReader makeReader() {
        return new TableReader();
    }
}
