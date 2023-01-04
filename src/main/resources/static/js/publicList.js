function initForm() {
    const INPUTANDSELECT = document.querySelectorAll('.form-control, .form-select');
    INPUTANDSELECT.forEach(i => i.value = null);
}