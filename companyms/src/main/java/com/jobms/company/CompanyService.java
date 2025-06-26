package com.jobms.company;

import java.util.List;

public interface CompanyService {
    void createCompany(Company company);
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    boolean deleteCompany(Long id);
    Company getCompanyById(Long id);
}
