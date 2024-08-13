package MidtermExam.Group2.service;

import MidtermExam.Group2.dto.CustomerDTO;
import MidtermExam.Group2.dto.InvoiceDetailDTO;
import MidtermExam.Group2.service.impl.PdfServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class PdfServiceImplTest {

    @InjectMocks
    private PdfServiceImpl pdfServiceImpl;

    @Mock
    private TemplateEngine templateEngine;

    @Test
    void generatePdfTest() throws Exception {
        InvoiceDetailDTO mockInvoiceDetail = mock(InvoiceDetailDTO.class);

        when(mockInvoiceDetail.getCustomer()).thenReturn(new CustomerDTO());
        when(mockInvoiceDetail.getProducts()).thenReturn(new ArrayList<>());

        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("invoice", mockInvoiceDetail);
        expectedData.put("customer", mockInvoiceDetail.getCustomer());
        expectedData.put("items", mockInvoiceDetail.getProducts());

        Context context = new Context();
        context.setVariables(expectedData);

        String expectedHtmlContent = "<html></html>";

        when(templateEngine.process(eq("pdf-template"), any(Context.class))).thenReturn(expectedHtmlContent);

        InputStream result = pdfServiceImpl.generatePdf(mockInvoiceDetail);

        assertNotNull(result);

        verify(templateEngine).process(eq("pdf-template"), any(Context.class));
    }
}
