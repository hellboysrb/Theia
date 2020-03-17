package com.smigic.sensorsReader.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Optional;

public class temperatureConverter extends StdConverter<Float, Float> {
    @Override
    public Float convert(final Float value) {
        return Optional.ofNullable(value)
                .filter(temperature -> (temperature.floatValue() != -999999.0))
                .orElse(null);
    }
}
