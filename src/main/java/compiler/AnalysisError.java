package compiler;

import static main.Main.pathMain;
import static structures.ReadFile.readError;

public class AnalysisError extends Exception
{
    /**
     *
     */
    private static final long serialVersionUID = -6385633929675477415L;
    private int position;

    public AnalysisError(String msg, int position)
    {
        super(msg);
        this.position = position;
    }

    public AnalysisError(String msg)
    {
        super(msg);
        this.position = -1;
    }

    public int getPosition()
    {
        return position;
    }

    @Override
    public String toString()
    {
        return readError(pathMain, position) + super.getMessage();
    }
}
