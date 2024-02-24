const editor = new toastui.Editor({
  el: document.querySelector('#editor'), // 에디터를 적용할 요소 (컨테이너)
  height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
  initialEditType: 'markdown',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
  initialValue: '',                       // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
  previewStyle: 'vertical',               // 마크다운 프리뷰 스타일 (tab || vertical)
});

mobiscroll.setOptions({
  locale: mobiscroll.localeEn,                                         // Specify language like: locale: mobiscroll.localePl or omit setting to use default
  theme: 'ios',                                                        // Specify theme like: theme: 'ios' or omit setting to use default
  themeVariant: 'light'                                                // More info about themeVariant: https://mobiscroll.com/docs/javascript/select/api#opt-themeVariant
});

mobiscroll.select('#author-multiple-select', {
  inputElement: document.getElementById('author-multiple-select-input')  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});
mobiscroll.select('#publisher-multiple-select', {
  inputElement: document.getElementById('publisher-multiple-select-input')  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});
mobiscroll.select('#tag-multiple-select', {
  inputElement: document.getElementById('tag-multiple-select-input')  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});

// 개수 제한
const select = document.getElementById("parentCategory");
const maxSelect = 10;

select.addEventListener("change", function() {
  const selectedOptions = Array.from(this.selectedOptions);
  if (selectedOptions.length > maxSelect) {
    // 최대 선택 개수를 초과한 경우 선택을 취소함
    selectedOptions.forEach(option => {
      if (!option.selected) {
        option.disabled = true;
      }
    });
  } else {
    // 선택 가능한 상태로 되돌림
    Array.from(this.options).forEach(option => {
      option.disabled = false;
    });
  }
});

(function() {
  "use strict";
  var inputFields = document.querySelectorAll('.validate-input .input100');

  inputFields.forEach(function(input) {
    input.addEventListener('blur', function() {
      if (!validate(this)) {
        showValidate(this);
      } else {
        this.parentElement.classList.add('true-validate');
      }
    });
  });

  var form = document.querySelector('.validate-form');
  form.addEventListener('submit', function(event) {
    var check = true;
    inputFields.forEach(function(input) {
      if (!validate(input)) {
        showValidate(input);
        check = false;
      }
    });
    if (!check) {
      event.preventDefault();
    }
  });

  inputFields.forEach(function(input) {
    input.addEventListener('focus', function() {
      hideValidate(this);
      this.parentElement.classList.remove('true-validate');
    });
  });

  function validate(input) {
    if (input.getAttribute('type') === 'email' || input.getAttribute('name') === 'email') {
      if (!input.value.trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/)) {
        return false;
      }
    } else {
      if (input.value.trim() === '') {
        return false;
      }
    }
    return true;
  }

  function showValidate(input) {
    var thisAlert = input.parentElement;
    thisAlert.classList.add('alert-validate');
    thisAlert.insertAdjacentHTML('beforeend', '<span class="btn-hide-validate">&#xf136;</span>');

    var hideButtons = document.querySelectorAll('.btn-hide-validate');
    hideButtons.forEach(function(button) {
      button.addEventListener('click', function() {
        hideValidate(this);
      });
    });
  }

  function hideValidate(input) {
    var thisAlert = input.parentElement;
    thisAlert.classList.remove('alert-validate');
    var hideButtons = thisAlert.querySelectorAll('.btn-hide-validate');
    hideButtons.forEach(function(button) {
      button.remove();
    });
  }
})();
