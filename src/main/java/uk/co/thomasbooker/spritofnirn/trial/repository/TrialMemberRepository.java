package uk.co.thomasbooker.spritofnirn.trial.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.thomasbooker.spritofnirn.trial.model.TrialMember;

import java.util.List;

public interface TrialMemberRepository extends CrudRepository<TrialMember, Long> {

    List<TrialMember> findByTrialId(Long trialId);

}