package compiler;

public class SyntaticError extends AnalysisError
{
    /**
     *
     */
    private static final long serialVersionUID = 6243383827036919002L;

    public SyntaticError(String msg, int position)
	 {
        super(msg, position);
    }

    public SyntaticError(String msg)
    {
        super(msg);
    }
}
