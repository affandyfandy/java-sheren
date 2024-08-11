package MidtermExam.Group2.service;

import MidtermExam.Group2.dto.RevenueDTO;
import MidtermExam.Group2.repository.InvoiceRepository;
import MidtermExam.Group2.service.impl.RevenueServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RevenueServiceImplTest {

    @InjectMocks
    private RevenueServiceImpl revenueServiceImpl;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Test
    void getRevenueByDayTest() {
        LocalDate date = LocalDate.of(2024, 8, 9);
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(LocalTime.MAX);
        BigDecimal expectedRevenue = BigDecimal.valueOf(1000.00);

        when(invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime))
                .thenReturn(expectedRevenue);

        RevenueDTO result = revenueServiceImpl.getRevenueByDay(date);

        assertNotNull(result);
        assertEquals("2024-08-09", result.getPeriod());
        assertEquals(expectedRevenue, result.getTotalRevenue());

        verify(invoiceRepository).calculateTotalRevenueByDateTime(startDateTime, endDateTime);
    }

    @Test
    void getRevenueByMonthTest() {
        int year = 2024;
        int month = 8;
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        BigDecimal expectedRevenue = BigDecimal.valueOf(30000.00);

        when(invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime))
                .thenReturn(expectedRevenue);

        RevenueDTO result = revenueServiceImpl.getRevenueByMonth(year, month);

        assertNotNull(result);
        assertEquals("AUGUST 2024", result.getPeriod());
        assertEquals(expectedRevenue, result.getTotalRevenue());

        verify(invoiceRepository).calculateTotalRevenueByDateTime(startDateTime, endDateTime);
    }

    @Test
    void getRevenueByYearTest() {
        int year = 2024;
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        BigDecimal expectedRevenue = BigDecimal.valueOf(100000.00);

        when(invoiceRepository.calculateTotalRevenueByDateTime(startDateTime, endDateTime))
                .thenReturn(expectedRevenue);

        RevenueDTO result = revenueServiceImpl.getRevenueByYear(year);

        assertNotNull(result);
        assertEquals("2024", result.getPeriod());
        assertEquals(expectedRevenue, result.getTotalRevenue());

        verify(invoiceRepository).calculateTotalRevenueByDateTime(startDateTime, endDateTime);
    }
}

