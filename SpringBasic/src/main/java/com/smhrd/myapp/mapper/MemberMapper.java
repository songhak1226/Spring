package com.smhrd.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.smhrd.myapp.domain.WebMember;

@Mapper
public interface MemberMapper {

	// 회원가입할때 쓸 메서드
	public int join(WebMember m);

	// 로그인할때 쓸 메서드
	public WebMember login(WebMember wm);

	// 전체회원정보 가지고오기
	public List<WebMember> select();

	// 회원 삭제
	public int delete(String email);
	
	// 회원정보 수정
	@Update("update webmember set pw=#{pw}, tel=#{tel}, address=#{address} where email=#{email}")
	public int update(WebMember m);
	
	// email 중복확인
	@Select("select count(*) from webmember where email=#{email}")
	public int emailCheck(String email);

}
