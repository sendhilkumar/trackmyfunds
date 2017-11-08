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
public class LatestNAVDateFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "tracker/domain/LatestNAVDate";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "tracker.domain.LatestNAVDate";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("tracker.domain.LatestNAVDate");
	private static final LatestNAVDateSingleFinder<LatestNAVDate, Object, LatestNAVDate> finder = new LatestNAVDateSingleFinder<LatestNAVDate, Object, LatestNAVDate>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(LatestNAVDateFinder.LatestNAVDateRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("date");
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

	public static LatestNAVDate findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static LatestNAVDate findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static LatestNAVDateList findMany(com.gs.fw.finder.Operation operation)
	{
		return (LatestNAVDateList) finder.findMany(operation);
	}

	public static LatestNAVDateList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (LatestNAVDateList) finder.findManyBypassCache(operation);
	}

	private static LatestNAVDate findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (LatestNAVDate) FinderUtils.findOne(found);
	}

	public static LatestNAVDate findByPrimaryKey(Timestamp date)
	{
		return finder.findByPrimaryKey(date);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			LatestNAVDateData _castedTargetData = (LatestNAVDateData) _targetData;
			if (_bean.getO1AsTimestamp().equals(_castedTargetData.getDate()))
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.hash(_bean.getO1AsTimestamp());
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.hash(_bean.getO1AsTimestamp());
		}
	}

	public static LatestNAVDate zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (LatestNAVDate) FinderUtils.findOne(found);
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

	public static class LatestNAVDateRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<LatestNAVDate, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private TimestampAttribute<ParentOwnerType> date;
		public LatestNAVDateRelatedFinder()
		{
			super();
		}

		public LatestNAVDateRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "tracker.domain.LatestNAVDateFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return LatestNAVDateFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return LatestNAVDateFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return LatestNAVDateFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[1];
			attributes[0] = this.date();
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

		public LatestNAVDate findOne(com.gs.fw.finder.Operation operation)
		{
			return LatestNAVDateFinder.findOne(operation, false);
		}

		public LatestNAVDate findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return LatestNAVDateFinder.findOne(operation, true);
		}

		public MithraList<? extends LatestNAVDate> findMany(com.gs.fw.finder.Operation operation)
		{
			return new LatestNAVDateList((Operation) operation);
		}

		public MithraList<? extends LatestNAVDate> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			LatestNAVDateList result = (LatestNAVDateList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends LatestNAVDate> constructEmptyList()
		{
			return new LatestNAVDateList();
		}

		public int getSerialVersionId()
		{
			return 1285100538;
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

		public TimestampAttribute<ParentOwnerType> date()
		{
			TimestampAttribute<ParentOwnerType> result = this.date;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("date","","date",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,false,null, (byte) 0, false) :
					new MappedTimestampAttribute(LatestNAVDateFinder.date(), this.mapper, this.zGetValueSelector());
				this.date = result;
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
			return LatestNAVDateFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return LatestNAVDateFinder.getMithraObjectPortal();
		}
	}

	public static class LatestNAVDateCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends LatestNAVDateRelatedFinder<ParentOwnerType, ReturnType, LatestNAVDateList, OwnerType>
	{
		public LatestNAVDateCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class LatestNAVDateCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends LatestNAVDateCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public LatestNAVDateCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class LatestNAVDateSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends LatestNAVDateRelatedFinder<ParentOwnerType, ReturnType, LatestNAVDateList, OwnerType>
	implements ToOneFinder
	{
		public LatestNAVDateSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public LatestNAVDateSingleFinder()
		{
			super(null);
		}

		public Operation eq(LatestNAVDate other)
		{
			return this.date().eq(other.getDate())
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public LatestNAVDate findByPrimaryKey(Timestamp date)
		{
			LatestNAVDate _result = null;
			Operation _op = null;
			Object _related = null;
			if (date != null)
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setO1(date);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (LatestNAVDate) _related;
			if (_related == null)
			{
				_op = this.date().eq(date);
			}

			if (_op != null)
			{
				_result = this.findOne(_op);
			}

			return _result;
		}
	}

	public static abstract class LatestNAVDateSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends LatestNAVDateSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public LatestNAVDateSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	/** maps to latest_nav_date.date **/
	public static TimestampAttribute<LatestNAVDate> date()
	{
		return finder.date();
	}

	public static Operation eq(LatestNAVDate other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(date());
	}

	public static LatestNAVDateSingleFinder<LatestNAVDate, Object, LatestNAVDate> getFinderInstance()
	{
		return finder;
	}

	public static Attribute[] getPrimaryKeyAttributes()
	{
		return new Attribute[] 
		{
			date()
		}

		;
	}

	public static Attribute[] getImmutableAttributes()
	{
		return new Attribute[] 
		{
			date()
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
		objectPortal = new UninitializedPortal("tracker.domain.LatestNAVDate");
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
