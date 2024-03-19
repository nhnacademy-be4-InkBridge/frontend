document.addEventListener("DOMContentLoaded", function() {


    // 폼 제출 전 필드 검증
    var submitButton = document.getElementById("oauthSubmit");
    var signupForm = document.getElementById("oauthForm");

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

    var birthday = document.getElementById("birthday");

    var today = new Date();

    birthday.setAttribute("max", today.toISOString().split("T")[0]);

});


async function emailCheck() {

    var emailInput = document.getElementById("email");
    var email = emailInput.value.trim();
    const requestData = {
        email: email
    };

    const response = await fetch("/checkEmail", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(requestData),
    });

    const data = await response.json();

    if (!response.ok) {
        console.log(data);
    }

    if (data === true) {
        alert("중복된 이메일입니다. 다른 이메일을 사용해주세요.");
        emailInput.value = ''; // 이메일 입력란을 비움
        emailInput.focus(); // 이메일 입력란에 포커스를 맞춤
    } else {
        alert("사용 가능한 이메일입니다.");
    }
}