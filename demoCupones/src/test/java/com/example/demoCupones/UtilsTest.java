package com.example.demoCupones;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demoCupones.models.Cupon;
import com.example.demoCupones.services.DemoService;
import com.example.demoCupones.utils.DemoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class UtilsTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

	@Test
	public void testObtenerCuponesVigentes() {
		// Mocks de cupones
		Cupon cupon1 = new Cupon("cupon_1", "Discount_1", "title_1", "imagen_1", "2045-12-01");
		Cupon cupon2 = new Cupon("cupon_2", "Discount_2", "title_2", "imagen_2", "2022-12-20");
		Cupon cupon3 = new Cupon("cupon_3", "Discount_3", "title_3", "imagen_3", "2042-12-25");

		Cupon[] mockCupones = { cupon1, cupon2, cupon3 };

		DemoUtils utils = new DemoUtils();
		List<Cupon> cuponesVigentes = utils.ObtenerCuponesVigentes(mockCupones);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			String cuponesJson = objectMapper.writeValueAsString(cuponesVigentes);
			LOGGER.info("El listado de cupones vigentes es: " + cuponesJson);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}


		assertEquals(2, cuponesVigentes.size());
	}
}

