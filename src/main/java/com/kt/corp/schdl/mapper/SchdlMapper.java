package com.kt.corp.schdl.mapper;

import com.kt.corp.schdl.dto.ResSchdlMonthlyQtyDTO;
import com.kt.corp.schdl.dto.SchdlDTO;
import com.kt.corp.schdl.dto.ResSchdlDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchdlMapper {
    int insertSchdl(SchdlDTO schdlDTO);

    List<ResSchdlMonthlyQtyDTO> getSchdlMonthlyQty(SchdlDTO schdl);

    List<ResSchdlDetailDTO> getSchdlDetailListDaily(SchdlDTO schdl);

    List<ResSchdlDetailDTO> getCloseSchdlDetailListDaily(SchdlDTO schdl);

    int deleteSchdlById (Long schdlSeq);

}
