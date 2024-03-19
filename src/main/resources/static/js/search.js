const items = document.querySelectorAll(".search-book-item");

items.forEach((item) => {
  const quantityButtons = item.querySelectorAll(
      ".search-book-quantity-btn");
  const quantityDisplay = item.querySelector(".search-book-quantity");

  quantityButtons.forEach((btn) => {
    btn.addEventListener("click", (e) => {
      let count = parseInt(e.target.textContent + 1);
      changeQuantity(count, quantityDisplay);
    })
  })

  item.addEventListener("click",()=>{
    const bookId = item.querySelector(".search-book-info-title").getAttribute("data-bookId");
    const amount = item.querySelector(".search-book-quantity").innerText;
    setCookie(bookId,amount);
  });

})

function changeQuantity(amount, quantityDisplay) {
  let quantity = parseInt(quantityDisplay.textContent);
  quantity += amount;
  quantity = Math.max(quantity, 1);
  quantityDisplay.textContent = quantity;
}

const setCookie = (bookId,amount) => {
  let cookies = {
    bookId: bookId,
    amount: amount
  };
  let existingCookie = [];
  existingCookie.push(cookies);
  document.cookie = 'info=' + encodeURIComponent(JSON.stringify(existingCookie))
      + '; path=/;';
};