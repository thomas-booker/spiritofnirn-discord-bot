package uk.co.thomasbooker.spritofnirn.trial.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.thomasbooker.spritofnirn.trial.model.Trial;

public interface TrialRepository extends CrudRepository<Trial, Long> {

    Trial findByName(String name);

}