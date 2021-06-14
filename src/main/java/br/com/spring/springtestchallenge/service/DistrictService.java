package br.com.spring.springtestchallenge.service;

import br.com.spring.springtestchallenge.exceptions.DistrictNotFoundException;

public interface DistrictService {

    Double priceBerDistrict(final String districtName) throws DistrictNotFoundException;
}
