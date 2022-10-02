package com.kt.corp.setting.mapper;

import com.kt.corp.setting.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettingMapper {
    ResGetSetting getSetting(String userId);

    int updatePfOnOff(ReqUpdatePfOnOff reqUpdatePfOnOff);

    int updateUser(ReqUpdateUserDTO reqUpdateUserDTO);

    int updateUserPhoneNum(ReqUpdateUserPhoneNumDTO reqUpdateUserPhoneNumDTO);

    String getUserSalt(String userId);

    int chkUserPw(ReqChkUserPwDTO reqChkUserPwDTO);

    ResNtcnDTO getNtcn(String userId);

    int updateNtcnPreview(ReqNtcnDTO reqNtcnDTO);

    int updateNtcnSound(ReqNtcnDTO reqNtcnDTO);

    int updateNtcnFavCom(ReqNtcnDTO reqNtcnDTO);

    int updateNtcnCallPopup(ReqNtcnDTO reqNtcnDTO);

    int updateNtcnSchdl(ReqNtcnDTO reqNtcnDTO);

    int updateMtgPopup(ReqNtcnDTO reqNtcnDTO);

    int updateEvent(ReqNtcnDTO reqNtcnDTO);

    int getTheme(String userId);

    int updateTheme(ReqNtcnDTO reqNtcnDTO);

    List<FavDTO> getFavList(String userId);

    int updateFavComOnOff(ReqUpdateFavComOnOffDTO reqUpdateFavComOnOffDTO);

    List<TeamDTO> getTeamList(String userId);

    int addTeam(ReqAddTeamDTO reqAddTeamDTO);

    int updateTeam(ReqUpdateTeamDTO reqUpdateTeamDTO);

    int deleteTeam(Long teamId);

    List<JobDTO> getJobList(String userId);

    int addJob(ReqAddJobDTO reqAddJobDTO);

    int updateJob(ReqUpdateJobDTO reqUpdateJobDTO);

    int deleteJob(Long jobId);

    int countJob(JobDTO jobDTO);

    int countTeam(TeamDTO teamDTO);

    int addNtcnSetup(String userId);

    ResBizcardDTO getBizcard(String userId);

    List<String> getSpecialty(String word);

    int addSepcialty(ReqSepcialtyDTO reqSepcialtyDTO);

    int countSepcialty(String userId);

    int deleteSepcialty(ReqSepcialtyDTO reqSepcialtyDTO);

    int updateFavFeed(ReqFavDTO reqFavDTO);

    int updateFavEven(ReqFavDTO reqFavDTO);

    ResComDTO getCom(String userId);

    int updateCom(ReqUpdateComDTO reqUpdateComDTO);

    int updateLogo(ReqUpdateLogoDTO reqUpdateLogoDTO);

    ComDetailDTO getComDetail(String userId);

    List<BsnsFieldDTO> getBsnsFieldList(String userId);

    List<BsnsFieldFormDTO> getBsnsFieldFormList();

    List<UtztnTechDTO> getUtztnTechList(String userId);

    List<UtztnTechFormDTO> getUtztnTechFormList();

    List<InvestStageDTO> getInvestStageList(String userId);

    List<InvestStageFormDTO> getInvestStageForm(String userId);

    List<HashTagDTO> hashTagList(String userId);

    int updateComDetail(ReqUpdateComDetailDTO reqUpdateComDetailDTO);

    String getComType(String userId);

    List<MgtSupDTO> getMgtSupList(String userId);

    String getComId(String userId);

    void deleteBsnsField(String comId);
    
    void deleteUtztnTech(String comId);
    
    void deleteInvestStage(String comId);

    void insertBsnsField(BsnsFieldDTO bsnsFieldDTO);

    void insertUtztnTech(UtztnTechDTO utztnTechDTO);

    void insertInvestStage(InvestStageDTO investStageDTO);

    void deleteMgtSup(String comId);

    void insertMgtSup(MgtSupDTO mgtSupDTO);

    List<String> getHashtagList(String word);

    int countHashtag(String comId);

    int addHashtag(HashTagDTO hashTagDTO);

    int deleteHashtag(HashTagDTO reqHashtagDTO);

    List<EmployeeDTO> getEmployeeList(String userId);

    int updateAuthType(ReqEmployeeAuthTypeDTO reqEmployeeAuthTypeDTO);

    void updateUnAuth(String userId);
    
    int updateAuth(String userId);

    String getUserId(String encMail);

    int getAuth(String userId);

    int deleteEmployee(String userId);

    int agreeInvite(String userId);

    int confirmInvite(String userId);

    ResInviteUserDTO getInviteUser(String userId);

    int addEmployee(InviteEmployeeDTO inviteEmployeeDTO);

    int countNtcnSetup(String userId);

    int countUser(String userId);

    int countExistHashtag(HashTagDTO comId);

    String getComIdByJobTitId(Long jobTitId);

    String getComIdByTeamId(Long teamId);

    int countEmployee(String userId);

    String getFavUserId(Long seq);

    String getComIdByJob(JobDTO jobDTO);
}
