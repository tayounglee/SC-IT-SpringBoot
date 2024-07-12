package net.datasa.web3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class PersonController {

	private final PersonService personService;
	
	@GetMapping("test")
	public String test() {
		personService.test();
		return "redirect:/";
	}
	
	/** 
	 * 입력폼으로 이동
	 * @return Form이 있는 HTML 파일 경로
	 */
	@GetMapping("save")
	public String save() {
		return "inputForm";
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute PersonDTO dto) {
		String id;
		String name;
		int age;
		id = dto.getId();
		name = dto.getName();
		age = dto.getAge();
		
		personService.save(dto);
		
		log.debug("id={}, name={}, age={}", id, name, age);
		
		return "redirect:/";
	}
	
	/**
	 * 검색폼으로 이동
	 * @return HTML 파일 경로
	 */
	@GetMapping("select")
	public String select() {
		return "selectForm";
	}
	
	@PostMapping("select")
	public String select(@RequestParam("id") String id, Model model) {
		
		PersonDTO dto = personService.select(id);
		
		model.addAttribute("id", id);
		model.addAttribute("person", dto);
		
		return "select";
	}
	
	@GetMapping("delete")
	public String delete() {
		return "deleteForm";
	}
	
	@PostMapping("delete")
	public String delete(@RequestParam("id") String id, Model model) {
		model.addAttribute("id", id);
		boolean result = personService.delete(id);
		//삭제 여부와 삭제한 아이디를 모델에 저장하고 HTML로 포워딩
		//1. XXX : 없는 아이디입니다.
		//2. XXX 회원정보를 삭제했습니다.
		model.addAttribute("result", result);
		return "delete";
	}
	
	//모든 회원보기
	@GetMapping("selectAll")
	public String selectAll(Model model) {
		List<PersonDTO> dtoList = personService.selectAll();
		//리스트를 모델에 저장하고 selectAll.html로 포워딩
		//결과를 화면에 표 형태로 출력한다.
		model.addAttribute("dtoList", dtoList);
		return "selectAll";
	}
	
	@GetMapping("view")
	public String view(@RequestParam("id") String id, Model model) {
		PersonDTO dto = personService.select(id);
		
		model.addAttribute("id", id);
		model.addAttribute("person", dto);
		
		return "select";
	}
	
	@GetMapping("info" + "/{id}")
	public String info(@PathVariable("id") String id, Model model) {
		PersonDTO dto = personService.select(id);
		
		model.addAttribute("id", id);
		model.addAttribute("person", dto);
		
		return "select";
	}
	
	@GetMapping("deleteUser")
	public String deleteUser(@RequestParam("id") String id, Model model) {
		
		personService.deleteUser(id);
		
		return "redirect:selectAll";
	}
	
	@GetMapping("update")
	public String update(@RequestParam("id") String id, Model model) {
		PersonDTO dto = personService.select(id);
		model.addAttribute("person", dto);
		
		return "updateForm";
	}
	
	@PostMapping("update")
	public String update(@ModelAttribute PersonDTO dto) {
				
		personService.update(dto);
		
		return "redirect:view?id=" + dto.getId();
	}
}