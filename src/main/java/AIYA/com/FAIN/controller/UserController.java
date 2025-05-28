//package AIYA.com.FAIN.controller;
//
//import AIYA.com.FAIN.dto.ApiResponseDto;
//import AIYA.com.FAIN.dto.UserSignupDto;
//import AIYA.com.FAIN.service.UserService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//public class UserController {
//  private final UserService userService;
//
//  public UserController (UserService userService) {
//    this.userService = userService;
//  }
//
//  @Transactional
//  @Operation(summary = "회원가입", description = "보호자가 보호자정보와 환자 정보를 입력하여 회원가합니다")
//  @PostMapping("/api/v1/signup")
//  public ResponseEntity<ApiResponseDto<Void>> signup(@RequestBody UserSignupDto dto) {
//    try {
//      userService.signup(dto);
//      ApiResponseDto<Void> response = new ApiResponseDto<>(true);
//      return ResponseEntity.ok(response);
//    } catch (IllegalArgumentException e) {
//      ApiResponseDto<Void> response = new ApiResponseDto<>(false,e.getMessage());
//      return ResponseEntity.badRequest().body(response);
//    }
//  }
//
//  @Transactional
//  @Operation(summary = "아이디 중복 확인", description = "회원가입 시 아이디가 사용가능한지 혹은 중복되어 사용불가능한지 확인합니다.")
//  @GetMapping("/api/v1/signup/check-id")
//  public ResponseEntity<ApiResponseDto<Void>> checkId(@Parameter(name = "userId", description = "중복 확인할 id") @RequestParam String userId){
//    boolean idDuplicated = userService.checkUserId(userId);
//    ApiResponseDto<Void> response;
//    if(idDuplicated){
//      response = new ApiResponseDto<>(false,"이미 사용중인 아이디 입니다.");
//    }else{
//      response = new ApiResponseDto<>(true, "사용 가능한 아이디 입니다.");
//    }
//    return ResponseEntity.ok(response);
//  }
//
//}
