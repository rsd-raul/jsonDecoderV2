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
import com.jsondecoder.domain.Participant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaultConfig.class)
@ActiveProfiles("test")
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class ParticipantRepositoryTest {

    @Autowired
	ParticipantRepository participantRepository;
 
	@Test
    public void testFindById() {
    	Participant participant = participantRepository.findById(68263961);
  
    	Assert.assertNotNull(participant);
    	Assert.assertEquals(68263961, participant.getId());
    }

	@Test
	public void findAll() {
		List<Participant> participants = participantRepository.findAll();
		
		assertEquals(23, participants.size());
	}
	
	@Test
	public void add() {		
		Participant participant = new Participant();
		participant.setBirth("testing");
		participant.setId(1234);
		participant.setName("testing");
		participant.setUrl("testing");
		
		participantRepository.add(participant);
		
		List<Participant> participants = participantRepository.findAll();
		assertEquals(24, participants.size());
	}
	
	@Test
	public void update() {		
		Participant participant = participantRepository.findById(68263961);
		participant.setBirth("testing");
		participantRepository.update(participant);
		
		participant = participantRepository.findById(68263961);
		assertEquals("testing", participant.getBirth());
	}
}