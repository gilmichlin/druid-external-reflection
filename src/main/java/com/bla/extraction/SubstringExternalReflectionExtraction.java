package com.bla.extraction;

import io.druid.query.extraction.ExternalReflectionExtraction;
import io.druid.query.extraction.ExtractionFn;

import java.util.List;

public class SubstringExternalReflectionExtraction extends ExternalReflectionExtraction {


    private int beginIndex;
    private int endIndex;

    public SubstringExternalReflectionExtraction(List<String> constructorParams) {
        super(constructorParams);

        // may throw
        beginIndex = Integer.valueOf(constructorParams.get(0));
        endIndex = Integer.valueOf(constructorParams.get(1));

    }

    @Override
    public String apply(Object value) {
        return apply(value == null ? null : value.toString());
    }

    @Override
    public String apply(long value) {
        return apply(Long.toString(value));
    }


    @Override
    public String apply(String value) {
        return value == null ? null : value.substring(beginIndex, endIndex);
    }


    @Override
    public boolean preservesOrdering() {
        return false;
    }

    @Override
    public ExtractionFn.ExtractionType getExtractionType() {
        return ExtractionFn.ExtractionType.ONE_TO_ONE;
    }
}
