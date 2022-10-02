package com.kt.corp.mtg.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "회의 DTO")
public class MtgDTO {
	
	@Schema(description = "회의 Seq", example = "1")
	private Long seq;
	
	@Schema(description = "회의 등록일시", example = "2022-09-22 14:00:00")
	private String regDate;
	
	@Schema(description = "회의 수정일시", example = "2022-09-22 14:00:00")
	private String upDate;
	
	@Schema(description = "[C] 회의 제목", example = "지니밸리 개발팀 회의")
	private String mtgTit;
	
	@Schema(description = "[C / 바로회의=null] 회의 시작 날짜", example = "2022-09-22")
	private String mtgStartDate;
	
	@Schema(description = "[C] 회의 종료 날짜", example = "2022-09-22")
	private String mtgEndDate;
	
	@Schema(description = "[C / 바로회의=null] 회의 시작 시간", example = "13:00:00")
	private String mtgStartTime;
	
	@Schema(description = "[C] 회의 종료 시간", example = "14:00:00")
	private String mtgEndTime;
	
	@Schema(description = "[C] 회의 잠금 여부", example = "1(잠금) or 0(공개)")
	private Boolean mtgLock;
	
	@Schema(description = "[C] 회의 잠금 비밀번호", example = "1234")
	private String mtgLockPw;
	
	@Schema(description = "[C] 회의 초대 링크", example = "sdf53EDs77D")
	private String mtgUrl;
	
	@Schema(description = "[C] 회의 내용", example = "개발팀 회의입니다.")
	private String mtgDtl;
	
	@Schema(description = "회의 자료 제목", example = "2주차_WBS.xlxs")
	private String mtgAttTit;
	
	@Schema(description = "회의 자료 경로", example = "file/ee5434FBVCWdr")
	private String mtgAttPath;
	
	@Schema(description = "회의 자료 수정일시", example = "2022-09-22 14:00:00")
	private String attUpDate;
	
	@Schema(description = "[C] 회의 참석 멤버 리스트")
	private List<MtgMemberDTO> mtgMembers;
	
	@Schema(description = "[C] 되풀이 주기", example = "D(매일) or W(매주) or MD(매월-날짜) or MW(매월-요일)")
	private String mtgRecur;
	
	@Schema(description = "[C] 되풀이 주기 설정", example = "IF(MTG_RECUR = W) => 1(월), 2(화), 3(수), 4(목), 5(금), 6(토), 7(일) / IF(MTG_RECURE = MD) => 날짜 / IF(MTG_RECURE = MW) => 1(첫째주), 2(둘째주), 3(셋째주), 4(마지막주) + 1(월), 2(화), 3(수), 4(목), 5(금), 6(토), 7(일) 조합")
	private String mtgRecurDtl;
	
	@Schema(description = "[C] 회의 개설자 ID", example = "DWRDY-4DJU75")
	private String mtgUserId;
	
	@Schema(description = "[C] 회의 개설 회사 ID", example = "156DG")
	private String mtgComId;

}
