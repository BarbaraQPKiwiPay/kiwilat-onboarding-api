package com.kiwipay.onboarding.guarantor.application.internal.queryservices;

import com.kiwipay.onboarding.guarantor.application.internal.dto.GuarantorResponse;
import com.kiwipay.onboarding.guarantor.domain.model.aggregates.Guarantor;
import com.kiwipay.onboarding.guarantor.domain.model.exceptions.GuarantorBusinessException;
import com.kiwipay.onboarding.guarantor.domain.services.GuarantorQueryService;
import com.kiwipay.onboarding.guarantor.infrastructure.persistence.jpa.GuarantorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuarantorQueryServiceImpl implements GuarantorQueryService {

    @Autowired
    private GuarantorRepository guarantorRepository;

    @Override
    public GuarantorResponse getGuarantorById(Long guarantorId) {
        Guarantor guarantor = guarantorRepository.findById(guarantorId)
                .orElseThrow(GuarantorBusinessException::guarantorNotFound);

        GuarantorResponse response = new GuarantorResponse();
        BeanUtils.copyProperties(guarantor, response);
        return response;
    }

    @Override
    public List<GuarantorResponse> getGuarantorsByLoanId(Long loanId) {
        return guarantorRepository.findByLoanId(loanId).stream()
                .map(guarantor -> {
                    GuarantorResponse response = new GuarantorResponse();
                    BeanUtils.copyProperties(guarantor, response);
                    return response;
                })
                .collect(Collectors.toList());
    }
}