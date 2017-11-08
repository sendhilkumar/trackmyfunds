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
public class NAVLoadFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "tracker/domain/NAVLoad";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "tracker.domain.NAVLoad";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("tracker.domain.NAVLoad");
	private static final NAVLoadSingleFinder<NAVLoad, Object, NAVLoad> finder = new NAVLoadSingleFinder<NAVLoad, Object, NAVLoad>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(NAVLoadFinder.NAVLoadRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("id");
		finderMethodMap.addNormalAttributeName("loadTime");
		finderMethodMap.addNormalAttributeName("latestNavDate");
		finderMethodMap.addNormalAttributeName("maxOccuringDate");
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

	public static NAVLoad findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static NAVLoad findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static NAVLoadList findMany(com.gs.fw.finder.Operation operation)
	{
		return (NAVLoadList) finder.findMany(operation);
	}

	public static NAVLoadList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (NAVLoadList) finder.findManyBypassCache(operation);
	}

	private static NAVLoad findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (NAVLoad) FinderUtils.findOne(found);
	}

	public static NAVLoad findByPrimaryKey(int id)
	{
		return finder.findByPrimaryKey(id);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			NAVLoadData _castedTargetData = (NAVLoadData) _targetData;
			if (_bean.getI1AsInteger() == _castedTargetData.getId())
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.hash(_bean.getI1AsInteger());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.hash(_bean.getI1AsInteger());
		}
	}

	public static NAVLoad zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (NAVLoad) FinderUtils.findOne(found);
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

	public static class NAVLoadRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<NAVLoad, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private IntegerAttribute<ParentOwnerType> id;
		private TimestampAttribute<ParentOwnerType> loadTime;
		private TimestampAttribute<ParentOwnerType> latestNavDate;
		private TimestampAttribute<ParentOwnerType> maxOccuringDate;
		public NAVLoadRelatedFinder()
		{
			super();
		}

		public NAVLoadRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "tracker.domain.NAVLoadFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return NAVLoadFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return NAVLoadFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return NAVLoadFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[4];
			attributes[0] = this.id();
			attributes[1] = this.loadTime();
			attributes[2] = this.latestNavDate();
			attributes[3] = this.maxOccuringDate();
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

		public NAVLoad findOne(com.gs.fw.finder.Operation operation)
		{
			return NAVLoadFinder.findOne(operation, false);
		}

		public NAVLoad findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return NAVLoadFinder.findOne(operation, true);
		}

		public MithraList<? extends NAVLoad> findMany(com.gs.fw.finder.Operation operation)
		{
			return new NAVLoadList((Operation) operation);
		}

		public MithraList<? extends NAVLoad> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			NAVLoadList result = (NAVLoadList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends NAVLoad> constructEmptyList()
		{
			return new NAVLoadList();
		}

		public int getSerialVersionId()
		{
			return -503990993;
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

		public IntegerAttribute<ParentOwnerType> id()
		{
			IntegerAttribute<ParentOwnerType> result = this.id;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("id","","id",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(NAVLoadFinder.id(), this.mapper, this.zGetValueSelector());
				this.id = result;
			}

			return result;
		}

		public TimestampAttribute<ParentOwnerType> loadTime()
		{
			TimestampAttribute<ParentOwnerType> result = this.loadTime;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("load_time","","loadTime",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,false,null, (byte) 0, false) :
					new MappedTimestampAttribute(NAVLoadFinder.loadTime(), this.mapper, this.zGetValueSelector());
				this.loadTime = result;
			}

			return result;
		}

		public TimestampAttribute<ParentOwnerType> latestNavDate()
		{
			TimestampAttribute<ParentOwnerType> result = this.latestNavDate;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("latest_nav_date","","latestNavDate",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,false,null, (byte) 0, false) :
					new MappedTimestampAttribute(NAVLoadFinder.latestNavDate(), this.mapper, this.zGetValueSelector());
				this.latestNavDate = result;
			}

			return result;
		}

		public TimestampAttribute<ParentOwnerType> maxOccuringDate()
		{
			TimestampAttribute<ParentOwnerType> result = this.maxOccuringDate;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("max_occuring_date","","maxOccuringDate",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,false,null, (byte) 0, false) :
					new MappedTimestampAttribute(NAVLoadFinder.maxOccuringDate(), this.mapper, this.zGetValueSelector());
				this.maxOccuringDate = result;
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
			return NAVLoadFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return NAVLoadFinder.getMithraObjectPortal();
		}
	}

	public static class NAVLoadCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends NAVLoadRelatedFinder<ParentOwnerType, ReturnType, NAVLoadList, OwnerType>
	{
		public NAVLoadCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class NAVLoadCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends NAVLoadCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public NAVLoadCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class NAVLoadSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends NAVLoadRelatedFinder<ParentOwnerType, ReturnType, NAVLoadList, OwnerType>
	implements ToOneFinder
	{
		public NAVLoadSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public NAVLoadSingleFinder()
		{
			super(null);
		}

		public Operation eq(NAVLoad other)
		{
			return this.id().eq(other.getId())
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public NAVLoad findByPrimaryKey(int id)
		{
			NAVLoad _result = null;
			Operation _op = null;
			Object _related = null;
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setI1AsInteger(id);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (NAVLoad) _related;
			if (_related == null)
			{
				_op = this.id().eq(id);
			}

			if (_op != null)
			{
				_result = this.findOne(_op);
			}

			return _result;
		}
	}

	public static abstract class NAVLoadSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends NAVLoadSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public NAVLoadSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	/** maps to nav_load.id **/
	public static IntegerAttribute<NAVLoad> id()
	{
		return finder.id();
	}

	/** maps to nav_load.load_time **/
	public static TimestampAttribute<NAVLoad> loadTime()
	{
		return finder.loadTime();
	}

	/** maps to nav_load.latest_nav_date **/
	public static TimestampAttribute<NAVLoad> latestNavDate()
	{
		return finder.latestNavDate();
	}

	/** maps to nav_load.max_occuring_date **/
	public static TimestampAttribute<NAVLoad> maxOccuringDate()
	{
		return finder.maxOccuringDate();
	}

	public static Operation eq(NAVLoad other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(id());
	}

	public static NAVLoadSingleFinder<NAVLoad, Object, NAVLoad> getFinderInstance()
	{
		return finder;
	}

	public static Attribute[] getPrimaryKeyAttributes()
	{
		return new Attribute[] 
		{
			id()
		}

		;
	}

	public static Attribute[] getImmutableAttributes()
	{
		return new Attribute[] 
		{
			id()
			, id()
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
		DoubleAttribute[] result = new DoubleAttribute[0];
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
		objectPortal = new UninitializedPortal("tracker.domain.NAVLoad");
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
