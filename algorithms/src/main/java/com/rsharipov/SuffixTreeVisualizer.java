package com.rsharipov;

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.rsharipov.SuffixTreeBuilder.Node;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

public class SuffixTreeVisualizer {
    
    public Component visualize(String line, Node node) {
        mxGraph graph = new mxGraph();
        graph.getModel().beginUpdate();
        try
        {
            Map<Node, Object> nodeToVertex = new HashMap<>();
            createChildren(line, graph, node, graph.getDefaultParent(), nodeToVertex);
        }
        finally {
            graph.getModel().endUpdate();
        }
        mxGraphComponent result = new mxGraphComponent(graph);
        morphGraph(graph, result);
        return result;
    }
    
    private static void morphGraph(final mxGraph graph, mxGraphComponent graphComponent) {
        new mxFastOrganicLayout(graph).execute(graph.getDefaultParent());
//        new mxHierarchicalLayout(graph).execute(graph.getDefaultParent());
    }

    private Object createChildren(String line, mxGraph graph, Node node, Object parent, Map<Node, Object> nodeToVertex) {        
        Object vertex = graph.insertVertex(parent, "", "", 0, 0, 50, 50);
        nodeToVertex.put(node, vertex);
        Map<Character, Node> children = node.getChildren();
        for (Node child : children.values()) {
            Object childVertex = createChildren(line, graph, child, parent, nodeToVertex);
            graph.insertEdge(parent, null, line.substring(child.getLeftBoundInclusive(), 
                    child.getRightBoundInclusive() + 1) + "(" + 
                    child.getLeftBoundInclusive() + ", " + 
                    (child.getRightBoundInclusive()) + ")", 
                    vertex, childVertex);            
        }
        return vertex;
    }
    
}
