package net.datasa.web2.domain;

import lombok.Data;

@Data
public class Member {
   String id;
   String pw;
   String name;
   String phone;
   String com;
}