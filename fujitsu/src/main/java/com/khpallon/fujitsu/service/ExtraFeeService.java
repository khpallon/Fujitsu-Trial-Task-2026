package com.khpallon.fujitsu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.khpallon.fujitsu.enums.Vehicle;
import com.khpallon.fujitsu.model.WeatherEntity;
import com.khpallon.fujitsu.service.rule.FeeRule;

@Service
public class ExtraFeeService {
        private final List<FeeRule> rules;

    public ExtraFeeService(List<FeeRule> rules) {
        this.rules = rules;
    }

    public double calculate(Vehicle vehicle, WeatherEntity weather) {
        return rules.stream()
                .mapToDouble(rule -> rule.apply(vehicle, weather))
                .sum();
    }
}
