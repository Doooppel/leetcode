package com.doppel.lineage;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BusinessLineageSearchServiceImpl implements BusinessLineageSearchService {
    @Override
    public BusinessLineageTextualOutput getBusinessLineageTextualOutput(Set<Lineage> rootNodes, LineageSearchInput lineageSearchInput) {
        List<String> selectedStages = lineageSearchInput.getSelectedStages();
        List<Map<String, Object>> allRowData = Lists.newArrayList();
        Set<String> fullColumns = Sets.newLinkedHashSet();
        rootNodes.forEach(node -> {
            List<List<Lineage>> paths = new ArrayList<>();
            getFullLineagePath(node, Lists.newArrayList(), paths);
            paths.forEach(path -> {
                Map<String, Object> rowData = buildRowData(path);
                if (!rowData.isEmpty()) {
                    allRowData.add(rowData);
                }
                fullColumns.addAll(rowData.keySet());
            });
        });
        return new BusinessLineageTextualOutput();
    }

    private Map<String, Object> buildRowData(List<Lineage> path) {
        return null;
    }

    private void getFullLineagePath(Lineage lineage, List<Lineage> curPath, List<List<Lineage>> paths) {
        if (lineage == null) {
            return;
        }
        curPath.add(lineage);
        if (CollectionUtils.isEmpty(lineage.getNext())) {
            paths.add(new ArrayList<>(curPath));
        } else {
            for (Lineage next : lineage.getNext()) {
                getFullLineagePath(next, curPath, paths);
            }
        }
        curPath.remove(curPath.size() - 1);
    }
}