package com.mtwproject.backend.mtwprojectbackend.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReportService {

    public byte[] generateExcelReport(List<Company> companies) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Companies");

            // Crear encabezado
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Business Name");
            header.createCell(2).setCellValue("Id Number");
            header.createCell(3).setCellValue("Address");
            header.createCell(4).setCellValue("TradeName");
            header.createCell(5).setCellValue("Phone");
           

            // Llenar datos
            int rowNum = 1;
            for (Company company : companies) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(company.getIdCompany());
                row.createCell(1).setCellValue(company.getBusinessName());
                row.createCell(2).setCellValue(company.getIdNumber());
                row.createCell(3).setCellValue(company.getAddress());
                row.createCell(4).setCellValue(company.getTradeName());
                row.createCell(5).setCellValue(company.getPhone());
               
            }
            int numeroDeColumnas = header.getPhysicalNumberOfCells(); // Se obtiene el número de columnas y se ajusta el tamaño de las mismas
          
            for (int i = 0; i < numeroDeColumnas; i++) {
            sheet.autoSizeColumn(i);
        } // Se ajusta el tamaño de las filas (el -1 es para que no cuente el encabezado) 
            workbook.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte", e);
        }
    } 
}