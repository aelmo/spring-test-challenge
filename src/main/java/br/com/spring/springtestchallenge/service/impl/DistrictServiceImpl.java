package br.com.spring.springtestchallenge.service.impl;

import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;
import br.com.spring.springtestchallenge.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DistrictServiceImpl implements DistrictService {

    private static final Map<String, Double> districtValue;
    static {
        districtValue = Map.of(
                "Cachoeira", 200D,
                "Canasvieiras", 100D);
    }

    @Override
    public Double priceBerDistrict(String districtName) throws DistrictNotFoundException {
        return Optional.ofNullable(districtValue.get(districtName)).orElseThrow(DistrictNotFoundException::new);
    }
}
