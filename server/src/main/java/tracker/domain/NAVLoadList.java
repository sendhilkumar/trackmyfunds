package tracker.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
public class NAVLoadList extends NAVLoadListAbstract
{
	public NAVLoadList()
	{
		super();
	}

	public NAVLoadList(int initialSize)
	{
		super(initialSize);
	}

	public NAVLoadList(Collection c)
	{
		super(c);
	}

	public NAVLoadList(Operation operation)
	{
		super(operation);
	}
}
