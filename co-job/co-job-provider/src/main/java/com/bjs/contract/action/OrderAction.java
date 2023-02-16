package com.bjs.contract.action;

import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.proto.coOrder.CoOrderBizService;
import com.bjs.contract.proto.coOrder.CoOrderReply;
import com.bjs.contract.proto.coOrder.CoOrderRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class OrderAction {
  @DubboReference
  private CoOrderBizService service;

  public CoOrderDTO create(CoOrderDTO orderDTO) {
    CoOrderRequest.Builder builder = CoOrderRequest.newBuilder();
    ProtoBeanUtils.toProtoBean(builder, orderDTO);
    CoOrderReply reply = service.create(builder.build());
    if (!reply.getStatus()) {
      throw new BizException(reply.getMessage(), reply.getMessage());
    }
    return ProtoBeanUtils.toPojoBean(CoOrderDTO.class, reply.getCoOrderPO());
  }

  public CoOrderDTO close(CoOrderDTO orderDTO) {
    CoOrderRequest.Builder builder = CoOrderRequest.newBuilder();
    ProtoBeanUtils.toProtoBean(builder, orderDTO);
    CoOrderReply reply = service.close(builder.build());
    if (!reply.getStatus()) {
      throw new BizException(reply.getMessage(), reply.getMessage());
    }
    return ProtoBeanUtils.toPojoBean(CoOrderDTO.class, reply.getCoOrderPO());
  }
}
