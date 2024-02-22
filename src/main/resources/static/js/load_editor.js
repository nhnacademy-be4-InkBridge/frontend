const editor = new toastui.Editor({
  el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
  height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
  initialEditType: 'markdown',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
  initialValue: '',                       // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
  previewStyle: 'vertical',               // 마크다운 프리뷰 스타일 (tab || vertical)
});
document.getElementById("create-tag-btn").addEventListener("click", function () {
  // 새로운 select 요소 생성
  console.log("hello")
  var select = document.createElement("select");

  // 옵션 추가
  var options = ["Option 1", "Option 2", "Option 3"];
  for (var i = 0; i < options.length; i++) {
    var option = document.createElement("option");
    option.text = options[i];
    option.value = "option" + (i + 1);
    select.appendChild(option);
  }

  // select를 컨테이너에 추가
  document.getElementById("tag-container").appendChild(select);
});