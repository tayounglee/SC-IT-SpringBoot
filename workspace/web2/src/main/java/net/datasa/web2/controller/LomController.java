package net.datasa.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.datasa.web2.domain.Person;

@Controller
@RequestMapping("lom")
@Slf4j
public class LomController {

   // http://localhost:8888/lom/lombok
   @GetMapping("lombok")
   public String lombok() {
      
      Person p = new Person();
      p.setName("홍길동");
      p.setAge(12);
      p.setPhone("010");
      System.out.println(p);
      
      Person p2 = new Person("김", 13, "011");
      
      System.out.println("왔다감");
      return "redirect:/";
   }
   
   @GetMapping("logger")
   public String logger() {
      log.error("error메소드로 출력");
      log.warn("warn메소드로 출력");
      log.info("info메소드로 출력");
      log.debug("debug메소드로 출력");
      log.trace("trace메소드로 출력");
      
      String a = "alsdf";
      int b = 1234;
      
      log.debug("a는 {}, b는 {} 입니다.", a, b);
      return "redirect:/";
   }
}
