package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Repair;
import com.it.app.repository.RepairRepository;
import com.it.app.service.RepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RepairServiceImpl implements RepairService {
    
    private final LocalizedMessageSource localizedMessageSource;

    private final RepairRepository repairRepository;

    public RepairServiceImpl(LocalizedMessageSource localizedMessageSource, RepairRepository repairRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.repairRepository = repairRepository;
    }

    @Override
    public List<Repair> findAll() {
        return repairRepository.findAll();
    }

    @Override
    public Repair findById(Long id) {
        return repairRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.repair.notExist", new Object[]{})));
    }

    @Override
    public Repair save(Repair repair) {
        validate(repair.getId() != null, localizedMessageSource.getMessage("error.repair.notHaveId", new Object[]{}));
        validate(repairRepository.existsByRepair(repair.getRepair()), localizedMessageSource.getMessage("error.repair.name.notUnique", new Object[]{}));
        return repairRepository.saveAndFlush(repair);
    }

    @Override
    public Repair update(Repair repair) {
        final Long repairId = repair.getId();
        validate(repairId == null, localizedMessageSource.getMessage("error.repair.haveId", new Object[]{}));
        final Repair duplicateRepair = repairRepository.findByRepair(repair.getRepair());
        final boolean isDuplicateExists = duplicateRepair!= null && !Objects.equals(duplicateRepair.getId(), repairId);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.repair.name.notUnique", new Object[]{}));
        return repairRepository.saveAndFlush(repair);
    }

    @Override
    public void delete(Repair repair) {
        validate(repair.getId() == null, localizedMessageSource.getMessage("error.Repair.haveId", new Object[]{}));
        repairRepository.delete(repair);
    }

    @Override
    public void deleteById(Long id) {
        repairRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
