package org.finos.cdm.ingest.fpml;

import cdm.ingest.fpml.confirmation.common.functions.StringContains;
import cdm.ingest.fpml.confirmation.common.functions.StringContainsImpl;
import cdm.ingest.fpml.confirmation.pricequantity.functions.CreateAssetKey;
import cdm.ingest.fpml.confirmation.pricequantity.functions.CreateAssetKeyImpl;
import cdm.ingest.fpml.confirmation.pricequantity.functions.CreateKey;
import cdm.ingest.fpml.confirmation.pricequantity.functions.CreateKeyImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * End-to-end runtime verification of the sibling-module split.
 *
 * Proves that when core CDM types are consumed from cdm-java on the
 * classpath (rather than compiled from source), Guice can still wire
 * CdmFpmlIngestRuntimeModule correctly and resolve its bindings.
 *
 * If this test passes against a core-only cdm-java (-P core), the
 * "-P fpml-only against published core" scenario from #4855 is
 * validated at runtime as well as compile-time.
 */
class CdmFpmlIngestRuntimeModuleSmokeTest {

    @Test
    void injector_creates_and_resolves_fpml_bindings() {
        Injector injector = Guice.createInjector(new CdmFpmlIngestRuntimeModule());

        StringContains stringContains = injector.getInstance(StringContains.class);
        assertNotNull(stringContains, "StringContains binding must resolve");
        assertTrue(stringContains instanceof StringContainsImpl,
                "StringContains must be bound to StringContainsImpl, was " + stringContains.getClass());

        CreateKey createKey = injector.getInstance(CreateKey.class);
        assertNotNull(createKey, "CreateKey binding must resolve");
        assertTrue(createKey instanceof CreateKeyImpl,
                "CreateKey must be bound to CreateKeyImpl, was " + createKey.getClass());

        CreateAssetKey createAssetKey = injector.getInstance(CreateAssetKey.class);
        assertNotNull(createAssetKey, "CreateAssetKey binding must resolve");
        assertTrue(createAssetKey instanceof CreateAssetKeyImpl,
                "CreateAssetKey must be bound to CreateAssetKeyImpl, was " + createAssetKey.getClass());
    }
}
