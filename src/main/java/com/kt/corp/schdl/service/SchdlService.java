package com.kt.corp.schdl.service;

import com.kt.corp.noti.dto.NotiDTO;
import com.kt.corp.noti.mapper.NotiMapper;
import com.kt.corp.schdl.dto.*;
import com.kt.corp.schdl.mapper.SchdlMapper;
import com.kt.corp.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchdlService {

    private final SchdlMapper schdlMapper;
    private final NotiMapper notiMapper;

    /**
     * 일정 입력
     *
     * @param reqAddSchdlDTO notiSeq - Noti PK, userId
     */
    public int insertSchdl(ReqAddSchdlDTO reqAddSchdlDTO) {
        // notiSeq로 schdlTypeSeq 가져오기
        NotiDTO noti = notiMapper.getNotiBySeq(reqAddSchdlDTO.getNotiSeq());

        // 데이터 추출
        String userId = reqAddSchdlDTO.getUserId();
        String fl = noti.getFl();
        String schdlDate = noti.getMtgDate();
        String schdlEndDate = noti.getMtgDate();
        String schdlTit = noti.getMtgTit();

        int result = 0;
        //fl 검증 CLD_MTG, CLD_EVT, EVT 값만 입력 가능
        if(fl.equals("CLD_EVT") || fl.equals("CLD_MTG") || fl.equals("EVT")){
            // schdl 입력 데이터 세팅
            SchdlDTO schdl = new SchdlDTO();
            schdl.setUserId(userId);
            schdl.setFl(fl);
            schdl.setSchdlDate(schdlDate);
            schdl.setSchdlEndDate(schdlEndDate);
            schdl.setSchdlTit(schdlTit);
            result = schdlMapper.insertSchdl(schdl);
        }

        // schdl DB 입력
        return result;
    }

    /**
     * 일정 월별 개수 리스트 조회
     * 특정 월의 첫째날 부터 마지막 날짜까지 일정 개수를 조회하여 반환
     *
     * @param schdl - String userId, Long SchdlTypeSeq, String Date (2022-08-01)
     * @return List<SchdlMonthlyQtyDTO>
     */
    public List<ResSchdlMonthlyQtyDTO> getSchdlMonthlyQtyList(SchdlDTO schdl) {
        return schdlMapper.getSchdlMonthlyQty(schdl);
    }

    /**
     * 일정 월별 개수 리스트 조회
     * 특정 월의 첫째날 부터 마지막 날짜까지 일정 개수를 조회하여 반환
     *
     * @param reqReadSchdlCountMonthlyDTO: year - 년도, month - 월, schdlTypeSeq - schdlType PK, userId - 사용자 아이디
     * @return List<SchdlMonthlyQtyDTO>
     */
    public List<ResSchdlMonthlyQtyDTO> getSchdlMonthlyQtyList(ReqReadSchdlCountMonthlyDTO reqReadSchdlCountMonthlyDTO) {
        String year = reqReadSchdlCountMonthlyDTO.getYear();
        String month = reqReadSchdlCountMonthlyDTO.getMonth();
        String userId = reqReadSchdlCountMonthlyDTO.getUserId();

        String date = year + "-" + month + "-01";

        SchdlDTO schdl = new SchdlDTO();
        schdl.setUserId(userId);
        schdl.setSchdlDate(date);

        return schdlMapper.getSchdlMonthlyQty(schdl);
    }

    /**
     * 특정 일자 일정 상세 목록 조회
     *
     * @param schdl - String userId, Long SchdlTypeSeq, String Date (2022-08-01)
     * @return List<ResResSchdlDetailDTO>
     */
    public List<ResSchdlDetailDTO> getSchdlDetailListDaily(SchdlDTO schdl) {
        return schdlMapper.getSchdlDetailListDaily(schdl);
    }

    /**
     * 특정 일자 일정 상세 목록 조회
     *
     * @param reqReadSchdlDetailDailyDTO: year - 년도, month - 월, day - 날짜, schdlTypeSeq - schdlType PK, userId - 사용자 아이디
     * @return List<ResResSchdlDetailDTO>
     */
    public List<ResSchdlDetailDTO> getSchdlDailyDetailList(ReqReadSchdlDetailDailyDTO reqReadSchdlDetailDailyDTO) {
        String year = reqReadSchdlDetailDailyDTO.getYear();
        String month = reqReadSchdlDetailDailyDTO.getMonth();
        String day = reqReadSchdlDetailDailyDTO.getDay();
        String userId = reqReadSchdlDetailDailyDTO.getUserId();
        String date = year + "-" + month + "-" + day;

        SchdlDTO schdl = new SchdlDTO();
        schdl.setUserId(userId);
        schdl.setSchdlDate(date);

        return schdlMapper.getSchdlDetailListDaily(schdl);
    }

    /**
     * 다가오는 일정 조회
     * 등록 된 일정 중 현재 시간과 가장 가까운 일정 조회
     * 최대 3개를 조회함
     * 일정이 하나도 등록되어 있지 않을 경우 [ ] 빈리스트 반환
     *
     * @param schdl - String userId, Long SchdlTypeSeq, String Date (2022-08-01)
     * @return List<ResResSchdlDetailDTO>
     */
    public List<ResSchdlDetailDTO> getCloseSchdlDetailListDaily(SchdlDTO schdl) {
        return schdlMapper.getCloseSchdlDetailListDaily(schdl);
    }

    /**
     * 일정 초기화 데이터 조회
     * 일정 초기화 데이터를 userId를 받아서 해당 사용자가 등록한
     * 일정을 조회하여 일정 화면 첫 화면에 필요한 데이터를 반환
     *
     * @param userId - 사용자 PK
     * @return ResSchdlDTO
     */
    public ResSchdlDTO getResSchdl(String userId) {
        String today = DateUtil.getToday();

        SchdlDTO todaySchdl = new SchdlDTO();
        todaySchdl.setUserId(userId);
        todaySchdl.setSchdlDate(today);

        List<ResSchdlMonthlyQtyDTO> schdlMonthlyQtyList = getSchdlMonthlyQtyList(todaySchdl);
        List<ResSchdlDetailDTO> schdlTodayList = getSchdlDetailListDaily(todaySchdl);
        List<ResSchdlDetailDTO> schdlCloseList = getCloseSchdlDetailListDaily(todaySchdl);

        ResSchdlDTO resSchdl = new ResSchdlDTO();
        resSchdl.setSchdlCloseList(schdlCloseList);
        resSchdl.setSchdlTodayList(schdlTodayList);
        resSchdl.setSchdlMonthlyQtyList(schdlMonthlyQtyList);

        return resSchdl;
    }

    /**
     * 일정 삭제
     * 일정의 PK값을 받아서 해당 일정을 삭제
     *
     * @param reqDeleteSchdlDTO - schdl PK
     * @return result - 삭제 성공한 열 개수 반환
     */
    public int deleteSchdlById(ReqDeleteSchdlDTO reqDeleteSchdlDTO) {
        return schdlMapper.deleteSchdlById(reqDeleteSchdlDTO.getSchdlSeq());
    }
}
