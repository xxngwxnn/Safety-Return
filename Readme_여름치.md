# 여름치 안전귀가앱 Safety Return

# 🎞️Project Introduction
 - 흉흉한 사건이 계속되는 가운데 전국 곳곳에서 살인예고 글로 인해 많은 국민이 불안에 떨고 있다. 따라서, 프로젝트 “안심 귀가 앱” 이 국민들의 불안을 해소하는데 조금이라도 도움이 되기를 기대하며 프로젝트 제작에 노력을 기울이고자 한다. 

# 📆Project Period
 - 2023.10.01 ~ 2023.12.16

# 👪Composition of Members
### 여름치
 - 팀장 : 여성원 - 전체적인 UI, (메인 페이지, 지도, API 등)통합 관리, PPT 제작 및 발표
 - 팀원 : 윤도균 - 게시판 관리, 전체적인 보고서 작성
 - 팀원 : 이창민 - 타이머, ID 찾기, PW 찾기, 회원 정보 관리
 - 팀원 : 장진호 - 클래스 다이어그램, 시퀀스 다이어그램, 보고서 제작 및 발표

# 💻Development Environment
 - OS : Android
 - Language : Java 
 - JDK : 17
 - Framework : spring
 - IDE : Android Studio
 - Code Sharing : GitHub

# 📱Main Function
 - 로그인 : 유저가 입력한 정보와 시스템에 저장 된 정보가 일치하는지 확인 후 로그인
 
 - 회원가입 : 유저의 정보를 입력 받고 시스템에 저장시켜 놓는다.
 - ID/PW 찾기 : 기존에 저장된 유저의 기본 정보와 비교하여 찾고 싶은 정보를 알려준다.
 
 - 회원 정보 수정 :  유저가 바꾸고 싶어하는 정보를 수정 할 수 있도록 하고, 수정된 정보를 시스템에 업데이트 한다.
 
 - 지도 : 자신의 위치를 gps로 확인 할 수 있고, 주변의 cctv를 지도에 같이 표시해준다. 
 
 - 타이머 : 귀가 할 시간을 유저가 타이머로 맞추고, 예정한 시간 안에 타이머를 끄지 않으면 미리 정해둔 비상 연락망으로 메세지가 전송된다.

 - 게시판 : 커뮤니티를 유저들에게 제공하여 안전하게 같이 귀가할 사람을 찾을 수 있도록 해준다.

# 📂Files
 - MainActivity - 앱의 메인 활동을 제어, 로그인 상태 표시 및 메인 페이지
 - MapActivity - 지도를 표시하고 gps 등 지도에 관련된 기능
 - TimerActivity - 타이머 설정 기능
 - TimerExpiredActivity - 타이머가 종료되었을 때, 알림 및 메세지 전송에 관한 기능
 - BoardActivity - 기본 게시판을 담당하고 작성된 게시물 목록을 보여줌
 - WriteActivity - 게시물을 작성할 수 있는 페이지
 - LoginActivity - 로그인에 대한 메인 페이지, 아이디/비밀번호 찾기 및 로그인 기능
 - SignUpActivity - 회원가입 기능
 - IdFindActivity - 아이디 찾기 기능
 - PasswordFindActivity - 비밀번호 찾기 기능
 - EditProfileActivity - 기존에 저장된 회원 정보를 수정할 수 있는 기능
