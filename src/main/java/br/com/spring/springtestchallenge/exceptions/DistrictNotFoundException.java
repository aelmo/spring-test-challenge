package br.com.spring.springtestchallenge.exceptions;

public class DistrictNotFoundException extends Exception {

    public DistrictNotFoundException() {
        super("District Not Found");
    }
}
