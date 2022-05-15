package uk.co.thomasbooker.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import uk.co.thomasbooker.model.Trial;
import uk.co.thomasbooker.spritofnirn.Controller;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(
//        classes = { Controller.class },
//        loader = AnnotationConfigContextLoader.class)
//@Transactional
class TrialRepositoryTest {
//
//    @Resource
//    private TrialRepository trialRepository;
//
//    @Test
//    void shouldStoreTrial(){
//        // Given
//        String expectedName = "Test Trial 1";
//        LocalDateTime expectedDateTime = LocalDateTime.now();
//        String expectedOwner = "Some Guy";
//
//        Trial trialToStore = new Trial()
//                .withName(expectedName)
//                .withStartTime(expectedDateTime)
//                .withOwner(expectedOwner);
//
//        // When
//        trialRepository.save(trialToStore);
//
//        // Then
//        Trial retrievedTrial = trialRepository.findById(trialToStore.getId()).orElse(null);
//        assertThat(retrievedTrial).isNotNull();
//        assertThat(retrievedTrial.getName()).isEqualTo(expectedName);
//        assertThat(retrievedTrial.getStartTime()).isEqualTo(expectedDateTime);
//        assertThat(retrievedTrial.getOwner()).isEqualTo(expectedOwner);
//    }

}
