# cdm-fixml-mappings (spike, placeholder)

Parallel to `../rosetta-source-fpml/`, this module demonstrates that
the sibling-module pattern used for FpML applies unchanged to a
FIXML ingest surface.

## Current content

There is **no active FIXML mapping workstream** in this repository as
of today. The single `ingest-fixml-example.rosetta` file living at
`../rosetta-source/src/main/rosetta/ingest-fixml-example.rosetta` is
a placeholder — a trivial passthrough function that exists so this
module has something to build and time.

When a real FIXML workstream lands, contributors would populate
`ingest-fixml-*.rosetta` files under `../rosetta-source/src/main/rosetta/`
and (optionally) Java impls under a new `../rosetta-source/src/ingest-fixml/java/`
source root, mirroring how FpML is structured. This module would pick
them up unchanged.

## Build

Prerequisite: install a core-only `cdm-java` to `~/.m2`.

```bash
# From the repo root:
mvn -pl rosetta-source clean install -P core -DskipTests

# Then build this module:
cd rosetta-source-fixml
mvn clean install -DskipTests
```

## Relationship to #4855

Established as evidence in the CDM Partitioning & Decoupling spike
(discussion #4855) that the FpML sibling-module pattern is
generalisable — same pom shape, same widened `classPathLookupFilter`,
same relative-path sourcing from the main rosetta tree.
