package com.kiwipay.onboarding.risk.application.internal.queryservices;

import com.kiwipay.onboarding.risk.application.internal.dto.LoanRiskInfoResponse;
import com.kiwipay.onboarding.risk.application.internal.dto.MedicalRiskInfoResponse;
import com.kiwipay.onboarding.risk.application.internal.dto.PatientRiskInfoResponse;
import com.kiwipay.onboarding.risk.domain.model.exceptions.RiskBusinessException;
import com.kiwipay.onboarding.risk.domain.services.RiskQueryService;
import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities.LoanRiskInfoEntity;
import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities.MedicalRiskInfoEntity;
import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.entities.PatientRiskInfoEntity;
import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.repositories.LoanRiskInfoRepository;
import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.repositories.MedicalRiskInfoRepository;
import com.kiwipay.onboarding.risk.infrastructure.persistence.jpa.repositories.PatientRiskInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiskQueryServiceImpl implements RiskQueryService {

    @Autowired
    private PatientRiskInfoRepository patientRiskInfoRepository;

    @Autowired
    private LoanRiskInfoRepository loanRiskInfoRepository;

    @Autowired
    private MedicalRiskInfoRepository medicalRiskInfoRepository;

    @Override
    public PatientRiskInfoResponse getPatientRiskInfo(Long clientId) {
        PatientRiskInfoEntity entity = patientRiskInfoRepository.findByClientId(clientId)
            .orElseThrow(RiskBusinessException::patientRiskInfoNotFound);
        
        PatientRiskInfoResponse response = new PatientRiskInfoResponse();
        response.setLastNames(entity.getLastNames());
        response.setFirstNames(entity.getFirstNames());
        response.setDocumentNumber(entity.getDocumentNumber());
        response.setBirthDate(entity.getBirthDate());
        response.setAge(entity.getAge());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setMonthlyIncome(entity.getMonthlyIncome());
        
        // Experian data
        response.setExperianClassification(entity.getExperianClassification());
        response.setExperianSegment(entity.getExperianSegment());
        response.setExperianGroup(entity.getExperianGroup());
        response.setExperianScore(entity.getExperianScore());
        response.setExperianResponse(entity.getExperianResponse());
        response.setExperianResult(entity.getExperianResult());
        
        return response;
    }

    @Override
    public LoanRiskInfoResponse getLoanRiskInfo(Long clientId) {
        LoanRiskInfoEntity entity = loanRiskInfoRepository.findByClientId(clientId)
            .orElseThrow(RiskBusinessException::loanRiskInfoNotFound);
        
        LoanRiskInfoResponse response = new LoanRiskInfoResponse();
        response.setCampaign(entity.getCampaign());
        response.setLoanStatus(entity.getLoanStatus());
        response.setApplicationDate(entity.getApplicationDate());
        response.setInstallments(entity.getInstallments());
        response.setMonthlyInstallment(entity.getMonthlyInstallment());
        response.setRequestedAmount(entity.getRequestedAmount());
        
        return response;
    }

    @Override
    public MedicalRiskInfoResponse getMedicalRiskInfo(Long clientId) {
        MedicalRiskInfoEntity entity = medicalRiskInfoRepository.findByClientId(clientId)
            .orElseThrow(RiskBusinessException::medicalRiskInfoNotFound);
        
        MedicalRiskInfoResponse response = new MedicalRiskInfoResponse();
        response.setMedicalCategory(entity.getMedicalCategory());
        response.setMedicalCenter(entity.getMedicalCenter());
        response.setBranch(entity.getBranch());
        
        return response;
    }
}