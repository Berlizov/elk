/*******************************************************************************
 * Copyright (c) 2013, 2015 Kiel University and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.elk.alg.mrtree.intermediate;

import java.util.List;

import org.eclipse.elk.alg.mrtree.graph.TEdge;
import org.eclipse.elk.alg.mrtree.graph.TGraph;
import org.eclipse.elk.alg.mrtree.options.InternalProperties;
import org.eclipse.elk.core.alg.ILayoutProcessor;
import org.eclipse.elk.core.util.IElkProgressMonitor;

/**
 * This processor should run as a post-processor after the first phase. It should run through 
 * the list of edges that once destroyed the tree property and simply insert that edges directly after
 * layouting the graph.
 * 
 * @author sor
 * @author sgu
 */
public class Untreeifyer implements ILayoutProcessor<TGraph> {

    /**
     * {@inheritDoc}
     */
    public void process(final TGraph tGraph, final IElkProgressMonitor progressMonitor) {
        List<TEdge>  edges = tGraph.getProperty(InternalProperties.REMOVABLE_EDGES);

        for (TEdge tEdge : edges) {
            tEdge.getSource().getOutgoingEdges().add(tEdge);
            tEdge.getTarget().getIncomingEdges().add(tEdge);
        }
    }
    
}
