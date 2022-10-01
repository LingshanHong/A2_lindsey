package asm2.model.factory;

import asm2.model.reader.BallReader;

public class BallReaderFactory implements ConfigReaderFactory {
    public BallReader makeReader() {
        return new BallReader();
    } 
}
