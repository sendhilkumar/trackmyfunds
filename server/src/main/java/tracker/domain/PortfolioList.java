package tracker.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
public class PortfolioList extends PortfolioListAbstract
{
	public PortfolioList()
	{
		super();
	}

	public PortfolioList(int initialSize)
	{
		super(initialSize);
	}

	public PortfolioList(Collection c)
	{
		super(c);
	}

	public PortfolioList(Operation operation)
	{
		super(operation);
	}
}
