const items = document.querySelectorAll(".search-book-item");

items.forEach((item) => {
  const quantityButtons = item.querySelectorAll(
      ".search-book-quantity-btn");
  const quantityDisplay = item.querySelector(".search-book-quantity");
  const buyButton = item.querySelector(".search-book-buy");
  const cartButton = item.querySelector(".search-book-cart");

  quantityButtons.forEach((btn) => {
    btn.addEventListener("click", (e) => {
      let count = parseInt(e.target.textContent + 1);
      changeQuantity(count, quantityDisplay);
    })
  })

  buyButton.addEventListener("click", () => {
    const bookId = item.querySelector(".search-book-info-title").getAttribute(
        "data-bookId");
    const amount = item.querySelector(".search-book-quantity").innerText;
    setCookie(bookId, amount);
  });

  cartButton.addEventListener("click", async (e) => {
    const bookId = item.querySelector(".search-book-info-title").getAttribute(
        "data-bookId");
    const amount = item.querySelector(".search-book-quantity").innerText;

    await fetch("/cart", {
      method: "POST",
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({bookId, amount}),
    })
    .then((response) => response.json)
  })
})

function changeQuantity(amount, quantityDisplay) {
  let quantity = parseInt(quantityDisplay.textContent);
  quantity += amount;
  quantity = Math.max(quantity, 1);
  quantityDisplay.textContent = quantity;
}

const setCookie = (bookId, amount) => {
  let cookies = {bookId, amount};
  let existingCookie = [cookies];
  document.cookie = `info=${encodeURIComponent(
      JSON.stringify(existingCookie))}; path=/;`;
};

const orderList = document.querySelectorAll(".search-book-order-item");
orderList.forEach((orderItem) => {
  orderItem.addEventListener("click", () => {
    const sortStr = orderItem.getAttribute("data-name");
    const currentUrl = new URL(window.location.href);
    const currentParams = currentUrl.searchParams;
    currentUrl.searchParams.set("page", "0");
    currentUrl.searchParams.set("size", currentParams.get("size") || 10);
    currentUrl.searchParams.set("sort", sortStr || "");

    window.location.href = currentUrl.href;
  })
})

const orderSelect = document.querySelector(".view-select");
orderSelect.addEventListener("change", (e) => {
  const currentUrl = new URL(location.href);
  currentUrl.searchParams.set("size", e.target.value);
  console.log(e.target)
  location.href = currentUrl.href;
})

document.addEventListener("DOMContentLoaded", () => {
  const currentUrl = new URL(location.href);
  const value =currentUrl.searchParams.get("size");
  for(let i=0; i<orderSelect.options.length;i++){
    if(orderSelect.options[i].value===value){
      orderSelect.options[i].selected=true;
    }
  }
})