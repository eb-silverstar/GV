package com.kt.corp.setting.controller;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.setting.dto.*;
import com.kt.corp.setting.service.SettingService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Setting", description = "설정 API")
@RestController
@RequestMapping("/setting")
@AllArgsConstructor
public class SettingController {

    private SettingService settingService;

    @Operation(summary = "유저 설정 입력", description = "유저 아이디를 입력하면 테마 light, 알림 on 으로 입력")
    @PostMapping("")
    public ResponseEntity<ApiResponseEntity> addNtcnSetup(@RequestParam String userId){
        int result = settingService.addNtcnSetup(userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "설정 초기화 정보 반환", description = "User ID에 해당하는 일정 목록을 조회 합니다.")
    @GetMapping("")
    public ResponseEntity<ApiResponseEntity> readSetting(@RequestParam String userId){
        ResGetSetting resGetSetting = settingService.getSetting(userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), resGetSetting, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "사용자 공개 여부 설정", description = "사용자 공개 여부 업데이트 0: 미공개, 1: 공개")
    @PostMapping("/user/pf")
    public ResponseEntity<ApiResponseEntity> updatePfOnOff(@RequestBody ReqUpdatePfOnOff reqUpdatePfOnOff){
        int result = settingService.updatePfOnOff(reqUpdatePfOnOff);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "사용자 정보 업데이트", description = "사용자 정보 이름, 자기소개, 전화번호 업데이트")
    @PostMapping("/user")
    public ResponseEntity<ApiResponseEntity> updateUser(@RequestBody ReqUpdateUserDTO reqUpdateUserDTO){
        int result = settingService.updateUser(reqUpdateUserDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "사용자 전화 번호 업데이트", description = "사옹자 아이디와 전화번호를 받아서 전화번호 업데이트")
    @PostMapping("/user/phone")
    public ResponseEntity<ApiResponseEntity> updateUserPhoneNum(@RequestBody ReqUpdateUserPhoneNumDTO reqUpdateUserPhoneNumDTO){
        int result = settingService.updateUserPhoneNum(reqUpdateUserPhoneNumDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "알림메뉴 초기 데이터 조회", description = "알림 메뉴 처음 입장시 필요한 초기 정보 조회")
    @GetMapping("/ntcn")
    public ResponseEntity<ApiResponseEntity> getNtcn(@RequestParam String userId){
        ResNtcnDTO resNtcnDTO = settingService.getNtcn(userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), resNtcnDTO, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "미리보기 알림 on/off", description = "미리보기 알림 0: off, 1: on")
    @PostMapping("/ntcn/preview")
    public ResponseEntity<ApiResponseEntity> updateNtcnPreview(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateNtcnPreview(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "사운드 알림 on/off", description = "사운드 알림 0: off, 1: on")
    @PostMapping("/ntcn/sound")
    public ResponseEntity<ApiResponseEntity> updateNtcnSound(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateNtcnSound(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "관심기업 알림 on/off", description = "관심기업 알림 0: off, 1: on")
    @PostMapping("/ntcn/fav-com")
    public ResponseEntity<ApiResponseEntity> updateNtcnFavCom(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateNtcnFavCom(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "호출팝업 알림 on/off", description = "호출 팝업 알림 0: off, 1: on")
    @PostMapping("/ntcn/cll-popup")
    public ResponseEntity<ApiResponseEntity> updateNtcnCallPopup(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateNtcnCallPopup(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "일정 알림 on/off", description = "일정 알림 0: off, 1:on")
    @PostMapping("/ntcn/schdl")
    public ResponseEntity<ApiResponseEntity> updateNtcnSchdl(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateNtcnSchdl(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "미팅 알림 on/off", description = "미팅 알림 0: off, 1: on")
    @PostMapping("/ntcn/mtg-popup")
    public ResponseEntity<ApiResponseEntity> updateMtgPopup(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateMtgPopup(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "이벤트 알림 on/off", description = "이벤트 알림 0: off, 1: on")
    @PostMapping("/ntcn/event")
    public ResponseEntity<ApiResponseEntity> updateEvent(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateEvent(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "테마 정보 가져오기", description = "0: light, 1: dark")
    @GetMapping("/ntcn/theme")
    public ResponseEntity<ApiResponseEntity> getTheme(@RequestParam String userId){
        ResThemeDTO result = settingService.getTheme(userId);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "테마 업데이트", description = "0: light, 1: dark")
    @PostMapping("/ntcn/theme")
    public ResponseEntity<ApiResponseEntity> updateTheme(@RequestBody ReqNtcnDTO reqNtcnDTO){
        int result = settingService.updateTheme(reqNtcnDTO);
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "관심 기업 알림 on/off", description = "관심 기업 PK를 받아 알림 여부 업데이트")
    @PostMapping("/fav-com-on-off")
    public ResponseEntity<ApiResponseEntity> updateFavComOnOff(@RequestBody ReqUpdateFavComOnOffDTO reqUpdateFavComOnOffDTO){
        int result = settingService.updateFavComOnOff(reqUpdateFavComOnOffDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @Operation(summary = "팀 목록 조회", description = "사용자 아이디를 받아서 사용자 아이디가 소속 된 회사의 팀 목록을 반환")
    @GetMapping("/team")
    public ResponseEntity<ApiResponseEntity> getTeamList(@RequestParam String userId){
        ResTeamListDTO resTeamListDTO = settingService.getTeamList(userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), resTeamListDTO, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "팀 추가", description = "새로운 팀 이름 값을 받아 회사 아이디를 매핑하여 입력")
    @PostMapping("/add/team")
    public ResponseEntity<ApiResponseEntity> addTeam(@RequestBody ReqAddTeamDTO reqAddTeamDTO){
        int result = settingService.addTeam(reqAddTeamDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "팀 수정", description = "팀 아이디를 받아 해당 팀을 수정")
    @PostMapping("/update/team")
    public ResponseEntity<ApiResponseEntity> updateTeam(@RequestBody ReqUpdateTeamDTO reqUpdateTeamDTO){
        int result = settingService.updateTeam(reqUpdateTeamDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "팀 삭제", description = "팀 아이디를 받아서 해당 팀 삭젝")
    @PostMapping("/delete/team")
    public ResponseEntity<ApiResponseEntity> deleteTeam(@RequestParam Long teamId,
                                                        @RequestParam String userId){
        int result = settingService.deleteTeam(teamId, userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "직책 목록 조회", description = "사용자 아이디를 받아서 사용자 아이디가 소속 된 회사의 직책 목록을 반환")
    @GetMapping("/job")
    public ResponseEntity<ApiResponseEntity> getJobList(@RequestParam String userId){
        ResJobListDTO resjobListDTO = settingService.getJobList(userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), resjobListDTO, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "직책 추가", description = "직책 값을 받아 회사 아이디와 매핑하여 입력력")
   @PostMapping("/add/job")
    public ResponseEntity<ApiResponseEntity> addJob(@RequestBody ReqAddJobDTO reqAddJobDTO){
        int result = settingService.addJob(reqAddJobDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "직책 수정", description = "직책 값과 아이디를 받아 직책 이름을 수정")
    @PostMapping("/update/job")
    public ResponseEntity<ApiResponseEntity> updateJob(@RequestBody ReqUpdateJobDTO reqUpdateJobDTO){
        int result = settingService.updateJob(reqUpdateJobDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "직책 삭제", description = "직책 아이디를 받아서 해당 직책을 삭제")
    @PostMapping("/delete/job")
    public ResponseEntity<ApiResponseEntity> deleteJob(@RequestParam Long jobTitId,
                                                       @RequestParam String userId){
        int result = settingService.deleteJob(jobTitId, userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "명함 조회", description = "사용자 ID를 받아서 해당 사용자의 명함 정보를 전달")
    @GetMapping("/bizcard")
    public ResponseEntity<ApiResponseEntity> getBizcard(@RequestParam String userId){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getBizcard(userId), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "전문 분야 자동완성 목록 조회", description = "전문 분야 입력 중 문자 값을 받아서 자동 완성 가능 목록 반환")
    @GetMapping("/user/specialty")
    public ResponseEntity<ApiResponseEntity> getSepcialty(@RequestParam String word){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getSpecialty(word), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "전문분야 입력", description = "사용자 아이디와 전문분야 값을 받아서 5개 미만일 경우 입력")
    @PostMapping("/user/sepcialty")
    public ResponseEntity<ApiResponseEntity> addSepcialty(@RequestBody ReqSepcialtyDTO reqSepcialtyDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.addSepcialty(reqSepcialtyDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "전문 분야 삭제", description = "사용자 아이디와 전문분야 값을 받아서 삭제")
    @PostMapping("/user/sepcialty/delete")
    public ResponseEntity<ApiResponseEntity> deleteSepcialty(@RequestBody ReqSepcialtyDTO reqSepcialtyDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.deleteSepcialty(reqSepcialtyDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "관심 기업 목록 조회", description = "사용자 아이디를 받아서 해당하는 관심 기업 목록을 반환")
    @PostMapping("/fav")
    public ResponseEntity<ApiResponseEntity> getFavList(@RequestParam String userId){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getFavList(userId), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "관심 기업 피드 알림 업데이트", description = "사용자 아이디와 알림 on/off 값을 받아서 알림 여부 업데이트")
    @PostMapping("/fav/update/feed")
    public ResponseEntity<ApiResponseEntity> updateFavFeed(@RequestBody ReqFavDTO reqFavDTO) {
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateFavFeed(reqFavDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "관심 기업 이벤트 알림 업데이트", description = "사용자 아이디와 알림 on/off 값을 받아서 이벤트 알림 여부 업데이트")
    @PostMapping("/fav/update/even")
    public ResponseEntity<ApiResponseEntity> updateFavEven(@RequestBody ReqFavDTO reqFavDTO) {
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateFavEven(reqFavDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "메타오피스 초기 정보 가져 오기", description = "사용자 아이디를 받아서 해당 아이디의 메타오피스 기본 정보를 반환")
    @GetMapping("/com")
    public ResponseEntity<ApiResponseEntity> getCom(@RequestParam String userId){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getCom(userId), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "메타오피스 기본정보 수정", description = "사용자 아이디와 회사 타입, 빌딩 템플릿, 빌딩 템플릿 컬러, 오피스 템플릿 값을 업데이트")
    @PostMapping("/com/update")
    public ResponseEntity<ApiResponseEntity> updateCom(@RequestBody ReqUpdateComDTO reqUpdateComDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateCom(reqUpdateComDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
//    2022.09.28
//    회사 로고 정책 관리 변경으로 사용하지 않음
//
//    @Operation(summary = "회사 로고 수정", description = "사용자 아이디와 회사로고 값을 받아서 회사 로고 업데이트")
//    @PostMapping("/com/update/logo")
//    public ResponseEntity<ApiResponseEntity> updateLogo(@RequestBody ReqUpdateLogoDTO reqUpdateLogoDTO){
//        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateLogo(reqUpdateLogoDTO), "", "");
//
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }

    @Operation(summary = "회사 설정 상제정보", description = "회사 설정 상세 정보 메뉴 호출시 회사 상세정보 반환")
    @GetMapping("/com/detail")
    public ResponseEntity<ApiResponseEntity> getComDetail(@RequestParam String userId){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getComDetail(userId) , "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "회사 상세 정보 변경", description = "사용자 아이디와 회사 상세정보를 받아서 업데이트")
    @PostMapping("/com/update/detail")
    public ResponseEntity<ApiResponseEntity> updateComDetail(@RequestBody ReqUpdateComDetailDTO reqUpdateComDetailDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateComDetail(reqUpdateComDetailDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "회사 사업 정보 업데이트", description = "사용자 아이디, 비지니스 분야, 활용 기술 목록, 투자 단계 목록을 받아서 업데이트")
    @PostMapping("/com/update/info")
    public ResponseEntity<ApiResponseEntity> updateComInfo(@RequestBody ReqUpdateComInfoDTO reqUpdateComInfoDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateComInfo(reqUpdateComInfoDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "회사 경영지원사 상세 정보 조회", description = "회사 타입이 경영지원 일 경우 사용자 아이디를 받아서 회사 상세 정보를 반환")
    @GetMapping("/com/detail/mgt")
    public ResponseEntity<ApiResponseEntity> getComDetailMgt(@RequestParam String userId){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getComDetailMgt(userId), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "회사 경영지원사 상세 정보 수정", description = "회사 타입이 경영지원 일 경우 사용자 아이이와 정보를 받아서 업데이트")
    @PostMapping("/com/update/detail/mgt")
    public ResponseEntity<ApiResponseEntity> updateComDetailMgt(@RequestBody ReqUpdateComDetailMgtDTO reqUpdateComDetailMgtDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateComDetailMgt(reqUpdateComDetailMgtDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "해쉬 태그 목록 조회", description = "해쉬태그 입력시 입력 값을 전달하면 해당 입력 값에 해당하는 자동완성 목록을 반환")
    @GetMapping("/com/hashtag")
    public ResponseEntity<ApiResponseEntity> getHashTag(@RequestParam String word){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getHashtagList(word), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "해시 태그 입력", description = "사용자 아이디와 해시태그 값을 받아서 해시태그 입력")
    @PostMapping("/com/hashtag")
    public ResponseEntity<ApiResponseEntity> addHashtag(@RequestBody ReqHashtagDTO reqHashtagDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.addHashtag(reqHashtagDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "해시태그 삭제", description = "사용자 아이디와 해시태그 값을 받아서 해당 해시태그를 삭제함")
    @PostMapping("/com/delete/hashtag")
    public ResponseEntity<ApiResponseEntity> deleteHashtag(@RequestBody ReqHashtagDTO reqHashtagDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.deleteHashtag(reqHashtagDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "우리멤버 목록 조회", description = "사용자 아이디를 받아서 해당 사용자의 회사에 속해 있는 우리 멤버 조회")
    @GetMapping("/employee")
    public ResponseEntity<ApiResponseEntity> getEmployeeList(@RequestParam String userId){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getEmployeeList(userId), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "사용자 권한목록 수정", description = "수정 할 사용자 아이디와 권한 목록으로 받아서 해당 사용자의 권한 목록을 수정")
    @PostMapping("/employee/update/authType")
    public ResponseEntity<ApiResponseEntity> updateAuthType(@RequestBody ReqEmployeeAuthTypeDTO reqEmployeeAuthTypeDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateAuthType(reqEmployeeAuthTypeDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "관리자 권한 양도", description = "본인의 아이디와, 새로운 관리자 이메일 주소를 받아서 기존 아이디 권리자 권한 해제 새로운 관리자 설정")
    @PostMapping("/employee/update/auth")
    public ResponseEntity<ApiResponseEntity> updateAuth(@RequestBody ReqInviteUserDTO reqEmployeeDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.updateAuth(reqEmployeeDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "우리멤버 퇴사, 초대 취소, 거절처리 삭제", description = "관리자 아이디와 퇴사할 이메일을 받아 우리멤버에서 삭제")
    @PostMapping("/employee/out")
    public ResponseEntity<ApiResponseEntity> outEmployee(@RequestBody ReqInviteUserDTO reqEmployeeDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.outEmployee(reqEmployeeDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "초대대기 수락", description = "초대 대기중인 사용자의 아이디를 받아서 초대요청 상태로 업데이트")
    @PostMapping("/employee/agree/invite")
    public ResponseEntity<ApiResponseEntity> agreeInvite(@RequestParam String userId){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.agreeInvite(userId), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "초대요청 수락", description = "초대요청 중인 사용자의 이메일을 받아서 초대완료 상태로 업데이트")
    @PostMapping("/employee/confirm/invite")
    public ResponseEntity<ApiResponseEntity> confirmInvite(@RequestBody ReqInviteUserDTO reqEmployeeDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.confirmInvite(reqEmployeeDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "우리멤버 초대 조회", description = "사용자 아이디와 검색 할 멤버 이메일을 받아서 해당 멤버의 이름과 이메일을 반환")
    @GetMapping("/employee/invite")
    public ResponseEntity<ApiResponseEntity> getInviteUser(@RequestParam String userId,
                                                    @RequestParam String mail){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.getInviteUser(userId, mail), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Operation(summary = "우리멤버 초대", description = "사용자 아이디와 초대할 사용자 이메일을 받아서 초대 받은 멤버 초대요청 상태로 등록")
    @PostMapping("/employee/invite")
    public ResponseEntity<ApiResponseEntity> inviteUser(@RequestBody ReqInviteUserDTO reqInviteUserDTO){
        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), settingService.inviteUser(reqInviteUserDTO), "", "");

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
