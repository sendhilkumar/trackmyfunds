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
public class PortfolioHoldingFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "tracker/domain/PortfolioHolding";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "tracker.domain.PortfolioHolding";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("tracker.domain.PortfolioHolding");
	private static final PortfolioHoldingSingleFinder<PortfolioHolding, Object, PortfolioHolding> finder = new PortfolioHoldingSingleFinder<PortfolioHolding, Object, PortfolioHolding>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(PortfolioHoldingFinder.PortfolioHoldingRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("portfolioId");
		finderMethodMap.addNormalAttributeName("schemeCode");
		finderMethodMap.addRelationshipName("scheme");
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
			result[0] = PortfolioHoldingFinder.schemeCode().constructEqualityMapper(SchemeFinder.code());
			result[1] = SchemeFinder.code().constructEqualityMapper(PortfolioHoldingFinder.schemeCode());
			constantJoinPool = result;
		}

		return constantJoinPool;
	}

	public static SourceAttributeType getSourceAttributeType()
	{
		return null;
	}

	public static PortfolioHolding findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static PortfolioHolding findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static PortfolioHoldingList findMany(com.gs.fw.finder.Operation operation)
	{
		return (PortfolioHoldingList) finder.findMany(operation);
	}

	public static PortfolioHoldingList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (PortfolioHoldingList) finder.findManyBypassCache(operation);
	}

	private static PortfolioHolding findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (PortfolioHolding) FinderUtils.findOne(found);
	}

	public static PortfolioHolding findByPrimaryKey(int portfolioId, int schemeCode)
	{
		return finder.findByPrimaryKey(portfolioId, schemeCode);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			PortfolioHoldingData _castedTargetData = (PortfolioHoldingData) _targetData;
			if (_bean.getI1AsInteger() == _castedTargetData.getPortfolioId() && _bean.getI2AsInteger() == _castedTargetData.getSchemeCode())
			{
				return true;
			}

			return false;
		}

		public int computeHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.combineHashes(HashUtil.hash(_bean.getI1AsInteger()),HashUtil.hash(_bean.getI2AsInteger()));
		}

		public int computeOffHeapHashCodeFromRelated(Object _srcObject, Object _srcData)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			return HashUtil.combineHashes(HashUtil.hash(_bean.getI1AsInteger()),HashUtil.hash(_bean.getI2AsInteger()));
		}
	}

	public static PortfolioHolding zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (PortfolioHolding) FinderUtils.findOne(found);
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

	public static class PortfolioHoldingRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<PortfolioHolding, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private IntegerAttribute<ParentOwnerType> portfolioId;
		private IntegerAttribute<ParentOwnerType> schemeCode;
		private PortfolioHoldingSchemeFinderSubclass<ParentOwnerType> scheme;
		public PortfolioHoldingRelatedFinder()
		{
			super();
		}

		public PortfolioHoldingRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "tracker.domain.PortfolioHoldingFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return PortfolioHoldingFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return PortfolioHoldingFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return PortfolioHoldingFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[2];
			attributes[0] = this.portfolioId();
			attributes[1] = this.schemeCode();
			return attributes;
		}

		public List<RelatedFinder> getRelationshipFinders()
		{
			if (relationshipFinders == null)
			{
				List<RelatedFinder> relatedFinders = new ArrayList<RelatedFinder>(1);
				relatedFinders.add(this.scheme());
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

		public PortfolioHolding findOne(com.gs.fw.finder.Operation operation)
		{
			return PortfolioHoldingFinder.findOne(operation, false);
		}

		public PortfolioHolding findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return PortfolioHoldingFinder.findOne(operation, true);
		}

		public MithraList<? extends PortfolioHolding> findMany(com.gs.fw.finder.Operation operation)
		{
			return new PortfolioHoldingList((Operation) operation);
		}

		public MithraList<? extends PortfolioHolding> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			PortfolioHoldingList result = (PortfolioHoldingList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends PortfolioHolding> constructEmptyList()
		{
			return new PortfolioHoldingList();
		}

		public int getSerialVersionId()
		{
			return -739652322;
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

		public IntegerAttribute<ParentOwnerType> portfolioId()
		{
			IntegerAttribute<ParentOwnerType> result = this.portfolioId;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("portfolio_id","","portfolioId",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(PortfolioHoldingFinder.portfolioId(), this.mapper, this.zGetValueSelector());
				result.zSetOwningReverseRelationship("tracker.domain", "Portfolio", "holdings");
				this.portfolioId = result;
			}

			return result;
		}

		public IntegerAttribute<ParentOwnerType> schemeCode()
		{
			IntegerAttribute<ParentOwnerType> result = this.schemeCode;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("scheme_code","","schemeCode",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(PortfolioHoldingFinder.schemeCode(), this.mapper, this.zGetValueSelector());
				result.zSetOwningRelationship("scheme");
				this.schemeCode = result;
			}

			return result;
		}

		public SchemeFinder.SchemeSingleFinderForRelatedClasses<ParentOwnerType, Scheme, PortfolioHolding> scheme()
		{
			PortfolioHoldingSchemeFinderSubclass<ParentOwnerType> result = this.scheme;
			if (result == null)
			{
				Mapper newMapper = combineWithMapperIfExists(PortfolioHoldingFinder.zGetPortfolioHoldingSchemeReverseMapper());
				newMapper.setToMany(false);
				result = new PortfolioHoldingSchemeFinderSubclass<ParentOwnerType>(newMapper , this.zGetValueSelector() );
				this.scheme = result;
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
			return PortfolioHoldingFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return PortfolioHoldingFinder.getMithraObjectPortal();
		}
	}

	public static class PortfolioHoldingCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends PortfolioHoldingRelatedFinder<ParentOwnerType, ReturnType, PortfolioHoldingList, OwnerType>
	{
		public PortfolioHoldingCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class PortfolioHoldingCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends PortfolioHoldingCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PortfolioHoldingCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class PortfolioHoldingSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends PortfolioHoldingRelatedFinder<ParentOwnerType, ReturnType, PortfolioHoldingList, OwnerType>
	implements ToOneFinder
	{
		public PortfolioHoldingSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public PortfolioHoldingSingleFinder()
		{
			super(null);
		}

		public Operation eq(PortfolioHolding other)
		{
			return this.portfolioId().eq(other.getPortfolioId())
			.and(this.schemeCode().eq(other.getSchemeCode()))
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public PortfolioHolding findByPrimaryKey(int portfolioId, int schemeCode)
		{
			PortfolioHolding _result = null;
			Operation _op = null;
			Object _related = null;
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setI1AsInteger(portfolioId);
				_bean.setI2AsInteger(schemeCode);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (PortfolioHolding) _related;
			if (_related == null)
			{
				_op = this.portfolioId().eq(portfolioId).and(this.schemeCode().eq(schemeCode));
			}

			if (_op != null)
			{
				_result = this.findOne(_op);
			}

			return _result;
		}
	}

	public static abstract class PortfolioHoldingSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends PortfolioHoldingSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public PortfolioHoldingSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	private static Mapper schemeReverseMapper = null;
	public static Mapper zGetPortfolioHoldingSchemeReverseMapper()
	{
		if (schemeReverseMapper == null)
		{
			schemeReverseMapper = zConstructPortfolioHoldingSchemeReverseMapper();
		}

		return schemeReverseMapper;
	}

	private static Mapper schemeMapper = null;
	public static Mapper zGetPortfolioHoldingSchemeMapper()
	{
		if (schemeMapper == null)
		{
			schemeMapper = zConstructPortfolioHoldingSchemeMapper();
		}

		return schemeMapper;
	}

	private static Mapper schemePureReverseMapper = null;
	public static Mapper zGetPortfolioHoldingSchemePureReverseMapper()
	{
		if (schemePureReverseMapper == null)
		{
			schemePureReverseMapper = zConstructPortfolioHoldingSchemePureReverseMapper();
		}

		return schemePureReverseMapper;
	}

	private static Mapper zConstructPortfolioHoldingSchemePureReverseMapper()
	{
		Mapper schemeMapper = PortfolioHoldingFinder.zGetConstantJoin(0);
		schemeMapper.setName("scheme");
		return schemeMapper;
	}

	private static Mapper zConstructPortfolioHoldingSchemeReverseMapper()
	{
		Mapper schemeMapper = PortfolioHoldingFinder.zGetConstantJoin(0);
		schemeMapper.setName("scheme");
		return schemeMapper;
	}

	private static Mapper zConstructPortfolioHoldingSchemeMapper()
	{
		Mapper schemeMapper = PortfolioHoldingFinder.zGetConstantJoin(1);
		schemeMapper.setName("scheme_Reverse");
		return schemeMapper;
	}

	/** maps to portfolio_holding.portfolio_id **/
	public static IntegerAttribute<PortfolioHolding> portfolioId()
	{
		return finder.portfolioId();
	}

	/** maps to portfolio_holding.scheme_code **/
	public static IntegerAttribute<PortfolioHolding> schemeCode()
	{
		return finder.schemeCode();
	}

	public static SchemeFinder.SchemeSingleFinderForRelatedClasses<PortfolioHolding, Scheme, PortfolioHolding> scheme()
	{
		return finder.scheme();
	}

	public static class PortfolioHoldingSchemeFinderSubclass<ParentOwnerType> extends SchemeFinder.SchemeSingleFinderForRelatedClasses<ParentOwnerType, Scheme, PortfolioHolding>
	{
		public PortfolioHoldingSchemeFinderSubclass(Mapper mapper, NormalAndListValueSelector parentSelector )
		{
			super(mapper);
			this._parentSelector = (AbstractRelatedFinder) parentSelector;
			this._orderBy = null;
			this._type = SIMPLE_TO_ONE;
			this._name = "scheme";
		}

		public DeepRelationshipAttribute copy()
		{
			return new PortfolioHoldingSchemeFinderSubclass(zGetMapper(), (NormalAndListValueSelector) this._parentSelector
			);
		}

		protected Scheme plainValueOf(PortfolioHolding _obj)
		{
			return _obj.getScheme();
		}

		protected SchemeList plainListValueOf(Object _obj)
		{
			return ((PortfolioHoldingList)_obj).getSchemes();
		}
	}

	public static Operation eq(PortfolioHolding other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(portfolioId());
	}

	public static PortfolioHoldingSingleFinder<PortfolioHolding, Object, PortfolioHolding> getFinderInstance()
	{
		return finder;
	}

	public static Attribute[] getPrimaryKeyAttributes()
	{
		return new Attribute[] 
		{
			portfolioId()
			, schemeCode()
		}

		;
	}

	public static Attribute[] getImmutableAttributes()
	{
		return new Attribute[] 
		{
			portfolioId()
			, portfolioId()
			, schemeCode()
		}

		;
	}

	public static AsOfAttribute[] getAsOfAttributes()
	{
		return null;
	}

	protected static void initializeIndicies(Cache cache)
	{
		cache.addIndex("0 Index", new Attribute[] 
		{
			portfolioId()
		}

		);
		cache.addIndex("1 Index", new Attribute[] 
		{
			schemeCode()
		}

		);
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

		portal.setParentFinders(new RelatedFinder[] 
		{
			PortfolioFinder.getFinderInstance(),SchemeFinder.getFinderInstance(),
		}

		);
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

		portal.setParentFinders(new RelatedFinder[] 
		{
			PortfolioFinder.getFinderInstance(),SchemeFinder.getFinderInstance(),
		}

		);
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
		objectPortal = new UninitializedPortal("tracker.domain.PortfolioHolding");
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
