package tracker.domain;
import com.gs.fw.finder.Operation;
import java.util.*;
public class NetAssetValueList extends NetAssetValueListAbstract
{
	public NetAssetValueList()
	{
		super();
	}

	public NetAssetValueList(int initialSize)
	{
		super(initialSize);
	}

	public NetAssetValueList(Collection c)
	{
		super(c);
	}

	public NetAssetValueList(Operation operation)
	{
		super(operation);
	}
}
