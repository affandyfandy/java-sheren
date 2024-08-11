package MidtermExam.Group2.controller;

import MidtermExam.Group2.dto.RevenueDTO;
import MidtermExam.Group2.service.impl.RevenueServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = RevenueController.class)
class RevenueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RevenueServiceImpl revenueService;

    @Test
    void getRevenueByDayTest() throws Exception {
        RevenueDTO revenueDTO = new RevenueDTO("2024-09-15", BigDecimal.valueOf(1000.00));

        when(revenueService.getRevenueByDay(any(LocalDate.class))).thenReturn(revenueDTO);

        mockMvc.perform(get("/api/v1/revenue/day")
                        .param("date", revenueDTO.getPeriod())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.period").value(revenueDTO.getPeriod()))
                .andExpect(jsonPath("$.totalRevenue").value(revenueDTO.getTotalRevenue()))
                .andDo(print());
    }

    @Test
    void getRevenueByMonthTest() throws Exception {
        RevenueDTO revenueDTO = new RevenueDTO("SEPTEMBER 2024", BigDecimal.valueOf(1000.00));

        when(revenueService.getRevenueByMonth(2024, 9)).thenReturn(revenueDTO);

        mockMvc.perform(get("/api/v1/revenue/month")
                        .param("year", "2024")
                        .param("month", "9")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.period").value(revenueDTO.getPeriod()))
                .andExpect(jsonPath("$.totalRevenue").value(revenueDTO.getTotalRevenue()))
                .andDo(print());
    }

    @Test
    void getRevenueByYearTest() throws Exception {
        RevenueDTO revenueDTO = new RevenueDTO("2024", BigDecimal.valueOf(360000.00));

        when(revenueService.getRevenueByYear(2024)).thenReturn(revenueDTO);

        mockMvc.perform(get("/api/v1/revenue/year")
                        .param("year", "2024")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.period").value(revenueDTO.getPeriod()))
                .andExpect(jsonPath("$.totalRevenue").value(revenueDTO.getTotalRevenue()))
                .andDo(print());
    }
}

