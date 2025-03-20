package Lawbot.demo.ai;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class LawAdvisorAIClient {

    private static final String AI_SERVER_URL = "http://localhost:8000/predict";  // FastAPI 서버 주소

    public String getLegalAdviceDecision(String description) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("description", description);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(AI_SERVER_URL, request, Map.class);
            Object decisionObj = response.getBody().get("decision");

            if (decisionObj != null) {
                String decision = decisionObj.toString().trim().toUpperCase();

                // 올바른 응답 값인지 확인
                if (decision.equals("NEEDED") || decision.equals("NOT_NEEDED")) {
                    return decision;
                }
            }

            // 예외 발생 시 기본값
            System.err.println("AI 서버 응답 이상, 기본값 NOT_NEEDED 반환");
            return "NOT_NEEDED";

        } catch (Exception e) {
            System.err.println("AI 서버 오류: " + e.getMessage());
            return "NOT_NEEDED"; // 기본값으로 설정
        }
    }
}
