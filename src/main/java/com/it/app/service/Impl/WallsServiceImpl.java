package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Walls;
import com.it.app.repository.WallsRepository;
import com.it.app.service.WallsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
/**
 * Class, which implements methods of WallsService interface
 */
@Service
@Transactional
public class WallsServiceImpl implements WallsService {

    private final LocalizedMessageSource localizedMessageSource;

    private final WallsRepository wallsRepository;

    public WallsServiceImpl(LocalizedMessageSource localizedMessageSource, WallsRepository wallsRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.wallsRepository = wallsRepository;
    }

    @Override
    public List<Walls> findAll() {
        return wallsRepository.findAll();
    }

    @Override
    public Walls findById(Long id) {
        return wallsRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.walls.notExist", new Object[]{})));
    }

    @Override
    public Walls save(Walls walls) {
        validate(walls.getId() != null, localizedMessageSource.getMessage("error.walls.notHaveId", new Object[]{}));
        validate(wallsRepository.existsByWalls(walls.getWalls()), localizedMessageSource.getMessage("error.walls.name.notUnique", new Object[]{}));
        return wallsRepository.saveAndFlush(walls);
    }

    @Override
    public Walls update(Walls walls) {
        final Long wallsId = walls.getId();
        validate(wallsId == null, localizedMessageSource.getMessage("error.walls.haveId", new Object[]{}));
        final Walls duplicateWalls = wallsRepository.findByWalls(walls.getWalls());
        final boolean isDuplicateExists = duplicateWalls!= null && !Objects.equals(duplicateWalls.getId(), wallsId);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.walls.name.notUnique", new Object[]{}));
        return wallsRepository.saveAndFlush(walls);
    }

    @Override
    public void delete(Walls walls) {
        validate(walls.getId() == null, localizedMessageSource.getMessage("error.walls.haveId", new Object[]{}));
        wallsRepository.delete(walls);
    }

    @Override
    public void deleteById(Long id) {
        wallsRepository.deleteById(id);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
