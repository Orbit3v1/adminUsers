function openPopUp(source, name){
    var popUp = window.open(source, name, 'width=840,height=750,resizable=yes,scrollbars=yes');
    return popUp;
}

function clickIn(elementId){
    var element = this.document.getElementById(elementId);
    element.click();
}

function save() {
    refreshOpener();
}

function exit() {
    window.close();
}

function saveExit() {
    save();
    exit();
}

function refreshOpener() {
    var opener = window.opener;
    if (!opener)
        return;
    var refreshButton = opener.document.getElementById("mainForm:refresh");
    if (refreshButton != null) {
        refreshButton.click();
    }
}