package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Flat;
import com.it.app.repository.FlatRepository;
import com.it.app.service.*;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
/**
 * Class, which implements methods of FlatService interface
 */
@Service
@Transactional
public class FlatServiceImpl implements FlatService {

    private final LocalizedMessageSource localizedMessageSource;

    private final FlatRepository flatRepository;

    private final RepairService repairService;

    private final PlanService planService;

    private final  WallsService wallsService;


    public FlatServiceImpl(LocalizedMessageSource localizedMessageSource, FlatRepository flatRepository, RepairService repairService, PlanService planService, WallsService wallsService) {
        this.localizedMessageSource = localizedMessageSource;
        this.flatRepository = flatRepository;
        this.repairService = repairService;
        this.planService = planService;
        this.wallsService = wallsService;
    }

    @Override
    public List<Flat> findAll() {
        return flatRepository.findAll();
    }

    @Override
    public List<Flat> findAllByNumberRooms(Long numberRooms) {
        return flatRepository.findAllByNumberRooms(numberRooms);
    }

    public List<Flat> findAllByValueLessThan(Long value) {
        return flatRepository.findAllByValueLessThan(value);
    }

    @Override
    public Flat findById(Long id) {
        return flatRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.flat.notExist", new Object[]{})));
    }

    @Override
    public Flat save(Flat flat) {
        validate(flat.getId() != null, localizedMessageSource.getMessage("error.flat.notHaveId", new Object[]{}));
        validate(flatRepository.existsByAddress(flat.getAddress()), localizedMessageSource.getMessage("error.flat.address.notUnique", new Object[]{}));
        return saveAndFlush(flat);
    }

    @Override
    public Flat update(Flat flat) {
        final Long id = flat.getId();
        validate(id == null, localizedMessageSource.getMessage("error.flat.haveId", new Object[]{}));
        final Flat duplicateFlat = flatRepository.findByAddress(flat.getAddress());
        final boolean isDuplicateExists = duplicateFlat != null && !Objects.equals(duplicateFlat.getId(), id);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.flat.address.notUnique", new Object[]{}));
        return saveAndFlush(flat);
    }

    @Override
    public void delete(Flat flat) {
        validate(flat.getId() == null, localizedMessageSource.getMessage("error.flat.haveId", new Object[]{}));
        flatRepository.delete(flat);
    }

    @Override
    public void deleteById(Long id) {
        flatRepository.deleteById(id);
    }

    private Flat saveAndFlush(Flat flat) {
        validate(flat.getPlan() == null || flat.getPlan().getId() == null, localizedMessageSource.getMessage("error.flat.plan.isNull", new Object[]{}));
        flat.setPlan(planService.findById(flat.getPlan().getId()));
        validate(flat.getRepair() == null || flat.getRepair().getId() == null, localizedMessageSource.getMessage("error.flat.repair.isNull", new Object[]{}));
        flat.setRepair(repairService.findById(flat.getRepair().getId()));
        validate(flat.getWalls() == null || flat.getWalls().getId() == null, localizedMessageSource.getMessage("error.flat.walls.isNull", new Object[]{}));
        flat.setWalls(wallsService.findById(flat.getWalls().getId()));
        return flatRepository.saveAndFlush(flat);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
