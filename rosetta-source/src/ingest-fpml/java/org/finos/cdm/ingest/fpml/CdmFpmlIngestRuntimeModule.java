package org.finos.cdm.ingest.fpml;

import cdm.ingest.fpml.confirmation.common.functions.StringContains;
import cdm.ingest.fpml.confirmation.common.functions.StringContainsImpl;
import cdm.ingest.fpml.confirmation.pricequantity.functions.*;
import cdm.ingest.fpml.confirmation.product.commodityoption.functions.CalculateCommodityCalculationPeriods;
import com.google.inject.AbstractModule;
import com.regnosys.runefpml.RuneFpmlRuntimeModule;

public class CdmFpmlIngestRuntimeModule extends AbstractModule {

    @Override
    protected void configure() {
        // upstream model dependency
        install(new RuneFpmlRuntimeModule());

        bind(StringContains.class).to(StringContainsImpl.class);
        bind(CreateKey.class).to(CreateKeyImpl.class);
        bind(CreateAssetKey.class).to(CreateAssetKeyImpl.class);
        bind(CreateKeyForQuotedCurrencyPair.class).to(CreateKeyForQuotedCurrencyPairImpl.class);
        bind(CalculateCommodityCalculationPeriods.class).to(CalculateCommodityCalculationPeriodsImpl.class);
        bind(MapCommodityOptionStrikePriceSchedule.class).to(MapCommodityOptionStrikePriceScheduleImpl.class);
    }
}
