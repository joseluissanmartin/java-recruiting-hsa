package com.example.demoCupones.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoCupones.constants.DemoConstant;
import com.example.demoCupones.models.Wrapper;
import com.example.demoCupones.services.DemoService;
import com.example.demoCupones.utils.DemoUtils;

@RestController
@RequestMapping("/api")
public class DemoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

	private DemoService demoService;

	@Autowired
	private DemoUtils demoUtils;

	@Autowired
	public DemoController(DemoService demoService) {
		this.demoService = demoService;
	}

	@GetMapping("/cuponesVigentes")
	@Cacheable(value = "cupones")
	public ResponseEntity<Wrapper> getCupones() {

		Wrapper result = new Wrapper();
		Date date = new Date();

		try {

			demoUtils.clearCache();

			result.setTimestamp(date.getTime());
			result.setStatus(HttpStatus.OK.value());
			result.setMensaje(DemoConstant.CONSULTA_EXITOSA);
			result.setData(demoService.consumirCupones());

			return ResponseEntity.status(HttpStatus.OK).body(result);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result.setMensaje(String.format("Error al realizar la petición: " + e.getMessage()));
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
		}
	}

}

