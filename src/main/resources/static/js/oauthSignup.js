let isEmailValid = false;
let isPhoneNumberValid = false;
document.addEventListener("DOMContentLoaded", function () {


    // 폼 제출 전 필드 검증
    const submitButton = document.getElementById("updateButton");
    const signupForm = document.getElementById("updateForm");


    submitButton.addEventListener("click", function (event) {
        event.preventDefault();

        let email = document.getElementById("email").value;
        let memberName = document.getElementById("memberName").value;
        let birthday = document.getElementById("birthday").value;
        let phoneNumber = document.getElementById("phoneNumber").value;

        if (email === "" || memberName === "" || birthday === "" || phoneNumber === "") {
            alert("모든 필드를 채워주세요.");
            return;
        }
        if (isEmailValid && isPhoneNumberValid) {
            signupForm.submit();
        }else{
            alert("입력한 정보를 확인하세요.");
        }

    });

    let birthday = document.getElementById("birthday");

    let today = new Date();

    birthday.setAttribute("max", today.toISOString().split("T")[0]);

    const phoneNumberInput = document.getElementById("phoneNumber");
    const phoneNumberFeedback = document.getElementById("phoneNumberFeedback");

    phoneNumberInput.addEventListener("input", function () {
        let phoneNumber = phoneNumberInput.value.trim();
        let phoneNumberRegex = /^010\d{8}$/;

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

});


async function emailCheck() {

    let emailInput = document.getElementById("email");
    let email = emailInput.value.trim();
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