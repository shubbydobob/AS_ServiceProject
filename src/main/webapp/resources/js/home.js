document.addEventListener("DOMContentLoaded", () => {
    // Navigation Menu Hover Effect
    const mainMenu = document.querySelector('.main-menu');

    mainMenu.addEventListener('mouseover', (event) => {
        const link = event.target.closest('a');
        if (link) {
            link.style.color = '#007bff'; // Highlight color on hover
        }
    });

    mainMenu.addEventListener('mouseout', (event) => {
        const link = event.target.closest('a');
        if (link) {
            link.style.color = ''; // Revert to default
        }
    });

    // Smooth Scroll for Anchor Links
    const links = document.querySelectorAll('a[href^="#"]');
    links.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const targetId = link.getAttribute('href').substring(1);
            const targetElement = document.getElementById(targetId);

            if (targetElement) {
                targetElement.scrollIntoView({ behavior: 'smooth' });
            }
        });
    });
    
});


