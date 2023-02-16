package com.bijinsuo.common.result;

/**
 * Created by zhongjingyuun on 20210927
 */
public enum ApiResultType {
    SUCCESS("0", "result.api.success", "成功"),

    // 系统错误码
    SYS_ERROR("100001", "result.api.sys.error", "系统异常"),
    SYS_UPDATE("100002", "result.api.sys.update", "系统升级"),
    REQEST_ILLEGAL("100003", "result.api.reqest.illegal", "请求非法，建议重启客户端"),
    PARAMETER_ILLEGAL("100004", "result.api.parameter.illegal", "请求参数不合法"),
    PARAMETER_SIGN_ILLEGAL("100005", "result.api.parameter.sign.illegal", "参数签名错误"),
    PARAMETER_TOKEN_ILLEGAL("100006", "result.api.parameter.token.illegal", "Token无效"),
    IP_ILLEGAL("100007", "result.api.ip.illegal", "非法IP"),
    REQUEST_TIME_EXPIRED("100008", "result.request.time.expired", "请求时间过期"),

    //Header
    HEADER_BUILD_ILLEGAL("100101", "result.header.build.error", "Header参数Build-CU错误"),
    HEADER_PLATFORM_ILLEGAL("100102", "result.header.platform.error", "Header参数Platform-CU错误"),
    HEADER_LANGUAGE_ILLEGAL("100103", "result.header.language.error", "Header参数Language错误"),

    // 业务错误码
    EMAILCODE_FAIL("110000", "result.api.emailcode.fail", "邮箱验证码错误或过期"),
    SMSCODE_FAIL("110001", "result.api.smscode.fail", "短信验证码错误或过期"),
    SYMBOL_UNKNOWN("110002", "result.api.symbol.unknown", "未知的货币代号"),
    PAYPWD_ERROR("110003", "result.api.capital.error", "资金密码错误"),
    WITHDRAW_LOCKED("110004", "result.api.withdraw.locked", "提现被冻结"),
    BALANCE_NOT_ENOUGH("110005", "result.api.balance.notenough", "可用余额不足"),
    WITHDRAW_ADDRESS_NOTBIND("110006", "result.api.withdraw_address.notbind", "不是已经绑定的提现地址"),
    GOOGLECODE_FAIL("110007", "result.api.googlecode.fail", "谷歌验证码错误或过期"),
    MODIFY_PAYPWD_ERROR("110008", "result.api.modify.capital.error", "修改资金密码错误"),
    MODIFY_LOGIN_ERROR("110009", "result.api.modify.login.error", "修改登录密码错误"),
    DELETE_WALLET_ERROR("110010", "result.api.delete.wallet.error", "删除提现地址失败"),
    SET_PAYSET_FAIL("110011", "result.api.set.payset.error", "修改密码输入频次错误"),
    MODIFY_NICKNAME_FAIL("110012", "result.api.modify.nickname.fail", "修改昵称错误"),

    SEND_SMS_FAILED("110031", "result.api.sendsms.failed", "发送短信验证码失败"),
    SEND_EMAIL_FAILED("110032", "result.api.sendemail.failed", "发送邮箱验证码失败"),

    USER_NOT_EXIST("110020", "result.api.user.not.exist", "用户名不存在"),
    USER_MOBILE_EXIST("110023", "result.api.user.mobile.exist", "手机号已注册"),
    USER_EMAIL_EXIST("110024", "result.api.user.email.exist", "邮箱已注册"),
    USER_LOCKED_ADMIN("110025", "result.api.user.locked.admin", "帐户被后台管理员锁定"),
    USER_FREEZE_LOGIN("110026", "result.api.user.freeze.login", "账号被冻结登录"),
    USER_LOCKED_2HOURS("110027", "result.api.user.account_locked_two_hours", "账户被锁定2小时"),
    USER_PASSWORD_ERROR("110028", "result.api.user.username_or_password_error_format", "用户名或密码错误,您还有%s次机会"),
    USER_AUTHRELANAME_ERROR("110029", "result.api.user.authRelaname.error", "您的证件即将于三个月内失效"),
    OLD_LOGIN_PWD_ERROR("110030", "result.api.login.pwd.error", "旧登录密码错误"),
    PLEASE_BIND_CELL_PHONE_NUMBER("110031", "result.please.bind.cell.phone.number", "请绑定手机号码"),
    USER_NO_AUTHORITY("110032", "result.api.user.no.authority", "该用户无权限进行此操作"),
    USER_CHARGE_FAIL("110033", "result.api.charge.fail", "充值失败"),
    USER_WITHDRAW_FAIL("110034", "result.api.withdraw.fail", "提现失败"),
    USER_DEPOSIT_WITHDRAW_FAIL("110035", "user.deposit.withdraw.fail", "当前币种尚未开放充值提现"),
    USER_TOKEN_PASSWORD_ERROR("110036", "result.api.user.token.password.error", "密码错误"),

    ErrorCode("110037", "result.api.error.code", "验证码错误"),
    FREQUENCY_LIMIT("110038", "result.api.frequency.limit", "发送验证码的频次过高"),

    CANCEL_SUC("110039", "result.api.cancel.suc", "取消成功"),
    USER_DEPOSIT_EXCHANGE_FAIL("110040", "user.deposit.exchange.fail", "当前币种尚未开放金币兑换"),
    REQ_RESULT_API_FREQUENCY_LIMIT("110041", "result.api.frequency.limit", "接口访问次数过快"),
    REQ_RESULT_API_FREQUENCY_LIMIT_MAX("110042", "result.api.frequency.limit.max", "当前接口访问量过多"),
    
    USER_NICKNAME_EXISTS("110042", "user.nickname_exists", "该昵称已存在"),
    ;
    public String code;
    public String languageKey;
    public String message;

    ApiResultType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private ApiResultType(String code, String languageKey, String message) {
        this.code = code;
        this.languageKey = languageKey;
        this.message = message;
    }

    public String getLanguageKey() {
        return languageKey;
    }

    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
