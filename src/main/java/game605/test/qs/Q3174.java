package game605.test.qs;

import com.alibaba.fastjson.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3174
 * @description TODO
 * @since 2024/9/5 10:29
 */
public class Q3174 {

    public String clearDigits(String s) {
        StringBuilder st = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                st.deleteCharAt(st.length() - 1);
            } else {
                st.append(c);
            }
        }
        return st.toString();
    }

    public static JSONObject getItems() {
        JSONObject items = new JSONObject();
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            JSONObject item = new JSONObject();
            item.put("id", "EXM-0001");
            item.put("exClinicalId", "EXC-0001");
            item.put("itemCode", "1001");
            item.put("itemName", "磁共振");
            item.put("examinationResultCode", "01");
            item.put("examinationResultName", "未见异常");
            item.put("examinationQuantification", "4.0");
            item.put("examinationQuantificationUnit", "x10^12/L");
            item.put("operatorId", "OP-001");
            item.put("operationTime", "2024-03-15 10:31:00");
            list.add(item);
        }
        items.put("item", new JSONArray(list));
        return items;
    }


    public static JSONObject createJson(JSONObject origin)  {
        JSONObject result = new JSONObject();
        result.put("request", new JSONObject());
        // 请求
        JSONObject request = result.getJSONObject("request");
        request.put("function", "updateexclinical");
        request.put("params", new JSONObject());
        // 参数
        JSONObject params = request.getJSONObject("params");
        params.put("type", 1);
        params.put("data", new JSONObject());
        // 数据
        JSONObject data = params.getJSONObject("data");
        data.put("id", "ACT-0001");
        data.put("patientId", "Pat0001");
        data.put("activityTypeCode", "1");
        data.put("activityTypeName", "门诊");
        data.put("serialNumber", "SER-0001");
        data.put("patientName", "王五");
        data.put("idCardTypeCode", "01");
        data.put("idCardTypeName", "居民身份证");
        data.put("idCard", "11010119920509233X");
        data.put("wardName", "门诊部");
        data.put("wardNo", "102");
        data.put("bedNo", "102-1");
        data.put("applicationFormNo", "FORM-0001");
        data.put("applyOrgCode", "110114110");
        data.put("applyOrgName", "XX 医院");
        data.put("applyDeptName", "呼吸内科专业");
        data.put("applyDeptCode", "A03.01");
        data.put("chiefComplaint", "头痛");
        data.put("symptomStartDate", "2024-03-15 10:30:43");
        data.put("symptomEndDate", "2024-03-15 10:30:43");
        data.put("symptomDesc", "持续性头痛，有时伴有恶心");
        data.put("treatmentDesc", "医生建议进行头颅CT检查");
        data.put("specialExaminationCode", "0");
        data.put("examinationTypeCode", "01");
        data.put("examinationTypeName", "影像学检查");
        data.put("examinationObjectiveDesc", "检查是否有颅内病变");
        data.put("examinationSubjectiveDesc", "患者主诉头痛，需进一步检查以确定病因");
        data.put("examinationNotes", "检查前请患者保持平静，去除身上金属物品");
        data.put("examinationReportNo", "REP-0001");
        data.put("examinationReportDate", "2024-03-15 10:30:43");
        data.put("examinationReportId", "REP-0001");
        data.put("orgCode", "110114110");
        data.put("orgName", "XX 医院");
        data.put("deptCode", "A03.01");
        data.put("deptName", "呼吸内科专业");
        data.put("operatorId", "OP-001");
        data.put("operationTime", "2024-03-15 10:30:43");
        // 添加子项目
        data.put("exclinicalitems", getItems());
        return result;
    }

    public static void main(String[] args) {
        JSONObject data = createJson(null);
        // json转xml
        String xml = XML.toString(data);
        System.out.println("json------------------");
        System.out.println(data.toString());
        System.out.println("xml------------------");
        System.out.println(xml);
    }

}
