package org.finos.cdm;

import cdm.base.datetime.functions.*;
import cdm.base.math.functions.*;
import cdm.base.staticdata.codelist.LoadCodeListImpl;
import cdm.base.staticdata.codelist.functions.LoadCodeList;
import cdm.observable.asset.calculatedrate.functions.IndexValueObservation;
import cdm.observable.asset.fro.functions.IndexValueObservationEmptyDataProvider;
import cdm.product.common.schedule.functions.*;
import com.google.inject.AbstractModule;
import com.google.inject.Module;
import com.regnosys.rosetta.common.hashing.ReferenceConfig;
import com.regnosys.rosetta.common.postprocess.qualify.QualificationHandlerProvider;
import com.rosetta.model.lib.ModuleConfig;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.validation.ValidatorFactory;
import org.finos.cdm.qualify.CdmQualificationHandlerProvider;
import org.finos.cdm.reference.CdmReferenceConfig;

@ModuleConfig(model = "COMMON-DOMAIN-MODEL", type = "Rosetta")
public class CdmRuntimeModule extends AbstractModule {

    @Override
    protected void configure() {
        // FpML ingest surface is loaded reflectively so a core-only build
        // (which excludes src/ingest-fpml/java) still compiles and runs.
        installIfPresent("org.finos.cdm.ingest.fpml.CdmFpmlIngestRuntimeModule");

        bind(QualifyFunctionFactory.class).to(bindQualifyFunctionFactory());
        bind(QualificationHandlerProvider.class).to(bindQualificationConfigProvider());
        bind(ValidatorFactory.class).to(bindValidatorFactory());
        bind(ReferenceConfig.class).toInstance(CdmReferenceConfig.get());

        // Requires DSL get-item(index)
        bind(VectorOperation.class).to(bindVectorOperation());
        bind(VectorGrowthOperation.class).to(bindVectorGrowthOperation());

        // Requires DSL remove-item(index)
        bind(PopOffDateList.class).to(bindPopOffDateList());

        // Rounding (not supported in DSL)
        bind(RoundToNearest.class).to(bindRoundToNearest());
        bind(RoundToPrecision.class).to(bindRoundToPrecision());
        bind(RoundToSignificantFigures.class).to(bindRoundToSignificantFigures());

        // Data providers (external data)
        bind(BusinessCenterHolidays.class).to(bindBusinessCenterHolidays()).asEagerSingleton();
        bind(IndexValueObservation.class).to(bindIndexValueObservation()).asEagerSingleton();

        // Date functions (not supported in DSL)
        bind(Now.class).to(bindNow());
        bind(Today.class).to(bindToday());
        bind(ToTime.class).to(bindToTime());
        bind(AddDays.class).to(bindAddDays());
        bind(DayOfWeek.class).to(bindDayOfWeek());
        bind(DateDifference.class).to(bindDateDifference());
        bind(LeapYearDateDifference.class).to(bindLeapYearDateDiff());
        bind(CalculationPeriodRange.class).to(bindCalculationPeriodRange());
        bind(CalculationPeriod.class).to(bindCalculationPeriod());
        bind(CalculationPeriods.class).to(bindCalculationPeriods());
        bind(ResolveAdjustableDate.class).to(bindResolveAdjustableDate());
        bind(ResolveAdjustableDates.class).to(bindResolveAdjustableDates());

		// External FpML Coding Schemes data loader
		bind(LoadCodeList.class).to(bindLoadCodeList());
    }

    private void installIfPresent(String moduleClassName) {
        try {
            Class<?> moduleClass = Class.forName(moduleClassName);
            install((Module) moduleClass.getDeclaredConstructor().newInstance());
        } catch (ClassNotFoundException e) {
            // Optional module not on classpath (e.g. -P core build); skip.
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to install optional module " + moduleClassName, e);
        }
    }

	protected Class<? extends LoadCodeList> bindLoadCodeList() {
		return LoadCodeListImpl.class;
	}

    protected Class<? extends CalculationPeriodRange> bindCalculationPeriodRange() {
        return CalculationPeriodRangeImpl.class;
    }

    protected Class<? extends VectorOperation> bindVectorOperation() {
        return VectorOperationImpl.class;
    }

    protected Class<? extends VectorGrowthOperation> bindVectorGrowthOperation() {
        return VectorGrowthOperationImpl.class;
    }

    protected Class<? extends QualifyFunctionFactory> bindQualifyFunctionFactory() {
        return QualifyFunctionFactory.Default.class;
    }

    protected Class<? extends QualificationHandlerProvider> bindQualificationConfigProvider() {
        return CdmQualificationHandlerProvider.class;
    }

    protected Class<? extends ValidatorFactory> bindValidatorFactory() {
        return ValidatorFactory.Default.class;
    }

    // Functions

    protected Class<? extends CalculationPeriod> bindCalculationPeriod() {
        return CalculationPeriodImpl.class;
    }

    protected Class<? extends CalculationPeriods> bindCalculationPeriods() {
        return CalculationPeriodsImpl.class;
    }

    private Class<? extends ResolveAdjustableDate> bindResolveAdjustableDate() {
        return ResolveAdjustableDateImpl.class;
    }

    private Class<? extends ResolveAdjustableDates> bindResolveAdjustableDates() {
        return ResolveAdjustableDatesImpl.class;
    }

    protected Class<? extends RoundToNearest> bindRoundToNearest() {
        return RoundToNearestImpl.class;
    }

    protected Class<? extends RoundToPrecision> bindRoundToPrecision() {
        return RoundToPrecisionImpl.class;
    }

    protected Class<? extends RoundToSignificantFigures> bindRoundToSignificantFigures() {
        return RoundToSignificantFiguresImpl.class;
    }

    protected Class<? extends Now> bindNow() {
        return NowImpl.class;
    }

    protected Class<? extends ToTime> bindToTime() {
        return ToTimeImpl.class;
    }

    protected Class<? extends AddDays> bindAddDays() {
        return AddDaysImpl.class;
    }

    protected Class<? extends PopOffDateList> bindPopOffDateList() {
        return PopOffDateListImpl.class;
    }

    protected Class<? extends BusinessCenterHolidays> bindBusinessCenterHolidays() {
        return BusinessCenterHolidaysEmptyDataProvider.class;
    }

    protected Class<? extends DateDifference> bindDateDifference() {
        return DateDifferenceImpl.class;
    }

    protected Class<? extends LeapYearDateDifference> bindLeapYearDateDiff() {
        return LeapYearDateDifferenceImpl.class;
    }

    protected Class<? extends DayOfWeek> bindDayOfWeek() {
        return DayOfWeekImpl.class;
    }

    protected Class<? extends IndexValueObservation> bindIndexValueObservation() {
        return IndexValueObservationEmptyDataProvider.class;
    }

    protected Class<? extends Today> bindToday() {
        return TodayImpl.class;
    }
}
