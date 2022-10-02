package com.kt.corp.setting.service;

import com.kt.corp.Constant;
import com.kt.corp.comm.ApiException;
import com.kt.corp.setting.dto.*;
import com.kt.corp.setting.mapper.SettingMapper;
import com.kt.corp.user.dto.UserDTO;
import com.kt.corp.user.service.UserService;
import com.kt.corp.util.DeEncrypter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SettingService {

    private SettingMapper settingMapper;

    /**
     * 사용자 아이디를 입력 받아 해당 사용자의 알림 on/off 정보를 반환
     *
     * @param userId 사용자 아이디
     * @return ResGetSetting
     */
    public ResGetSetting getSetting(String userId) {
        return settingMapper.getSetting(userId);
    }

    /**
     * 사용자 아이디와 on/off 정보를 받아 개인 프로필 공개 여부 수정
     *
     * @param reqUpdatePfOnOff 1: 공개, 0: 비공개
     * @return 1: update 성공, 0: update 실패
     */
    public int updatePfOnOff(ReqUpdatePfOnOff reqUpdatePfOnOff) {
        return settingMapper.updatePfOnOff(reqUpdatePfOnOff);
    }

    /**
     * 사용자 아이디와 사용자 이름, 자기 소개, 전화번호 값을 받아서 수정
     *
     * @param reqUpdateUserDTO 사용자 아이디, 이름, 자기 소개, 전화번호
     * @return 1: update 성공, 0: update 실패
     */
    public int updateUser(ReqUpdateUserDTO reqUpdateUserDTO) {
        return settingMapper.updateUser(reqUpdateUserDTO);
    }

    /**
     * 사용자 아이디와 사용자, 전화번호 값을 받아서 수정
     *
     * @param reqUpdateUserPhoneNumDTO 사용자 아이디, 전화번호
     * @return 1: update 성공, 0: update 실패
     */
    public int updateUserPhoneNum(ReqUpdateUserPhoneNumDTO reqUpdateUserPhoneNumDTO) {
        return settingMapper.updateUserPhoneNum(reqUpdateUserPhoneNumDTO);
    }

    /**
     * 사용자 아이디를 받아서 알림 여부 설정 반환
     *
     * @param userId 사용자 아이디
     * @return ResNtcnDTO 미리보기, 사운드 알림, 관심기업, 호출 팝업, 일정 알림, 이벤트 팝업, 이벤트
     */
    public ResNtcnDTO getNtcn(String userId) {
        return settingMapper.getNtcn(userId);
    }

    /**
     * 사용자 아이디와 on/off 값을 받아서 알림 미리보기 여부 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, value
     * @return 1: update 성공, 0: update 실패
     */
    public int updateNtcnPreview(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateNtcnPreview(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디와 on/off 값을 받아서 사운드 알림 여부 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, value
     * @return 1: update 성공, 0: update 실패
     */
    public int updateNtcnSound(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateNtcnSound(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디와 on/off 값을 받아서 관심기업 알림 여부 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, value
     * @return 1: update 성공, 0: update 실패
     */
    public int updateNtcnFavCom(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateNtcnFavCom(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디와 on/off 값을 받아서 호출 팝업 알림 여부 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, value
     * @return 1: update 성공, 0: update 실패
     */
    public int updateNtcnCallPopup(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateNtcnCallPopup(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디와 on/off 값을 받아서 스케쥴 알림 여부 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, value
     * @return 1: update 성공, 0: update 실패
     */
    public int updateNtcnSchdl(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateNtcnSchdl(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디와 on/off 값을 받아서 미팅 팝업 알림 여부 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, value
     * @return 1: update 성공, 0: update 실패
     */
    public int updateMtgPopup(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateMtgPopup(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디와 on/off 값을 받아서 이벤트 알림 여부 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, value
     * @return 1: update 성공, 0: update 실패
     */
    public int updateEvent(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateEvent(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디를 받아서 해당 사용자의 테마 설정 값 받아오기
     *
     * @param userId 사용자 아이디
     * @return 0: light, 1: dark
     */
    public ResThemeDTO getTheme(String userId) {
        ResThemeDTO resThemeDTO = new ResThemeDTO();
        int countUser = settingMapper.countUser(userId);
        resThemeDTO.setCountUser(countUser);
        if(countUser == 1){
            resThemeDTO.setTheme(settingMapper.getTheme(userId));
        }
        return resThemeDTO;
    }

    /**
     * 사용자 아이디와 테마 값을 받아서 테마 정보 업데이트
     *
     * @param reqNtcnDTO 사용자 아이디, 테마 0: light, 1: dark
     * @return 1: update 성공, 0: update 실패
     */
    public int updateTheme(ReqNtcnDTO reqNtcnDTO) {
        int result = 0;
        if (checkYn(reqNtcnDTO.getValue())){
            result = settingMapper.updateTheme(reqNtcnDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디에 해당하는 관심 기업 목록 반환
     *
     * @param userId 사용자 아이디
     * @return ResFavListDTO - List<FavDTO> - 관심기업 PK, 사용자 아이디, 관심 기업 이름, 기업 아이디, 관심기업 알림 여부
     */
    public ResFavListDTO getFavList(String userId) {
        ResFavListDTO resFavListDTO = new ResFavListDTO();
        resFavListDTO.setFavList(settingMapper.getFavList(userId));
        return resFavListDTO;
    }

    /**
     * 관심기업 PK, 알림 여부 받아서 알림 여부 업데이트
     *
     * @param reqUpdateFavComOnOffDTO 관심기업 PK, 알림 여부
     * @return 1: update 성공, 0: update 실패
     */
    public int updateFavComOnOff(ReqUpdateFavComOnOffDTO reqUpdateFavComOnOffDTO) {
        int result = 0;
        if (checkYn(reqUpdateFavComOnOffDTO.getFavComOnOff())){
            result = settingMapper.updateFavComOnOff(reqUpdateFavComOnOffDTO);
        }
        return result;
    }

    /**
     * 사용자 아이디를 받아서 해당 사용자가 소속 된 회사의 팀 목록을 반환
     *
     * @param userId 사용자 아이디
     * @return ResTeamListDTO - List<TeamDTO> - 팀 아이디, 팀 이름, 회사 아이디
     */
    public ResTeamListDTO getTeamList(String userId) {
        ResTeamListDTO resTeamListDTO = new ResTeamListDTO();
        resTeamListDTO.setTeamList(settingMapper.getTeamList(userId ));
        return resTeamListDTO;
    }

    /**
     * 사용자 아이디와 팀 이름을 받아서 팀을 추가
     *
     * @param reqAddTeamDTO 사용자 아이디, 팀 이름
     * @return 1: insert 성공, 0: update 실패
     */
    public int addTeam(ReqAddTeamDTO reqAddTeamDTO) {
        TeamDTO teamDTO = new TeamDTO();
        String comId = settingMapper.getComId(reqAddTeamDTO.getUserId());
        teamDTO.setComId(comId);
        teamDTO.setTeam(reqAddTeamDTO.getTeam());

        int countTeam = settingMapper.countTeam(teamDTO);

        if (countTeam < 1){
            return settingMapper.addTeam(reqAddTeamDTO);
        }
        return 0;
    }

    /**
     * 팀 아이디와 팀 이름을 받아서 팀 이름을 수정
     *
     * @param reqUpdateTeamDTO 팀 아이디, 팀 이름
     * @return 1: update 성공, 0: update 실패
     */
    public int updateTeam(ReqUpdateTeamDTO reqUpdateTeamDTO) {
        int countUser = settingMapper.countUser(reqUpdateTeamDTO.getUserId());

        String comId = settingMapper.getComId(reqUpdateTeamDTO.getUserId());

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeam(reqUpdateTeamDTO.getTeam());
        teamDTO.setComId(comId);

        int countTeam = settingMapper.countTeam(teamDTO);

        String teamComId = settingMapper.getComIdByTeamId(reqUpdateTeamDTO.getTeamId());

        if(countTeam < 1 && comId.equals(teamComId) && countUser == 1){
            return settingMapper.updateTeam(reqUpdateTeamDTO);
        }

        return 0;
    }

    /**
     * 팀 아이디를 받아서 해당 아이디의 팀 삭제
     *
     * @param teamId 팀 아이지
     * @return 1: delete 성공, 0: 실패
     */
    public int deleteTeam(Long teamId, String userId) {
        String userComId = settingMapper.getComId(userId);
        String teamComId = settingMapper.getComIdByTeamId(teamId);
        if (userComId.equals(teamComId)){
            return settingMapper.deleteTeam(teamId);
        }
        return 0;
    }

    /**
     * 사용자 아이디를 받아서 직책 목록을 반환
     *
     * @param userId 사용자 아이디
     * @return ResJobListDTO - List<JobDTO> - 직책, 직책 아이디, 회사 아이디
     */
    public ResJobListDTO getJobList(String userId) {
        ResJobListDTO resJobListDTO = new ResJobListDTO();
        resJobListDTO.setJobList(settingMapper.getJobList(userId));
        return resJobListDTO;
    }

    /**
     * 사용자 아이디와 직책 이름을 받아서 직책을 추가
     *
     * @param reqAddJobDTO 사용자 아이디, 직책
     * @return 1: insert 성공, 0: update 실패
     */
    public int addJob(ReqAddJobDTO reqAddJobDTO) {
        JobDTO jobDTO = new JobDTO();
        String comId = settingMapper.getComId(reqAddJobDTO.getUserId());

        jobDTO.setJobTit(reqAddJobDTO.getJobTit());
        jobDTO.setComId(comId);

        int countJob = settingMapper.countJob(jobDTO);

        int countUser = settingMapper.countUser(reqAddJobDTO.getUserId());

        if (countJob < 1 && countUser == 1){
            return settingMapper.addJob(reqAddJobDTO);
        }

        return 0;
    }

    /**
     * 직책 아이디와 직책이름을 받아서 해당 직책 수정
     *
     * @param reqUpdateJobDTO 직책 아이디, 직책
     * @return 1: update 성공, 0: update 실패
     */
    public int updateJob(ReqUpdateJobDTO reqUpdateJobDTO) {
        JobDTO jobDTO = new JobDTO();
        String comId = settingMapper.getComId(reqUpdateJobDTO.getUserId());

        int countUser = settingMapper.countUser(reqUpdateJobDTO.getUserId());

        jobDTO.setJobTit(reqUpdateJobDTO.getJobTit());
        jobDTO.setComId(comId);

        int countJob = settingMapper.countJob(jobDTO);

        String jobComId = settingMapper.getComIdByJobTitId(reqUpdateJobDTO.getJobTitId());

        if(countUser == 1 && countJob < 1 && comId.equals(jobComId)){
            return settingMapper.updateJob(reqUpdateJobDTO);
        }

        return 0;
    }

    /**
     * 직책 아이디를 받아서 해당 아이디의 직책 삭제
     *
     * @param jobTitId 팀 아이지
     * @return 1: delete 성공, 0: 실패
     */
    public int deleteJob(Long jobTitId, String userId) {

        String userCom = settingMapper.getComId(userId);
        String jobCom = settingMapper.getComIdByJobTitId(jobTitId);
        if(userCom.equals(jobCom)){
            return settingMapper.deleteJob(jobTitId);
        }

        return 0;
    }

    /**
     * 사용자 아이디를 받아서 해당 사용자의 알림 내용 추가
     *
     * @param userId 사용자 아이디
     * @return 1: insert 성공, 0: update 실패
     */
    public int addNtcnSetup(String userId){
        int result = 0;
        int count = settingMapper.countNtcnSetup(userId);
        int countUser = settingMapper.countUser(userId);
        if (count < 1 && countUser == 1){
            return settingMapper.addNtcnSetup(userId);
        }
        return result;
    }

    /**
     * on/off 값을 parameter 받을 때 1 or 0의 값을 같는지 확인
     *
     * @param yn 체크 하는 값
     * @return check 성공 true, check 실패 false
     */
    private boolean checkYn(int yn){
        return yn == 0 || yn == 1;
    }

    public ResBizcardDTO getBizcard(String userId){
        return settingMapper.getBizcard(userId);
    }

    public ResSepcialtyListDTO getSpecialty(String word) {
        List<String> stringList = settingMapper.getSpecialty(word);
        ResSepcialtyListDTO resSepcialtyListDTO = new ResSepcialtyListDTO();
        resSepcialtyListDTO.setSepcialtyList(stringList);
        return resSepcialtyListDTO;
    }

    public int addSepcialty(ReqSepcialtyDTO reqSepcialtyDTO) {
        int result = 0;
        int countSepcialty = settingMapper.countSepcialty(reqSepcialtyDTO.getUserId());
        if(countSepcialty  < 5){
            return settingMapper.addSepcialty(reqSepcialtyDTO);
        }
        return result;
    }

    public int deleteSepcialty(ReqSepcialtyDTO reqSepcialtyDTO) {
        return settingMapper.deleteSepcialty(reqSepcialtyDTO);
    }

    public int updateFavFeed(ReqFavDTO reqFavDTO) {
        String favUserId = settingMapper.getFavUserId(reqFavDTO.getSeq());
        if(checkYn(reqFavDTO.getOnOff()) && reqFavDTO.getUserId().equals(favUserId)){
            return settingMapper.updateFavFeed(reqFavDTO);
        }
        return 0;
    }

    public int updateFavEven(ReqFavDTO reqFavDTO){
        return settingMapper.updateFavEven(reqFavDTO);
    }

    public ResComDTO getCom(String userId) {
        return settingMapper.getCom(userId);
    }

    public int updateCom(ReqUpdateComDTO reqUpdateComDTO) {
        return settingMapper.updateCom(reqUpdateComDTO);
    }

//    public int updateLogo(ReqUpdateLogoDTO reqUpdateLogoDTO) {
//        return settingMapper.updateLogo(reqUpdateLogoDTO);
//    }

    public ResComDetailDTO getComDetail(String userId) {
        ResComDetailDTO resComDetailDTO = new ResComDetailDTO();

        ComDetailDTO comDetailDTO = settingMapper.getComDetail(userId);

        List<BsnsFieldDTO> bsnsFieldDTOList = settingMapper.getBsnsFieldList(userId);
        List<BsnsFieldFormDTO> bsnsFieldFormDTOList = settingMapper.getBsnsFieldFormList();

        List<UtztnTechDTO> utztnTechDTOList = settingMapper.getUtztnTechList(userId);
        List<UtztnTechFormDTO> utztnTechFormDTOList = settingMapper.getUtztnTechFormList();

        List<InvestStageDTO> investStageDTOList = settingMapper.getInvestStageList(userId);
        List<InvestStageFormDTO> investStageFormDTOList = settingMapper.getInvestStageForm(userId);

        List<HashTagDTO> hashTagDTOList = settingMapper.hashTagList(userId);

        resComDetailDTO.setComDetailDTO(comDetailDTO);
        resComDetailDTO.setBsnsFieldDTOList(bsnsFieldDTOList);
        resComDetailDTO.setBsnsFieldFormDTOList(bsnsFieldFormDTOList);
        resComDetailDTO.setUtztnTechDTOList(utztnTechDTOList);
        resComDetailDTO.setUtztnTechFormDTOList(utztnTechFormDTOList);
        resComDetailDTO.setInvestStageDTOList(investStageDTOList);
        resComDetailDTO.setInvestStageFormDTOList(investStageFormDTOList);
        resComDetailDTO.setHashTagDTOList(hashTagDTOList);

        return resComDetailDTO;
    }

    public int updateComDetail(ReqUpdateComDetailDTO reqUpdateComDetailDTO) {
        return settingMapper.updateComDetail(reqUpdateComDetailDTO);
    }

    public ResUpdateComInfoDTO updateComInfo(ReqUpdateComInfoDTO reqUpdateComInfoDTO) {
        String comId = settingMapper.getComId(reqUpdateComInfoDTO.getUserId());

        settingMapper.deleteBsnsField(comId);
        settingMapper.deleteUtztnTech(comId);
        settingMapper.deleteInvestStage(comId);

        for (Long bfId: reqUpdateComInfoDTO.getBsnsFieldIdList()
             ) {
            BsnsFieldDTO bsnsFieldDTO = new BsnsFieldDTO();
            bsnsFieldDTO.setComId(comId);
            bsnsFieldDTO.setBsnsFieldId(bfId);

            settingMapper.insertBsnsField(bsnsFieldDTO);
        }

        for (Long utId: reqUpdateComInfoDTO.getUtztnTechIdList()
             ) {
            UtztnTechDTO utztnTechDTO = new UtztnTechDTO();
            utztnTechDTO.setComId(comId);
            utztnTechDTO.setUtztnTechId(utId);
            settingMapper.insertUtztnTech(utztnTechDTO);
        }

        for (Long isId : reqUpdateComInfoDTO.getInvestStageIdList()
                ) {
            InvestStageDTO investStageDTO = new InvestStageDTO();
            investStageDTO.setComId(comId);
            investStageDTO.setInvestStageId(isId);
            settingMapper.insertInvestStage(investStageDTO);
        }

        ResUpdateComInfoDTO resUpdateComInfoDTO = new ResUpdateComInfoDTO();

        List<BsnsFieldDTO> bsnsFieldDTOList = settingMapper.getBsnsFieldList(reqUpdateComInfoDTO.getUserId());
        List<UtztnTechDTO> utztnTechDTOList = settingMapper.getUtztnTechList(reqUpdateComInfoDTO.getUserId());
        List<InvestStageDTO> investStageDTOList = settingMapper.getInvestStageList(reqUpdateComInfoDTO.getUserId());

        resUpdateComInfoDTO.setBsnsFieldDTOList(bsnsFieldDTOList);
        resUpdateComInfoDTO.setUtztnTechDTOList(utztnTechDTOList);
        resUpdateComInfoDTO.setInvestStageDTOList(investStageDTOList);

        return resUpdateComInfoDTO;
    }

    public ResComDetailMgtDTO getComDetailMgt(String userId) {
        ResComDetailMgtDTO resComDetailMgtDTO = new ResComDetailMgtDTO();

        ComDetailDTO comDetailDTO = settingMapper.getComDetail(userId);
        List<MgtSupDTO> mgtSupDTOList = settingMapper.getMgtSupList(userId);
        List<HashTagDTO> hashTagDTOList = settingMapper.hashTagList(userId);

        resComDetailMgtDTO.setComDetailDTO(comDetailDTO);
        resComDetailMgtDTO.setMgtSupDTOList(mgtSupDTOList);
        resComDetailMgtDTO.setHashTagDTOList(hashTagDTOList);

        return resComDetailMgtDTO;
    }

    public ResUpdateComDetailMgtDTO updateComDetailMgt(ReqUpdateComDetailMgtDTO reqUpdateComDetailMgtDTO) {
        String comId = settingMapper.getComId(reqUpdateComDetailMgtDTO.getUserId());
        settingMapper.deleteMgtSup(comId);

        for (String ms: reqUpdateComDetailMgtDTO.getMgtSupList()
             ) {
            MgtSupDTO mgtSupDTO = new MgtSupDTO();
            mgtSupDTO.setComId(comId);
            mgtSupDTO.setMgtSup(ms);

            settingMapper.insertMgtSup(mgtSupDTO);
        }

        List<MgtSupDTO> mgtSupDTOList = settingMapper.getMgtSupList(reqUpdateComDetailMgtDTO.getUserId());

        ResUpdateComDetailMgtDTO resUpdateComDetailMgtDTO = new ResUpdateComDetailMgtDTO();
        resUpdateComDetailMgtDTO.setMgtSupDTOList(mgtSupDTOList);
        return resUpdateComDetailMgtDTO;
    }

    public ResHashtagListDTO getHashtagList(String word) {
        List<String> stringList = settingMapper.getHashtagList(word);
        ResHashtagListDTO resHashtagListDTO = new ResHashtagListDTO();
        resHashtagListDTO.setHashtagList(stringList);
        return resHashtagListDTO;
    }

    public int addHashtag(ReqHashtagDTO reqAddHashtagDTO) {
        int result = 0;
        String comId = settingMapper.getComId(reqAddHashtagDTO.getUserId());
        int countHashtag = settingMapper.countHashtag(comId);

        HashTagDTO countHashTagDTO = new HashTagDTO();
        countHashTagDTO.setComHashtag(reqAddHashtagDTO.getComHashtag());
        countHashTagDTO.setComId(comId);

        int countExistHashtag = settingMapper.countExistHashtag(countHashTagDTO);

        if(countHashtag  < 5 && countExistHashtag < 1){
            HashTagDTO hashTagDTO = new HashTagDTO();
            hashTagDTO.setComId(comId);
            hashTagDTO.setComHashtag(reqAddHashtagDTO.getComHashtag());
            return settingMapper.addHashtag(hashTagDTO);
        }
        return result;
    }

    public int deleteHashtag(ReqHashtagDTO reqHashtagDTO) {
        String comId = settingMapper.getComId(reqHashtagDTO.getUserId());

        HashTagDTO hashTagDTO = new HashTagDTO();
        hashTagDTO.setComId(comId);
        hashTagDTO.setComHashtag(reqHashtagDTO.getComHashtag());

        return settingMapper.deleteHashtag(hashTagDTO);
    }

    public ResEmployeeDTO getEmployeeList(String userId) {
        List<EmployeeDTO> employeeDTOList = settingMapper.getEmployeeList(userId);
        ResEmployeeDTO resEmployeeDTO = new ResEmployeeDTO();
        resEmployeeDTO.setEmployeeDTOList(employeeDTOList);

        return resEmployeeDTO;
    }

    public int updateAuthType(ReqEmployeeAuthTypeDTO reqEmployeeAuthTypeDTO) {
        int auth = settingMapper.getAuth(reqEmployeeAuthTypeDTO.getUserId());
        String userId = reqEmployeeAuthTypeDTO.getUserId();

        String userComId = settingMapper.getComId(userId);
        String employeeUserId = settingMapper.getUserId(getEncMail(reqEmployeeAuthTypeDTO.getMail()));
        String employeeComId = settingMapper.getComId(employeeUserId);

        if (auth == 1 && userComId.equals(employeeComId)){
            return settingMapper.updateAuthType(reqEmployeeAuthTypeDTO);
        }

        return 0;
    }

    public int updateAuth(ReqInviteUserDTO reqEmployeeDTO) {
        int auth = settingMapper.getAuth(reqEmployeeDTO.getUserId());
        String userId = reqEmployeeDTO.getUserId();
        String employeeUserId = settingMapper.getUserId(getEncMail(reqEmployeeDTO.getMail()));

        String userComId = settingMapper.getComId(userId);
        String employeeComId = settingMapper.getComId(employeeUserId);

        if(auth == 1 && userComId.equals(employeeComId)){
            settingMapper.updateUnAuth(userId);
            settingMapper.updateAuth(employeeUserId);
            return 1;
        }

        return 0;
    }

    public int outEmployee(ReqInviteUserDTO reqEmployeeDTO) {
        int result = 0;
        int auth = settingMapper.getAuth(reqEmployeeDTO.getUserId());
        String userComId = settingMapper.getComId(reqEmployeeDTO.getUserId());
        String employeeUserId = settingMapper.getUserId(getEncMail(reqEmployeeDTO.getMail()));
        String employeeComId = settingMapper.getComId(employeeUserId);

        if(auth == 1 && userComId.equals(employeeComId)){

            return settingMapper.deleteEmployee(employeeUserId);
        }
        return result;

    }

    public int agreeInvite(String userId) {
        return settingMapper.agreeInvite(userId);
    }

    public int confirmInvite(ReqInviteUserDTO reqEmployeeDTO) {
        int result = 0;
        int auth = settingMapper.getAuth(reqEmployeeDTO.getUserId());
        if(auth == 1){
            String userId = settingMapper.getUserId(getEncMail(reqEmployeeDTO.getMail()));
            return settingMapper.confirmInvite(userId);
        }
        return result;
    }

    public ResInviteUserDTO getInviteUser(String userId, String mail) {
        ResInviteUserDTO resInviteUserDTO = new ResInviteUserDTO();
        int auth = settingMapper.getAuth(userId);
        if(auth == 1){
            return settingMapper.getInviteUser(settingMapper.getUserId(getEncMail(mail)));
        }
        return resInviteUserDTO;
    }

    public int inviteUser(ReqInviteUserDTO reqInviteUserDTO) {
        int result = 0;
        int auth = settingMapper.getAuth(reqInviteUserDTO.getUserId());
        String userId = settingMapper.getUserId(getEncMail(reqInviteUserDTO.getMail()));

        int countEmployee = settingMapper.countEmployee(userId);

        if(auth == 1 && countEmployee < 1){
            String comId = settingMapper.getComId(reqInviteUserDTO.getUserId());

            InviteEmployeeDTO inviteEmployeeDTO = new InviteEmployeeDTO();
            inviteEmployeeDTO.setUserId(userId);
            inviteEmployeeDTO.setSttus("초대요청");
            inviteEmployeeDTO.setComId(comId);

            return settingMapper.addEmployee(inviteEmployeeDTO);

        }
        return result;
    }

    private String getEncMail(String mail){
        String encMail = "";
        try {
            encMail = DeEncrypter.encryptAES(mail);
        } catch (Exception e) {
            // TODO: handle exception
            throw new ApiException(Constant.ERR_0001, "encryptAES Error");
        }
        return encMail;
    }
}
