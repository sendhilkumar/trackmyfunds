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
public class SchemeFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "tracker/domain/Scheme";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "tracker.domain.Scheme";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("tracker.domain.Scheme");
	private static final SchemeSingleFinder<Scheme, Object, Scheme> finder = new SchemeSingleFinder<Scheme, Object, Scheme>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(SchemeFinder.SchemeRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("code");
		finderMethodMap.addNormalAttributeName("name");
		finderMethodMap.addNormalAttributeName("amc");
		finderMethodMap.addNormalAttributeName("rtaCode");
		finderMethodMap.addRelationshipName("transactions");
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

	public static Scheme findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static Scheme findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static SchemeList findMany(com.gs.fw.finder.Operation operation)
	{
		return (SchemeList) finder.findMany(operation);
	}

	public static SchemeList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (SchemeList) finder.findManyBypassCache(operation);
	}

	private static Scheme findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (Scheme) FinderUtils.findOne(found);
	}

	public static Scheme findByPrimaryKey(int code)
	{
		return finder.findByPrimaryKey(code);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			SchemeData _castedTargetData = (SchemeData) _targetData;
			if (_bean.getI1AsInteger() == _castedTargetData.getCode())
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

	public static Scheme zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (Scheme) FinderUtils.findOne(found);
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

	public static class SchemeRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<Scheme, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private IntegerAttribute<ParentOwnerType> code;
		private StringAttribute<ParentOwnerType> name;
		private StringAttribute<ParentOwnerType> amc;
		private StringAttribute<ParentOwnerType> rtaCode;
		private SchemeTransactionsFinderSubclass<ParentOwnerType> transactions;
		public SchemeRelatedFinder()
		{
			super();
		}

		public SchemeRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "tracker.domain.SchemeFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return SchemeFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return SchemeFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return SchemeFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[4];
			attributes[0] = this.code();
			attributes[1] = this.name();
			attributes[2] = this.amc();
			attributes[3] = this.rtaCode();
			return attributes;
		}

		public List<RelatedFinder> getRelationshipFinders()
		{
			if (relationshipFinders == null)
			{
				List<RelatedFinder> relatedFinders = new ArrayList<RelatedFinder>(1);
				relatedFinders.add(this.transactions());
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

		public Scheme findOne(com.gs.fw.finder.Operation operation)
		{
			return SchemeFinder.findOne(operation, false);
		}

		public Scheme findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return SchemeFinder.findOne(operation, true);
		}

		public MithraList<? extends Scheme> findMany(com.gs.fw.finder.Operation operation)
		{
			return new SchemeList((Operation) operation);
		}

		public MithraList<? extends Scheme> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			SchemeList result = (SchemeList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends Scheme> constructEmptyList()
		{
			return new SchemeList();
		}

		public int getSerialVersionId()
		{
			return 2007847256;
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

		public IntegerAttribute<ParentOwnerType> code()
		{
			IntegerAttribute<ParentOwnerType> result = this.code;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("code","","code",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(SchemeFinder.code(), this.mapper, this.zGetValueSelector());
				this.code = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> name()
		{
			StringAttribute<ParentOwnerType> result = this.name;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("name","","name",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,500,true, false) :
					new MappedStringAttribute(SchemeFinder.name(), this.mapper, this.zGetValueSelector());
				this.name = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> amc()
		{
			StringAttribute<ParentOwnerType> result = this.amc;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("amc","","amc",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,500,true, false) :
					new MappedStringAttribute(SchemeFinder.amc(), this.mapper, this.zGetValueSelector());
				this.amc = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> rtaCode()
		{
			StringAttribute<ParentOwnerType> result = this.rtaCode;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("rta_code","","rtaCode",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,50,true, false) :
					new MappedStringAttribute(SchemeFinder.rtaCode(), this.mapper, this.zGetValueSelector());
				this.rtaCode = result;
			}

			return result;
		}

		public TransactionFinder.TransactionCollectionFinderForRelatedClasses<ParentOwnerType, TransactionList, Scheme> transactions()
		{
			SchemeTransactionsFinderSubclass<ParentOwnerType> result = this.transactions;
			if (result == null)
			{
				Mapper newMapper = combineWithMapperIfExists(TransactionFinder.zGetTransactionSchemeMapper());
				newMapper.setToMany(true);
				result = new SchemeTransactionsFinderSubclass<ParentOwnerType>(newMapper , this.zGetValueSelector() );
				this.transactions = result;
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
			return SchemeFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return SchemeFinder.getMithraObjectPortal();
		}
	}

	public static class SchemeCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends SchemeRelatedFinder<ParentOwnerType, ReturnType, SchemeList, OwnerType>
	{
		public SchemeCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class SchemeCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends SchemeCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public SchemeCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class SchemeSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends SchemeRelatedFinder<ParentOwnerType, ReturnType, SchemeList, OwnerType>
	implements ToOneFinder
	{
		public SchemeSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public SchemeSingleFinder()
		{
			super(null);
		}

		public Operation eq(Scheme other)
		{
			return this.code().eq(other.getCode())
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public Scheme findByPrimaryKey(int code)
		{
			Scheme _result = null;
			Operation _op = null;
			Object _related = null;
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setI1AsInteger(code);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (Scheme) _related;
			if (_related == null)
			{
				_op = this.code().eq(code);
			}

			if (_op != null)
			{
				_result = this.findOne(_op);
			}

			return _result;
		}
	}

	public static abstract class SchemeSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends SchemeSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public SchemeSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	/** maps to scheme.code **/
	public static IntegerAttribute<Scheme> code()
	{
		return finder.code();
	}

	/** maps to scheme.name **/
	public static StringAttribute<Scheme> name()
	{
		return finder.name();
	}

	/** maps to scheme.amc **/
	public static StringAttribute<Scheme> amc()
	{
		return finder.amc();
	}

	/** maps to scheme.rta_code **/
	public static StringAttribute<Scheme> rtaCode()
	{
		return finder.rtaCode();
	}

	public static TransactionFinder.TransactionCollectionFinderForRelatedClasses<Scheme, TransactionList, Scheme> transactions()
	{
		return finder.transactions();
	}

	public static class SchemeTransactionsFinderSubclass<ParentOwnerType> extends TransactionFinder.TransactionCollectionFinderForRelatedClasses<ParentOwnerType, TransactionList, Scheme>
	{
		public SchemeTransactionsFinderSubclass(Mapper mapper, NormalAndListValueSelector parentSelector )
		{
			super(mapper);
			this._parentSelector = (AbstractRelatedFinder) parentSelector;
			this._orderBy = null;
			this._type = SIMPLE_TO_MANY;
			this._name = "transactions";
		}

		public DeepRelationshipAttribute copy()
		{
			return new SchemeTransactionsFinderSubclass(zGetMapper(), (NormalAndListValueSelector) this._parentSelector
			);
		}

		protected TransactionList plainValueOf(Scheme _obj)
		{
			return _obj.getTransactions();
		}

		protected TransactionList plainListValueOf(Object _obj)
		{
			return ((SchemeList)_obj).getTransactions();
		}
	}

	public static Operation eq(Scheme other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(code());
	}

	public static SchemeSingleFinder<Scheme, Object, Scheme> getFinderInstance()
	{
		return finder;
	}

	public static Attribute[] getPrimaryKeyAttributes()
	{
		return new Attribute[] 
		{
			code()
		}

		;
	}

	public static Attribute[] getImmutableAttributes()
	{
		return new Attribute[] 
		{
			code()
			, code()
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
		objectPortal = new UninitializedPortal("tracker.domain.Scheme");
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
