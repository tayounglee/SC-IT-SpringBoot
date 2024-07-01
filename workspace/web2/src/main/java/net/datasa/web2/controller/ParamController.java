package net.datasa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.Member;
import net.datasa.web2.domain.Person;

@Controller
@RequestMapping("param")
@Slf4j
public class ParamController {
   //http://localhost:8888/param/view1 경로의 요청 처리
   @GetMapping("view1")
   public String view1() {
      
      //templates/paramView/view1.html 파일로 포워딩
      return "paramView/view1";
   }
   
   @GetMapping("input1") //action값이랑 같아야함 //GetMapping -> method를 get로 받았기 때문에..
   public String input1(
         @RequestParam(name="id", defaultValue="") String id, //RequestParam : 지금 가져온 것 중 괄호안의 값이 name값인 것을 받아온다
         @RequestParam("pw") String pw,
         @RequestParam("name") String name,
         @RequestParam("phone") String phone,
         @RequestParam("com") String com
         ) { //괄호 안에는 name값이 입력되어야함
      
      log.debug("id : {}, pw : {}, name : {}",id,pw,name);
      return "redirect:/";
   }
   
   @GetMapping("view2")
   public String view2() {
      
      //templates/paramView/view1.html 파일로 포워딩
      return "paramView/view2";
   }
   
   @PostMapping("input2") //action값이랑 같아야함 //GetMapping -> method를 get로 받았기 때문에..
   public String input2(@ModelAttribute Member m) { //괄호 안에는 name값이 입력되어야함
      
//     Member m = new Member();
//     m.setId(request.getParameter("id"));
      
      log.debug("객체값 : {}", m);
      
      return "redirect:/";
   }
   
   @GetMapping("model")
   public String model(Model model) {
      String str = "서버의 문자열";
      int num = 100;
      Person p = new Person("김", 22, "010-1111-1111");
      
      model.addAttribute("str", str);
      model.addAttribute("n", num);
      model.addAttribute("person", p);
      
      
      return "paramView/model";
   }
}
