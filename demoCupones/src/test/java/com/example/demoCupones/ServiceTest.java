package com.example.demoCupones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.demoCupones.models.Cupon;
import com.example.demoCupones.services.DemoService;
import com.example.demoCupones.utils.DemoUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private DemoUtils demoUtils;

    @InjectMocks
    private DemoService demoService;

    @Test
    void testConsumirCupones() {
 
    	Cupon cupon1 = new Cupon("cupon_1", "Discount_1", "title_1", "imagen_1", "2045-12-01");
		Cupon cupon2 = new Cupon("cupon_2", "Discount_2", "title_2", "imagen_2", "2022-12-20");
		Cupon cupon3 = new Cupon("cupon_3", "Discount_3", "title_3", "imagen_3", "2042-12-25");

        Cupon[] mockCupones = { cupon1, cupon2, cupon3 };
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Cupon[].class))).thenReturn(mockCupones);


        DemoUtils utils = new DemoUtils();
        List<Cupon> cuponesVigentes = utils.ObtenerCuponesVigentes(mockCupones);  
        
        when(demoUtils.ObtenerCuponesVigentes(Mockito.eq(mockCupones))).thenReturn(cuponesVigentes);
        
        
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			String cuponesUtils = objectMapper.writeValueAsString(cuponesVigentes);
			LOGGER.info("El listado de cupones vigentes es: " + cuponesUtils);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		

        List<Cupon> resultado = demoService.consumirCupones();
        

		try {
			
			String cuponesService = objectMapper.writeValueAsString(resultado);
			LOGGER.info("El listado de cupones vigentes es: " + cuponesService);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

        assertEquals(cuponesVigentes, resultado);
    }
    }
