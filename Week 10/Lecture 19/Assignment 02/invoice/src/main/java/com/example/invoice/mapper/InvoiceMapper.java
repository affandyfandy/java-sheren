package com.example.invoice.mapper;

import com.example.invoice.dto.InvoiceDTO;
import com.example.invoice.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    @Mapping(source = "productIds", target = "productIds")
    InvoiceDTO toDTO(Invoice invoice);

    @Mapping(source = "productIds", target = "productIds")
    Invoice toEntity(InvoiceDTO invoiceDTO);
}
