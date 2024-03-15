function doPay() {
  console.log(payInfo);
  console.log(memberId);
  console.log(clientKey);

  // ------  결제위젯 초기화 ------
  let paymentWidget = memberId == null ? PaymentWidget(clientKey,
      PaymentWidget.ANONYMOUS) : PaymentWidget(clientKey, memberId.toString().padStart(2, '0'));

// ------  결제 UI 렌더링 ------
  paymentMethodWidget = paymentWidget.renderPaymentMethods(
      "#payment-method",
      {value: payInfo.amount},
      {variantKey: "DEFAULT"}
  );

// ------  이용약관 UI 렌더링 ------
  paymentWidget.renderAgreement("#agreement", {variantKey: "AGREEMENT"});

// ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
  document.getElementById("payment-button").addEventListener("click",
      function () {
        paymentWidget.requestPayment({
          orderId: payInfo.orderCode,
          orderName: payInfo.orderName,
          successUrl: window.location.origin + "/pays/success",
          failUrl: window.location.origin + "/pays/fail",
        });
      });
}

document.addEventListener("DOMContentLoaded", doPay);
