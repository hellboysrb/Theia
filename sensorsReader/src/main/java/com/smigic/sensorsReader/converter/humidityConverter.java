package com.smigic.sensorsReader.converter;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.util.Optional;

public class humidityConverter extends StdConverter<Float, Float> {
    @Override
    public Float convert(final Float value) {
        return Optional.ofNullable(value)
                .filter(humidity -> (humidity.floatValue() != -1))
                .orElse(null);
    }
}
