document.addEventListener("DOMContentLoaded", function() {


    // 폼 제출 전 필드 검증
    var submitButton = document.getElementById("submitButton");
    var signupForm = document.getElementById("signupForm");

    submitButton.addEventListener("click", function(event) {
        event.preventDefault();

        var email = document.getElementById("email").value;
        var memberName = document.getElementById("memberName").value;
        var birthday = document.getElementById("birthday").value;
        var phoneNumber = document.getElementById("phoneNumber").value;

        if (email === "" || memberName === "" || birthday === "" || phoneNumber === "") {
            alert("모든 필드를 채워주세요.");
            return;
        }

        signupForm.submit();
    });

    // 이메일 중복 확인
    var checkDuplicateButton = document.getElementById("checkDuplicate");

    checkDuplicateButton.addEventListener("click", function() {
        var emailInput = document.getElementById("email");
        var email = emailInput.value.trim();

        // 이메일 형식 검사
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("올바른 이메일 주소를 입력하세요.");
            emailInput.focus(); // 이메일 입력란에 포커스를 맞춤
            return;
        }

        // TODO: 여기에 나중에 서버에 Ajax 요청을 보내는 로직을 추가.
        // TODO: Ajax 요청 후의 동작을 여기에 작성.
    });


    var birthday = document.getElementById("birthday");

    var today = new Date();

    birthday.setAttribute("max", today.toISOString().split("T")[0]);

});
