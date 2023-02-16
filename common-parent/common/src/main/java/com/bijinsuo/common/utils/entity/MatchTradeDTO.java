package com.bijinsuo.common.utils.entity;

import com.bijinsuo.common.utils.enums.MatchInfoTypeEnum;
import lombok.Data;

/**
 * @author nike
 * @date 2022年11月09日 15:26
 */
@Data
public class MatchTradeDTO {
    //撤单id
    private Long orderId;
    //币对
    private String symbol;
    //消息类型
    private MatchInfoTypeEnum matchInfoType;
    //币对
    //撮合数据
    private MatchTradeDetailsDTO detailsDTO;
}
