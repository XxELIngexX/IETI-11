//-----Pega entre las dos lineas todo el unit test de la clase WeatherReportService---------
package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class WeatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    void testGetWeatherReport() {
        // Arrange
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemp(309.12);
        main.setHumidity(23.0);

        WeatherApiResponse fakeApiResponse = new WeatherApiResponse();
        fakeApiResponse.setMain(main);

        // 👇 Aquí usamos lenient()
        lenient().when(restTemplate.getForObject(anyString(), eq(WeatherApiResponse.class)))
                .thenReturn(fakeApiResponse);

        // Act
        WeatherReport report = weatherReportService.getWeatherReport(20, 50);

        // Assert (ajusta a los valores que realmente devuelvas)
        assertEquals(309.12, report.getTemperature());
        assertEquals(23.0, report.getHumidity());
    }
}
