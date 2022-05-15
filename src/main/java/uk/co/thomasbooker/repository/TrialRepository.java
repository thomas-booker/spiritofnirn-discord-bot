package uk.co.thomasbooker.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.thomasbooker.model.Trial;

import java.util.List;

public interface TrialRepository extends CrudRepository<Trial, Long> {

    List<Trial> findByName(String name);

}