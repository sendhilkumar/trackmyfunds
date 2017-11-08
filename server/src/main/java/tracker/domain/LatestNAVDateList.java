package tracker.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
public class LatestNAVDateList extends LatestNAVDateListAbstract
{
	public LatestNAVDateList()
	{
		super();
	}

	public LatestNAVDateList(int initialSize)
	{
		super(initialSize);
	}

	public LatestNAVDateList(Collection c)
	{
		super(c);
	}

	public LatestNAVDateList(Operation operation)
	{
		super(operation);
	}
}
