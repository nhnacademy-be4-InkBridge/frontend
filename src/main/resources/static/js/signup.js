document.addEventListener("DOMContentLoaded", function () {


    // 폼 제출 전 필드 검증
    var submitButton = document.getElementById("submitButton");
    var signupForm = document.getElementById("signupForm");


    // 각각의 필드 유효성 검증 여부를 저장할 변수
    var isEmailValid = false;
    var isPasswordValid = false;
    var isPhoneNumberValid = false;
    var isDooraySendValid = false;

    // 이메일 중복 확인 버튼 클릭 시 이벤트 처리
    var checkDuplicateButton = document.getElementById("checkDuplicate");
    checkDuplicateButton.addEventListener("click", function () {
        emailCheck();
    });

    async function emailCheck() {
        var emailInput = document.getElementById("email");
        var email = emailInput.value.trim();

        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("올바른 이메일 주소를 입력하세요.");
            emailInput.focus(); // 이메일 입력란에 포커스를 맞춤
            isEmailValid = false;
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
            isEmailValid = false;
        } else {
            alert("사용 가능한 이메일입니다.");
            isEmailValid = true;
        }
    }


    var phoneNumberInput = document.getElementById("phoneNumber");
    var phoneNumberFeedback = document.getElementById("phoneNumberFeedback");

    phoneNumberInput.addEventListener("input", function () {
        var phoneNumber = phoneNumberInput.value.trim();
        var phoneNumberRegex = /^010\d{8}$/;

        if (!phoneNumberRegex.test(phoneNumber)) {
            phoneNumberInput.classList.add("is-invalid");
            phoneNumberFeedback.style.display = "block";
            isPhoneNumberValid = false;
        } else {
            phoneNumberInput.classList.remove("is-invalid");
            phoneNumberFeedback.style.display = "none";
            isPhoneNumberValid = true;
        }
    });

    submitButton.addEventListener("click", function (event) {
        event.preventDefault();

        var email = document.getElementById("email").value;
        var password = document.getElementById("inputValid1").value;
        var confirmPassword = document.getElementById("inputInvalid2").value;
        var memberName = document.getElementById("memberName").value;
        var birthday = document.getElementById("birthday").value;
        var phoneNumber = document.getElementById("phoneNumber").value;


        if (email === "" || password === "" || confirmPassword === "" || memberName === "" || birthday === "" || phoneNumber === "") {
            alert("모든 필드를 채워주세요.");
            return;
        }
        // 모든 필드의 유효성 검사를 통과한 경우에만 제출
        if (isEmailValid && isPasswordValid && isPhoneNumberValid && isDooraySendValid) {
            signupForm.submit();
        } else {
            alert("입력한 정보를 확인하세요.");
        }
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
            isPasswordValid = false;
        } else {
            tooltip.style.display = "none";
            passwordInput.classList.remove("is-invalid");
            isPasswordValid = true;
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
            isPasswordValid = false;
        } else {
            feedbackMessage.style.display = "none";
            confirmPasswordInput.classList.remove("is-invalid");
            isPasswordValid = true;
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


    var sendVerificationCodeButton = document.getElementById("sendVerificationCode");
    var confirmVerificationCodeButton = document.getElementById("confirmVerificationCode");
    var checkDooraySend = false;

    // 전송 버튼 클릭 시
    sendVerificationCodeButton.addEventListener("click", function () {
        if (isPhoneNumberValid === false) {
            alert("전화번호를 입력해주세요.")
            return;
        }
        if (checkDooraySend === false) {
            sendVerificationCode();
            checkDooraySend = true;
        }else{
            alert("전송된 숫자를 적어주세요.");
        }
        // 전송 버튼 숨기고 인증번호 입력 버튼 보이기
        document.getElementById("verificationLabel").style.display = "inline-block";
        document.getElementById("verificationCode").style.display = "inline-block";
        document.getElementById("confirmVerificationCode").style.display = "inline-block";
    });

    // 인증번호 확인 버튼 클릭 시
    confirmVerificationCodeButton.addEventListener("click", function () {
        var confirmCodeInput = document.getElementById("verificationCode")
        var confirmCode = confirmCodeInput.value.trim();
        if (certificationMessage === confirmCode) {
            isDooraySendValid = true;
            checkDooraySend = true;
            alert("인증 성공");
        } else {
            alert("인증을 다시 시도해주세요.");
            confirmCodeInput.value = ''; // 이메일 입력란을 비움
            confirmCodeInput.focus(); // 이메일 입력란에 포커스를 맞춤
            isDooraySendValid = false;
            checkDooraySend = false;
        }
    });

    // 전송 함수
    async function sendVerificationCode() {
        const response = await fetch("/dooraySend", {
            method: "POST",
        });

        if (!response.ok) {
            console.error("Failed to send verification code");
            return;
        }
        certificationMessage = await response.text();
        alert("인증번호가 메신저로 전송되었습니다.");
    }


});
