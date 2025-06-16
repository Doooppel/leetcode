package com.doppel.leetcode;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Converter {

    private Set<LineageSearchEntity> buildHierarchy(LineageGraphEntity current, Map<Long, List<LineageGraphEntity>> sourceMap, Set<LineageGraphEntity> visited) {
        Set<LineageSearchEntity> result = Sets.newHashSet();
        recursiveBuildPath(new StringBuilder(), new StringBuilder(), current, sourceMap, result, visited);
        return result;
    }

    private void recursiveBuildPath(StringBuilder nodeIdSb, StringBuilder pathSb, LineageGraphEntity current, Map<Long, List<LineageGraphEntity>> sourceMap, Set<LineageSearchEntity> result, Set<LineageGraphEntity> visited) {
        List<LineageGraphEntity> nexts = sourceMap.get(current.getTargetNodeId());
        checkNAppend(nodeIdSb, pathSb, current.getSourceNodeId(), current.getSourceNodePath());
        if (visited.contains(current) || nexts == null) {
            checkNAppend(nodeIdSb, pathSb, current.getTargetNodeId(), current.getTargetNodePath());
            addToResult(nodeIdSb, pathSb, current, result);
            visited.add(current);
            return;

        }
        for (LineageGraphEntity next : nexts) {
            recursiveBuildPath(nodeIdSb, pathSb, next, sourceMap, result, visited);
        }


    }

    private void addToResult(StringBuilder nodeIdSb, StringBuilder pathSb, LineageGraphEntity current, Set<LineageSearchEntity> result) {
        LineageSearchEntity lineageSearchEntity = new LineageSearchEntity();
        lineageSearchEntity.setNodes(nodeIdSb.toString());
        lineageSearchEntity.setPath(pathSb.toString());
        result.add(lineageSearchEntity);
        nodeIdSb.setLength(0);
        pathSb.setLength(0);
    }

    private void checkNAppend(StringBuilder nodeIdSb, StringBuilder pathSb, Object sourceNodeId, Object sourceNodePath) {
        if (!nodeIdSb.toString().contains(sourceNodeId.toString())) {
            nodeIdSb.append(sourceNodeId);
            pathSb.append(sourceNodePath);
        }
    }
}
