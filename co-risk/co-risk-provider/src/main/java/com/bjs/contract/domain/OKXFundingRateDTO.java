package com.bjs.contract.domain;

import java.util.List;

/**
 * @author Watson
 */
public class OKXFundingRateDTO {


    /**
     * code : 0
     * data : [{"fundingRate":"0.000073840780357","fundingTime":"1667808000000","instId":"BTC-USDT-SWAP","instType":"SWAP","nextFundingRate":"0.0001071604738052","nextFundingTime":"1667836800000"}]
     * msg :
     */

    private String code;
    private String msg;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "fundingRate='" + fundingRate + '\'' +
                    ", fundingTime='" + fundingTime + '\'' +
                    ", instId='" + instId + '\'' +
                    ", instType='" + instType + '\'' +
                    ", nextFundingRate='" + nextFundingRate + '\'' +
                    ", nextFundingTime='" + nextFundingTime + '\'' +
                    '}';
        }

        /**
         * fundingRate : 0.000073840780357
         * fundingTime : 1667808000000
         * instId : BTC-USDT-SWAP
         * instType : SWAP
         * nextFundingRate : 0.0001071604738052
         * nextFundingTime : 1667836800000
         */

        private String fundingRate;
        private String fundingTime;
        private String instId;
        private String instType;
        private String nextFundingRate;
        private String nextFundingTime;

        public String getFundingRate() {
            return fundingRate;
        }

        public void setFundingRate(String fundingRate) {
            this.fundingRate = fundingRate;
        }

        public String getFundingTime() {
            return fundingTime;
        }

        public void setFundingTime(String fundingTime) {
            this.fundingTime = fundingTime;
        }

        public String getInstId() {
            return instId;
        }

        public void setInstId(String instId) {
            this.instId = instId;
        }

        public String getInstType() {
            return instType;
        }

        public void setInstType(String instType) {
            this.instType = instType;
        }

        public String getNextFundingRate() {
            return nextFundingRate;
        }

        public void setNextFundingRate(String nextFundingRate) {
            this.nextFundingRate = nextFundingRate;
        }

        public String getNextFundingTime() {
            return nextFundingTime;
        }

        public void setNextFundingTime(String nextFundingTime) {
            this.nextFundingTime = nextFundingTime;
        }
    }

    @Override
    public String toString() {
        return "OKXFundingRateDTO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
