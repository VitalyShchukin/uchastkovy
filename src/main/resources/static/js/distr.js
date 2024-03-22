const tooltip = document.querySelector('.tooltip');
const districts = document.querySelectorAll('.distr');
const popupBg = document.querySelector('.info__bg');
const popup = document.querySelector('.info');

districts.forEach(distr => {

    distr.addEventListener('click', function(){
        popup.querySelector('.info__photo').setAttribute('src', this.dataset.photo);
        popup.querySelector('.info__title').innerText = this.dataset.title;
        popup.querySelector('.info__text').innerText = this.dataset.description;
        popupBg.classList.add('active');
    });
    


    distr.addEventListener('mousemove', function(e){
        tooltip.innerText = this.dataset.title;
        tooltip.style.top = (e.y + 20) + 'px';
        tooltip.style.left = (e.x + 20) + 'px'
    });

    distr.addEventListener('mouseenter', function(){
        tooltip.style.display = 'block';
    });

    distr.addEventListener('mouseleave', function(){
        tooltip.style.display = 'none';
    })
});

document.addEventListener('click', (e) => {
    if(e.target === popupBg){
        popupBg.classList.remove('active');
    }
});
