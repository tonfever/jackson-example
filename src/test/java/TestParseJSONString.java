import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ChecklistInquiry;
import model.ChecklistItem;
import model.GetTaskByTaskIdResponse;
import org.junit.Test;

import java.io.IOException;

public class TestParseJSONString {

    private String jason;

    @Test
    public void readJsonToObject() throws IOException {
        jason = "{ \"task-id\" : 172, \"task-priority\" : 0, \"task-name\" : \"Rest-All Decision\", \"task-subject\" : null, \"task-description\" : null, \"task-type\" : null, \"task-form\" : \"Rest-AllDecision\", \"task-status\" : \"Ready\", \"task-actual-owner\" : \"\", \"task-created-by\" : \"\", \"task-created-on\" : { \"java.util.Date\" : 1530689462514 }, \"task-activation-time\" : { \"java.util.Date\" : 1530689462514 }, \"task-expiration-time\" : null, \"task-skippable\" : true, \"task-workitem-id\" : 172, \"task-process-instance-id\" : 212, \"task-parent-id\" : -1, \"task-process-id\" : \"src.First-Business-Process\", \"task-container-id\" : \"Test-True-Project_1.0.0\", \"task-pot-owners\" : null, \"task-excl-owners\" : null, \"task-business-admins\" : null, \"task-input-data\" : null, \"task-output-data\" : null }";

        ObjectMapper mapper = new ObjectMapper();

        GetTaskByTaskIdResponse response =  mapper.readValue(jason, GetTaskByTaskIdResponse.class);
        System.out.println(response.getTaskForm());
    }

    @Test
    public void readJsonArray() throws IOException {
        jason = "[\n" +
            "    {\n" +
            "        \"checkListCd\": \"TO_VERIFY_TO_MANAGER\",\n" +
            "        \"checkListDesc\": \"Send to manager for review\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"checkListCd\": \"TO_VERIFY_TO_REVIEW\",\n" +
            "        \"checkListDesc\": \"Review the contents\"\n" +
            "    }\n" +
            "]";

        ObjectMapper mapper = new ObjectMapper();

        ChecklistItem[] response = mapper.readValue(jason,ChecklistItem[].class);
        System.out.println(response.length);
    }

    @Test
    public void writeJson() throws JsonProcessingException {
        ChecklistInquiry inquiry = new ChecklistInquiry();
        inquiry.setTaskId(123);
        inquiry.setProcCd("PROCESS_1");

        ChecklistItem item1 = new ChecklistItem();
        item1.setCheckListCd("to_review");
        item1.setCheckListDesc("to review by manager");

        inquiry.addItem(item1);

        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(inquiry);
        System.out.println(result);
    }

    @Test
    public void readJsonInnerNodes() throws IOException {
        jason = "{\n" +
            "  \"type\": \"SUCCESS\",\n" +
            "  \"msg\": \"Container abc112 successfully called.\",\n" +
            "  \"result\": {\n" +
            "    \"execution-results\": {\n" +
            "      \"results\": [\n" +
            "        {\n" +
            "          \"value\": {\n" +
            "            \"ChecklistInquiry\": {\n" +
            "              \"taskId\": 0,\n" +
            "              \"procCd\": \"PROCESS_1\",\n" +
            "              \"taskCd\": \"TO_VERIFY\",\n" +
            "              \"listOfCheckList\": [\n" +
            "                {\n" +
            "                  \"checkListCd\": \"TO_VERIFY_TO_MANAGER\",\n" +
            "                  \"checkListDesc\": \"Send to manager for review\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"checkListCd\": \"TO_VERIFY_TO_REVIEW\",\n" +
            "                  \"checkListDesc\": \"Review the contents\"\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          },\n" +
            "          \"key\": \"ChecklistInquiry\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"facts\": [\n" +
            "        {\n" +
            "          \"value\": {\n" +
            "            \"org.drools.core.common.DefaultFactHandle\": {\n" +
            "              \"external-form\": \"0:1:750061703:750061703:1:DEFAULT:NON_TRAIT:ChecklistInquiry\"\n" +
            "            }\n" +
            "          },\n" +
            "          \"key\": \"ChecklistInquiry\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jason);

        JsonNode taskIdNode = jsonNode.findPath("taskId");
        JsonNode procCdNode = jsonNode.findPath("procCd");
        JsonNode taskCdNode = jsonNode.findPath("taskCd");
        JsonNode listOfCheckListNode = jsonNode.findPath("listOfCheckList");

        if (listOfCheckListNode.isMissingNode()==false) {
            ChecklistItem[] response = mapper.readValue(listOfCheckListNode.toString(),ChecklistItem[].class);
        }

        System.out.println(taskIdNode+","+procCdNode+","+taskCdNode+","+listOfCheckListNode.toString());
    }
}
