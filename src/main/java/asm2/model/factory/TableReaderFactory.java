package asm2.model.factory;

import asm2.model.reader.TableReader;

public class TableReaderFactory implements ConfigReaderFactory{
    public TableReader makeReader() {
        return new TableReader();
    }
}
