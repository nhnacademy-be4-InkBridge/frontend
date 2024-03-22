const nowPassword = document.getElementById('now_pw');
const newPassword = document.getElementById('new_pw');
const confirmPassword = document.getElementById('conf_pw');
const labels = [document.getElementById('lb_now_pw'), document.getElementById('lb_new_pw'), document.getElementById('lb_conf_pw')];

function toggleLabelVisibility(input, label) {
    label.style.display = input.value === '' ? 'block' : 'none';
}

nowPassword.addEventListener('input', function () {
    toggleLabelVisibility(nowPassword, labels[0]);
});

newPassword.addEventListener('input', function () {
    toggleLabelVisibility(newPassword, labels[1]);
});

confirmPassword.addEventListener('input', function () {
    toggleLabelVisibility(confirmPassword, labels[2]);
});

labels.forEach(function (label, index) {
    label.addEventListener('click', function () {
        [nowPassword, newPassword, confirmPassword][index].focus();
    });
});


async function updatePassword() {
    let nowPwInput = document.getElementById("now_pw");
    let password = nowPwInput.value.trim();
    let newPwInput = document.getElementById("new_pw");
    let newPassword = newPwInput.value.trim();
    let confPwInput = document.getElementById("conf_pw");
    let newConfPassword = confPwInput.value.trim();

    let passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,20}$/;
    if (newPassword !== newConfPassword && !passwordRegex.test(password) &&
        !passwordRegex.test(newPassword) && !passwordRegex.test(newConfPassword)) {
        alert("비밀번호가 일치하거나 유효하지 않습니다.");
        return;
    }

    const requestData = {
        password: password,
        newPassword: newPassword
    };

    const response = await fetch("/updatePassword", {
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
        alert("비밀번호가 변경되었습니다.");
        isEmailValid = false;
    } else {
        alert("비밀번호를 잘못 입력하셨습니다.");
        isEmailValid = true;
    }
}
