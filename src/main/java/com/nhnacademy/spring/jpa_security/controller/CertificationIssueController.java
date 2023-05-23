package com.nhnacademy.spring.jpa_security.controller;

import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateBirthReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateDeathReportDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateFamilyRelationshipDto;
import com.nhnacademy.spring.jpa_security.domain.certificate_issue.CertificateResidentRegistrationDto;
import com.nhnacademy.spring.jpa_security.entity.CertificateIssue;
import com.nhnacademy.spring.jpa_security.service.certificate_issue.CertificateIssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/certificate/{serialNumber}")
@RequiredArgsConstructor
public class CertificationIssueController {
    private final CertificateIssueService certificateIssueService;
    private final int PAGE_SIZE = 10;


    @GetMapping("/relation")
    public String showFamilyRelationshipCertificate(@PathVariable("serialNumber") Long serialNumber,
                                                    Model model) {
        CertificateFamilyRelationshipDto familyRelationCertificate =
                certificateIssueService.getFamilyRelationshipCertificate(serialNumber);

        model.addAttribute("certificate", familyRelationCertificate);
        return "certificate/relationship";
    }

    @GetMapping("/registration")
    public String showResidentRegistrationCertificate(@PathVariable("serialNumber") Long serialNumber,
                                                      Model model) {
        CertificateResidentRegistrationDto residentRegistrationCertificate =
                certificateIssueService.getResidentRegistrationCertificate(serialNumber);

        model.addAttribute("certificate", residentRegistrationCertificate);

        return "certificate/registration";
    }

    @GetMapping("/birth")
    public String showBirthReportCertificate(@PathVariable("serialNumber") Long serialNumber,
                                             Model model) {
        CertificateBirthReportDto birthReportCertificate =
                certificateIssueService.getBirthReportCertificate(serialNumber);

        model.addAttribute("certificate", birthReportCertificate);

        return "certificate/birth";
    }

    @GetMapping("/death")
    public String showDeathReportCertificate(@PathVariable("serialNumber") Long serialNumber,
                                             Model model) {
        CertificateDeathReportDto deathReportCertificate =
                certificateIssueService.getDeathReportCertificate(serialNumber);

        model.addAttribute("certificate", deathReportCertificate);

        return "certificate/death";
    }

    @GetMapping
    public String showInquiryCertificateList(@RequestParam("page") Optional<Integer> page,
                                             @PathVariable("serialNumber") Long serialNumber,
                                             Model model) {
        int curPage = page.orElse(1);

        Page<CertificateIssue> list = certificateIssueService
                .getCertificateIssues(
                        PageRequest.of(curPage - 1, PAGE_SIZE, Sort.by("certificateIssueDate").descending()),
                        serialNumber);

        int totalPages = list.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("curPage", curPage);
        model.addAttribute("lists", list);
        model.addAttribute("serialNumber", serialNumber);

        return "certificate/list";
    }
}
