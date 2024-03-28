function payCancel() {
  const cancelReason = document.getElementById("cancelReason");
  
  if (cancelReason == null || cancelReason == "") {
    alert("결제 취소 사유를 작성해 주세요.")
    return;
  }
  
  const cancelForm = document.getElementById("cancel_form");

  const cancelAmount = document.createElement('input');

  cancelAmount.setAttribute("type", "hidden");
  cancelAmount.setAttribute("name", "cancelAmount");
  cancelAmount.setAttribute("value", order.payInfo.totalAmount);

  cancelForm.appendChild(cancelAmount);

  alert("결제 취소를 진행합니다.");
  cancelForm.submit();
}