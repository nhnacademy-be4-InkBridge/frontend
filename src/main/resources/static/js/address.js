function execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      var roadAddr = data.roadAddress;
      var extraRoadAddr = '';

      if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
        extraRoadAddr += data.bname;
      }
      if (data.buildingName !== '' && data.apartment === 'Y') {
        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
      }
      if (extraRoadAddr !== '') {
        extraRoadAddr = ' (' + extraRoadAddr + ')';
        roadAddr = roadAddr + extraRoadAddr;

        console.log(roadAddr);
      }
      document.getElementById('zipCode').value = data.zonecode;
      document.getElementById("address").value = roadAddr;

    }
  }).open();
}
function fillAddressFields(selectedDropdown) {
  var selectedOption = selectedDropdown.options[selectedDropdown.selectedIndex];

  // '새 주소 입력' 선택시 입력 필드를 비웁니다.
  if(selectedDropdown.value === "") {
    document.getElementById('receiverName').value = "";
    document.getElementById('receiverNumber').value = "";
    document.getElementById('zipCode').value = "";
    document.getElementById('address').value = "";
    document.getElementById('detailAddress').value = "";
  } else {
    // 선택된 주소의 상세 정보로 입력 필드를 채웁니다.
    document.getElementById('receiverName').value = selectedOption.getAttribute('data-receiver-name');
    document.getElementById('zipCode').value = selectedOption.getAttribute('data-zip-code');
    document.getElementById('address').value = selectedOption.getAttribute('data-address');
    document.getElementById('detailAddress').value = selectedOption.getAttribute('data-detail-address');
    document.getElementById('receiverNumber').value = selectedOption.getAttribute('data-receiver-number');
  }
}