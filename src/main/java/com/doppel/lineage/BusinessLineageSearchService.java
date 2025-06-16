package com.doppel.lineage;

import java.util.Set;

public interface BusinessLineageSearchService {

    BusinessLineageTextualOutput getBusinessLineageTextualOutput(Set<Lineage> rootNodes, LineageSearchInput lineageSearchInput);
}
