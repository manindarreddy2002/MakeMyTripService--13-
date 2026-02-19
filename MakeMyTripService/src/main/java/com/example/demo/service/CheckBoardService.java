package com.example.demo.service;

import com.example.demo.model.BoardingPassDto;
import com.example.demo.model.CheckReqDto;

public interface CheckBoardService {

	BoardingPassDto doCheckIn(CheckReqDto checkReqDto);

}
