document.getElementById("deleteForm").addEventListener("submit", function(event) {
    event.preventDefault(); // 폼 제출 막기
    if (confirm("탈퇴가 성공적으로 처리되었습니다.")) {
        // 확인 버튼을 눌렀을 때
        this.submit(); // 폼 제출
    }
});