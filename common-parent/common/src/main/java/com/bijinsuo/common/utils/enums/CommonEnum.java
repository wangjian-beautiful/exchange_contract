package com.bijinsuo.common.utils.enums;

import com.bijinsuo.common.utils.exception.BaseErrorInfoInterface;

/**
 * @author nike
 * @date 2022年11月09日 16:59
 */
public enum CommonEnum implements BaseErrorInfoInterface {
    // 数据操作错误定义
    SUCCESS("200", "成功!"),
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
    USER_AUTHENTICATION_FAILED("403", "用户身份验证失败!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),
    ERROR_PARAMETER("-1000","请求参数:{0},不能为空"),
    ERROR_PARAMETER_BODY("-999","参数体不能为空"),

    //下单相关异常
    SYMBOL_NOT_EXISTS("-1001", "币对不存在"),
    SYMBOL_IS_NOT_ACCESSIBLE("-1002", "币对不可交易"),
    ORDER_PRICE_ERROR("-1003", "下单价格不能高于或低于当前价的{0}%"),
    ORDER_PRICE_PRECISION_ERROR("-1004", "下单价格精度不正确"),
    NOT_ENOUGH_POSITION("-1005", "持仓数量不足"),
    NO_ACCOUNT("-1006", "账户不存在"),
    NOT_ENOUGH_BALANCE("-1007", "账户可用余额不足"),
    TOO_LOW_MARGIN("-1008", "保证金率过低，不允许用户操作"),
    FREEZE_ACCOUNT_BALANCE_FAIL("-1009", "冻结账户余额失败"),
    AMOUNT_OUT_OF_RANGE("-1010", "开/平仓金额不能小于{0}, 不能大于{1}"),
    VOLUME_OUT_OF_RANGE("-1011", "开/平仓数量不能小于{0}, 不能大于{1}"),
    VOLUME_MISMATCH_AMOUNT("-1012", "下单数量和价格与下单金额不匹配"),
    ORDER_VOLUME_PRECISION_ERROR("-1013", "下单数量精度不正确"),
    ORDER_LEVER_EXCEED_THE_LIMIT_ERROR("-1014", "杠杆倍数超过限制"),
    LOW_MAINTENANCE_MARGIN_RATIO("-1015", "维持保证金率不足，不允许开仓"),
    CREATE_ORDER_ERROR("-1016", "创建订单失败"),
    CANCEL_ORDER_ERROR("-1017", "撤销订单失败"),

    PLEASE_SET_LEVERAGE_FIRST("-1018", "请先设置杠杆"),
    UPDATE_POSITION_ERROR("-1019", "修改用户持仓发生异常"),
    UPDATE_ACCOUNT_MARGIN_ERROR("-1020", "调整保证金发生异常"),

    PARAMETER_ERROR("-1021", "参数错误"),
    FREEZE_POSITION_FAIL("-1022", "调整持仓冻结发生异常"),

    REFUND_FREEZE_FEE_FAIL("-1023", "退回剩余冻结手续费发生异常"),

    NEW_PRICE_FAIL("-1024", "获取最新价格失败"),
    ACCOUNT_LIQUIDATION_FAIL("-1025", "爆仓账户处理异常"),
    CREATE_CLOSE_ORDER_ERROR("-1026", "创建平仓订单失败"),

    ;

    /**
     * 错误码
     */
    private final String resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    CommonEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
