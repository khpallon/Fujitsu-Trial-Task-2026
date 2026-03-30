package com.khpallon.fujitsu.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.service.rule.FeeRule;

class ExtraFeeServiceTest {

    private FeeRule rule1 = mock(FeeRule.class);
    private FeeRule rule2 = mock(FeeRule.class);

    private ExtraFeeService service = new ExtraFeeService(List.of(rule1, rule2));

    @Test
    void sumsAllRuleResults() {
        Vehicle vehicle = Vehicle.BIKE;
        WeatherEntity weather = new WeatherEntity();

        when(rule1.apply(vehicle, weather)).thenReturn(1.0);
        when(rule2.apply(vehicle, weather)).thenReturn(2.0);

        double result = service.calculate(vehicle, weather);

        assertEquals(3.0, result);
    }

    @Test
    void returnsZeroWhenNoRules() {
        ExtraFeeService emptyService = new ExtraFeeService(List.of());

        double result = emptyService.calculate(Vehicle.CAR, new WeatherEntity());

        assertEquals(0.0, result);
    }
}

