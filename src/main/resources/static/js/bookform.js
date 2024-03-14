// 수정 시 저장된 도서 설명을 js로 갖고 온다
const existingContent = document.getElementById(
    'existing-content').innerHTML;
let fileIdList = [];

const editor = new toastui.Editor({
  el: document.querySelector('#editor'),
  height: '500px',
  initialEditType: 'wysiwyg',
  initialValue: existingContent || '',
  previewStyle: 'vertical',
  placeholder: '',
  hooks: {
    async addImageBlobHook(blob, callback) { // 이미지 업로드 로직 커스텀
      try {
        const fileExtension = blob.name.split('.').pop().toLowerCase();
        if (fileExtension === 'svg') {
          alert('svg 형식은 지원하지 않습니다');
          return;
        }

        /*
         * 1. 에디터에 업로드한 이미지를 FormData 객체에 저장
         *    (이때, 컨트롤러 uploadEditorImage 메서드의 파라미터인 'image'와 formData에 append 하는 key('image')값은 동일해야 함)
         */
        const formData = new FormData();
        formData.append('image', blob);

        // 2. FileApiController - uploadEditorImage 메서드 호출
        const response = await fetch('https://inkbridge.store/image-upload', {
          method: 'POST',
          body: formData,
        });
        if (response.status !== 201) {
          alert('업로드 실패');
          return;
        }

        // 3. 컨트롤러에서 전달받은 디스크에 저장된 파일명
        const {fileId, fileName} = await response.json();

        fileIdList.push(fileId);

        // 4. addImageBlobHook의 callback 함수를 통해, 디스크에 저장된 이미지를 에디터에 렌더링
        const resource = `/image-load/${fileName}`;

        callback(resource, 'image alt attribute');
      } catch (error) {
        console.error('업로드 실패 : ', error);
      }
    }
  }
});
// 저장할 때 데이터를 컨트롤러로
document.getElementById('bookForm').addEventListener('submit',
    function () {
      document.getElementById('descriptionHidden').value = editor.getMarkdown(); // 숨겨진 입력 필드에 설정
      document.getElementById('fileIdListHidden').value = fileIdList; // BookFile에 저장할 목적으로 도서 설명에 첨부된 파일 아이디를 전달
    });

mobiscroll.setOptions({
  locale: mobiscroll.localeEn,                                         // Specify language like: locale: mobiscroll.localePl or omit setting to use default
  theme: 'ios',                                                        // Specify theme like: theme: 'ios' or omit setting to use default
  themeVariant: 'light'                                                // More info about themeVariant: https://mobiscroll.com/docs/javascript/select/api#opt-themeVariant
});

// Initialize Mobiscroll Select
var categorySelect = mobiscroll.select('#category-multiple-select', {
  inputElement: document.getElementById('category-multiple-select-input'),
  onChange: function (event, inst) {
    try {
      // Get the selected values directly from the Mobiscroll instance
      var selectedValues = inst.getVal();
      var selectedCount = selectedValues.length;

      if (selectedCount > 10) {
        // Display toast message
        console.log('over 10');
        mobiscroll.toast({
          message: '10개까지 선택 가능합니다',
          duration: 3000 // 3 seconds
        });

        // Unselect the last selected option
        inst.setVal(selectedValues.slice(0, -1), true); // Remove the last selected option
      }

      var selectElement = document.getElementById('category-multiple-select');
      var options = selectElement.querySelectorAll('option');

      // 자식 카테고리를 설정할 경우 해당하는 부모 카테고리 또한 추가된다
      selectedValues.forEach(function (value) {
        options.forEach(function (option) {
          if (option.value === value) {
            var optgroup = option.parentElement;
            var hiddenOption = optgroup.querySelector('.parent-category');
            if (!hiddenOption.selected && !selectedValues.includes(
                hiddenOption.value)) {
              selectedValues.push(hiddenOption.value);
            }
            if (!selectedValues.includes(hiddenOption.value)) {
              var optgroupOptions = Array.from(
                  optgroup.querySelectorAll('option'));
              optgroupOptions.forEach(function (optgroupOption) {
                var elementToRemove = selectedValues.indexOf(
                    optgroupOption.value);
                if (elementToRemove !== -1) {
                  inst.setVal(selectedValues.slice(elementToRemove, 0));
                }
              });
            }
          }
        });
      });
    } catch (error) {
      console.error('An error occurred:', error);
    }
  }
});

mobiscroll.select('#tag-multiple-select', {
  inputElement: document.getElementById('tag-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});
mobiscroll.select('#status-multiple-select', {
  inputElement: document.getElementById('status-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});
mobiscroll.select('#author-multiple-select', {
  inputElement: document.getElementById('author-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});
mobiscroll.select('#publisher-multiple-select', {
  inputElement: document.getElementById('publisher-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});

// 입력값 검증
(function () {
  "use strict";
  var inputFields = document.querySelectorAll('.validate-input .input100');

  inputFields.forEach(function (input) {
    input.addEventListener('blur', function () {
      if (!validate(this)) {
        showValidate(this);
      } else {
        this.parentElement.classList.add('true-validate');
      }
    });
  });

  var form = document.querySelector('.validate-form');
  form.addEventListener('submit', function (event) {
    var check = true;
    inputFields.forEach(function (input) {
      if (!validate(input)) {
        showValidate(input);
        check = false;
      }
    });
    if (!check) {
      event.preventDefault();
    }
  });

  inputFields.forEach(function (input) {
    input.addEventListener('focus', function () {
      hideValidate(this);
      this.parentElement.classList.remove('true-validate');
    });
  });

  function validate(input) {
    // isbn check
    if (input.getAttribute('name') === 'isbn') {
      return input.value.match(/^\d{13}$/g);
    }
    if (input.getAttribute('name') === 'discountRatio' || input.getAttribute(
        'name') === 'stock') {
      return input.value >= 0;
    }
    return input.value.trim() !== '';
  }

  function showValidate(input) {
    var thisAlert = input.parentElement;
    thisAlert.classList.add('alert-validate');
    thisAlert.insertAdjacentHTML('beforeend',
        '<span class="btn-hide-validate">&#xf136;</span>');

    var hideButtons = document.querySelectorAll('.btn-hide-validate');
    hideButtons.forEach(function (button) {
      button.addEventListener('click', function () {
        hideValidate(this);
      });
    });
  }

  function hideValidate(input) {
    var thisAlert = input.parentElement;
    thisAlert.classList.remove('alert-validate');
    var hideButtons = thisAlert.querySelectorAll('.btn-hide-validate');
    hideButtons.forEach(function (button) {
      button.remove();
    });
  }
})();

// 판매가 - 할인율 연동
document.getElementById("price").addEventListener("input", function () {
  calculateDiscount();
});

document.getElementById("discountRatio").addEventListener("input",
    function () {
      calculateSalePrice();
    });

function calculateDiscount() {
  var regularPrice = parseFloat(
      document.getElementById("regularPrice").value);
  var price = parseFloat(document.getElementById("price").value);
  if (!isNaN(regularPrice) && !isNaN(price)) {
    var discountRatio = ((regularPrice - price) / regularPrice) * 100;
    document.getElementById("discountRatio").value = discountRatio.toFixed(1);
  }
}

function calculateSalePrice() {
  var regularPrice = parseFloat(
      document.getElementById("regularPrice").value);
  var discountRatio = parseFloat(
      document.getElementById("discountRatio").value);
  if (!isNaN(regularPrice) && !isNaN(discountRatio)) {
    var price = regularPrice - (regularPrice * (discountRatio / 100));
    document.getElementById("price").value = price.toFixed(0);
  }
}