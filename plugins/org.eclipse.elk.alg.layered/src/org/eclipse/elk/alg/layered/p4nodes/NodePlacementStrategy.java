/*******************************************************************************
 * Copyright (c) 2012, 2015 Kiel University and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.elk.alg.layered.p4nodes;

import org.eclipse.elk.alg.layered.ILayoutPhase;
import org.eclipse.elk.alg.layered.ILayoutPhaseFactory;
import org.eclipse.elk.alg.layered.p4nodes.bk.BKNodePlacer;

/**
 * Definition of the available node placement strategies for the layered layout approach.
 */
public enum NodePlacementStrategy implements ILayoutPhaseFactory {

    /**
     * Very simple and very fast node placement that centers all nodes vertically.
     */
    SIMPLE,
    
    /**
     * Interactive node placer that leaves y coordinates of nodes untouched.
     */
    INTERACTIVE,
    
    /**
     * Node placement algorithm that aligns long edges using linear segments.
     * Nodes are aligned according to the <em>pendulum</em> method, which is similar to
     * the barycenter method for node ordering.
     */
    LINEAR_SEGMENTS,
    
    /**
     * Node placement which groups nodes to blocks which result in straight edges.
     */
    BRANDES_KOEPF,
    
    /**
     * Using an auxiliary graph and the
     * {@link de.cau.cs.kieler.klay.layered.networksimplex.NetworkSimplex NetworkSimplex} algorithm
     * to calculate a balanced placement with straight long edges.
     */
    NETWORK_SIMPLEX;
    
    /**
     * {@inheritDoc}
     */
    public ILayoutPhase create() {
        switch (this) {
        case SIMPLE:
            return new SimpleNodePlacer();
            
        case INTERACTIVE:
            return new InteractiveNodePlacer();
            
        case LINEAR_SEGMENTS:
            return new LinearSegmentsNodePlacer();
            
        case BRANDES_KOEPF:
            return new BKNodePlacer();
            
        case NETWORK_SIMPLEX:
            return new NetworkSimplexPlacer();
            
        default:
            throw new IllegalArgumentException(
                    "No implementation is available for the node placer " + this.toString());
        }
    }
    
}
