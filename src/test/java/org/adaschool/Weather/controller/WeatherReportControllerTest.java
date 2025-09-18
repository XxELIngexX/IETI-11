package org.adaschool.Weather.controller;

import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherReportController.class)
@ContextConfiguration(classes = {WeatherReportController.class, WeatherReportService.class})
class WeatherReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherReportService weatherReportService;

    @Test
    void testGetWeatherReportEndpoint() throws Exception {
        // Arrange
        WeatherReport fakeReport = new WeatherReport();
        fakeReport.setTemperature(309.12);
        fakeReport.setHumidity(23.0);

        when(weatherReportService.getWeatherReport(20, 50)).thenReturn(fakeReport);

        // Act & Assert
        mockMvc.perform(get("/v1/api/weather-report")
                        .param("latitude", "20")
                        .param("longitude", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperature").value(309.12))
                .andExpect(jsonPath("$.humidity").value(23.0));
    }

    @Test
    void testPingEndpoint() throws Exception {
        mockMvc.perform(get("/v1/api/ping"))
                .andExpect(status().isOk())
                .andExpect(content().string("pong"));
    }
}
