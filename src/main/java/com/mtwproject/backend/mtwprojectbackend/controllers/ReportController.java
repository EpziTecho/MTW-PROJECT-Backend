package com.mtwproject.backend.mtwprojectbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtwproject.backend.mtwprojectbackend.models.entities.Company;
import com.mtwproject.backend.mtwprojectbackend.repositories.CompanyRepository;
import com.mtwproject.backend.mtwprojectbackend.services.ReportService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/report") 
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private CompanyRepository companyRepository; 

    @GetMapping("/companies/xml")
    public ResponseEntity<byte[]> downloadReport() {
        List<Company> companies = (List<Company>) companyRepository.findAll();

        byte[] reportData = reportService.generateExcelReport(companies);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=companies_report.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(reportData);
    } // Se resume en que se crea un método que se encarga de generar el reporte en excel, y se retorna un ResponseEntity con el reporte en el body.


}