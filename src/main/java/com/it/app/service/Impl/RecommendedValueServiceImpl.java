package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.RecommendedValue;
import com.it.app.repository.RecommendedValueRepository;
import com.it.app.service.RecommendedValueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecommendedValueServiceImpl implements RecommendedValueService {

    private final LocalizedMessageSource localizedMessageSource;

    private final RecommendedValueRepository recommendedValueRepository;

    public RecommendedValueServiceImpl(RecommendedValueRepository recommendedValueRepository, LocalizedMessageSource localizedMessageSource) {
        this.recommendedValueRepository = recommendedValueRepository;
        this.localizedMessageSource = localizedMessageSource;
    }

    @Override
    public List<RecommendedValue> findAll() {
        return recommendedValueRepository.findAll();
    }

    @Override
    public RecommendedValue findById(Long id) {
        return recommendedValueRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.recommendedValue.notExist", new Object[]{})));
    }

    @Override
    public RecommendedValue save(RecommendedValue recommendedValue) {
        validate(recommendedValue.getId() != null, localizedMessageSource.getMessage("error.recommendedValue.notHaveId", new Object[]{}));
        return recommendedValueRepository.saveAndFlush(recommendedValue);
    }

    @Override
    public RecommendedValue update(RecommendedValue recommendedValue) {
        final Long recommendedValueId = recommendedValue.getId();
        validate(recommendedValueId == null, localizedMessageSource.getMessage("error.recommendedValue.haveId", new Object[]{}));
        return recommendedValueRepository.saveAndFlush(recommendedValue);
    }

    @Override
    public void delete(RecommendedValue recommendedValue) {
        validate(recommendedValue.getId() == null, localizedMessageSource.getMessage("error.recommendedValue.haveId", new Object[]{}));
        recommendedValueRepository.delete(recommendedValue);
    }

    @Override
    public void deleteById(Long id) {
        recommendedValueRepository.deleteById(id);
    }


    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }


}
