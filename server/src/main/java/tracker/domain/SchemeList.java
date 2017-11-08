package tracker.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
public class SchemeList extends SchemeListAbstract
{
	public SchemeList()
	{
		super();
	}

	public SchemeList(int initialSize)
	{
		super(initialSize);
	}

	public SchemeList(Collection c)
	{
		super(c);
	}

	public SchemeList(Operation operation)
	{
		super(operation);
	}
}
