// This is the file where we handle the switching of the Themes.
/* Author: - Akhil Gullapalli */

document.addEventListener('DOMContentLoaded', function () {
    // Constants
    const THEME_STORAGE_KEY = 'Theme';
    const RETRO_THEME_VALUE = 'Switch Retro Theme';
    const ULTRA_THEME_VALUE = 'Switch Ultra Theme';
    const button = document.getElementById('button');
    const retroSheet = document.getElementById('retro');
    const ultraSheet = document.getElementById('ultra');

    if (!button || !retroSheet || !ultraSheet) {
        console.error('Required theme elements not found in the DOM.');
        return;
    }

    /**
     * Applies the selected theme to the page.
     * @param {string} theme - The theme to apply.
     */
    function applyTheme(theme) {
        if (theme === RETRO_THEME_VALUE) {
            button.innerText = ULTRA_THEME_VALUE;
            retroSheet.removeAttribute('disabled');
            ultraSheet.setAttribute('disabled', 'true');
        } else { // Default to Ultra theme
            button.innerText = RETRO_THEME_VALUE;
            retroSheet.setAttribute('disabled', 'true');
            ultraSheet.removeAttribute('disabled');
        }
    }

    /**
     * Toggles the theme, saves the choice to localStorage, and applies it.
     */
    function toggleTheme() {
        const currentTheme = localStorage.getItem(THEME_STORAGE_KEY);
        // If current is Ultra or null, switch to Retro. Otherwise, switch to Ultra.
        const newTheme = (currentTheme === ULTRA_THEME_VALUE || !currentTheme) ? RETRO_THEME_VALUE : ULTRA_THEME_VALUE;
        
        localStorage.setItem(THEME_STORAGE_KEY, newTheme);
        applyTheme(newTheme);
    }

    // Initialize theme on page load
    const savedTheme = localStorage.getItem(THEME_STORAGE_KEY) || ULTRA_THEME_VALUE;
    applyTheme(savedTheme);

    // Attach event listener
    button.addEventListener('click', toggleTheme);
});
