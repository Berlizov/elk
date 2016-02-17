/*******************************************************************************
 * Copyright (c) 2016 Kiel University and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Kiel University - initial API and implementation
 *******************************************************************************/
package org.eclipse.elk.alg.layered.intermediate.compaction;

/**
 * Definition of strategies for horizontal compaction.
 */
public enum GraphCompactionStrategy {
    /** doesn't apply compaction. */
    NONE,
    /** compacts to the left. */
    LEFT,
    /** compacts to the right. */
    RIGHT,
    /** compacts left, locks {@link CNode}s that are not constrained, compacts right. */
    LEFT_RIGHT_CONSTRAINT_LOCKING,
    /** compacts left, locks {@link CNode}s based on their {@link CompactionLock}, compacts right.
     *  Yields better results for average edge length because {@link CNode}s are locked in the
     *  direction of fewer connections. */
    LEFT_RIGHT_CONNECTION_LOCKING,
    /** compacts as much as possible, however instead of minimizing the width it minimizes edge
     *  length.
     */
    EDGE_LENGTH;
}
