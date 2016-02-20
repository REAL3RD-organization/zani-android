package com.jani.real.jani;

import android.graphics.drawable.Drawable;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by JeongMinCha on 2016. 2. 3..
 */
public class ListData {

    public Drawable icon;
    public String name;

    public static final Comparator<ListData> ALPHA_COMPARATOR = new Comparator<ListData>() {
        private final Collator collator = Collator.getInstance();

        @Override
        public int compare(ListData listData, ListData t1) {
            return collator.compare(listData.name, t1.name);
        }
    };
}
