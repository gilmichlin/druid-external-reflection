package com.bla.filter;


import com.google.common.base.Predicate;
import com.metamx.collections.bitmap.ImmutableBitmap;
import io.druid.query.filter.BitmapIndexSelector;
import io.druid.query.filter.ValueMatcher;
import io.druid.query.filter.ValueMatcherFactory;
import io.druid.segment.ColumnSelectorFactory;
import io.druid.segment.filter.DimensionPredicateFilter;
import io.druid.segment.filter.ExternalReflectionFilter;

import java.util.List;


public class StartsWithExternalReflectionFilter extends ExternalReflectionFilter {


    private final class StartsWithDimensionPredicateFilter extends DimensionPredicateFilter
    {
        public StartsWithDimensionPredicateFilter(
                final String dimension,
                final String prefix
        )
        {
            super(
                    dimension,
                    new Predicate<String>()
                    {
                        @Override
                        public boolean apply(String input)
                        {
                            return (input != null) && (prefix != null) && input.startsWith(prefix);
                        }
                    }
            );
        }
    }
    private final StartsWithDimensionPredicateFilter filter;


    public StartsWithExternalReflectionFilter(List<String> constructorParams) {
        super(constructorParams);

        filter = new StartsWithDimensionPredicateFilter(
                constructorParams.get(0),
                constructorParams.get(1)
        );
    }


    @Override
    public ImmutableBitmap getBitmapIndex(BitmapIndexSelector selector) {
        return filter.getBitmapIndex(selector);
    }

    @Override
    public ValueMatcher makeMatcher(ValueMatcherFactory factory) {
        return filter.makeMatcher(factory);
    }

    @Override
    public ValueMatcher makeMatcher(ColumnSelectorFactory columnSelectorFactory) {
        return filter.makeMatcher(columnSelectorFactory);
    }
}
