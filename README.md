7주차 연습문제
/board_edit
![image](https://github.com/user-attachments/assets/53e60acf-87c5-4e91-b9c0-509be4eb58e6)

 AddArticleRequest.java
![image](https://github.com/user-attachments/assets/ba75802b-2269-409f-9320-6c8cdc4553fa)
 AddArticleRequest 클래스는 게시글 추가 요청을 처리하기 위한 데이터 전송 객체로, 제목, 내용, 작성자, 날짜, 조회수, 좋아요 수를 필드로 갖는다. Lombok을 사용하여 기본 생성자와 모든 필드를 받는 생성자, getter/setter 등을 자동 생성한다. toEntity() 메서드를 통해 Board 객체로 변환할 수 있다. 기존 코드 주석 처리 하지않고 필드만 추가 수정함.

 BlogService.java
 ![image](https://github.com/user-attachments/assets/0d8e2781-e029-43c5-9a66-5432e44be1c2)

수정화면:
![image](https://github.com/user-attachments/assets/45110f82-37fd-4281-b9fa-4defeb5d6b2a)

8주차 연습문제
/board_list 매핑
![image](https://github.com/user-attachments/assets/4f1ffbed-e3fe-4de9-9428-c435627fa50d)
 int startNum 을 사용해 한페이지에 3개씩 출력되고, Id키값이 아닌 글 번호가 출력되도록 설정.
 
 delete기능 매핑
 ![image](https://github.com/user-attachments/assets/1e7071ac-2e32-442f-a1e8-3ad8d553893f)

완료화면 :
![image](https://github.com/user-attachments/assets/7ed90121-00f1-46fa-8484-202745f8a81b)
이름은 Id이지만 글 번호만 출력됩니다.

9주차 연습문제 

![image](https://github.com/user-attachments/assets/f503334b-305c-4255-8ca2-d5d1acc0e58e)
이렇게 수정하면 MemberService.java의 save부분이 에러가 나서 실제로 적용하지는 않았음.

10주차 연습문제

![image](https://github.com/user-attachments/assets/df3de380-280e-4447-8b07-9c481e64ad2b)

게시글 추가 시 작성자 정보 저장:
addboards 메소드에서 세션에서 현재 로그인한 사용자의 ID를 가져와 request.setUser(userId);로 설정함.

게시글 보기에서 작성자만 수정/삭제 버튼 표시:
board_view 메소드에서 model.addAttribute("isAuthor", userId != null && userId.equals(board.getUser()));를 추가하여 현재 사용자가 게시글 작성자인지를 확인하고, 이를 모델에 추가.

완료화면:
![image](https://github.com/user-attachments/assets/190b5962-dae3-4c5e-a919-b28e553f9cc3)

11주차 연습문제

@Controller
public class AuthController {
    @Autowired
    private BlogService blogService; // 필요한 서비스 클래스

    // 로그인 페이지로 이동
    @GetMapping("/member_login")
    public String loginPage() {
        return "login"; // 로그인 페이지로 이동
    }

    // 로그인 처리
    @PostMapping("/api/login")
    public String login(@RequestParam String userId, @RequestParam String password, HttpSession session) {
        // 사용자 인증 로직 (예: 사용자 검증)
        // 예시로 간단한 조건으로 처리
        if (blogService.authenticate(userId, password)) {
            session.setAttribute("userId", userId); // 세션에 사용자 ID 저장
            return "redirect:/board_list"; // 게시판 목록으로 리다이렉트
        } else {
            return "redirect:/member_login?error"; // 로그인 실패 시 다시 로그인 페이지로
        }
    }

    // 로그아웃 처리
    @GetMapping("/api/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate(); // 세션 무효화
        // 쿠키 삭제
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/"); // 모든 경로에서 쿠키 삭제
        response.addCookie(cookie);
        return "redirect:/member_login"; // 로그인 페이지로 리다이렉트
    }

위 코드처럼 추가하는 방법을 생각했으나 오류때문에 추가하지는 못했음.
