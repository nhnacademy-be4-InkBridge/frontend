const booksContainers = document.querySelectorAll(".books-container");

booksContainers.forEach((booksContainer)=>{
  const bookInfoContainer=booksContainer.querySelector(".book-info-container");

  let index = 0;

  const prevButton = booksContainer.querySelector(".prev-btn");
  prevButton.addEventListener("click",()=>{
      if(index===0) return;
        index-=1;
        bookInfoContainer.style.transform = `translate3d(-${270*index}px,0,0)`;
  })

  const nextButton = booksContainer.querySelector(".next-btn");
  nextButton.addEventListener("click",()=>{
    if(index===4) return;
    index+=1;
    bookInfoContainer.style.transform = `translate3d(-${270*index}px,0,0)`;
  })
})

