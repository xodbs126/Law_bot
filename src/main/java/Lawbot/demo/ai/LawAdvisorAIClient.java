package Lawbot.demo.ai;

import Lawbot.demo.legalinquiry.InquiryMessage;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class LawAdvisorAIClient {

    private static final String AI_SERVER_URL = "http://localhost:8000/predict";  // FastAPI 서버 주소

    public String getLegalAdviceDecision(List<InquiryMessage> historyMessages) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        List<Map<String, String>> historyPayload = new ArrayList<>();
        for (InquiryMessage msg : historyMessages) {
            Map<String, String> messageMap = new HashMap<>();
            messageMap.put("role", msg.getSender().equalsIgnoreCase("USER") ? "user" : "ai");
            messageMap.put("content", msg.getMessage());
            historyPayload.add(messageMap);
        }


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("history", historyPayload);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(AI_SERVER_URL, request, Map.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Object decisionObj = response.getBody().get("decision");

                if (decisionObj != null) {
                    String decision = decisionObj.toString().trim().toUpperCase();


                    if (decision.equals("NEEDED") || decision.equals("NOT_NEEDED") || decision.equals("MORE_INFO")) {
                        return decision;
                    }
                }
            }

            System.err.println("⚠️ AI 응답 이상, 기본값 NOT_NEEDED 반환");
            return "NOT_NEEDED";

        } catch (Exception e) {
            System.err.println("❌ AI 서버 오류: " + e.getMessage());
            return "NOT_NEEDED";
        }
    }
}
