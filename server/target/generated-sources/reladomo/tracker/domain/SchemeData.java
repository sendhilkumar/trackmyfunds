package tracker.domain;
import java.util.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.*;
import com.gs.fw.common.mithra.util.*;
import com.gs.fw.common.mithra.notification.*;
import com.gs.fw.common.mithra.notification.listener.*;
import com.gs.fw.common.mithra.list.cursor.Cursor;
import com.gs.fw.common.mithra.bulkloader.*;
import com.gs.fw.common.mithra.finder.PrintablePreparedStatement;
import com.gs.fw.common.mithra.finder.RelatedFinder;
import com.gs.fw.common.mithra.cache.offheap.MithraOffHeapDataObject;
import com.gs.fw.common.mithra.cache.offheap.OffHeapDataStorage;
/**
* This file was automatically generated using Mithra 16.6.1. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public class SchemeData
implements MithraDataObject
{
	private Object[] _relationships;
	private String amc;
	private int code;
	private String name;
	private String rtaCode;
	public boolean isAmcNull()
	{
		return this.getAmc() == null;
	}

	public boolean isCodeNull()
	{
		return false;
	}

	public boolean isNameNull()
	{
		return this.getName() == null;
	}

	public boolean isRtaCodeNull()
	{
		return this.getRtaCode() == null;
	}

	public void zSerializeFullData(ObjectOutput out) throws IOException
	{
		zWriteNullBits(out);
		out.writeObject(this.amc);
		out.writeInt(this.code);
		out.writeObject(this.name);
		out.writeObject(this.rtaCode);
	}

	private void zWriteNullBits(ObjectOutput out) throws IOException
	{
	}

	public String getAmc()
	{
		return this.amc;
	}

	public int zGetAmcAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(amc);
	}

	public void setAmc(String value)
	{
		this.amc = StringPool.getInstance().getOrAddToCache(value, SchemeFinder.isFullCache());
	}

	public void setAmcNull()
	{
		this.setAmc(null);
	}

	public int getCode()
	{
		return this.code;
	}

	public void setCode(int value)
	{
		this.code = value;
	}

	public void setCodeNull()
	{
		throw new RuntimeException("should never be called");
	}

	public String getName()
	{
		return this.name;
	}

	public int zGetNameAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(name);
	}

	public void setName(String value)
	{
		this.name = StringPool.getInstance().getOrAddToCache(value, SchemeFinder.isFullCache());
	}

	public void setNameNull()
	{
		this.setName(null);
	}

	public String getRtaCode()
	{
		return this.rtaCode;
	}

	public int zGetRtaCodeAsInt()
	{
		return StringPool.getInstance().getOffHeapAddressWithoutAdding(rtaCode);
	}

	public void setRtaCode(String value)
	{
		this.rtaCode = StringPool.getInstance().getOrAddToCache(value, SchemeFinder.isFullCache());
	}

	public void setRtaCodeNull()
	{
		this.setRtaCode(null);
	}

	protected void copyInto(SchemeData copy, boolean withRelationships)
	{
		copy.amc = this.amc;
		copy.code = this.code;
		copy.name = this.name;
		copy.rtaCode = this.rtaCode;
		if (withRelationships)
		{
			if (_relationships != null)
			{
				copy._relationships = new Object[1];
				System.arraycopy(_relationships, 0, copy._relationships, 0, _relationships.length);
			}
		}
	}

	public void zDeserializeFullData(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.amc = StringPool.getInstance().getOrAddToCache((String)in.readObject(), SchemeFinder.isFullCache());
		this.code = in.readInt();
		this.name = StringPool.getInstance().getOrAddToCache((String)in.readObject(), SchemeFinder.isFullCache());
		this.rtaCode = StringPool.getInstance().getOrAddToCache((String)in.readObject(), SchemeFinder.isFullCache());
	}

	public boolean hasSamePrimaryKeyIgnoringAsOfAttributes(MithraDataObject other)
	{
		if (this == other) return true;
		final SchemeData otherData = (SchemeData) other;
		if (getCode() != otherData.getCode())
		{
			return false;
		}

		return true;
	}

	public void zSerializePrimaryKey(ObjectOutput out) throws IOException
	{
		out.writeInt(this.code);
	}

	public void zDeserializePrimaryKey(ObjectInput in) throws IOException, ClassNotFoundException
	{
		this.code = in.readInt();
	}

	public void clearRelationships()
	{
		_relationships = null;
		clearAllDirectRefs();
	}

	public void clearAllDirectRefs()
	{
	}

	public Object getTransactions()
	{
		if (_relationships != null)
		{
			return _relationships[0];
		}

		return null;
	}

	public void setTransactions(Object related)
	{
		if (_relationships == null)
		{
			_relationships = new Object[1];
		}

		_relationships[0] = related;
	}

	public void zSerializeRelationships(ObjectOutputStream out) throws IOException
	{
		if (_relationships == null)
		{
			out.writeInt(0);
			return;
		}

		out.writeInt(_relationships.length);
		for(int i=0;i<_relationships.length;i++)
		{
			out.writeObject(_relationships[i]);
		}
	}

	public void zDeserializeRelationships(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		int total = in.readInt();
		if(total > 0)
		{
			_relationships = new Object[total];
			for(int i=0;i<total;i++)
			{
				_relationships[i] = in.readObject();
			}
		}
	}

	public MithraOffHeapDataObject zCopyOffHeap()
	{
		throw new RuntimeException("off heap no supported");
	}

	public void copyNonPkAttributes(MithraDataObject newData)
	{
		final SchemeData schemeData = (SchemeData) newData;
		this.setAmc(schemeData.getAmc());
		this.setName(schemeData.getName());
		this.setRtaCode(schemeData.getRtaCode());
	}

	public byte zGetDataVersion() 
	{
		return (byte)0; 
	}

	public void zSetDataVersion(byte version) 
	{
	}
	// only used by dated objects
	public void zIncrementDataVersion() 
	{
	}

	public boolean zNonPrimaryKeyAttributesChanged(MithraDataObject newData)
	{
		return this.zNonPrimaryKeyAttributesChanged(newData, 0.0);
	}

	public boolean zNonPrimaryKeyAttributesChanged(MithraDataObject newData, double toleranceForFloatingPointFields)
	{
		final SchemeData other = (SchemeData) newData;
		if (!isAmcNull() ? !getAmc().equals(other.getAmc()) : !other.isAmcNull())
		{
			return true;
		}

		if (!isNameNull() ? !getName().equals(other.getName()) : !other.isNameNull())
		{
			return true;
		}

		if (!isRtaCodeNull() ? !getRtaCode().equals(other.getRtaCode()) : !other.isRtaCodeNull())
		{
			return true;
		}

		return false;
	}

	public MithraDataObject copy()
	{
		SchemeData copy = new SchemeData();
		this.copyInto(copy, true);
		return copy;
	}

	public MithraDataObject copy(boolean withRelationships)
	{
		SchemeData copy = new SchemeData();
		this.copyInto(copy, withRelationships);
		return copy;
	}

	public String zGetPrintablePrimaryKey()
	{
		String result = "";
		result += "code: "+(""+getCode())+ " / ";
		return result;
	}

	public boolean zAsOfAttributesFromEquals(MithraDataObject other)
	{
		return true;
	}

	public boolean zAsOfAttributesChanged(MithraDataObject other)
	{
		return false;
	}

	public void zWriteDataClassName(ObjectOutput out) throws IOException
	{
	}

	public String zReadDataClassName(ObjectInput in) throws IOException, ClassNotFoundException
	{
		return "tracker.domain.SchemeData";
	}

	public boolean changed(MithraDataObject newData)
	{
		if(zNonPrimaryKeyAttributesChanged(newData))
		{
			return true;
		}
		else
		{
			return zAsOfAttributesChanged(newData);
		}
	}

	public boolean zHasSameNullPrimaryKeyAttributes(MithraDataObject newData)
	{
		return true;
	}

	public MithraObjectPortal zGetMithraObjectPortal(int hierarchyDepth)
	{
		return SchemeFinder.getMithraObjectPortal();
	}

	public MithraObjectPortal zGetMithraObjectPortal()
	{
		return SchemeFinder.getMithraObjectPortal();
	}

	public Number zGetIdentityValue()
	{
		return null;
	}

	public boolean zHasIdentity()
	{
		return false;
	}

	public void zSetIdentity(Number identityValue)
	{
	}

	public String zGetSerializationClassName()
	{
		return "tracker.domain.SchemeData";
	}
}
