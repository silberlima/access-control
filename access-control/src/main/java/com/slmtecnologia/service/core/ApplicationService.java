package com.slmtecnologia.service.core;

import com.slmtecnologia.exceptions.RequiredObjectIsNullException;
import com.slmtecnologia.exceptions.ResourceNotFoundException;
import com.slmtecnologia.model.dto.ApplicationDto;
import com.slmtecnologia.model.entity.Application;
import com.slmtecnologia.model.mapper.ApplicationMapper;
import com.slmtecnologia.repository.ApplicationRepository;
import com.slmtecnologia.service.IApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApplicationService implements IApplicationService {
    private  static final String RESOURCE_NOT_FOUND = "Application not found!";

    private final ApplicationRepository repository;

    @Override
    public Page<ApplicationDto> findAll(Pageable pageable) {
        var page = repository.findAll(pageable);
        return page.map(ApplicationMapper::entityToDto);
    }

    @Override
    public ApplicationDto findById(Long id) {
        return ApplicationMapper.entityToDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND)));
    }

    @Override
    @Transactional
    public ApplicationDto create(ApplicationDto dto) {
        if(Objects.isNull(dto)) throw new RequiredObjectIsNullException();

        Application entity = ApplicationMapper.dtoToEntity(dto);

        return ApplicationMapper.entityToDto(repository.save(entity));
    }

    @Override
    @Transactional
    public ApplicationDto update(Long id, ApplicationDto dto) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));

        var entityToSave = ApplicationMapper.dtoToEntity(entity, dto);

        return ApplicationMapper.entityToDto(repository.save(entityToSave));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND));
        repository.deleteById(entity.getId());
    }

    @Override
    public Page<ApplicationDto> findByName(String name, Pageable pageable) {
        var entity = repository.findByName(name, pageable);
        return entity.map(ApplicationMapper::entityToDto);
    }
}
