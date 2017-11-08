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
public class PortfolioFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "tracker/domain/Portfolio";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "tracker.domain.Portfolio";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("tracker.domain.Portfolio");
	private static final PortfolioSingleFinder<Portfolio, Object, Portfolio> finder = new PortfolioSingleFinder<Portfolio, Object, Portfolio>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(PortfolioFinder.PortfolioRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("id");
		finderMethodMap.addNormalAttributeName("name");
		finderMethodMap.addRelationshipName("holdings");
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

	public static Mapper zGetConstantJoin(int index)
	{
		return getConstantJoinPool()[index];
	}

	private static Mapper[] constantJoinPool;
	private static Mapper[] getConstantJoinPool()
	{
		if (constantJoinPool == null)
		{
			Mapper[] result = new Mapper[2];
			result[0] = PortfolioFinder.id().constructEqualityMapper(PortfolioHoldingFinder.portfolioId());
			result[1] = PortfolioHoldingFinder.portfolioId().constructEqualityMapper(PortfolioFinder.id());
			constantJoinPool = result;
		}

		return constantJoinPool;
	}

	public static SourceAttributeType getSourceAttributeType()
	{
		return null;
	}

	public static Portfolio findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static Portfolio findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static PortfolioList findMany(com.gs.fw.finder.Operation operation)
	{
		return (PortfolioList) finder.findMany(operation);
	}

	public static PortfolioList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (PortfolioList) finder.findManyBypassCache(operation);
	}

	private static Portfolio findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (Portfolio) FinderUtils.findOne(found);
	}

	public static Portfolio findByPrimaryKey(int id)
	{
		return finder.findByPrimaryKey(id);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			PortfolioData _castedTargetData = (PortfolioData) _targetData;
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

	public static Portfolio zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (Portfolio) FinderUtils.findOne(found);
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

	public static class PortfolioRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<Portfolio, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private IntegerAttribute<ParentOwnerType> id;
		private StringAttribute<ParentOwnerType> name;
		private PortfolioHoldingsFinderSubclass<ParentOwnerType> holdings;
		public PortfolioRelatedFinder()
		{
			super();
		}

		public PortfolioRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "tracker.domain.PortfolioFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return PortfolioFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return PortfolioFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return PortfolioFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[2];
			attributes[0] = this.id();
			attributes[1] = this.name();
			return attributes;
		}

		public List<RelatedFinder> getRelationshipFinders()
		{
			if (relationshipFinders == null)
			{
				List<RelatedFinder> relatedFinders = new ArrayList<RelatedFinder>(1);
				relatedFinders.add(this.holdings());
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

		public Portfolio findOne(com.gs.fw.finder.Operation operation)
		{
			return PortfolioFinder.findOne(operation, false);
		}

		public Portfolio findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return PortfolioFinder.findOne(operation, true);
		}

		public MithraList<? extends Portfolio> findMany(com.gs.fw.finder.Operation operation)
		{
			return new PortfolioList((Operation) operation);
		}

		public MithraList<? extends Portfolio> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			PortfolioList result = (PortfolioList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends Portfolio> constructEmptyList()
		{
			return new PortfolioList();
		}

		public int getSerialVersionId()
		{
			return -776240472;
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
					new MappedIntegerAttribute(PortfolioFinder.id(), this.mapper, this.zGetValueSelector());
				this.id = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> name()
		{
			StringAttribute<ParentOwnerType> result = this.name;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("name","","name",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,100,true, false) :
					new MappedStringAttribute(PortfolioFinder.name(), this.mapper, this.zGetValueSelector());
				this.name = result;
			}

			return result;
		}

		public PortfolioHoldingFinder.PortfolioHoldingCollectionFinderForRelatedClasses<ParentOwnerType, PortfolioHoldingList, Portfolio> holdings()
		{
			PortfolioHoldingsFinderSubclass<ParentOwnerType> result = this.holdings;
			if (result == null)
			{
				Mapper newMapper = combineWithMapperIfExists(PortfolioFinder.zGetPortfolioHoldingsReverseMapper());
				newMapper.setToMany(true);
				result = new PortfolioHoldingsFinderSubclass<ParentOwnerType>(newMapper , this.zGetValueSelector() );
				this.holdings = result;
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
			return PortfolioFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return PortfolioFinder.getMithraObjectPortal();
		}
	}

	public static class PortfolioCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends PortfolioRelatedFinder<ParentOwnerType, ReturnType, PortfolioList, OwnerType>
	{
		public PortfolioCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class PortfolioCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends PortfolioCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PortfolioCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class PortfolioSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends PortfolioRelatedFinder<ParentOwnerType, ReturnType, PortfolioList, OwnerType>
	implements ToOneFinder
	{
		public PortfolioSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public PortfolioSingleFinder()
		{
			super(null);
		}

		public Operation eq(Portfolio other)
		{
			return this.id().eq(other.getId())
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public Portfolio findByPrimaryKey(int id)
		{
			Portfolio _result = null;
			Operation _op = null;
			Object _related = null;
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setI1AsInteger(id);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (Portfolio) _related;
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

	public static abstract class PortfolioSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends PortfolioSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PortfolioSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	private static Mapper holdingsReverseMapper = null;
	public static Mapper zGetPortfolioHoldingsReverseMapper()
	{
		if (holdingsReverseMapper == null)
		{
			holdingsReverseMapper = zConstructPortfolioHoldingsReverseMapper();
		}

		return holdingsReverseMapper;
	}

	private static Mapper holdingsMapper = null;
	public static Mapper zGetPortfolioHoldingsMapper()
	{
		if (holdingsMapper == null)
		{
			holdingsMapper = zConstructPortfolioHoldingsMapper();
		}

		return holdingsMapper;
	}

	private static Mapper holdingsPureReverseMapper = null;
	public static Mapper zGetPortfolioHoldingsPureReverseMapper()
	{
		if (holdingsPureReverseMapper == null)
		{
			holdingsPureReverseMapper = zConstructPortfolioHoldingsPureReverseMapper();
		}

		return holdingsPureReverseMapper;
	}

	private static Mapper zConstructPortfolioHoldingsPureReverseMapper()
	{
		Mapper holdingsMapper = PortfolioFinder.zGetConstantJoin(0);
		holdingsMapper.setName("holdings");
		return holdingsMapper;
	}

	private static Mapper zConstructPortfolioHoldingsReverseMapper()
	{
		Mapper holdingsMapper = PortfolioFinder.zGetConstantJoin(0);
		holdingsMapper.setName("holdings");
		return holdingsMapper;
	}

	private static Mapper zConstructPortfolioHoldingsMapper()
	{
		Mapper holdingsMapper = PortfolioFinder.zGetConstantJoin(1);
		holdingsMapper.setName("holdings_Reverse");
		return holdingsMapper;
	}

	/** maps to portfolio.id **/
	public static IntegerAttribute<Portfolio> id()
	{
		return finder.id();
	}

	/** maps to portfolio.name **/
	public static StringAttribute<Portfolio> name()
	{
		return finder.name();
	}

	public static PortfolioHoldingFinder.PortfolioHoldingCollectionFinderForRelatedClasses<Portfolio, PortfolioHoldingList, Portfolio> holdings()
	{
		return finder.holdings();
	}

	public static class PortfolioHoldingsFinderSubclass<ParentOwnerType> extends PortfolioHoldingFinder.PortfolioHoldingCollectionFinderForRelatedClasses<ParentOwnerType, PortfolioHoldingList, Portfolio>
	{
		public PortfolioHoldingsFinderSubclass(Mapper mapper, NormalAndListValueSelector parentSelector )
		{
			super(mapper);
			this._parentSelector = (AbstractRelatedFinder) parentSelector;
			this._orderBy = null;
			this._type = SIMPLE_TO_MANY;
			this._name = "holdings";
		}

		public DeepRelationshipAttribute copy()
		{
			return new PortfolioHoldingsFinderSubclass(zGetMapper(), (NormalAndListValueSelector) this._parentSelector
			);
		}

		protected PortfolioHoldingList plainValueOf(Portfolio _obj)
		{
			return _obj.getHoldings();
		}

		protected PortfolioHoldingList plainListValueOf(Object _obj)
		{
			return ((PortfolioList)_obj).getHoldings();
		}
	}

	public static Operation eq(Portfolio other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(id());
	}

	public static PortfolioSingleFinder<Portfolio, Object, Portfolio> getFinderInstance()
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
		objectPortal = new UninitializedPortal("tracker.domain.Portfolio");
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
