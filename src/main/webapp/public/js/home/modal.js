let closeBtns = document.querySelectorAll(".js-close-modal")
for (let btn of closeBtns) {
    btn.addEventListener('click',closeModal)
}
function openModal(){
    let modal = document.querySelector(".modal");
    console.log(modal.classList)
    modal.classList.add('modal__open')
}

function closeModal() {
    let modal = document.querySelector(".modal");
    modal.classList.remove('modal__open')
}