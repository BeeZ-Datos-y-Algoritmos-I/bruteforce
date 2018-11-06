package reader.common;

import analyzer.data.Bee;

import java.util.LinkedList;

public interface IReader {

    public void load();

    public LinkedList<Bee> getBees();

}
