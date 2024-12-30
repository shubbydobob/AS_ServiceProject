document.addEventListener("DOMContentLoaded", () => {
    // 네비게이션 메뉴 전체 선택
    const mainMenu = document.querySelector('.main-menu');

    // 이벤트 위임을 활용해 네비게이션의 <li> 또는 <span>에 마우스 올렸을 때 처리
    mainMenu.addEventListener('mouseover', (event) => {
        const parentMenu = event.target.closest('.menu-item'); // 이벤트 대상이 속한 가장 가까운 .menu-item 찾기
        if (parentMenu) {
            const links = parentMenu.querySelectorAll('a'); // .menu-item 내부 모든 <a> 태그 선택
            links.forEach(link => {
                link.style.color = '#007bff'; // Hover 시 색상 변경
            });
        }
    });

    mainMenu.addEventListener('mouseout', (event) => {
        const parentMenu = event.target.closest('.menu-item');
        if (parentMenu) {
            const links = parentMenu.querySelectorAll('a');
            links.forEach(link => {
                link.style.color = '#333'; // Hover 해제 시 기본 색상
            });
        }
    });
});