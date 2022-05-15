package uk.co.thomasbooker.service.raidplanner;

import org.hibernate.service.spi.InjectService;
import uk.co.thomasbooker.model.Trial;
import uk.co.thomasbooker.repository.TrialRepository;

public class CreateTrial {

    TrialRepository trialRepository;

    @InjectService
    public void execute(Trial trial) {
        if (!exists(trial.getId())) {
            trialRepository.save(trial);
        }
    }

    private boolean exists(Long ID) {
        return trialRepository.existsById(ID);
    }
}
