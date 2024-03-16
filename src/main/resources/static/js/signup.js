document.addEventListener("DOMContentLoaded", function () {


    // 폼 제출 전 필드 검증
    var submitButton = document.getElementById("submitButton");
    var signupForm = document.getElementById("signupForm");

    submitButton.addEventListener("click", function (event) {
        event.preventDefault();

        var email = document.getElementById("email").value;
        var password = document.getElementById("inputValid").value;
        var confirmPassword = document.getElementById("inputInvalid").value;
        var memberName = document.getElementById("memberName").value;
        var birthday = document.getElementById("birthday").value;
        var phoneNumber = document.getElementById("phoneNumber").value;

        if (email === "" || password === "" || confirmPassword === "" || memberName === "" || birthday === "" || phoneNumber === "") {
            alert("모든 필드를 채워주세요.");
            return;
        }

        signupForm.submit();
    });

    // 비밀번호 유효성 검사
    var passwordInput = document.getElementById("inputValid1");
    var confirmPasswordInput = document.getElementById("inputInvalid2");
    var tooltip = document.querySelector(".invalid-tooltip");
    var feedbackMessage = document.querySelector(".invalid-feedback");

    passwordInput.addEventListener("input", function () {
        var password = passwordInput.value.trim();
        var passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,20}$/;

        if (!passwordRegex.test(password)) {
            tooltip.style.display = "block";
            passwordInput.classList.add("is-invalid");
        } else {
            tooltip.style.display = "none";
            passwordInput.classList.remove("is-invalid");
        }

        // 내용이 지워질 때 알림창 사라지게 함
        if (password === "") {
            tooltip.style.display = "none";
            passwordInput.classList.remove("is-invalid");
        }
    });

    confirmPasswordInput.addEventListener("input", function () {
        var password = passwordInput.value.trim();
        var confirmPassword = confirmPasswordInput.value.trim();

        if (password !== confirmPassword) {
            feedbackMessage.style.display = "block";
            confirmPasswordInput.classList.add("is-invalid");
        } else {
            feedbackMessage.style.display = "none";
            confirmPasswordInput.classList.remove("is-invalid");
        }

        // 내용이 지워질 때 알림창 사라지게 함
        if (confirmPassword === "") {
            feedbackMessage.style.display = "none";
            confirmPasswordInput.classList.remove("is-invalid");
        }
    });

    var birthday = document.getElementById("birthday");

    var today = new Date();

    birthday.setAttribute("max", today.toISOString().split("T")[0]);

});


async function emailCheck() {

    var emailInput = document.getElementById("email");
    var email = emailInput.value.trim();

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert("올바른 이메일 주소를 입력하세요.");
        emailInput.focus(); // 이메일 입력란에 포커스를 맞춤
        return;
    }

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