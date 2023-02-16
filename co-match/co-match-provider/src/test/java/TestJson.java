import com.alibaba.fastjson.JSONObject;
import com.bijinsuo.common.utils.entity.MatchOrderDTO;
import com.bjs.contract.entity.vo.MatchOrderVO;

public class TestJson {
    public static void main(String[] args) {
        String s = "{\"id\":30017,\"matchInfoType\":\"match_order\",\"operateType\":\"OPEN\",\"price\":16500,\"robot\":0,\"side\":\"BUY\",\"status\":1,\"symbol\":\"BTCUSDT\",\"type\":11,\"userId\":20442,\"volume\":165000.00000000}";
        MatchOrderVO matchOrderVO = JSONObject.parseObject(s, MatchOrderVO.class);

        System.out.println(matchOrderVO);
    }
}
