let isEmailValid = false;
let isPhoneNumberValid = false;
let hiddenEmail= "";
let hiddenPhone = "";
const trueValue = true;
document.addEventListener("DOMContentLoaded", function () {
    hiddenEmail = document.getElementById("hiddenEmail").value;
    hiddenPhone = document.getElementById("hiddenPhone").value;

// 폼 제출 전 필드 검증
    const submitButton = document.getElementById("updateButton");
    const updateForm = document.getElementById("updateForm");

    submitButton.addEventListener("click", function (event) {
        event.preventDefault();

        let email = document.getElementById("email").value.trim();
        let memberName = document.getElementById("name").value.trim();
        let phoneNumber = document.getElementById("phone").value.trim();
        if (phoneNumber === hiddenPhone) {
            isPhoneNumberValid = true;
        }
        if (email === hiddenEmail) {
            isEmailValid = true;
        }

        if (email === "" || memberName === "" || phoneNumber === "") {
            alert("모든 필드를 채워주세요.");
            return;
        }
        if (isEmailValid && isPhoneNumberValid && trueValue) {
            updateForm.submit();
        } else {
            alert("정보를 입력해 주세요.");
        }

    });

    const phoneNumberInput = document.getElementById("phone");
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
    const emailInput = document.getElementById("email");
    let email = emailInput.value.trim();
    if (email === hiddenEmail) {
        isEmailValid = true;
        alert("현재 사용중인 이메일입니다.");
        return;
    }

    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
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
