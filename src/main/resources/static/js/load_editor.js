const editor = new toastui.Editor({
  el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
  height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
  initialEditType: 'markdown',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
  initialValue: '',                       // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
  previewStyle: 'vertical',               // 마크다운 프리뷰 스타일 (tab || vertical)
  placeholder: '내용을 입력해 주세요.',
  hooks: {
    async addImageBlobHook(blob, callback) { // 이미지 업로드 로직 커스텀
      try {
        /*
         * 1. 에디터에 업로드한 이미지를 FormData 객체에 저장
         *    (이때, 컨트롤러 uploadEditorImage 메서드의 파라미터인 'image'와 formData에 append 하는 key('image')값은 동일해야 함)
         */
        const formData = new FormData();
        formData.append('image', blob);

        // 2. FileApiController - uploadEditorImage 메서드 호출
        const response = await fetch('/tui-editor/image-upload', {
          method: 'POST',
          body: formData,
        });

        // 3. 컨트롤러에서 전달받은 디스크에 저장된 파일명
        const filename = await response.text();
        console.log('서버에 저장된 파일명 : ', filename);

        // 4. addImageBlobHook의 callback 함수를 통해, 디스크에 저장된 이미지를 에디터에 렌더링
        const imageUrl = `/tui-editor/image-print?filename=${filename}`;
        callback(imageUrl, 'image alt attribute');

      } catch (error) {
        console.error('업로드 실패 : ', error);
      }
    }
  }
  /* end of hooks */
});

// 모든 클래스가 "js-select2"인 요소를 선택
var select2Elements = document.querySelectorAll('.js-select2');

// 각 요소에 대해 반복
select2Elements.forEach(function(element) {
  // select2 초기화
  new Select2(element, {
    minimumResultsForSearch: 20,
    dropdownParent: element.nextElementSibling.querySelector('.dropDownSelect2')
  });

  // select2 닫힐 때 이벤트 리스너 추가
  element.addEventListener('select2:close', function(event) {
    // 선택된 값이 "Please chooses"인지 확인
    if (element.value == "Please chooses") {
      // 선택된 값이 "Please chooses"이면 '.js-show-service' 요소를 숨김
      document.querySelector('.js-show-service').style.display = 'none';
    } else {
      // 선택된 값이 "Please chooses"가 아니면 '.js-show-service' 요소를 표시
      document.querySelector('.js-show-service').style.display = 'block';
    }
  });
});
