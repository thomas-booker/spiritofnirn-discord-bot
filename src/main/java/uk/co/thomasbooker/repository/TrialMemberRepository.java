package uk.co.thomasbooker.repository;

import org.springframework.data.repository.CrudRepository;
import uk.co.thomasbooker.model.TrialMember;

import java.util.List;

public interface TrialMemberRepository extends CrudRepository<TrialMember, Long> {

    List<TrialMember> findByTrialId(Long trialId);

}