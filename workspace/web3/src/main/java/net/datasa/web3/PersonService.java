package net.datasa.web3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class PersonService {
	
	//@Autowired
	//PersonRepository personRepository;
	private final PersonRepository personRepository;
	
	public void test() {
		PersonEntity entity = new PersonEntity();
		entity.setId("abcde2");
		entity.setName("김길동");
		entity.setAge(22);
		
		personRepository.save(entity);
	}
	
	public void save(PersonDTO dto) {
		PersonEntity entity = new PersonEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		
		personRepository.save(entity);
	}

	public PersonDTO select(String id) {
		
		PersonEntity entity = personRepository.findById(id).orElse(null);
		PersonDTO dto = new PersonDTO();
		if(entity == null) {
			return dto;
		}
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAge(entity.getAge());
		
		return dto;
	}

	public boolean delete(String id) {
		boolean result = personRepository.existsById(id);
		
		if(result) {
			personRepository.deleteById(id);
		}
		
		return result;
	}
	
	public List<PersonDTO> selectAll(){
		
		List<PersonEntity> entityList = personRepository.findAll();
		List<PersonDTO> dtoList = new ArrayList<>();
		
		for(PersonEntity entity : entityList) {
			PersonDTO dto = new PersonDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setAge(entity.getAge());
			dtoList.add(dto);
		}
		
		return dtoList;
	}
}