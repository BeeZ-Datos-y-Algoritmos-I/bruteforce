package analyzer.common;

import java.io.IOException;

public interface IAnalyzer {

    public boolean make(boolean print) throws IOException;

    public boolean requiresReader();

}
