package net.datasa.web5.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	String memberId;
	String memberPassword;
	String memberName;
	String email;
	String phone;
	String address;
	boolean enabled;
	String rolename;
}