package com.example.cleveradapter;

import java.util.HashMap;
import java.util.Map;

public class ViewTypeManager {

    private static final Map<Class, Integer> VIEW_TYPE_MAP = new HashMap<>();


//    public int getType(CleverModel<?> cleverModel){
//
//    }



    private static int getViewTypeInternal(CleverModel<?> model) {
        int defaultViewType = model.getViewType();
        if (defaultViewType != 0) {
            return defaultViewType;
        }
        Class modelClass = model.getClass();

        Integer viewType = VIEW_TYPE_MAP.get(modelClass);

        if (viewType == null) {
            viewType = -VIEW_TYPE_MAP.size() - 1;
            VIEW_TYPE_MAP.put(modelClass, viewType);
        }

        return viewType;
    }
}
