package tracker.domain;
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
import com.gs.fw.common.mithra.*;
import com.gs.fw.common.mithra.attribute.*;
import com.gs.fw.common.mithra.attribute.calculator.procedure.ObjectProcedure;
import com.gs.fw.common.mithra.behavior.txparticipation.*;
import com.gs.fw.common.mithra.cache.Cache;
import com.gs.fw.common.mithra.cache.bean.*;
import com.gs.fw.common.mithra.extractor.*;
import com.gs.fw.common.mithra.finder.*;
import com.gs.fw.common.mithra.finder.booleanop.*;
import com.gs.fw.common.mithra.finder.integer.*;
import com.gs.fw.common.mithra.finder.longop.*;
import com.gs.fw.common.mithra.finder.orderby.OrderBy;
import com.gs.fw.common.mithra.finder.string.*;
import com.gs.fw.common.mithra.extractor.NormalAndListValueSelector;
import com.gs.fw.common.mithra.list.NulledRelation;
import com.gs.fw.common.mithra.querycache.CachedQuery;
import com.gs.fw.common.mithra.querycache.QueryCache;
import com.gs.fw.common.mithra.portal.*;
import com.gs.fw.common.mithra.remote.*;
import com.gs.fw.common.mithra.transaction.MithraObjectPersister;
import com.gs.fw.common.mithra.util.TimestampPool;
import com.gs.collections.impl.map.mutable.UnifiedMap;
import java.io.Serializable;
/**
* This file was automatically generated using Mithra 16.6.1. Please do not modify it.
* Add custom logic to its subclass instead.
*/
public class NetAssetValueFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "tracker/domain/NetAssetValue";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "tracker.domain.NetAssetValue";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("tracker.domain.NetAssetValue");
	private static final NetAssetValueSingleFinder<NetAssetValue, Object, NetAssetValue> finder = new NetAssetValueSingleFinder<NetAssetValue, Object, NetAssetValue>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(NetAssetValueFinder.NetAssetValueRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("schemeCode");
		finderMethodMap.addNormalAttributeName("date");
		finderMethodMap.addNormalAttributeName("netAssetValue");
		finderMethodMap.addNormalAttributeName("repurchasePrice");
		finderMethodMap.addNormalAttributeName("salePrice");
		finderMethodMap.addNormalAttributeName("loadId");
	}

	public static Attribute[] allPersistentAttributes()
	{
		return finder.getPersistentAttributes();
	}

	public static List<RelatedFinder> allRelatedFinders()
	{
		return finder.getRelationshipFinders();
	}

	public static List<RelatedFinder> allDependentRelatedFinders()
	{
		return finder.getDependentRelationshipFinders();
	}

	public static ConstantStringSet zGetConstantStringSet(int index)
	{
		return constantStringSets[index];
	}

	public static ConstantIntSet zGetConstantIntSet(int index)
	{
		return constantIntSets[index];
	}

	public static ConstantShortSet zGetConstantShortSet(int index)
	{
		return constantShortSets[index];
	}

	public static SourceAttributeType getSourceAttributeType()
	{
		return null;
	}

	public static NetAssetValue findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static NetAssetValue findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static NetAssetValueList findMany(com.gs.fw.finder.Operation operation)
	{
		return (NetAssetValueList) finder.findMany(operation);
	}

	public static NetAssetValueList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (NetAssetValueList) finder.findManyBypassCache(operation);
	}

	private static NetAssetValue findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (NetAssetValue) FinderUtils.findOne(found);
	}

	public static NetAssetValue findByPrimaryKey(int schemeCode, Timestamp date)
	{
		return finder.findByPrimaryKey(schemeCode, date);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			NetAssetValueData _castedTargetData = (NetAssetValueData) _targetData;
			if (_bean.getI1AsInteger() == _castedTargetData.getSchemeCode() && _bean.getO1AsTimestamp().equals(_castedTargetData.getDate()))
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.combineHashes(HashUtil.hash(_bean.getI1AsInteger()),HashUtil.hash(_bean.getO1AsTimestamp()));
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.combineHashes(HashUtil.hash(_bean.getI1AsInteger()),HashUtil.hash(_bean.getO1AsTimestamp()));
		}
	}

	public static NetAssetValue zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (NetAssetValue) FinderUtils.findOne(found);
	}

	public static MithraObjectPortal getMithraObjectPortal()
	{
		return objectPortal.getInitializedPortal();
	}

	public static void clearQueryCache()
	{
		objectPortal.clearQueryCache();
	}

	public static void reloadCache()
	{
		objectPortal.reloadCache();
	}

	public static class NetAssetValueRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<NetAssetValue, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private IntegerAttribute<ParentOwnerType> schemeCode;
		private TimestampAttribute<ParentOwnerType> date;
		private DoubleAttribute<ParentOwnerType> netAssetValue;
		private DoubleAttribute<ParentOwnerType> repurchasePrice;
		private DoubleAttribute<ParentOwnerType> salePrice;
		private IntegerAttribute<ParentOwnerType> loadId;
		public NetAssetValueRelatedFinder()
		{
			super();
		}

		public NetAssetValueRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "tracker.domain.NetAssetValueFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return NetAssetValueFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return NetAssetValueFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return NetAssetValueFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[6];
			attributes[0] = this.schemeCode();
			attributes[1] = this.date();
			attributes[2] = this.netAssetValue();
			attributes[3] = this.repurchasePrice();
			attributes[4] = this.salePrice();
			attributes[5] = this.loadId();
			return attributes;
		}

		public List<RelatedFinder> getRelationshipFinders()
		{
			if (relationshipFinders == null)
			{
				List<RelatedFinder> relatedFinders = new ArrayList<RelatedFinder>(0);
				relationshipFinders = Collections.unmodifiableList(relatedFinders);
			}

			return relationshipFinders;
		}

		public List<RelatedFinder> getDependentRelationshipFinders()
		{
			if (dependentRelationshipFinders == null)
			{
				List<RelatedFinder> dependentRelatedFinders = new ArrayList<RelatedFinder>(0);
				dependentRelationshipFinders = Collections.unmodifiableList(dependentRelatedFinders);
			}

			return dependentRelationshipFinders;
		}

		public NetAssetValue findOne(com.gs.fw.finder.Operation operation)
		{
			return NetAssetValueFinder.findOne(operation, false);
		}

		public NetAssetValue findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return NetAssetValueFinder.findOne(operation, true);
		}

		public MithraList<? extends NetAssetValue> findMany(com.gs.fw.finder.Operation operation)
		{
			return new NetAssetValueList((Operation) operation);
		}

		public MithraList<? extends NetAssetValue> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			NetAssetValueList result = (NetAssetValueList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends NetAssetValue> constructEmptyList()
		{
			return new NetAssetValueList();
		}

		public int getSerialVersionId()
		{
			return 1621874603;
		}

		public boolean isPure()
		{
			return false;
		}

		public boolean isTemporary()
		{
			return false;
		}

		public int getHierarchyDepth()
		{
			return 0;
		}

		public IntegerAttribute<ParentOwnerType> schemeCode()
		{
			IntegerAttribute<ParentOwnerType> result = this.schemeCode;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("scheme_code","","schemeCode",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(NetAssetValueFinder.schemeCode(), this.mapper, this.zGetValueSelector());
				this.schemeCode = result;
			}

			return result;
		}

		public TimestampAttribute<ParentOwnerType> date()
		{
			TimestampAttribute<ParentOwnerType> result = this.date;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("date","","date",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,false,null, (byte) 0, false) :
					new MappedTimestampAttribute(NetAssetValueFinder.date(), this.mapper, this.zGetValueSelector());
				this.date = result;
			}

			return result;
		}

		public DoubleAttribute<ParentOwnerType> netAssetValue()
		{
			DoubleAttribute<ParentOwnerType> result = this.netAssetValue;
			if (result == null)
			{
				result = mapper == null ? SingleColumnDoubleAttribute.generate("net_asset_value","","netAssetValue",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1, false) :
					new MappedDoubleAttribute(NetAssetValueFinder.netAssetValue(), this.mapper, this.zGetValueSelector());
				this.netAssetValue = result;
			}

			return result;
		}

		public DoubleAttribute<ParentOwnerType> repurchasePrice()
		{
			DoubleAttribute<ParentOwnerType> result = this.repurchasePrice;
			if (result == null)
			{
				result = mapper == null ? SingleColumnDoubleAttribute.generate("repurchase_price","","repurchasePrice",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1, false) :
					new MappedDoubleAttribute(NetAssetValueFinder.repurchasePrice(), this.mapper, this.zGetValueSelector());
				this.repurchasePrice = result;
			}

			return result;
		}

		public DoubleAttribute<ParentOwnerType> salePrice()
		{
			DoubleAttribute<ParentOwnerType> result = this.salePrice;
			if (result == null)
			{
				result = mapper == null ? SingleColumnDoubleAttribute.generate("sale_price","","salePrice",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1, false) :
					new MappedDoubleAttribute(NetAssetValueFinder.salePrice(), this.mapper, this.zGetValueSelector());
				this.salePrice = result;
			}

			return result;
		}

		public IntegerAttribute<ParentOwnerType> loadId()
		{
			IntegerAttribute<ParentOwnerType> result = this.loadId;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("load_id","","loadId",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(NetAssetValueFinder.loadId(), this.mapper, this.zGetValueSelector());
				this.loadId = result;
			}

			return result;
		}

		public Attribute getSourceAttribute()
		{
			return null;
		}

		private Mapper combineWithMapperIfExists(Mapper newMapper)
		{
			if (this.mapper != null)
			{
				return new LinkedMapper(this.mapper, newMapper);
			}

			return newMapper;
		}

		public Attribute[] getPrimaryKeyAttributes()
		{
			return NetAssetValueFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return NetAssetValueFinder.getMithraObjectPortal();
		}
	}

	public static class NetAssetValueCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends NetAssetValueRelatedFinder<ParentOwnerType, ReturnType, NetAssetValueList, OwnerType>
	{
		public NetAssetValueCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class NetAssetValueCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends NetAssetValueCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public NetAssetValueCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class NetAssetValueSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends NetAssetValueRelatedFinder<ParentOwnerType, ReturnType, NetAssetValueList, OwnerType>
	implements ToOneFinder
	{
		public NetAssetValueSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public NetAssetValueSingleFinder()
		{
			super(null);
		}

		public Operation eq(NetAssetValue other)
		{
			return this.schemeCode().eq(other.getSchemeCode())
			.and(this.date().eq(other.getDate()))
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public NetAssetValue findByPrimaryKey(int schemeCode, Timestamp date)
		{
			NetAssetValue _result = null;
			Operation _op = null;
			Object _related = null;
			if (date != null)
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setI1AsInteger(schemeCode);
				_bean.setO1(date);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (NetAssetValue) _related;
			if (_related == null)
			{
				_op = this.schemeCode().eq(schemeCode).and(this.date().eq(date));
			}

			if (_op != null)
			{
				_result = this.findOne(_op);
			}

			return _result;
		}
	}

	public static abstract class NetAssetValueSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends NetAssetValueSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public NetAssetValueSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	/** maps to net_asset_value.scheme_code **/
	public static IntegerAttribute<NetAssetValue> schemeCode()
	{
		return finder.schemeCode();
	}

	/** maps to net_asset_value.date **/
	public static TimestampAttribute<NetAssetValue> date()
	{
		return finder.date();
	}

	/** maps to net_asset_value.net_asset_value **/
	public static DoubleAttribute<NetAssetValue> netAssetValue()
	{
		return finder.netAssetValue();
	}

	/** maps to net_asset_value.repurchase_price **/
	public static DoubleAttribute<NetAssetValue> repurchasePrice()
	{
		return finder.repurchasePrice();
	}

	/** maps to net_asset_value.sale_price **/
	public static DoubleAttribute<NetAssetValue> salePrice()
	{
		return finder.salePrice();
	}

	/** maps to net_asset_value.load_id **/
	public static IntegerAttribute<NetAssetValue> loadId()
	{
		return finder.loadId();
	}

	public static Operation eq(NetAssetValue other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(schemeCode());
	}

	public static NetAssetValueSingleFinder<NetAssetValue, Object, NetAssetValue> getFinderInstance()
	{
		return finder;
	}

	public static Attribute[] getPrimaryKeyAttributes()
	{
		return new Attribute[] 
		{
			schemeCode()
			, date()
		}

		;
	}

	public static Attribute[] getImmutableAttributes()
	{
		return new Attribute[] 
		{
			schemeCode()
			, schemeCode()
			, date()
		}

		;
	}

	public static AsOfAttribute[] getAsOfAttributes()
	{
		return null;
	}

	protected static void initializeIndicies(Cache cache)
	{
	}

	protected static void initializePortal(MithraObjectDeserializer objectFactory, Cache cache,
		MithraConfigurationManager.Config config)
	{
		initializeIndicies(cache);
		isFullCache = cache.isFullCache();
		isOffHeap = cache.isOffHeap();
		MithraObjectPortal portal;
		if (config.isParticipatingInTx())
		{
			portal = new MithraTransactionalPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(), null,
				null, null, 0,
				(MithraObjectPersister) objectFactory);
		}
		else
		{
			portal = new MithraReadOnlyPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(), null,
				null, null, 0,
				(MithraObjectPersister) objectFactory);
		}

		portal.setIndependent(true);
		config.initializePortal(portal);
		objectPortal.destroy();
		objectPortal = portal;
	}

	protected static void initializeClientPortal(MithraObjectDeserializer objectFactory, Cache cache,
		MithraConfigurationManager.Config config)
	{
		initializeIndicies(cache);
		isFullCache = cache.isFullCache();
		isOffHeap = cache.isOffHeap();
		MithraObjectPortal portal;
		if (config.isParticipatingInTx())
		{
			portal = new MithraTransactionalPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(),
				null, null,
				null, 0,
				new RemoteMithraObjectPersister(config.getRemoteMithraService(), getFinderInstance(), false));
		}
		else
		{
			portal = new MithraReadOnlyPortal(objectFactory, cache, getFinderInstance(),
				config.getRelationshipCacheSize(), config.getMinQueriesToKeep(),
				null, null,
				null, 0,
				new RemoteMithraObjectPersister(config.getRemoteMithraService(), getFinderInstance(), false));
		}

		portal.setIndependent(true);
		config.initializePortal(portal);
		objectPortal.destroy();
		objectPortal = portal;
	}

	public static boolean isFullCache()
	{
		return isFullCache;
	}

	public static boolean isOffHeap()
	{
		return isOffHeap;
	}

	public static Attribute getAttributeByName(String attributeName)
	{
		return finder.getAttributeByName(attributeName);
	}

	public static com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
	{
		return finder.getAttributeOrRelationshipSelector(attributeName);
	}

	public static RelatedFinder getRelatedFinderByName(String relationshipName)
	{
		return finder.getRelationshipFinderByName(relationshipName);
	}

	public static DoubleAttribute[] zGetDoubleAttributes()
	{
		DoubleAttribute[] result = new DoubleAttribute[3];
		result[0] = netAssetValue();
		result[1] = repurchasePrice();
		result[2] = salePrice();
		return result;
	}

	public static BigDecimalAttribute[] zGetBigDecimalAttributes()
	{
		BigDecimalAttribute[] result = new BigDecimalAttribute[0];
		return result;
	}

	public static void zResetPortal()
	{
		objectPortal.destroy();
		objectPortal = new UninitializedPortal("tracker.domain.NetAssetValue");
		isFullCache = false;
		isOffHeap = false;
	}

	public static void setTransactionModeFullTransactionParticipation(MithraTransaction tx)
	{
		tx.setTxParticipationMode(objectPortal, FullTransactionalParticipationMode.getInstance());
	}

	public static void setTransactionModeReadCacheUpdateCausesRefreshAndLock(MithraTransaction tx)
	{
		tx.setTxParticipationMode(objectPortal, ReadCacheUpdateCausesRefreshAndLockTxParticipationMode.getInstance());
	}

	public static void registerForNotification(MithraApplicationClassLevelNotificationListener listener)
	{
		getMithraObjectPortal().registerForApplicationClassLevelNotification(listener);
	}
}
