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
public class TransactionFinder
{
	private static final String IMPL_CLASS_NAME_WITH_SLASHES = "tracker/domain/Transaction";
	private static final String BUSINESS_CLASS_NAME_WITH_DOTS = "tracker.domain.Transaction";
	private static final FinderMethodMap finderMethodMap;
	private static boolean isFullCache;
	private static boolean isOffHeap;
	private static volatile MithraObjectPortal objectPortal = new UninitializedPortal("tracker.domain.Transaction");
	private static final TransactionSingleFinder<Transaction, Object, Transaction> finder = new TransactionSingleFinder<Transaction, Object, Transaction>();
	private static ConstantStringSet[] constantStringSets = new ConstantStringSet[0];
	private static ConstantIntSet[] constantIntSets = new ConstantIntSet[0];
	private static ConstantShortSet[] constantShortSets = new ConstantShortSet[0];
	static
	{
		finderMethodMap = new FinderMethodMap(TransactionFinder.TransactionRelatedFinder.class);
		finderMethodMap.addNormalAttributeName("id");
		finderMethodMap.addNormalAttributeName("date");
		finderMethodMap.addNormalAttributeName("schemeCode");
		finderMethodMap.addNormalAttributeName("amount");
		finderMethodMap.addNormalAttributeName("description");
		finderMethodMap.addNormalAttributeName("units");
		finderMethodMap.addNormalAttributeName("price");
		finderMethodMap.addNormalAttributeName("unitBalance");
		finderMethodMap.addNormalAttributeName("rtaCode");
		finderMethodMap.addNormalAttributeName("schemeName");
		finderMethodMap.addNormalAttributeName("folioNumber");
		finderMethodMap.addNormalAttributeName("registrar");
		finderMethodMap.addNormalAttributeName("source");
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
			result[0] = TransactionFinder.schemeCode().constructEqualityMapper(SchemeFinder.code());
			result[1] = SchemeFinder.code().constructEqualityMapper(TransactionFinder.schemeCode());
			constantJoinPool = result;
		}

		return constantJoinPool;
	}

	public static SourceAttributeType getSourceAttributeType()
	{
		return null;
	}

	public static Transaction findOne(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, false);
	}

	public static Transaction findOneBypassCache(com.gs.fw.finder.Operation operation)
	{
		return findOne(operation, true);
	}

	public static TransactionList findMany(com.gs.fw.finder.Operation operation)
	{
		return (TransactionList) finder.findMany(operation);
	}

	public static TransactionList findManyBypassCache(com.gs.fw.finder.Operation operation)
	{
		return (TransactionList) finder.findManyBypassCache(operation);
	}

	private static Transaction findOne(com.gs.fw.finder.Operation operation, boolean bypassCache)
	{
		List found = getMithraObjectPortal().find((Operation) operation, bypassCache);
		return (Transaction) FinderUtils.findOne(found);
	}

	public static Transaction findByPrimaryKey(int id)
	{
		return finder.findByPrimaryKey(id);
	}

	private static final RelationshipHashStrategy forPrimaryKey = new PrimaryKeyRhs();
	private static final class PrimaryKeyRhs implements RelationshipHashStrategy
	{
		public boolean equalsForRelationship(Object _srcObject, Object _srcData, Object _targetData, Timestamp _asOfDate0, Timestamp _asOfDate1)
		{
			I3O3L3 _bean = (I3O3L3) _srcData;
			TransactionData _castedTargetData = (TransactionData) _targetData;
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

	public static Transaction zFindOneForRelationship(Operation operation)
	{
		List found = getMithraObjectPortal().findAsCachedQuery(operation, null, false, true, 0).getResult();
		return (Transaction) FinderUtils.findOne(found);
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

	public static class TransactionRelatedFinder<ParentOwnerType, ReturnType, ReturnListType extends List, OwnerType> extends AbstractRelatedFinder<Transaction, ParentOwnerType, ReturnType, ReturnListType, OwnerType>
	{
		private List<RelatedFinder> relationshipFinders;
		private List<RelatedFinder> dependentRelationshipFinders;
		private IntegerAttribute<ParentOwnerType> id;
		private TimestampAttribute<ParentOwnerType> date;
		private IntegerAttribute<ParentOwnerType> schemeCode;
		private DoubleAttribute<ParentOwnerType> amount;
		private StringAttribute<ParentOwnerType> description;
		private DoubleAttribute<ParentOwnerType> units;
		private DoubleAttribute<ParentOwnerType> price;
		private DoubleAttribute<ParentOwnerType> unitBalance;
		private StringAttribute<ParentOwnerType> rtaCode;
		private StringAttribute<ParentOwnerType> schemeName;
		private StringAttribute<ParentOwnerType> folioNumber;
		private StringAttribute<ParentOwnerType> registrar;
		private StringAttribute<ParentOwnerType> source;
		private TransactionSchemeFinderSubclass<ParentOwnerType> scheme;
		public TransactionRelatedFinder()
		{
			super();
		}

		public TransactionRelatedFinder(Mapper mapper)
		{
			super(mapper);
		}

		public String getFinderClassName()
		{
			return "tracker.domain.TransactionFinder";
		}

		public RelatedFinder getRelationshipFinderByName(String relationshipName)
		{
			return TransactionFinder.finderMethodMap.getRelationshipFinderByName(relationshipName, this);
		}

		public Attribute getAttributeByName(String attributeName)
		{
			return TransactionFinder.finderMethodMap.getAttributeByName(attributeName, this);
		}

		public com.gs.collections.api.block.function.Function getAttributeOrRelationshipSelector(String attributeName)
		{
			return TransactionFinder.finderMethodMap.getAttributeOrRelationshipSelectorFunction(attributeName, this);
		}

		public Attribute[] getPersistentAttributes()
		{
			Attribute[] attributes = new Attribute[13];
			attributes[0] = this.id();
			attributes[1] = this.date();
			attributes[2] = this.schemeCode();
			attributes[3] = this.amount();
			attributes[4] = this.description();
			attributes[5] = this.units();
			attributes[6] = this.price();
			attributes[7] = this.unitBalance();
			attributes[8] = this.rtaCode();
			attributes[9] = this.schemeName();
			attributes[10] = this.folioNumber();
			attributes[11] = this.registrar();
			attributes[12] = this.source();
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

		public Transaction findOne(com.gs.fw.finder.Operation operation)
		{
			return TransactionFinder.findOne(operation, false);
		}

		public Transaction findOneBypassCache(com.gs.fw.finder.Operation operation)
		{
			return TransactionFinder.findOne(operation, true);
		}

		public MithraList<? extends Transaction> findMany(com.gs.fw.finder.Operation operation)
		{
			return new TransactionList((Operation) operation);
		}

		public MithraList<? extends Transaction> findManyBypassCache(com.gs.fw.finder.Operation operation)
		{
			TransactionList result = (TransactionList) this.findMany(operation);
			result.setBypassCache(true);
			return result;
		}

		public MithraList<? extends Transaction> constructEmptyList()
		{
			return new TransactionList();
		}

		public int getSerialVersionId()
		{
			return -1662517371;
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
					new MappedIntegerAttribute(TransactionFinder.id(), this.mapper, this.zGetValueSelector());
				this.id = result;
			}

			return result;
		}

		public TimestampAttribute<ParentOwnerType> date()
		{
			TimestampAttribute<ParentOwnerType> result = this.date;
			if (result == null)
			{
				result = mapper == null ? SingleColumnTimestampAttribute.generate("date","","date",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1,TimestampAttribute.CONVERT_NONE,false,false,null, (byte) 0, false) :
					new MappedTimestampAttribute(TransactionFinder.date(), this.mapper, this.zGetValueSelector());
				this.date = result;
			}

			return result;
		}

		public IntegerAttribute<ParentOwnerType> schemeCode()
		{
			IntegerAttribute<ParentOwnerType> result = this.schemeCode;
			if (result == null)
			{
				result = mapper == null ? SingleColumnIntegerAttribute.generate("scheme_code","","schemeCode",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,false,-1,-1,-1,false, false) :
					new MappedIntegerAttribute(TransactionFinder.schemeCode(), this.mapper, this.zGetValueSelector());
				result.zSetOwningRelationship("scheme");
				result.zSetOwningReverseRelationship("tracker.domain", "Scheme", "transactions");
				this.schemeCode = result;
			}

			return result;
		}

		public DoubleAttribute<ParentOwnerType> amount()
		{
			DoubleAttribute<ParentOwnerType> result = this.amount;
			if (result == null)
			{
				result = mapper == null ? SingleColumnDoubleAttribute.generate("amount","","amount",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,false,false,this,null,true,false,-1,-1,-1, false) :
					new MappedDoubleAttribute(TransactionFinder.amount(), this.mapper, this.zGetValueSelector());
				this.amount = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> description()
		{
			StringAttribute<ParentOwnerType> result = this.description;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("description","","description",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,500,true, false) :
					new MappedStringAttribute(TransactionFinder.description(), this.mapper, this.zGetValueSelector());
				this.description = result;
			}

			return result;
		}

		public DoubleAttribute<ParentOwnerType> units()
		{
			DoubleAttribute<ParentOwnerType> result = this.units;
			if (result == null)
			{
				result = mapper == null ? SingleColumnDoubleAttribute.generate("units","","units",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1, false) :
					new MappedDoubleAttribute(TransactionFinder.units(), this.mapper, this.zGetValueSelector());
				this.units = result;
			}

			return result;
		}

		public DoubleAttribute<ParentOwnerType> price()
		{
			DoubleAttribute<ParentOwnerType> result = this.price;
			if (result == null)
			{
				result = mapper == null ? SingleColumnDoubleAttribute.generate("price","","price",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1, false) :
					new MappedDoubleAttribute(TransactionFinder.price(), this.mapper, this.zGetValueSelector());
				this.price = result;
			}

			return result;
		}

		public DoubleAttribute<ParentOwnerType> unitBalance()
		{
			DoubleAttribute<ParentOwnerType> result = this.unitBalance;
			if (result == null)
			{
				result = mapper == null ? SingleColumnDoubleAttribute.generate("unitBalance","","unitBalance",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1, false) :
					new MappedDoubleAttribute(TransactionFinder.unitBalance(), this.mapper, this.zGetValueSelector());
				this.unitBalance = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> rtaCode()
		{
			StringAttribute<ParentOwnerType> result = this.rtaCode;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("rta_code","","rtaCode",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,50,true, false) :
					new MappedStringAttribute(TransactionFinder.rtaCode(), this.mapper, this.zGetValueSelector());
				this.rtaCode = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> schemeName()
		{
			StringAttribute<ParentOwnerType> result = this.schemeName;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("schemeName","","schemeName",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,500,true, false) :
					new MappedStringAttribute(TransactionFinder.schemeName(), this.mapper, this.zGetValueSelector());
				this.schemeName = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> folioNumber()
		{
			StringAttribute<ParentOwnerType> result = this.folioNumber;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("folioNumber","","folioNumber",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,100,true, false) :
					new MappedStringAttribute(TransactionFinder.folioNumber(), this.mapper, this.zGetValueSelector());
				this.folioNumber = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> registrar()
		{
			StringAttribute<ParentOwnerType> result = this.registrar;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("registrar","","registrar",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,100,true, false) :
					new MappedStringAttribute(TransactionFinder.registrar(), this.mapper, this.zGetValueSelector());
				this.registrar = result;
			}

			return result;
		}

		public StringAttribute<ParentOwnerType> source()
		{
			StringAttribute<ParentOwnerType> result = this.source;
			if (result == null)
			{
				result = mapper == null ? SingleColumnStringAttribute.generate("source","","source",BUSINESS_CLASS_NAME_WITH_DOTS,IMPL_CLASS_NAME_WITH_SLASHES,true,false,this,null,true,false,-1,-1,-1,100,true, false) :
					new MappedStringAttribute(TransactionFinder.source(), this.mapper, this.zGetValueSelector());
				this.source = result;
			}

			return result;
		}

		public SchemeFinder.SchemeSingleFinderForRelatedClasses<ParentOwnerType, Scheme, Transaction> scheme()
		{
			TransactionSchemeFinderSubclass<ParentOwnerType> result = this.scheme;
			if (result == null)
			{
				Mapper newMapper = combineWithMapperIfExists(TransactionFinder.zGetTransactionSchemeReverseMapper());
				newMapper.setToMany(false);
				result = new TransactionSchemeFinderSubclass<ParentOwnerType>(newMapper , this.zGetValueSelector() );
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
			return TransactionFinder.getPrimaryKeyAttributes();
		}

		public VersionAttribute getVersionAttribute()
		{
			return null;
		}

		public MithraObjectPortal getMithraObjectPortal()
		{
			return TransactionFinder.getMithraObjectPortal();
		}
	}

	public static class TransactionCollectionFinder<ParentOwnerType, ReturnType extends List, OwnerType> extends TransactionRelatedFinder<ParentOwnerType, ReturnType, TransactionList, OwnerType>
	{
		public TransactionCollectionFinder(Mapper mapper)
		{
			super(mapper);
		}
	}

	public static abstract class TransactionCollectionFinderForRelatedClasses<ParentOwnerType, ReturnType extends List, OwnerType>
	extends TransactionCollectionFinder<ParentOwnerType, ReturnType, OwnerType>
	implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public TransactionCollectionFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	public static class TransactionSingleFinder<ParentOwnerType, ReturnType, OwnerType> extends TransactionRelatedFinder<ParentOwnerType, ReturnType, TransactionList, OwnerType>
	implements ToOneFinder
	{
		public TransactionSingleFinder(Mapper mapper)
		{
			super(mapper);
		}

		public TransactionSingleFinder()
		{
			super(null);
		}

		public Operation eq(Transaction other)
		{
			return this.id().eq(other.getId())
			;
		}
		// this implementation uses private API. Do NOT copy to application code. Application code must use normal operations for lookups.
		public Transaction findByPrimaryKey(int id)
		{
			Transaction _result = null;
			Operation _op = null;
			Object _related = null;
			{
				I3O3L3 _bean = I3O3L3.POOL.getOrConstruct();
				_bean.setI1AsInteger(id);
				MithraObjectPortal _portal = this.getMithraObjectPortal();
				_related = _portal.getAsOneFromCacheForFind(_bean, _bean, forPrimaryKey, null, null);
				_bean.release();
			}

			if (!(_related instanceof NulledRelation)) _result = (Transaction) _related;
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

	public static abstract class TransactionSingleFinderForRelatedClasses<ParentOwnerType, ReturnType, OwnerType> extends TransactionSingleFinder<ParentOwnerType, ReturnType, OwnerType> implements DeepRelationshipAttribute<ParentOwnerType, ReturnType>
	{
		public TransactionSingleFinderForRelatedClasses(Mapper mapper)
		{
			super(mapper);
		}

		protected NormalAndListValueSelector zGetValueSelector()
		{
			return this;
		}
	}

	private static Mapper schemeReverseMapper = null;
	public static Mapper zGetTransactionSchemeReverseMapper()
	{
		if (schemeReverseMapper == null)
		{
			schemeReverseMapper = zConstructTransactionSchemeReverseMapper();
		}

		return schemeReverseMapper;
	}

	private static Mapper schemeMapper = null;
	public static Mapper zGetTransactionSchemeMapper()
	{
		if (schemeMapper == null)
		{
			schemeMapper = zConstructTransactionSchemeMapper();
		}

		return schemeMapper;
	}

	private static Mapper schemePureReverseMapper = null;
	public static Mapper zGetTransactionSchemePureReverseMapper()
	{
		if (schemePureReverseMapper == null)
		{
			schemePureReverseMapper = zConstructTransactionSchemePureReverseMapper();
		}

		return schemePureReverseMapper;
	}

	private static Mapper zConstructTransactionSchemePureReverseMapper()
	{
		Mapper schemeMapper = TransactionFinder.zGetConstantJoin(0);
		schemeMapper.setName("scheme");
		return schemeMapper;
	}

	private static Mapper zConstructTransactionSchemeReverseMapper()
	{
		Mapper schemeMapper = TransactionFinder.zGetConstantJoin(0);
		schemeMapper.setName("scheme");
		return schemeMapper;
	}

	private static Mapper zConstructTransactionSchemeMapper()
	{
		Mapper schemeMapper = TransactionFinder.zGetConstantJoin(1);
		schemeMapper.setName("transactions");
		return schemeMapper;
	}

	/** maps to transaction.id **/
	public static IntegerAttribute<Transaction> id()
	{
		return finder.id();
	}

	/** maps to transaction.date **/
	public static TimestampAttribute<Transaction> date()
	{
		return finder.date();
	}

	/** maps to transaction.scheme_code **/
	public static IntegerAttribute<Transaction> schemeCode()
	{
		return finder.schemeCode();
	}

	/** maps to transaction.amount **/
	public static DoubleAttribute<Transaction> amount()
	{
		return finder.amount();
	}

	/** maps to transaction.description **/
	public static StringAttribute<Transaction> description()
	{
		return finder.description();
	}

	/** maps to transaction.units **/
	public static DoubleAttribute<Transaction> units()
	{
		return finder.units();
	}

	/** maps to transaction.price **/
	public static DoubleAttribute<Transaction> price()
	{
		return finder.price();
	}

	/** maps to transaction.unitBalance **/
	public static DoubleAttribute<Transaction> unitBalance()
	{
		return finder.unitBalance();
	}

	/** maps to transaction.rta_code **/
	public static StringAttribute<Transaction> rtaCode()
	{
		return finder.rtaCode();
	}

	/** maps to transaction.schemeName **/
	public static StringAttribute<Transaction> schemeName()
	{
		return finder.schemeName();
	}

	/** maps to transaction.folioNumber **/
	public static StringAttribute<Transaction> folioNumber()
	{
		return finder.folioNumber();
	}

	/** maps to transaction.registrar **/
	public static StringAttribute<Transaction> registrar()
	{
		return finder.registrar();
	}

	/** maps to transaction.source **/
	public static StringAttribute<Transaction> source()
	{
		return finder.source();
	}

	public static SchemeFinder.SchemeSingleFinderForRelatedClasses<Transaction, Scheme, Transaction> scheme()
	{
		return finder.scheme();
	}

	public static class TransactionSchemeFinderSubclass<ParentOwnerType> extends SchemeFinder.SchemeSingleFinderForRelatedClasses<ParentOwnerType, Scheme, Transaction>
	{
		public TransactionSchemeFinderSubclass(Mapper mapper, NormalAndListValueSelector parentSelector )
		{
			super(mapper);
			this._parentSelector = (AbstractRelatedFinder) parentSelector;
			this._orderBy = null;
			this._type = SIMPLE_TO_ONE;
			this._name = "scheme";
		}

		public DeepRelationshipAttribute copy()
		{
			return new TransactionSchemeFinderSubclass(zGetMapper(), (NormalAndListValueSelector) this._parentSelector
			);
		}

		protected Scheme plainValueOf(Transaction _obj)
		{
			return _obj.getScheme();
		}

		protected SchemeList plainListValueOf(Object _obj)
		{
			return ((TransactionList)_obj).getSchemes();
		}
	}

	public static Operation eq(Transaction other)
	{
		return finder.eq(other);
	}

	public static Operation all()
	{
		return new All(id());
	}

	public static TransactionSingleFinder<Transaction, Object, Transaction> getFinderInstance()
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
		cache.addIndex("0 Index", new Attribute[] 
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
			SchemeFinder.getFinderInstance(),
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
			SchemeFinder.getFinderInstance(),
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
		DoubleAttribute[] result = new DoubleAttribute[4];
		result[0] = amount();
		result[1] = units();
		result[2] = price();
		result[3] = unitBalance();
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
		objectPortal = new UninitializedPortal("tracker.domain.Transaction");
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
