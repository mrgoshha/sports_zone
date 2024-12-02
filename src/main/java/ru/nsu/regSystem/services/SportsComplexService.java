package ru.nsu.regSystem.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.regSystem.entities.SportsComplex;
import ru.nsu.regSystem.repositories.SportsComplexRepository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class SportsComplexService {
    private final SportsComplexRepository sportsComplexRepository;

    public Page<SportsComplex> getSportsComplexesPage(Pageable pageable) {
        return sportsComplexRepository.findAll(pageable);
    }

    @Cacheable("complexes")
    public List<SportsComplex> getSportsComplexes() {
        return sportsComplexRepository.findAll();
    }

    public List<Object[]> getAttendance(String startDateAndTime, SportsComplex complex) {
        return sportsComplexRepository.getAttendance(startDateAndTime, complex);
    }


    @Caching(
            cacheable = {
                    @Cacheable("complex")
            },
            evict = {
                    @CacheEvict(value = "complexes", allEntries = true)
            })
    public void saveComplex(SportsComplex complex) {
        log.info("save new {}", complex);
        sportsComplexRepository.save(complex);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "complex", key = "#id"),
                    @CacheEvict(value = "complexes", allEntries = true)
            })
    public void deleteComplex(Long id) {
        sportsComplexRepository.deleteById(id);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "complex", key = "#sportsComplex.id"),
                    @CacheEvict(value = "complexes", allEntries = true)
            })
    public void updateComplex(SportsComplex sportsComplex) {
        sportsComplexRepository.save(sportsComplex);
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "complex", key = "#id")
            },
            evict = {
                    @CacheEvict(value = "complexes", allEntries = true)
            })
    public SportsComplex getSportComplexById(Long id) {
        return sportsComplexRepository.findById(id).orElse(null);
    }
}
