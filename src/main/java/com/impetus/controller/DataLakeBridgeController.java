package com.impetus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.impetus.request.DataLakeRequest;
import com.impetus.response.DataLakeResponse;
import com.impetus.utils.DataLakeTokens;

/**
 * @author jay.prakash
 *
 */
@SuppressWarnings("all")
@RestController
public class DataLakeBridgeController {

	private Logger logger = LoggerFactory.getLogger(DataLakeBridgeController.class);

	@RequestMapping("/ping")
	public String healthCheck() {
		return "Data Lake Bridge API Up and Running";
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity executeScheduler(@RequestBody DataLakeRequest request,
			@RequestHeader("authToken") String authToken) {

		DataLakeResponse response = new DataLakeResponse();
		ResponseEntity responseEntity;

		if (!EnumUtils.isValidEnum(DataLakeTokens.class, authToken)) {
			response.addMessages(" UnAuthorized to Application");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		} else if (DataLakeTokens.eb04ed0c34f09e0390eb4c8187fc733e.name().equalsIgnoreCase(authToken)) {
			// TODO send request to rbac API for validation
			if (true) {
				// TODO send request to ETL API

			} else {
				response.addMessages(" UnAuthorized to Application");
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

			}

			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
