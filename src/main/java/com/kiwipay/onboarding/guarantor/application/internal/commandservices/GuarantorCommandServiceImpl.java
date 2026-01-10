package com.kiwipay.onboarding.guarantor.application.internal.commandservices;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorCreateRequest;
import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor;
import com.kiwipay.onboarding.guarantor.domain.model.exceptions.GuarantorBusinessException;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorCommandService;
import com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa.GuarantorRepository;
import com.kiwipay.onboarding.loan.infrastructure.persistence.jpa.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service
@Transactional
public class GuarantorCommandServiceImpl implements GuarantorCommandService {

    @Autowired
    private GuarantorRepository guarantorRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public GuarantorResponse createGuarantor(Long loanId, GuarantorCreateRequest request) {
        // Validate loan exists
        if (!loanRepository.existsById(loanId)) {
            throw GuarantorBusinessException.loanNotFound();
        }

        Guarantor guarantor = new Guarantor(
                loanId,
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getMonthlyIncome(),
                request.getFirstNames(),
                request.getLastNames(),
                request.getGender(),
                request.getMaritalStatus(),
                request.getEmail(),
                request.getPhone(),
                request.getDistrictId(),
                request.getAddressLine1());

        Guarantor savedGuarantor = guarantorRepository.save(guarantor);

        GuarantorResponse response = new GuarantorResponse();
        BeanUtils.copyProperties(savedGuarantor, response);
        return response;
    }

    @Override
    public GuarantorResponse updateGuarantor(Long guarantorId, GuarantorCreateRequest request) {
        Guarantor guarantor = guarantorRepository.findById(guarantorId)
                .orElseThrow(GuarantorBusinessException::guarantorNotFound);

        guarantor.updateDetails(
                request.getDocumentType(),
                request.getDocumentNumber(),
                request.getMonthlyIncome(),
                request.getFirstNames(),
                request.getLastNames(),
                request.getGender(),
                request.getMaritalStatus(),
                request.getEmail(),
                request.getPhone(),
                request.getDistrictId(),
                request.getAddressLine1());

        Guarantor updatedGuarantor = guarantorRepository.save(guarantor);

        GuarantorResponse response = new GuarantorResponse();
        BeanUtils.copyProperties(updatedGuarantor, response);
        return response;
    }

    @Override
    public GuarantorResponse patchGuarantor(Long guarantorId, Map<String, Object> updates) {
        Guarantor guarantor = guarantorRepository.findById(guarantorId)
                .orElseThrow(GuarantorBusinessException::guarantorNotFound);

        updates.forEach((key, value) -> {
            switch (key) {
                case "documentNumber":
                    guarantor.setDocumentNumber((String) value);
                    break;
                case "monthlyIncome":
                    if (value instanceof Number) {
                        guarantor.setMonthlyIncome(new BigDecimal(value.toString()));
                    }
                    break;
                case "firstNames":
                    guarantor.setFirstNames((String) value);
                    break;
                case "lastNames":
                    guarantor.setLastNames((String) value);
                    break;
                case "email":
                    guarantor.setEmail((String) value);
                    break;
                case "phone":
                    guarantor.setPhone((String) value);
                    break;
                case "districtId":
                    guarantor.setDistrictId((String) value);
                    break;
                case "addressLine1":
                    guarantor.setAddressLine1((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        Guarantor updatedGuarantor = guarantorRepository.save(guarantor);

        GuarantorResponse response = new GuarantorResponse();
        BeanUtils.copyProperties(updatedGuarantor, response);
        return response;
    }

    @Override
    public void deleteGuarantor(Long guarantorId) {
        if (!guarantorRepository.existsById(guarantorId)) {
            throw GuarantorBusinessException.guarantorNotFound();
        }

        guarantorRepository.deleteById(guarantorId);
    }
}