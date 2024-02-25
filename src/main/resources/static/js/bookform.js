const existingContent = document.getElementById(
    'existing-content').innerHTML;

const editor = new toastui.Editor({
  el: document.querySelector('#editor'),
  height: '500px',
  initialEditType: 'markdown',
  initialValue: existingContent || '',
  previewStyle: 'vertical',
  placeholder: '',
  hooks: {
    // async addImageBlobHook(blob, callback) {
    //   try {
    //     // const filename = blob.name;
    //     // console.log('서버에 저장된 파일명 : ', blob.name);
    //     //
    //     // const url = URL.createObjectURL(blob);
    //     // // 4. addImageBlobHook의 callback 함수를 통해, 디스크에 저장된 이미지를 에디터에 렌더링
    //     // // const imageUrl = `/tui-editor/image-print?filename=${filename}`;
    //     // //   const imageUrl = `usr/upload?filename=${filename}`;
    //     // callback(url, 'image alt attribute');
    //
    //   } catch (error) {
    //     console.error('업로드 실패 : ', error);
    //   }
    // }
  }
});
// 저장할 때 데이터를 컨트롤러로
document.getElementById('bookForm').addEventListener('submit',
    function (event) {
      let description = editor.getMarkdown();
      document.getElementById('descriptionHidden').value = description; // 숨겨진 입력 필드에 설정
    });

mobiscroll.setOptions({
  locale: mobiscroll.localeEn,                                         // Specify language like: locale: mobiscroll.localePl or omit setting to use default
  theme: 'ios',                                                        // Specify theme like: theme: 'ios' or omit setting to use default
  themeVariant: 'light'                                                // More info about themeVariant: https://mobiscroll.com/docs/javascript/select/api#opt-themeVariant
});

mobiscroll.select('#category-multiple-select', {
  inputElement: document.getElementById('category-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
  // onCancel: handleSelectedCategories,
});
document.getElementById('category-multiple-select').addEventListener('change',
    function () {
      var selectedOptions = this.selectedOptions;
      for (var i = 0; i < selectedOptions.length; i++) {
        var selectedOption = selectedOptions[i];
        var optgroup = selectedOption.parentElement;
        var hiddenOption = optgroup.querySelector('.parent');
        if (hiddenOption && !hiddenOption.selected) { // Check if hiddenOption is not already selected
          hiddenOption.selected = true;
        }
      }
    });
mobiscroll.select('#author-multiple-select', {
  inputElement: document.getElementById('author-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});
mobiscroll.select('#publisher-multiple-select', {
  inputElement: document.getElementById('publisher-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});
mobiscroll.select('#tag-multiple-select', {
  inputElement: document.getElementById('tag-multiple-select-input'),  // More info about inputElement: https://mobiscroll.com/docs/javascript/select/api#opt-inputElement
});

document.getElementById('category-multiple-select').addEventListener('change',
function handleSelectedCategories() {
  var selectedOptions = this.selectedOptions;
  var selectedCount = 0;
  for (var i = 0; i < selectedOptions.length; i++) {
    if (selectedOptions[i].parentElement.tagName === 'OPTGROUP') {
      selectedCount++;
      console.log('selected: ' + selectedCount);
    }
  }
  if (selectedCount > 10) {
    alert('You can only select up to 10 options.');
    this.options[this.selectedIndex].selected = false;
    console.log('selected2: ' + selectedCount);
  }
});

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

document.getElementById("price").addEventListener("input", function () {
  calculateDiscount();
});

document.getElementById("discountRatio").addEventListener("input", function () {
  calculateSalePrice();
});

function calculateDiscount() {
  var regularPrice = parseFloat(document.getElementById("regularPrice").value);
  var price = parseFloat(document.getElementById("price").value);
  if (!isNaN(regularPrice) && !isNaN(price)) {
    var discountRatio = ((regularPrice - price) / regularPrice) * 100;
    document.getElementById("discountRatio").value = discountRatio.toFixed(1);
  }
}

function calculateSalePrice() {
  var regularPrice = parseFloat(document.getElementById("regularPrice").value);
  var discountRatio = parseFloat(
      document.getElementById("discountRatio").value);
  if (!isNaN(regularPrice) && !isNaN(discountRatio)) {
    var price = regularPrice - (regularPrice * (discountRatio / 100));
    document.getElementById("price").value = price.toFixed(0);
  }
}