package net.datasa.web2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//import lombok.Data;

//@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {
   String name;
   int age;
   String phone;
}