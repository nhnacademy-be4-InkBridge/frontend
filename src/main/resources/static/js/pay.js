function a() {
  console.log(payInfo);
  const generateRandomString = () =>
      window.btoa(Math.random()).slice(0, 20);
  // ------  결제위젯 초기화 ------
  let paymentWidget = memberId === "anonymousUser" ? PaymentWidget(clientKey,
      PaymentWidget.ANONYMOUS) : PaymentWidget(clientKey, memberId);

// ------  결제 UI 렌더링 ------
  paymentMethodWidget = paymentWidget.renderPaymentMethods(
      "#payment-method",
      {value: payInfo.price},
      {variantKey: "DEFAULT"}
  );

// ------  이용약관 UI 렌더링 ------
  paymentWidget.renderAgreement("#agreement", {variantKey: "AGREEMENT"});

// ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
  document.getElementById("payment-button").addEventListener("click",
      function () {
        paymentWidget.requestPayment({
          orderId: payInfo.orderId,
          orderName: payInfo.orderName,
          successUrl: window.location.origin + "/pay/success",
          failUrl: window.location.origin + "/pay/fail",
        });
      });
}

document.addEventListener("DOMContentLoaded", a);