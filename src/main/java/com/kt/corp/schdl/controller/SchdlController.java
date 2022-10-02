package com.kt.corp.schdl.controller;

import com.kt.corp.comm.ApiResponseEntity;
import com.kt.corp.message.manage.DbMessageManager;
import com.kt.corp.schdl.dto.*;
import com.kt.corp.schdl.service.SchdlService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Schdl", description = "일정 API")
@RestController
@RequestMapping("/schdl")
@AllArgsConstructor
public class SchdlController {

    private final SchdlService schdlService;

    /**
     *  일정 등록
     *  알림 PK값을 받아서 일정을 등록 합니다.
     *  임의의 사용자 아이디로 일정을 등록 합니다.
     *  추후 로그인 된 사용자 아이디를 받아서 등록하는 로직으로 수정 해야 합니다.
     * @param reqAddSchdlDTO - Noti PK, userId
     * @return message
     */
    @Operation(summary = "일정 등록", description = "임의의 사용자 아이디로 일정을 등록 합니다.")
    @PostMapping
    public ResponseEntity<ApiResponseEntity> addSchdl(@RequestBody ReqAddSchdlDTO reqAddSchdlDTO){
        int result = schdlService.insertSchdl(reqAddSchdlDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null, null);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     *  일정 목록 조회
     *  일정을 path로 받은 UserId 기준으로 조회 합니다.
     * @param userId - User PK
     */
    @Operation(summary = "일정 목록 조회", description = "User ID에 해당하는 일정 목록을 조회 합니다.")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseEntity> readSchdlListByUserId(@PathVariable("userId") String userId){
        ResSchdlDTO resSchdl = schdlService.getResSchdl(userId);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), resSchdl, null, null);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 월별 일정 개수 조회
     * 일정에서 년도, 월, <, >, 버튼 클릭시 선택하는 월이 변경되고
     * 해당 월의 일자별 일정 개수를 리스트로 반환 합니다.
     * 일정이 없다면 [ ] 빈 리스트를 반환 합니다.
     *
     * 일정 개수 조회시 schdlTypeSeq 값을 같이 보내 줍니다.
     *
     * @param reqReadSchdlCountMonthlyDTO: year - 년, month - 월, type - schdlTypeSeq, userID - 사용자 ID
     *
     * @return List<ResSchdlMonthlyQtyDTO>
     */
    @Operation(summary = "월별 일정 개수 조회", description = "특정 월의 첫날 부터 마지막 날짜까지 일정 개수를 조회 합니다.")
    @PostMapping("/month")
    public ResponseEntity<ApiResponseEntity> readSchdlCountMonthly(@RequestBody ReqReadSchdlCountMonthlyDTO reqReadSchdlCountMonthlyDTO){
        List<ResSchdlMonthlyQtyDTO> schdlMonthlyList = schdlService.getSchdlMonthlyQtyList(reqReadSchdlCountMonthlyDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), schdlMonthlyList, null, null);

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    /**
     * 특정일자 상세 목록 조회
     * 일정에서 달력의 날짜를 클릭 하였을 때 해당 날짜의 일정 목록을 조회 합니다.
     * 일정이 없다면 [ ] 빈 리트스를 반환 합니다.
     *
     * @param reqReadSchdlDetailDailyDTO: year - 년도, month - 월, day - 날짜, schdlTypeSeq - schdlType PK, userId - 사용자 아이디
     * @return List<ResResSchdlDetailDTO>
     */
    @Operation(summary = "특정일자 일정 상세 목록 조회", description = "특정일자에 해당하는 일정 상세 목록을 조회 합니다.")
    @PostMapping("/day")
    public ResponseEntity<ApiResponseEntity> readSchdlDetailDaily(@RequestBody ReqReadSchdlDetailDailyDTO reqReadSchdlDetailDailyDTO){
        List<ResSchdlDetailDTO> schdlDetailList = schdlService.getSchdlDailyDetailList(reqReadSchdlDetailDailyDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), schdlDetailList, null, null);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * 일정 삭제
     *
     * @param reqDeleteSchdlDTO: schdl - 일정 PK
     * @return result - 삭제한 열의 개수
     */
    @Operation(summary = "일정 삭제", description = "일정의 PK 값을 받아서 해당 일정을 삭제")
    @PostMapping("/remove-schdl")
    public ResponseEntity<ApiResponseEntity> deleteSchdl(@RequestBody ReqDeleteSchdlDTO reqDeleteSchdlDTO){
        int result = schdlService.deleteSchdlById(reqDeleteSchdlDTO);

        ApiResponseEntity message = new ApiResponseEntity(DbMessageManager.getMessage("I0001", "ko"), result, null,null);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
