package com.jsondecoder.repository;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jsondecoder.DefaultConfig;
import com.jsondecoder.domain.CHObject;
import com.jsondecoder.domain.Participant;
import com.jsondecoder.domain.Participation;
import com.jsondecoder.domain.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaultConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ParticipationRepositoryTest {

    @Autowired
	ParticipationRepository participationRepository;
    @Autowired
	ParticipantRepository participantRepository;
    @Autowired
	RoleRepository roleRepository;
    @Autowired
	CHObjectRepository chObjectRepository;
    
    
	@Test
    public void testFindById() {
    	Participation participation = participationRepository.findById(68268207, 68263961, 35236657);
  
    	Assert.assertNotNull(participation);
    	Participant participant = participantRepository.findById(68263961);
    	Assert.assertEquals(participant.getId(), participation.getParticipant().getId());
    }

	@Test
	public void findAll() {
		List<Participation> participations = participationRepository.findAll();
		
		assertEquals(113, participations.size());
	}
	
	@Test
	public void add() {		
		Participation participation = new Participation();
		Participant participant = participantRepository.findById(68263961);
		participation.setParticipant(participant);
		Role role = roleRepository.findById(35351535);
		participation.setRole(role);
		CHObject chObject = chObjectRepository.findById(68268203);
		
		participationRepository.add(participation, chObject);
		
		List<Participation> participations = participationRepository.findAll();
		assertEquals(114, participations.size());
	}
}