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
        roadAddr += roadAddr + extraRoadAddr;
      }
      document.getElementById('zipCode').value = data.zonecode;
      document.getElementById("address").value = roadAddr;

    }
  }).open();
}