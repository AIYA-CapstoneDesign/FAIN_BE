package AIYA.com.FAIN.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerRedirectController {
  @GetMapping("/swagger-ui/index.html")
  public String redirectToSwagger(){
    //강제 리다이렉트시, 서버주소를 포함해 API문서 로드하도록
    return "redirect:/swagger-ui/index.html?url=https://fain-aiya.shop/v3/api-docs";
  }
}
