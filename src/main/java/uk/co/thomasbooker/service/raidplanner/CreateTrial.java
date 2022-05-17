package uk.co.thomasbooker.service.raidplanner;

import uk.co.thomasbooker.model.Trial;
import uk.co.thomasbooker.repository.TrialRepository;

public class CreateTrial {

    TrialRepository trialRepository;

    public void execute(Trial trial) {
        if (!exists(trial.getId())) {
            trialRepository.save(trial);
        }
    }

    private boolean exists(Long ID) {
        return trialRepository.existsById(ID);
    }
}
