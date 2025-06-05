package AIYA.com.FAIN.client;

import AIYA.com.FAIN.dto.MonthlyRequestDto;
import AIYA.com.FAIN.dto.ReportRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PythonApiClient {
  private final RestTemplate restTemplate;

  private final String pythonServerUrl = "http://localhost:8000/report";
//  private final String pythonServerUrl = "http://python:8000/report";

  private final String monthServerUrl = "http://localhost:8000/month";
//  private final String monthServerUrl = "http://python:8000/month";


  public String gptReport(ReportRequestDto requestDto){
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<ReportRequestDto> request = new HttpEntity<>(requestDto,headers);
    ResponseEntity<String> response = restTemplate.postForEntity(pythonServerUrl,request, String.class);

    return response.getBody();

  }

  public String monthlyReport(MonthlyRequestDto requestDto){
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<MonthlyRequestDto> request = new HttpEntity<>(requestDto,headers);
    ResponseEntity<String> response = restTemplate.postForEntity(monthServerUrl,request, String.class);

    return response.getBody();
  }
}
