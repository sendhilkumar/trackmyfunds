package tracker.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
public class PortfolioHoldingList extends PortfolioHoldingListAbstract
{
	public PortfolioHoldingList()
	{
		super();
	}

	public PortfolioHoldingList(int initialSize)
	{
		super(initialSize);
	}

	public PortfolioHoldingList(Collection c)
	{
		super(c);
	}

	public PortfolioHoldingList(Operation operation)
	{
		super(operation);
	}
}
