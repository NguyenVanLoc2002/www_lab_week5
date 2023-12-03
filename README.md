# www_lab_week5
I. Tạo các trang web cho phép công ty đăng tin tuyển người với các skill mong muốn
  - Ở giao diện trang chủ khi click nút <a th:href="@{/cj}">Đăng tin tuyển dụng</a> thì ứng dụng sẽ di chuyển đến JobsController cụ thể là ở phương thức
     @GetMapping("/cj")
  - Tại phương thức  @GetMapping("/cj") truyền vào model lần lượt là :
      List<Job> jobs = jobModel.findAll();
      List<Skill> skills = skillModel.findAll();
      SkillLevel[] skillLevels = SkillLevel.values();
    và thêm mới 1 new Job_skill() để sau khi thêm các yêu cầu bên giao diện tạo tin tuyển dụng thì các thông tin sẽ được lưu vào.
  - Chuyển đến giao diện createjobposting.html
  - Ở giao diện createjobposting.html tạo 1 <form action="createjobposting/new" method="post" th:object="${job_skill}">
  - Trong form chứa các input và select để người dùng có thể tạo tin tuyển dụng
  - Ở phần Chọn kĩ năng thì tương ứng với mỗi kĩ năng sẽ có level và mô tả thêm, chỉ khi click chọn kĩ năng mới cho chọn cấp độ và điền mô tả thêm
    Nếu không click vào chọn kĩ năng thì sẽ không thể chọn cấp độ và điền mô tả thêm.
  - Sau khi điền các thông tin mong muốn thì click vào nút <button type="submit">Create</button> thì giao diện sẽ chuyển dữ liệu từ view xuống JobsController để 
    thêm 1 hoặc nhiều job_skill( do 1 công việc có thể có nhiều yêu cầu kĩ năng, nên nếu chỉ chọn 1 kĩ năng thì nó sẽ thêm 1 còn nếu chọn nhiều thì nó sẽ thêm nhiều)
  - Qua JobsController tại phương thức @PostMapping("/createjobposting/new") ta sẽ lấy các thông tin vừa được truyền từ view qua bằng việc dùng
    @ModelAttribute("job_skill") Job_skill jobSkill, HttpServletRequest request:
    + Để lấy các mảng skillArrays, skillLevelArrays, moreInfoArrays ta dùng request.getParameterValues
    + Dùng vòng lặp for duyệt qua mảng skillArrays( tại vì tương ứng với 1 kĩ năng thì sẽ có 1 level và 1 moreInfo --> 1 job_skill):
      Lấy String skillId = skillArrays[i];
          String skillLv = skillLevelArrays[i];
          String moreInfo = moreInfoArrays[i]; 
      Sau đó lấy skill thông qua skillId rồi chuyển nó về dạng Enum
      Cuối cùng tạo new Job_skill() rồi set các giá trị tương ứng --> lưu vào CSDL
    + Chuyển hướng đến /viewjobpostings --> Chuyển đến giao diện hiển thị dánh sách cách thông tin ứng tuyển
II. Các ứng viên khi log vào sẽ được gợi ý các công việc có skill phù hợp với mình
  - Khi click vào nút ứng tuyển công việc:<li class="navitem"><a th:href="@{/apply}">Ứng tuyển công việc</a></li> --> chuyển đến CandidateController 
  - Tại phương thức @GetMapping("/apply") thêm vào model các list sau 
        List<Address> addresses = addressService.findAll();
        List<Skill> skills = skillService.findAll();
        SkillLevel[] skillLevels = SkillLevel.values();
    Chuyển hướng đến view add-candidate.html
  - Tại giao diện add-candidate.html tạo 1 form <form action="apply/new" method="post" > cho người dùng điền các thông tin sau đó lấy dữ liệu nhập vào
    thông qua việc dùng name để lấy thông qua request trong các input cụ thể:
      <input type="text" id="name" name="name"></br></br>
    Sau khi ấn nút  <button type="submit">Apply</button> thì chuyển đến CandidateController cụ thể là  @PostMapping("/apply/new")
  - Tại phương thức @PostMapping("/apply/new") lấy các dữ liệu từ view thông qua request.getParameter:
    + Thêm 1 newCandidate vào CSDL
    + Sau đó duyệt mảng skillArrays để set các thuộc tính tướng ứng với 1 Candidate_skill newCandidate_skill = new Candidate_skill() rồi thêm nó vào CSDL thông qua 
      candidateSkillService.addCanSkill(newCandidate_skill);
    + Lưu vào session candidate vừa mới được lưu
    + Chuyển hướng đến /candidates/success --> Chuyển đến JobsController cụ thể là tại  @GetMapping("/candidates/success")
  - Tại  @GetMapping("/candidates/success"):
    + Lấy candidate được lưu vào session lúc nãy
    + Lấy danh sách các Job phù hợp với ứng viên:  List<Job> jobs = jobModel.findJobMatchingCandidate(candidate);
        findJobMatchingCandidate được viết ở lớp JobService dùng câu lệnh SQL để truy vấn từ 2 bảng Job và Job_skill
    + Dùng vòng for:each để lấy danh sách các jobSkills theo job:List<Job_skill> jobSkills = jobSkillModel.findJob_skillByJob(job.getId());
    + Thêm vào model danh sách jobs và jobSkills
    + Chuyển đến view jobsmatchingcandidate.html
  - Ở View jobsmatchingcandidate.html dùng thẻ <div th:each="job :${jobs}"> để hiển thị ra thông tin công việc 
    và dùng thẻ <div th:each="js :${jobSkills}"> để hiển thị ra các kĩ năng mà công việc đó yêu cầu

III. Giúp các công ty tìm các ứng viên có skill phù hợp rồi gửi mail mời.
  - Khi click vào nút  <li class="navitem"><a th:href="@{/findCanBySkill}">Tìm ứng viên phù hợp</a></li> ở trên giao diện thì chuyển đến CandidateController cụ thể
    là tại  @GetMapping("/findCanBySkill")
  - Tại  @GetMapping("/findCanBySkill") lấy danh sách các skill  List<Skill> skills = skillService.findAll(); sau đó thêm nó vào model
  - Chuyển đến view findcandidatebyskill.html
  - Tại view findcandidatebyskill.html tạo 1 <form action="/findCanBySkill/candidates" method="get"> cho phép người dùng chọn kĩ năng mong muốn để tìm ra ưng viên phù hơp
    , sau khi chọn và ấn nút submit <input type="submit" value="Submit"> thì dữ liệu sẽ được chuyển đến  @GetMapping("/findCanBySkill/candidates") ở CandidateController
  - Tại  @GetMapping("/findCanBySkill/candidates") ta dùng @RequestParam("selSkill") Long skillID để lấy id của Skill từ view:
    + Lấy danh sách các ứng viên có skill tương ứng với skill được chọn:List<Candidate> matchingCandidates = candidateServices.findCanMatchingSkill(skillID);
      findCanMatchingSkill dùng câu lệnh sql join 2 bảng Candidate và Candidate_skill theo candidate.id --> Thêm vào model để hiển thị dữ liệu
    + Lấy danh sách skill :List<Skill> skills = skillService.findAll(); --> thêm vào model để hiển thị lại lựa chọn Skill sau khi render 
    + Chuyển đến view findcandidatebyskill.html
  - Ở view findcandidatebyskill.html tạo 1 table để hiển thị dữ liệu các candidate phù hợp


IV. Đề xuất một số skill mà ứng viên chưa có để học
  - Khi chọn  <td><a th:href="@{/details/{id}(id=${candidate.id})}">Details</a></td> tại giao diện candidates-paging.html thì chuyển đến  
    @GetMapping("/details/{id}") ở CandidateController.
  - Tại @GetMapping("/details/{id}") ta lấy id của ứng viên thông qua @PathVariable("id") Long id:
    + Lấy danh sách các Candidate_skill có id phù hợp:  List<Candidate_skill> candidateSkills = candidateSkillService.findCanSkillByCanId(id);
      phương thức findCanSkillByCanId được viết bằng câu lệnh sql (dùng  @Query("SELECT cs.skill.id FROM Candidate_skill cs WHERE cs.candidate.id = :candidateId")
      List<Long> findSkillIdByCandidateId(@Param("candidateId") Long canId); ở lớp Candidate_skillRepository)
      --> Thêm vào model để hiển thị lên view danh sách các kĩ năng mà ứng viên đó đã có 
    + Lấy thông tin candidate theo id:  Candidate candidate = candidateServices.findById(id).get(); --> Thêm vào model để hiển thị lên view thông tin của ứng viên đó
    + Lấy danh sách các skill còn thiếu của ứng viên đó:  List<Skill> skillList = skillService.findSkillByCanId(id);
      findSkillByCanId viết theo câu lệnh sql dùng Left Join để tìm những skill chỉ có bên skill chứ không có bên bảng Candidate_skill.--> Thêm vào model
    + Chuyển đến view candidatedetails.html
  - Tại view candidatedetails.html hiển thị thông tin của ứng viên - các thông tin về kĩ năng đã có- các kỹ năng còn thiếu.

V. Và các yêu cầu khác
1. Xem tin tuyển dụng đã đăng
  - Khi click vào nút <a th:href="@{/view}">Xem tin tuyển dụng</a> thì sẽ chuyển đến @GetMapping("/view") ở JobsController
  - Tại  @GetMapping("/view"):
    + Lấy danh sách jobSkills: List<Job_skill> jobSkills = jobSkillModel.findAll(); --> Thêm vào model
    + Lấy danh sách jobs: List<Job> jobs = jobModel.findAll();--> Thêm vào model
    + Chuyển đến view viewjobpostings.html
  - Ở view viewjobpostings.html dùng thẻ <div th:each="job : ${jobs}"> để hiển thị ra danh sách các công việc muốn ứng tuyển 
    trong thẻ div này ta dùng 1 thẻ div với th:if để lấy ra các kĩ năng tương ứng với công việc yêu cầu
    <div th:each="jobskill : ${jobSkills}" th:if="${jobskill.job.id == job.id}">
2. Hiển thị danh sách các ứng viên
  - Khi click vào nút <li class="navitem"><a th:href="@{/candidates}">Ứng viên</a></li> thì sẽ chuyển đến @GetMapping("/candidates") ở CandidateController
  - Ở tại phương thức @GetMapping("/candidates") ta sẽ hiển thị theo danh sách dạng phân trang, sử dụng  @RequestParam("page") Optional<Integer> page để lấy trang,
    @RequestParam("size") Optional<Integer> size --> số phần từ của 1 trang:
    + Lấy các đối tượng candidate theo trang:
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Candidate> candidatePage = candidateServices.findPaginated(PageRequest.of(currentPage - 1, pageSize));
    --> Mỗi 1 trang sẽ lưu trữ 10 candidate  --> thêm vào model
    + Lấy tổng số trang --> Thêm vào model
    + Chuyển đến view candidates-paging
  - Tại view candidates-paging dùng thẻ <div th:switch="${candidatePage}"> đê kiểm tra:
    + Nếu null hiển thị <h2 th:case="null">No candidates yet</h2>
    + Ngược lại hiển thị các candidate trong candidatePage.content <tr th:each="candidate ,iStat: ${candidatePage.content}"
    + Hiển thị dòng chuyển trang


