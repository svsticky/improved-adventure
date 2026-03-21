window.onload = function() {
    var footer = document.createElement('footer');
    footer.id = 'custom-sticky-footer'; 
    footer.innerHTML = `
<div class="footer-container">
    <div class="footer-section section-links">
        <a target="_blank" href="https://svsticky.nl">
            <img class="footer-logo" src="https://public.svsticky.nl/logos/hoofd_outline_wit.svg">
        </a>
        <div class="link-group">
            <a target="_blank" href="https://svsticky.nl/vereniging/over-ons">Over ons</a>
            <a target="_blank" href="https://svsticky.nl/vereniging/contact">Contact</a>
        </div>
    </div>
    
    <div class="footer-section section-socials">
        <a target="_blank" href="https://www.facebook.com/stickyutrecht"><i class="fab fa-facebook-f"></i></a>
        <a target="_blank" href="https://www.linkedin.com/company/1125476"><i class="fab fa-linkedin-in"></i></a>
        <a target="_blank" href="https://www.github.com/svsticky"><i class="fab fa-github"></i></a>
    </div>

    <div class="footer-section section-privacy">
        <a target="_blank" href="https://public.svsticky.nl/privacystatement.pdf">Privacy</a>
    </div>
</div>
    `;
    document.body.appendChild(footer);
};