package com.example.demo.service;

import com.example.demo.model.BaggageReqDto;
import com.example.demo.model.BaggageResponse;

public interface BaggageService {

	BaggageResponse checkLaugageWeight(BaggageReqDto baggageReqDto);

}
