/*******************************************************************************
 * Copyright (c) 2017 Kiel University and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.elk.graph.json

import org.eclipse.elk.core.data.ILayoutMetaData
import org.eclipse.elk.core.data.LayoutAlgorithmData
import org.eclipse.elk.core.data.LayoutCategoryData
import org.eclipse.elk.core.data.LayoutOptionData

/**
 * Utility class, allows to convert {@link ILayoutMetaData}s retrieved from the {@link ILayoutMetaDataService}
 * to a json representation.
 */
public final class JsonMetaDataConverter {
    
    private static extension JsonAdapter = new JsonAdapter
   
    private static def createCommon(ILayoutMetaData data) {
        val jsonObj = newJsonObject
        if (data.id !== null) {
            jsonObj.addJsonObj("id", data.id)    
        }
        if (data.name !== null) {
            jsonObj.addJsonObj("name", data.name)
        }
        if (data.description !== null) {
            jsonObj.addJsonObj("description", data.description)
        }
        return jsonObj
    }
    
    public static def dispatch toJson(LayoutAlgorithmData lad) {
        val jsonObj = lad.createCommon
        if (lad.categoryId !== null) {
            jsonObj.addJsonObj("category", lad.categoryId)
        }
        if (!lad.knownOptionIds.nullOrEmpty) {
            val jsonArr = newJsonArray
            jsonObj.addJsonObj("knownOptions", jsonArr)
            lad.knownOptionIds.forEach[ o | jsonArr.addJsonArr(o.toJson) ]    
        }
        if (!lad.supportedFeatures.nullOrEmpty) {
            val jsonArr = newJsonArray
            jsonObj.addJsonObj("supportedFeatures", jsonArr)
            lad.supportedFeatures.forEach[ f | jsonArr.addJsonArr(f.toString.toJson) ]    
        }
        return jsonObj
    }
    
    public static def dispatch toJson(LayoutCategoryData lcd) {
        val jsonObj = lcd.createCommon
        if (!lcd.layouters.nullOrEmpty) {
            val jsonArr = newJsonArray
            jsonObj.addJsonObj("knownLayouters", jsonArr)
            lcd.layouters.forEach[ l |
                if (l.id !== null) {
                    jsonArr.addJsonArr(l.id.toJson)
                }
            ]    
        }
        return jsonObj 
    }

    public static def dispatch toJson(LayoutOptionData lod) {
        val jsonObj = lod.createCommon
        if (lod.group !== null) {
            jsonObj.addJsonObj("group", lod.group)
        }
        if (lod.type !== null) {
            jsonObj.addJsonObj("type", lod.type.toString)
        }
        if (!lod.targets.nullOrEmpty) {
            val jsonArr = newJsonArray
            jsonObj.addJsonObj("targets", jsonArr)
            lod.targets.forEach[ t | jsonArr.addJsonArr(t.toString.toJson) ]    
        }
        return jsonObj
    }
    
}