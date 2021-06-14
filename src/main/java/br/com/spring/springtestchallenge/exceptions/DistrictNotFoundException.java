package br.com.spring.springtestchallenge.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistrictNotFoundException extends Exception {

    private String invalidDistrict;

    public DistrictNotFoundException(String invalidDistrict) {
        super("District not found: " + invalidDistrict + ".");
    }
}
